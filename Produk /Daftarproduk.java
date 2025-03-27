import java.io.*;
import java.net.URL;
import java.util.*;
import java.text.SimpleDateFormat;

// Class to represent a Product entry
class Product {
    String invoiceNo;
    String stockCode;
    String description;
    int quantity;
    Date invoiceDate;
    double unitPrice;
    String customerID;
    String country;

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
        String url = "https://drive.google.com/uc?export=download&id=14DWF2kG0hGD3hYJjAcsexOCGedVfrv3r";
        
        List<Product> productList = new ArrayList<>();
        Set<String> uniqueCountries = new HashSet<>();
        Map<String, Integer> totalSoldByStockCode = new HashMap<>();
        Map<String, Double> revenueByCountry = new HashMap<>();
        Map<String, Product> productByStockCode = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new URL(url).openStream()))) {
            String line;
            boolean firstLine = true;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            int counter = 0;

            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }


                String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                String invoiceNo = values[0];
                String stockCode = values[1];
                String description = values[2];
                int quantity = Integer.parseInt(values[3]);
                Date invoiceDate = dateFormat.parse(values[4]);
                double unitPrice = Double.parseDouble(values[5]);
                String customerID = values[6].isEmpty() ? null : values[6];
                String country = values[7];

                Product product = new Product(invoiceNo, stockCode, description, quantity, invoiceDate, unitPrice, customerID, country);
                productList.add(product);

                uniqueCountries.add(country);
                totalSoldByStockCode.put(stockCode, totalSoldByStockCode.getOrDefault(stockCode, 0) + quantity);
                revenueByCountry.put(country, revenueByCountry.getOrDefault(country, 0.0) + (quantity * unitPrice));
                productByStockCode.putIfAbsent(stockCode, product);

                counter++;
            }

            System.out.println("\n=======================================");
            System.out.println("Dataset berhasil dibaca!");
            System.out.println("Jumlah produk yang dianalisis: " + productList.size());
            System.out.println("=======================================\n");

            System.out.println("DATA PRODUK (5 PERTAMA):");
            System.out.printf("%-10s %-10s %-40s %-10s %-10s %-15s\n", 
                              "InvoiceNo", "StockCode", "Description", "Qty", "Price", "Country");
            System.out.println("-------------------------------------------------------------------------------------");
            productList.stream().limit(5).forEach(p -> 
                System.out.printf("%-10s %-10s %-40s %-10d %-10.2f %-15s\n", 
                                  p.invoiceNo, p.stockCode, p.description, p.quantity, p.unitPrice, p.country)
            );
            System.out.println("-------------------------------------------------------------------------------------\n");

            System.out.println("Total negara unik: " + uniqueCountries.size());
            System.out.println("---------------------------------------");

            System.out.println("\nTOTAL PRODUK TERJUAL PER STOCKCODE:");
            System.out.printf("%-15s %-10s\n", "StockCode", "Total Sold");
            System.out.println("-------------------------------");
            totalSoldByStockCode.entrySet().stream().limit(5).forEach(entry ->
                System.out.printf("%-15s %-10d\n", entry.getKey(), entry.getValue())
            );
            System.out.println("-------------------------------\n");

            System.out.println("TOTAL PENDAPATAN PER NEGARA:");
            System.out.printf("%-15s %-15s\n", "Country", "Total Revenue");
            System.out.println("---------------------------------------");
            revenueByCountry.forEach((key, value) -> 
                System.out.printf("%-15s $%-15.2f\n", key, value)
            );
            System.out.println("---------------------------------------\n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
