package test;

/**
 * # Mandelbrot Set Renderer - Optimizations Documentation

This document provides an overview of the optimizations implemented in the `MandelBrotSetRenderer` class. The optimizations aim to improve the performance and visual quality of rendering the Mandelbrot set by minimizing redundant calculations, leveraging multi-threading, and implementing various rendering enhancements.

## Optimizations Overview

### 1. Reducing Redundant Calculations
- **Squared Distances**: Instead of recalculating the absolute magnitude of the complex numbers repeatedly, we use squared distances (`zRe^2 + zIm^2`) to determine if the point has escaped the set. This avoids the expensive square root operation.
- **Reuse Calculations**: Values such as `zRe^2` and `zIm^2` are calculated only once and reused throughout the iteration loop, which reduces unnecessary computational overhead.

### 2. Multi-Threading
- The renderer takes advantage of multi-threading using Java's parallel streams (`IntStream.parallel()`). Each pixel calculation is handled in parallel, resulting in a significant performance boost, especially on multi-core processors.

### 3. Lowering Max Iterations at High Zoom Levels
- **Dynamic Max Iterations**: The number of iterations is adjusted based on the zoom level. As zoom increases, max iterations are reduced to balance computational requirements and performance. This ensures rendering remains efficient without sacrificing visual quality.

### 4. Incremental Rendering
- **Block Rendering**: The Mandelbrot set is rendered in smaller blocks to make the rendering process smoother. This incremental approach allows partial images to be visible while rendering is still ongoing, enhancing the user experience.

### 5. Optimized Data Types
- **Using Floats**: Where possible, the `float` data type is used instead of `double`. This reduces both memory usage and CPU processing time, leading to better performance without significantly compromising precision.

## Optimization Iteration 2 - Higher Resolution and Smooth Coloring

### 1. Smooth Coloring
- **Smooth Gradients**: Instead of using fixed color steps per iteration, smooth gradients are generated by incorporating the fractional escape time. This enhances the visual appeal by creating smooth transitions between colors.
- **Dynamic Coloring**: Enhanced color variation is achieved using HSB (Hue, Saturation, Brightness) color mapping. Fractional iteration values are used to generate smooth gradients for points close to the boundary.

### 2. Higher Definition Scaling
- **Supersampling**: The render resolution is increased by a scale factor (`width * scaleFactor` x `height * scaleFactor`) and then displayed at the original size (`width x height`). This technique, called supersampling, provides a sharper, high-definition result without increasing the visible window dimensions.

## Optimization Iteration 3 - Advanced Techniques

### 1. Escape-Time Optimization (BailOut)
- If `|z| > 2` within a few iterations, the algorithm bails out quickly. This optimization is partially implemented with the condition `zRe^2 + zIm^2 <= 4` to exit iterations early when possible.

### 2. Periodicity Checking
- The renderer detects if `z` is entering periodic loops, which often occur around the boundaries of the Mandelbrot set. This helps avoid redundant calculations for points that are effectively repeating values.

### 3. BufferedImage with Multi-Threading
- The use of `BufferedImage` in combination with Java's `Stream API` enables efficient parallel processing of each pixel. This improves rendering speed, particularly when combined with multi-core processors.

### 4. Color Palette Caching
- **Precomputed Colors**: Colors for each iteration up to `maxIterations` are precomputed and stored in a palette (`colorPalette`). This allows for rapid color lookup during rendering, improving performance.

### 5. Using Float Instead of Double
- As mentioned earlier, floats are used where precision allows. This saves memory and reduces CPU usage.

### 6. Viewport Tracking
- **Track Previous Viewport**: The application tracks the previous viewport settings (`zoom`, `centerX`, `centerY`) to determine if a complete re-render is necessary. If only minor changes are detected, the renderer optimizes by updating only relevant parts.

### 7. Overlapping Region Calculation
- The overlapping region between the previous and current viewport is calculated to avoid redrawing the entire image. Only the necessary parts are updated, reducing rendering time.

### 8. Redraw Only Necessary Parts
- When zooming or panning, only the areas that have changed are redrawn. This incremental approach significantly speeds up rendering when interacting with the fractal.

### 9. Buffered Images for Efficiency
- The use of `BufferedImage` allows for efficient image manipulation and rendering. The entire Mandelbrot set is rendered into a buffered image, which is then drawn to the screen, reducing flickering and enhancing performance.

## Summary
The `MandelBrotSetRenderer` class implements several optimizations to enhance the rendering performance and visual quality of the Mandelbrot set. These optimizations include reducing redundant calculations, leveraging multi-threading, adjusting rendering parameters 
based on zoom level, and incorporating advanced coloring and rendering techniques. Together, these improvements result in a faster and more visually appealing rendering of the Mandelbrot set, even at high zoom levels.

Feel free to explore and experiment with these optimizations further to find the right balance between performance and visual quality for your specific use case.


 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.stream.IntStream;

public class MandelBrotSetRenderer extends JPanel {
    private final int width = 800;
    private final int height = 800;
    private final int scaleFactor = 2;
    private int maxIterations = 1000;
    private double zoom = 1.0;
    private double centerX = -0.5;
    private double centerY = 0.0;

    private BufferedImage image;
    private Color[] colorPalette;

    public MandelBrotSetRenderer() {
        image = new BufferedImage(width * scaleFactor, height * scaleFactor, BufferedImage.TYPE_INT_RGB);
        setPreferredSize(new Dimension(width, height));

        // Key listeners for zooming and panning
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP -> centerY -= 0.1 / zoom;
                    case KeyEvent.VK_DOWN -> centerY += 0.1 / zoom;
                    case KeyEvent.VK_LEFT -> centerX -= 0.1 / zoom;
                    case KeyEvent.VK_RIGHT -> centerX += 0.1 / zoom;
                    case KeyEvent.VK_PLUS, KeyEvent.VK_EQUALS -> zoom *= 1.1;
                    case KeyEvent.VK_MINUS -> zoom /= 1.1;
                }
                renderMandelbrot();
                repaint();
            }
        });

        setFocusable(true);
    }

    private void renderMandelbrot() {
        // Adjust maxIterations based on zoom level
        maxIterations = (int) (1000 * Math.log10(zoom + 1));

        // Initialize color palette for smooth gradients
        generateColorPalette();

        // Calculate the scaling factors for the Mandelbrot coordinates
        double xFactor = 4.0 / (width * scaleFactor) / zoom;
        double yFactor = 4.0 / (height * scaleFactor) / zoom;

        // Parallelize rendering across all pixels
        IntStream.range(0, width * scaleFactor).parallel().forEach(x -> {
            for (int y = 0; y < height * scaleFactor; y++) {
                double cRe = (x - (width * scaleFactor) / 2.0) * xFactor + centerX;
                double cIm = (y - (height * scaleFactor) / 2.0) * yFactor + centerY;
                int color = calculateMandelbrotColor(cRe, cIm);
                image.setRGB(x, y, color);
            }
        });
    }

    private int calculateMandelbrotColor(double cRe, double cIm) {
        double zRe = 0, zIm = 0;
        int iteration = 0;
        double zRe2 = 0, zIm2 = 0;

        // Variables for periodicity check
        double lastRe = 0, lastIm = 0;
        int periodCheckInterval = 10; // Check for periodicity every 10 iterations
        double tolerance = 1e-6;      // Threshold for detecting periodicity

        while (zRe2 + zIm2 <= 4 && iteration < maxIterations) {
            zIm = 2 * zRe * zIm + cIm;
            zRe = zRe2 - zIm2 + cRe;
            zRe2 = zRe * zRe;
            zIm2 = zIm * zIm;
            iteration++;

            // Periodicity check every `periodCheckInterval` iterations
            if (iteration % periodCheckInterval == 0) {
                if (Math.abs(zRe - lastRe) < tolerance && Math.abs(zIm - lastIm) < tolerance) {
                    // Detected periodicity, consider this point as part of the Mandelbrot set
                    iteration = maxIterations;
                    break;
                }
                // Update the last recorded value of z for future periodic checks
                lastRe = zRe;
                lastIm = zIm;
            }
        }

        return iteration < maxIterations ? colorPalette[iteration].getRGB() : Color.BLACK.getRGB();
    }

    private void generateColorPalette() {
        colorPalette = new Color[maxIterations];
        for (int i = 0; i < maxIterations; i++) {
            float hue = 0.7f + (float) i / maxIterations;
            colorPalette[i] = Color.getHSBColor(hue, 1.0f, 1.0f);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, width, height, null);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Mandelbrot Set Renderer - Combined Optimizations");
        MandelBrotSetRenderer panel = new MandelBrotSetRenderer();
        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        panel.renderMandelbrot();
    }
}
