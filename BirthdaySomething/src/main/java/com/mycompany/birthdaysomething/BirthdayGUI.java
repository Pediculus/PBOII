package com.mycompany.birthdaysomething;

import javax.swing.*;
import java.awt.*;

// This class represents the graphical user interface (GUI) for the Birthday application.
class BirthdayGUI extends JFrame {
    // Declare GUI components
    public JTextField nameField = new JTextField(15);  // Input field for the person's name
    public JTextField dateField = new JTextField(10);  // Input field for the birth date (format: yyyy-mm-dd)
    public JButton calculateButton = new JButton("Hitung");  // Button to trigger the age and birthday calculation
    public JTextArea resultArea = new JTextArea(5, 30);  // Area to display the calculation results

    // Constructor to initialize the GUI
    public BirthdayGUI() {
        setTitle("Kalkulator Umur & Ulang Tahun");  // Set the title of the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Close the application when the window is closed
        setLayout(new BorderLayout());  // Use BorderLayout for the main layout

        // Create a panel for the input fields and button
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Nama:"));  // Label for the name input
        inputPanel.add(nameField);  // Add the name input field
        inputPanel.add(new JLabel("Tanggal Lahir (yyyy-mm-dd): "));  // Label for the date input
        inputPanel.add(dateField);  // Add the date input field
        inputPanel.add(calculateButton);  // Add the calculate button

        // Set the result area to be non-editable
        resultArea.setEditable(false);

        // Add the input panel to the top section of the window (BorderLayout.NORTH)
        add(inputPanel, BorderLayout.NORTH);

        // Add a scrollable area for the result text area (BorderLayout.CENTER)
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        // Pack the window to fit the preferred size of the components
        pack();
        // Center the window on the screen
        setLocationRelativeTo(null);
        // Make the window visible
        setVisible(true);
    }
}
