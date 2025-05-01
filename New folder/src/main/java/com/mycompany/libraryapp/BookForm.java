/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.libraryapp;

/**
 *
 * @author HAK_PHENG
 */
import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.OutputStream;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BookForm extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private final String[] columnNames = {"ID", "Title", "Author"};
    private JTextField titleField;
    private JTextField authorField;

    public BookForm() throws MalformedURLException, IOException {
        setTitle("Book Manager GUI");
        setSize(700, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add (scrollPane, BorderLayout.CENTER);
        
        JPanel controlPanel = new JPanel(new GridLayout(2,1));
        
        JPanel inputPanel = new JPanel(new FlowLayout(1));
        titleField = new JTextField(15);
        authorField = new JTextField(15);
        inputPanel.add(new JLabel("Title: "));
        inputPanel.add(titleField);
        inputPanel.add(new JLabel("Author: "));
        inputPanel.add(authorField);
        
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Book");
        JButton editButton = new JButton("Edit Book");
        JButton deleteButton = new JButton ("Delete");
        JButton refreshButton = new JButton ("Refresh");

        
        
        addButton.addActionListener(e -> addBookViaAPI());
        editButton.addActionListener(e -> editBookViaAPI());
        deleteButton.addActionListener(e -> deleteBookViaAPI());
        refreshButton.addActionListener(e -> loadDataFromAPI());
        
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);
        
        controlPanel.add(inputPanel);
        controlPanel.add(buttonPanel);
        
        add(controlPanel, BorderLayout.SOUTH);
        
        loadDataFromAPI();
        
    }
    
    private void addBookViaAPI(){
        String title = titleField.getText().trim();
        String author = authorField.getText().trim();
        
        if(title.isEmpty() || author.isEmpty()){
            JOptionPane.showMessageDialog(this, "Judul dan penulis harus diisi!");
            return;
        }
        
        try{
           URL url = new URL("http://localhost:4567/api/books");
           HttpURLConnection conn = (HttpURLConnection) url.openConnection();
           conn.setRequestMethod("POST");
           conn.setRequestProperty("Content-type", "application/json");
           conn.setDoOutput(true);
           
           String jsonBody = new Gson().toJson(new Book(0, title, author));
           try(OutputStream os = conn.getOutputStream()){
               os.write(jsonBody.getBytes());
               os.flush();               
           }
           
           int responseCode = conn.getResponseCode();
           if(responseCode == 200 || responseCode == 201){
               JOptionPane.showMessageDialog(this, "Buku berhasil ditambahkan!");
               titleField.setText("");
               authorField.setText("");
               loadDataFromAPI();
           } else{
               JOptionPane.showMessageDialog(this, "Gagal menambahkan buku. Code: " + responseCode);
           }
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Error\n" + e.getMessage());
        }
    }
    
    private void editBookViaAPI() {
        int selectedRow = table.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih buku yang ingin diubah!");
            return;
        }

        String title = titleField.getText().trim();
        String author = authorField.getText().trim();

        if (title.isEmpty() || author.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Judul dan penulis harus diisi!");
            return;
        }

        long bookId = (long) table.getValueAt(selectedRow, 0);  // Get ID from table

        try {
            URL url = new URL("http://localhost:4567/api/books/" + bookId);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-type", "application/json");
            conn.setDoOutput(true);

            String jsonBody = new Gson().toJson(new Book(bookId, title, author));
            try (OutputStream os = conn.getOutputStream()) {
                os.write(jsonBody.getBytes());
                os.flush();
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                JOptionPane.showMessageDialog(this, "Buku berhasil diedit!");
                titleField.setText("");
                authorField.setText("");
                loadDataFromAPI();
            } else {
                JOptionPane.showMessageDialog(this, "Gagal mengedit buku. Code: " + responseCode);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error\n" + e.getMessage());
        }
    }
    
private void deleteBookViaAPI() {
    int selectedRow = table.getSelectedRow();

    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Pilih buku yang ingin dihapus!");
        return;
    }

    int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus buku ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
    if (confirm != JOptionPane.YES_OPTION) {
        return;
    }

    int bookId = ((Long) table.getValueAt(selectedRow, 0)).intValue();

    try {
        URL url = new URL("http://localhost:4567/api/books/" + bookId);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("DELETE");

        int responseCode = conn.getResponseCode();
        if (responseCode == 200 || responseCode == 204) {
            JOptionPane.showMessageDialog(this, "Buku berhasil dihapus!");
            titleField.setText("");
            authorField.setText("");
            loadDataFromAPI();
        } else {
            JOptionPane.showMessageDialog(this, "Gagal menghapus buku. Code: " + responseCode);
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error\n" + e.getMessage());
    }
}

    
    private void loadDataFromAPI(){
        try{
            URL url = new URL("http://localhost:4567/api/books");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String json = in.lines().collect(Collectors.joining());
            in.close();
            
            List<Book> books = new Gson().fromJson(json, new TypeToken<List<Book>>() {}.getType());
            
            //Kosongkan tabel
            tableModel.setRowCount(0);
            
            //Tambahkan data baru
            for (Book book : books){
                Object [] row = {book.getId(), book.getTitle(), book.getAuthor()};
                tableModel.addRow(row);
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Gagal mengambil data:\n" + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BookForm gui;
            try {
                gui = new BookForm();
                gui.setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(BookForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
}
