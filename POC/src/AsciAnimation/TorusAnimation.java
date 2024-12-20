package AsciAnimation;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class TorusAnimation extends JPanel {
    private double A = 0, B = 0; // Rotation angles
    private final int CHAR_WIDTH = 10; // Width of a character in pixels
    private final int CHAR_HEIGHT = 10; // Height of a character in pixels
    private final int ROWS = 80; // Number of rows for the torus
    private final int COLS = 80; // Number of columns for the torus
    private final int TOTAL_PIXELS = ROWS * COLS; // Total number of torus pixels
    private final double[] z = new double[TOTAL_PIXELS];
    private final char[] b = new char[TOTAL_PIXELS];

    public TorusAnimation() {
        // Timer to update the animation at regular intervals
        Timer timer = new Timer(30, e -> {
            A += 0.04;
            B += 0.02;
            repaint(); // Repaint the panel with updated torus
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Fill the screen with black
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Render the torus
        renderTorus(g);
    }

    private void renderTorus(Graphics g) {
        Arrays.fill(b, 0, TOTAL_PIXELS, ' ');
        Arrays.fill(z, 0, TOTAL_PIXELS, 0);

        for (double j = 0; j < 6.28; j += 0.07) {
            for (double i = 0; i < 6.28; i += 0.02) {
                double c = Math.sin(i),
                        d = Math.cos(j),
                        e = Math.sin(A),
                        f = Math.sin(j),
                        gA = Math.cos(A),
                        h = d + 2,
                        D = 1 / (c * h * e + f * gA + 5),
                        l = Math.cos(i),
                        m = Math.cos(B),
                        n = Math.sin(B),
                        t = c * h * gA - f * e;

                int x = (int) (COLS / 2 + COLS / 2 * D * (l * h * m - t * n));
                int y = (int) (ROWS / 2 - ROWS / 2 * D * (l * h * n + t * m));

                if (x >= 0 && x < COLS && y >= 0 && y < ROWS) {
                    int o = x + COLS * y;
                    if (o >= 0 && o < TOTAL_PIXELS && D > z[o]) {
                        z[o] = D;
                        b[o] = new char[]{'.', ',', '-', '~', ':', ';', '=', '!', '*', '#', '$', '@'}[Math.max((int) (8 * ((f * e - c * d * gA) * m - c * d * e - f * gA - l * d * n)), 0)];
                    }
                }
            }
        }

        // Draw the torus
        g.setFont(new Font("Monospaced", Font.PLAIN, 12));
        g.setColor(Color.GREEN);
        for (int k = 0; k < TOTAL_PIXELS; k++) {
            if (b[k] != ' ') {
                int x = (k % COLS) * CHAR_WIDTH;
                int y = (k / COLS) * CHAR_HEIGHT;
                g.drawString(String.valueOf(b[k]), x, y);
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Rotating Torus Animation");
        TorusAnimation torus = new TorusAnimation();

        frame.add(torus);
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
