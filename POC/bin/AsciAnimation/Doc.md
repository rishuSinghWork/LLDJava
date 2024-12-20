# **Rotating Torus Animation Documentation**

---

## **1. Overview**

This program generates a 3D rotating torus (donut shape) animation using ASCII characters. The animation is rendered in a graphical window created with Java Swing and is based on mathematical concepts like **3D transformations**, **lighting and shading**, and **perspective projection**.

---

## **2. Mathematical Concepts**

### **2.1 Torus Parametric Equation**
A torus is defined by two parameters:
- \( i \): Angle around the small circular cross-section (tube radius).
- \( j \): Angle around the central axis (donut radius).

The parametric equations for a torus are:
\[
x = (R + r \cdot \cos(i)) \cdot \cos(j)
\]
\[
y = (R + r \cdot \cos(i)) \cdot \sin(j)
\]
\[
z = r \cdot \sin(i)
\]

Where:
- \( R \): Distance from the center of the torus to the center of the tube.
- \( r \): Radius of the tube.
- \( i, j \): Angles in radians.

This creates a set of 3D points that define the shape of the torus in 3D space.

---

### **2.2 3D Rotation**
To make the torus rotate, we apply rotation transformations to its points in 3D space. These transformations are performed using **rotation matrices** for the \( x \)-axis and \( y \)-axis.

1. **Rotation about the \( x \)-axis**:
\[
x' = x
\]
\[
y' = y \cdot \cos(A) - z \cdot \sin(A)
\]
\[
z' = y \cdot \sin(A) + z \cdot \cos(A)
\]

2. **Rotation about the \( y \)-axis**:
\[
x'' = x' \cdot \cos(B) + z' \cdot \sin(B)
\]
\[
y'' = y'
\]
\[
z'' = -x' \cdot \sin(B) + z' \cdot \cos(B)
\]

Where:
- \( A \): Rotation angle around the \( x \)-axis.
- \( B \): Rotation angle around the \( y \)-axis.

The result is a rotated set of 3D points that defines the updated torus orientation.

---

### **2.3 Perspective Projection**
The 3D points of the torus are projected onto a 2D plane for rendering on the screen. This involves a **perspective projection**, which creates the illusion of depth by scaling points based on their distance from the viewer.

\[
D = \frac{1}{z'' + k}
\]
\[
x_{\text{screen}} = x'' \cdot D
\]
\[
y_{\text{screen}} = y'' \cdot D
\]

Where:
- \( D \): Depth factor, which makes closer points appear larger.
- \( k \): A constant that shifts the torus away from the viewer to avoid division by zero.

This projection maps the 3D coordinates \((x'', y'', z'')\) to 2D screen coordinates \((x_{\text{screen}}, y_{\text{screen}})\).

---

### **2.4 Lighting and Shading**
To make the torus visually appealing, shading is applied based on the surface normal and the direction of a virtual light source. The brightness of each point is calculated as:
\[
N = 8 \cdot ((f \cdot e - c \cdot d \cdot gA) \cdot m - c \cdot d \cdot e - f \cdot gA - l \cdot d \cdot n)
\]

#### Explanation:
- \( f, e, c, d, gA, l, m, n \): Intermediate trigonometric terms derived from the torus parametric equations and rotations.
- \( N \): Normalized brightness value for a point on the torus.

This brightness value is mapped to ASCII characters, where brighter points correspond to characters like `@` and darker points to `.`:
```java
new char[]{'.', ',', '-', '~', ':', ';', '=', '!', '*', '#', '$', '@'};
```

---

## **3. Torus Generation Logic**

### **3.1 Steps to Generate the Torus**
1. **Initialize Buffers**:
   - `z[]`: A depth buffer to track the depth of each point on the grid.
   - `b[]`: A character buffer to store the ASCII representation of the torus.

2. **Iterate Over Torus Angles**:
   - Loop through values of \( i \) (tube angle) and \( j \) (donut angle).
   - For each combination of \( i \) and \( j \), calculate the 3D coordinates of the corresponding point on the torus.

3. **Apply Rotations**:
   - Rotate the calculated 3D point using the \( x \)-axis and \( y \)-axis rotation matrices.

4. **Project to 2D**:
   - Use the perspective projection formula to map the rotated 3D point to a 2D screen position.

5. **Calculate Brightness**:
   - Determine the shading for the point based on the surface normal and light direction.
   - Map the brightness to an ASCII character.

6. **Depth Buffering**:
   - Compare the depth of the current point with the value in the `z[]` buffer.
   - Update the `z[]` buffer and `b[]` character buffer if the current point is closer to the viewer.

### **3.2 Rendering**
After generating the torus, the `b[]` buffer contains the ASCII characters for each point. The characters are drawn onto the screen in a grid layout.

1. **Clear the Screen**:
   - Fill the panel with a black background.

2. **Draw Characters**:
   - For each point in the grid, use its brightness value from `b[]` to draw the corresponding ASCII character.

The rendering process repeats every 30 milliseconds, creating the animation effect.

---

## **4. Summary of the Math and Logic**

1. **Parametric Representation**:
   - The torus is represented mathematically using two angles \( i \) and \( j \).

2. **3D Transformations**:
   - The torus points are rotated in 3D space using trigonometric transformations.

3. **Perspective Projection**:
   - The 3D points are projected onto a 2D screen with perspective depth scaling.

4. **Lighting and Shading**:
   - Brightness is calculated based on surface normals and mapped to ASCII characters.

5. **Rendering Pipeline**:
   - Depth buffering ensures correct visibility, and characters are rendered on a grid to simulate the rotating torus.

---

