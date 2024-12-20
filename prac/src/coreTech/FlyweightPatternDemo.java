package coreTech;

import java.util.HashMap;
import java.util.Map;

// Flyweight Pattern Implementation
class TreeType {
    private String name;
    private String color;
    private String texture;

    public TreeType(String name, String color, String texture) {
        this.name = name;
        this.color = color;
        this.texture = texture;
    }

    public void draw(int x, int y) {
        // Removed the print statement to reduce IO impact on performance measurements
    }
}

class TreeFactory {
    private static Map<String, TreeType> treeTypes = new HashMap<>();

    public static TreeType getTreeType(String name, String color, String texture) {
        String key = name + "-" + color + "-" + texture;
        TreeType result = treeTypes.get(key);
        if (result == null) {
            result = new TreeType(name, color, texture);
            treeTypes.put(key, result);
        }
        return result;
    }
}

class Tree {
    private int x;
    private int y;
    private TreeType type;

    public Tree(int x, int y, TreeType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void draw() {
        type.draw(x, y);
    }
}

public class FlyweightPatternDemo {
    public static void main(String[] args) throws InterruptedException {
        long startTime, endTime;
        int treeCount = 1000000;

        // Without Flyweight Pattern
        System.out.println("Without Flyweight Pattern:");
        System.gc();
        Thread.sleep(500); // Allow time for GC to complete
        long startMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        startTime = System.currentTimeMillis();
        for (int i = 0; i < treeCount; i++) {
            TreeType type = new TreeType("Oak", "Green", "Rough");
            Tree tree = new Tree(i % 1000, i / 1000, type);
            tree.draw();
        }
        endTime = System.currentTimeMillis();
        long endMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("Execution Time without Flyweight: " + (endTime - startTime) + " ms");
        System.out.println("Memory Usage without Flyweight: " + ((endMemory - startMemory) / (1024.0 * 1024.0)) + " MB\n");

        // With Flyweight Pattern
        System.out.println("With Flyweight Pattern:");
        System.gc();
        Thread.sleep(500); // Allow time for GC to complete
        startMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        startTime = System.currentTimeMillis();
        for (int i = 0; i < treeCount; i++) {
            TreeType type = TreeFactory.getTreeType("Oak", "Green", "Rough");
            Tree tree = new Tree(i % 1000, i / 1000, type);
            tree.draw();
        }
        endTime = System.currentTimeMillis();
        endMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("Execution Time with Flyweight: " + (endTime - startTime) + " ms");
        System.out.println("Memory Usage with Flyweight: " + ((endMemory - startMemory) / (1024.0 * 1024.0)) + " MB\n");
    }
}
