/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mavenproject2;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
/**
 *
 * @author HAK_PHENG
 */
public class Mavenproject2 {
    private static ArrayList<Product> products = new ArrayList<>();  // Corrected to ArrayList<Product>
    private static DefaultTableModel tableModel;
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Daftar Produk");
        frame.setSize(700, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        String[] columnNames = {"Nama Produk", "Harga Produk"};
        tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        
        JTextField nameField = new JTextField(10);
        JTextField priceField = new JTextField(10);
        JButton addButton = new JButton("Tambah");
        JButton removeButton = new JButton("Hapus");
        JButton editButton = new JButton("Ubah");
        
        addButton.addActionListener(e -> {
            try {
                String name = nameField.getText();
                double price = Double.parseDouble(priceField.getText());
                Product product = new Product(name, price);  // Corrected to Product
                products.add(product);
                tableModel.addRow(new Object[]{name, price});
                nameField.setText("");
                priceField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Harga harus berupa angka!");
            }
        });
        
        removeButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                products.remove(selectedRow);
                tableModel.removeRow(selectedRow);
                nameField.setText("");
                priceField.setText("");
            } else {
                JOptionPane.showMessageDialog(frame, "Tidak ada yang dipilih!");
            }
        });
        
        editButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String newName = nameField.getText();
                double newPrice;

                try {
                    newPrice = Double.parseDouble(priceField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Harga harus berupa angka!");
                    return;
                }

                // Update data di ArrayList
                products.get(selectedRow).setName(newName);
                products.get(selectedRow).setPrice(newPrice);

                // Update data di Table Model
                tableModel.setValueAt(newName, selectedRow, 0);
                tableModel.setValueAt(newPrice, selectedRow, 1);

                // Clear input setelah edit
                nameField.setText("");
                priceField.setText("");
            } else {
                JOptionPane.showMessageDialog(frame, "Pilih produk yang ingin diubah!");
            }
        });
        
        JPanel panel = new JPanel();
        panel.add(new JLabel("Nama:"));
        panel.add(nameField);
        panel.add(new JLabel("Harga:"));
        panel.add(priceField);
        panel.add(addButton);
        panel.add(removeButton);
        panel.add(editButton);
        
        frame.add(new JScrollPane(table), BorderLayout.CENTER);
        frame.add(panel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}
