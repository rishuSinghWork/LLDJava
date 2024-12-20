package test;

import javax.swing.*;
import java.awt.*;

public class TestFrame extends JPanel {
    public TestFrame() {
        setPreferredSize(new Dimension(400, 400));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Test Frame");
        frame.add(new TestFrame());
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

