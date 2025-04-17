/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.birthdaysomething;

/**
 *
 * @author HAK_PHENG
 */

import javax.swing.*;
import java.awt.*;

class BirthdayGUI extends JFrame{
    public JTextField nameField = new JTextField(15);
    public JTextField dateField = new JTextField(10);
    public JButton calculateButton = new JButton("Hitung");
    public JTextArea resultArea = new JTextArea(5, 30);
    
    public BirthdayGUI(){
        setTitle("Kalkulator Umur & Ulang Tahun");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Nama:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Tanggal Lahir (yyyy-mm-dd): "));
        inputPanel.add(dateField);
        inputPanel.add(calculateButton);

        resultArea.setEditable(false);
        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}