//package AsciAnimation;
//
//import java.util.Arrays;
//
//public class MainOne {
//    public static void main(String[] args) {
//        int k;
//        double A = 0, B = 0, i, j;
//        double[] z = new double[1760];
//        char[] b = new char[1760];
//
//        // ANSI escape code to clear the screen and hide cursor
//        System.out.print("\u001b[2J\u001b[?25l");
//
//        // Initial print of torus (one-time setup)
//        renderFrame(b, z, A, B);
//
//        // Rotate the torus in place
//        for (; ; ) {
//            A += 0.04;
//            B += 0.02;
//
//            // Update specific parts of the torus to simulate rotation
//            renderFrame(b, z, A, B);
//
//            // Small delay to control animation speed
//            try {
//                Thread.sleep(30);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public static void renderFrame(char[] b, double[] z, double A, double B) {
//        Arrays.fill(b, 0, 1760, ' ');
//        Arrays.fill(z, 0, 1760, 0);
//
//        // Calculate torus points
//        for (double j = 0; 6.28 > j; j += 0.07) {
//            for (double i = 0; 6.28 > i; i += 0.02) {
//                double c = Math.sin(i),
//                        d = Math.cos(j),
//                        e = Math.sin(A),
//                        f = Math.sin(j),
//                        g = Math.cos(A),
//                        h = d + 2,
//                        D = 1 / (c * h * e + f * g + 5),
//                        l = Math.cos(i),
//                        m = Math.cos(B),
//                        n = Math.sin(B),
//                        t = c * h * g - f * e;
//
//                int x = (int) (40 + 30 * D * (l * h * m - t * n)),
//                        y = (int) (12 + 15 * D * (l * h * n + t * m)),
//                        o = x + 80 * y,
//                        N = (int) (8 * ((f * e - c * d * g) * m - c * d * e - f * g - l * d * n));
//
//                if (22 > y && y > 0 && x > 0 && 80 > x && D > z[o]) {
//                    z[o] = D;
//                    b[o] = new char[]{'.', ',', '-', '~', ':', ';', '=', '!', '*', '#', '$', '@'}[Math.max(N, 0)];
//                }
//            }
//        }
//
//        // Move cursor to top-left position
//        System.out.print("\u001b[H");
//
//        // Print the updated torus
//        for (int k = 0; k < 1760; k++) {
//            System.out.print(k % 80 > 0 ? b[k] : "\n");
//        }
//    }
//}
