package com.mycompany.libraryapp;

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
    // Components for displaying the book data and input fields
    private JTable table;
    private DefaultTableModel tableModel;
    private final String[] columnNames = {"ID", "Title", "Author"};
    private JTextField titleField;
    private JTextField authorField;

    public BookForm() throws MalformedURLException, IOException {
        // Setup the window (JFrame) properties
        setTitle("Book Manager GUI");
        setSize(700, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setLayout(new BorderLayout());

        // Table setup to display book data
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);  // Add the table to the center of the window
        
        // Panel to hold input fields and buttons
        JPanel controlPanel = new JPanel(new GridLayout(2, 1));
        
        // Input panel for title and author
        JPanel inputPanel = new JPanel(new FlowLayout(1));
        titleField = new JTextField(15);
        authorField = new JTextField(15);
        inputPanel.add(new JLabel("Title: "));
        inputPanel.add(titleField);
        inputPanel.add(new JLabel("Author: "));
        inputPanel.add(authorField);
        
        // Button panel with action listeners for the various buttons
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Book");
        JButton editButton = new JButton("Edit Book");
        JButton deleteButton = new JButton("Delete");
        JButton refreshButton = new JButton("Refresh");

        // Adding event listeners for buttons
        addButton.addActionListener(e -> addBookViaAPI());
        editButton.addActionListener(e -> editBookViaAPI());
        deleteButton.addActionListener(e -> deleteBookViaAPI());
        refreshButton.addActionListener(e -> loadDataFromAPI());

        // Adding buttons to the panel
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);
        
        // Adding the input and button panels to the control panel
        controlPanel.add(inputPanel);
        controlPanel.add(buttonPanel);
        
        // Adding the control panel to the bottom of the window
        add(controlPanel, BorderLayout.SOUTH);
        
        // Load the data from the API on startup
        loadDataFromAPI();
    }
    
    // Method to add a book via the API (POST request)
    private void addBookViaAPI() {
        String title = titleField.getText().trim();
        String author = authorField.getText().trim();

        // Check if title or author fields are empty
        if (title.isEmpty() || author.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Judul dan penulis harus diisi!");
            return;
        }

        try {
            // URL of the API for adding a book
            URL url = new URL("http://localhost:4567/api/books");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-type", "application/json");
            conn.setDoOutput(true);
            
            // Create a JSON object for the book and send it in the request body
            String jsonBody = new Gson().toJson(new Book(0, title, author));
            try (OutputStream os = conn.getOutputStream()) {
                os.write(jsonBody.getBytes());
                os.flush();               
            }
            
            int responseCode = conn.getResponseCode();
            if (responseCode == 200 || responseCode == 201) {
                JOptionPane.showMessageDialog(this, "Buku berhasil ditambahkan!");
                titleField.setText("");
                authorField.setText("");
                loadDataFromAPI();  // Reload the data after adding
            } else {
                JOptionPane.showMessageDialog(this, "Gagal menambahkan buku. Code: " + responseCode);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error\n" + e.getMessage());
        }
    }
    
    // Method to edit a book via the API (PUT request)
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
            // URL of the API for editing the book
            URL url = new URL("http://localhost:4567/api/books/" + bookId);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-type", "application/json");
            conn.setDoOutput(true);

            // Create a JSON object for the updated book and send it in the request body
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
                loadDataFromAPI();  // Reload the data after editing
            } else {
                JOptionPane.showMessageDialog(this, "Gagal mengedit buku. Code: " + responseCode);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error\n" + e.getMessage());
        }
    }
    
    // Method to delete a book via the API (DELETE request)
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

        int bookId = ((Long) table.getValueAt(selectedRow, 0)).intValue();  // Get ID from table

        try {
            // URL of the API for deleting the book
            URL url = new URL("http://localhost:4567/api/books/" + bookId);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");

            int responseCode = conn.getResponseCode();
            if (responseCode == 200 || responseCode == 204) {
                JOptionPane.showMessageDialog(this, "Buku berhasil dihapus!");
                titleField.setText("");
                authorField.setText("");
                loadDataFromAPI();  // Reload the data after deletion
            } else {
                JOptionPane.showMessageDialog(this, "Gagal menghapus buku. Code: " + responseCode);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error\n" + e.getMessage());
        }
    }
    
    // Method to load all books from the API (GET request)
    private void loadDataFromAPI() {
        try {
            // URL of the API for fetching books
            URL url = new URL("http://localhost:4567/api/books");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String json = in.lines().collect(Collectors.joining());
            in.close();
            
            // Parse the JSON response into a list of books
            List<Book> books = new Gson().fromJson(json, new TypeToken<List<Book>>() {}.getType());
            
            // Clear the table before loading new data
            tableModel.setRowCount(0);
            
            // Add each book to the table
            for (Book book : books) {
                Object[] row = {book.getId(), book.getTitle(), book.getAuthor()};
                tableModel.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal mengambil data:\n" + e.getMessage());
        }
    }

    // Main method to launch the GUI application
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
