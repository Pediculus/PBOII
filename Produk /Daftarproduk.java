import java.io.*;
import java.net.URL;
import java.util.*;
import java.text.SimpleDateFormat;

// Class to represent a Product entry
class Product {
    String invoiceNo;    // Invoice number
    String stockCode;    // Stock code of the product
    String description;  // Description of the product
    int quantity;        // Quantity of the product sold
    Date invoiceDate;    // Date the invoice was generated
    double unitPrice;    // Unit price of the product
    String customerID;   // Customer ID (if available)
    String country;      // Country where the product was sold

    // Constructor to initialize the Product object
    public Product(String invoiceNo, String stockCode, String description, int quantity, Date invoiceDate, double unitPrice, String customerID, String country) {
        this.invoiceNo = invoiceNo;
        this.stockCode = stockCode;
        this.description = description;
        this.quantity = quantity;
        this.invoiceDate = invoiceDate;
        this.unitPrice = unitPrice;
        this.customerID = customerID;
        this.country = country;
    }
}

public class Products {
    public static void main(String[] args) {
        // URL of the CSV file containing the product data
        String url = "https://drive.google.com/uc?export=download&id=14DWF2kG0hGD3hYJjAcsexOCGedVfrv3r";
        
        // Lists and maps to store product information
        List<Product> productList = new ArrayList<>();               // List to store Product objects
        Set<String> uniqueCountries = new HashSet<>();                // Set to store unique countries (for statistics)
        Map<String, Integer> totalSoldByStockCode = new HashMap<>();  // Map to store total quantity sold by stock code
        Map<String, Double> revenueByCountry = new HashMap<>();       // Map to store total revenue by country
        Map<String, Product> productByStockCode = new HashMap<>();    // Map to store the Product object by stock code (for unique stock code)

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new URL(url).openStream()))) {
            String line;  // String to store each line from the CSV file
            boolean firstLine = true;  // Flag to skip the header line
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  // Date format for parsing invoice date
            int counter = 0;  // Counter for the number of processed records

            // Read through each line of the CSV file
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;  // Skip the header line
                    continue;
                }

                // Split the CSV line by commas, handling commas inside quoted strings
                String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                // Parse the values from the CSV line
                String invoiceNo = values[0];
                String stockCode = values[1];
                String description = values[2];
                int quantity = Integer.parseInt(values[3]);
                Date invoiceDate = dateFormat.parse(values[4]);
                double unitPrice = Double.parseDouble(values[5]);
                String customerID = values[6].isEmpty() ? null : values[6];  // If customer ID is empty, set it to null
                String country = values[7];

                // Create a Product object and add it to the product list
                Product product = new Product(invoiceNo, stockCode, description, quantity, invoiceDate, unitPrice, customerID, country);
                productList.add(product);

                // Add the country to the unique set
                uniqueCountries.add(country);

                // Update the total quantity sold for each stock code
                totalSoldByStockCode.put(stockCode, totalSoldByStockCode.getOrDefault(stockCode, 0) + quantity);

                // Update the total revenue for each country
                revenueByCountry.put(country, revenueByCountry.getOrDefault(country, 0.0) + (quantity * unitPrice));

                // Store the first product found for each stock code (to ensure uniqueness)
                productByStockCode.putIfAbsent(stockCode, product);

                counter++;  // Increment the counter for each processed record
            }

            // Print the success message and the total number of products processed
            System.out.println("\n=======================================");
            System.out.println("Dataset berhasil dibaca!");  // "Dataset successfully read!"
            System.out.println("Jumlah produk yang dianalisis: " + productList.size());  // Total number of products analyzed
            System.out.println("=======================================\n");

            // Print the first 5 products in the dataset
            System.out.println("DATA PRODUK (5 PERTAMA):");
            System.out.printf("%-10s %-10s %-40s %-10s %-10s %-15s\n", 
                              "InvoiceNo", "StockCode", "Description", "Qty", "Price", "Country");
            System.out.println("-------------------------------------------------------------------------------------");
            productList.stream().limit(5).forEach(p -> 
                System.out.printf("%-10s %-10s %-40s %-10d %-10.2f %-15s\n", 
                                  p.invoiceNo, p.stockCode, p.description, p.quantity, p.unitPrice, p.country)
            );
            System.out.println("-------------------------------------------------------------------------------------\n");

            // Print the total number of unique countries
            System.out.println("Total negara unik: " + uniqueCountries.size());
            System.out.println("---------------------------------------");

            // Print total products sold per stock code
            System.out.println("\nTOTAL PRODUK TERJUAL PER STOCKCODE:");
            System.out.printf("%-15s %-10s\n", "StockCode", "Total Sold");
            System.out.println("-------------------------------");
            totalSoldByStockCode.entrySet().stream().limit(5).forEach(entry -> 
                System.out.printf("%-15s %-10d\n", entry.getKey(), entry.getValue())
            );
            System.out.println("-------------------------------\n");

            // Print total revenue per country
            System.out.println("TOTAL PENDAPATAN PER NEGARA:");
            System.out.printf("%-15s %-15s\n", "Country", "Total Revenue");
            System.out.println("---------------------------------------");
            revenueByCountry.forEach((key, value) -> 
                System.out.printf("%-15s $%-15.2f\n", key, value)
            );
            System.out.println("---------------------------------------\n");

        } catch (Exception e) {
            // Print the stack trace if an error occurs during processing
            e.printStackTrace();
        }
    }
}
