import java.awt.*;
import java.util.*;
import javax.swing.*;

public class queez {
    public static void main(String[] args) {
        // Create the main window (JFrame)
        JFrame frame = new JFrame("Banner");
        frame.setSize(400, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the window

        // Create a panel to draw and animate the banner text
        JPanel panel = new JPanel() {
            ArrayList<Character> chars = new ArrayList<>(); // List to hold characters

            {
                String text = "Your name here! ";

                // Use a classic for loop to fill the character list
                for (int i = 0; i < text.length(); i++) {
                    chars.add(text.charAt(i));
                }

                setBackground(Color.CYAN); // Set background color

                // Start a thread to animate the scrolling text
                new Thread(() -> {
                    while (true) {
                        // Move last character to the front (forward scroll)
                        char last = chars.remove(chars.size() - 1);
                        chars.add(0, last);

                        // Refresh the panel to show updated text
                        repaint();

                        // Pause for a short time to control speed
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start(); // Start the animation thread
            }

            // Draw the characters on the panel
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.RED); // Text color
                g.setFont(new Font("Arial", Font.BOLD, 20)); // Text font

                // Build the full string from character list
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < chars.size(); i++) {
                    sb.append(chars.get(i));
                }

                // Draw the string at a fixed position
                g.drawString(sb.toString(), 50, 60);
            }
        };

        // Add panel to frame and show the window
        frame.add(panel);
        frame.setVisible(true);
    }
}
