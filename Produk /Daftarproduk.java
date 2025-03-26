import java.io.*;
import java.net.URL;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;

class Product {
    String invoiceNo;
    String stockCode;
    String description;
    int quantity;
    Date invoiceDate;
    double unitPrice;
    Double customerID;
    String country;

    public Product(String invoiceNo, String stockCode, String description, int quantity, Date invoiceDate, double unitPrice, Double customerID, String country) {
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
            int counter = 0; // Menambahkan penghitung data

            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                if (counter >= 100) break; // Hentikan setelah 10 data

                String[] values = line.split(",");
                String invoiceNo = values[0];
                String stockCode = values[1];
                String description = values[2];
                int quantity = Integer.parseInt(values[3]);
                Date invoiceDate = dateFormat.parse(values[4]);
                double unitPrice = Double.parseDouble(values[5]);
                Double customerID = values[6].isEmpty() ? null : Double.parseDouble(values[6]);
                String country = values[7];

                Product product = new Product(invoiceNo, stockCode, description, quantity, invoiceDate, unitPrice, customerID, country);
                productList.add(product);

                uniqueCountries.add(country);
                totalSoldByStockCode.put(stockCode, totalSoldByStockCode.getOrDefault(stockCode, 0) + quantity);
                revenueByCountry.put(country, revenueByCountry.getOrDefault(country, 0.0) + (quantity * unitPrice));
                productByStockCode.putIfAbsent(stockCode, product);

                counter++; // Tambahkan penghitung setiap kali data ditambahkan
            }

            System.out.println("Dataset berhasil dibaca! Jumlah produk yang dianalisis: " + productList.size());

            // Menampilkan 10 produk pertama
            for (Product p : productList) {
                System.out.println(p.invoiceNo + ", " + p.stockCode + ", " + p.description + ", " + p.quantity + ", " + p.unitPrice + ", " + p.country);
            }

            // Menampilkan jumlah negara unik
            System.out.println("Total negara unik: " + uniqueCountries.size());

            // Menampilkan total produk terjual berdasarkan StockCode
            System.out.println("Total produk terjual berdasarkan StockCode:");
            totalSoldByStockCode.forEach((key, value) -> System.out.println(key + ": " + value));

            // Menampilkan total pendapatan per negara
            System.out.println("Total pendapatan per negara:");
            revenueByCountry.forEach((key, value) -> System.out.println(key + ": " + value));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
