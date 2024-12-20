//package AsciAnimation;
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.List;
//
//public class ASCIIAnimationPOC {
//    private static JTextArea textArea;
//    private static JTextField inputField;
//    private static Timer animationTimer;
//
//    public static void main(String[] args) {
//        // Set up the frame
//        JFrame frame = new JFrame("ASCII Animation Proof of Concept");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(800, 600);
//
//        // Set up the text area for output
//        textArea = new JTextArea();
//        textArea.setBackground(Color.BLACK);
//        textArea.setForeground(Color.WHITE);
//        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
//        textArea.setEditable(false);
//        JScrollPane scrollPane = new JScrollPane(textArea);
//
//        // Set up the input field
//        inputField = new JTextField();
//        inputField.setBackground(Color.DARK_GRAY);
//        inputField.setForeground(Color.WHITE);
//        inputField.setCaretColor(Color.WHITE);
//        inputField.setFont(new Font("Monospaced", Font.PLAIN, 14));
//
//        // Set up the action for the input field
//        inputField.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String input = inputField.getText();
//                inputField.setText("");
//                processCommand(input);
//            }
//        });
//
//        // Add components to frame
//        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
//        frame.getContentPane().add(inputField, BorderLayout.SOUTH);
//
//        // Make the frame visible
//        frame.setVisible(true);
//
//        // Welcome message
//        textArea.append("Welcome to the ASCII Animation POC!\n");
//        textArea.append("Type 'start' to start the ASCII restaurant animation.\n");
//        textArea.append("Type 'exit' to stop the animation and close the application.\n");
//    }
//
//    private static void processCommand(String command) {
//        if (command.equalsIgnoreCase("start")) {
//            startAsciiAnimation();
//        } else if (command.equalsIgnoreCase("exit")) {
//            stopAsciiAnimation();
//            System.exit(0);
//        } else {
//            textArea.append("Unknown command: " + command + "\n");
//        }
//    }
//
//    private static void startAsciiAnimation() {
//        // Define the frames of the ASCII animation (detailed representation of a restaurant)
//        List<String> frames = List.of(
//                "  _______________________________\n" +
//                " /                               \\\n" +
//                "/   WELCOME TO THE RESTAURANT     \\\n" +
//                "|   _________________________     |\n" +
//                "|  |                         |    |\n" +
//                "|  |    COOKING DELICIOUS    |    |\n" +
//                "|  |        MEALS...         |    |\n" +
//                "|  |_________________________|    |\n" +
//                "|                               |\n" +
//                "|     ________   _________      |\n" +
//                "|    |        | |         |     |\n" +
//                "|    | FOOD   | |  SERVING |     |\n" +
//                "|    |________| |_________|     |\n" +
//                "|______________________________|\n",
//
//                "  _______________________________\n" +
//                " /                               \\\n" +
//                "/   ENJOY YOUR MEAL AT OUR        \\\n" +
//                "|   _________________________     |\n" +
//                "|  |                         |    |\n" +
//                "|  |      SERVING HOT AND     |    |\n" +
//                "|  |     FRESH FOOD TODAY!    |    |\n" +
//                "|  |_________________________|    |\n" +
//                "|                               |\n" +
//                "|     ________   _________      |\n" +
//                "|    |        | |         |     |\n" +
//                "|    | OPEN   | |  TABLES  |     |\n" +
//                "|    |________| |_________|     |\n" +
//                "|______________________________|\n",
//
//                "  _______________________________\n" +
//                " /                               \\\n" +
//                "/   RESTAURANT IS NOW OPEN       \\\n" +
//                "|   _________________________     |\n" +
//                "|  |                         |    |\n" +
//                "|  |      TAKING ORDERS...    |    |\n" +
//                "|  |       CALL WAITER!       |    |\n" +
//                "|  |_________________________|    |\n" +
//                "|                               |\n" +
//                "|     ________   _________      |\n" +
//                "|    |        | |         |     |\n" +
//                "|    | CHEF   | |  WAITER  |     |\n" +
//                "|    |________| |_________|     |\n" +
//                "|______________________________|\n"
//        );
//
//        // Timer to handle frame switching
//        animationTimer = new Timer(500, new ActionListener() {
//            int frameIndex = 0;
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // Clear the text area and show the next frame
//                textArea.setText(frames.get(frameIndex));
//                frameIndex = (frameIndex + 1) % frames.size();
//            }
//        });
//
//        // Start the animation timer
//        animationTimer.setRepeats(true);
//        animationTimer.start();
//    }
//
//    private static void stopAsciiAnimation() {
//        if (animationTimer != null && animationTimer.isRunning()) {
//            animationTimer.stop();
//        }
//    }
//}
