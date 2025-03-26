import java.io.*;
import java.net.URL;
import java.util.*;
public class Products {

    public static void main(String[] args) {
        String url = "https://drive.google.com/uc?export=download&id=14DWF2kG0hGD3hYJjAcsexOCGedVfrv3r";
        try (BufferedReader br = new BufferedReader(new InputStreamReader (new URL(url).openStream()))) {
            String line;
            boolean firstLine = true;
            List <String[]> data = new ArrayList<>();
            
            while ((line = br.readLine())!=null){
                if (firstLine){//skip header
                    firstLine = false;
                    continue;
                }
                String[] values = line.split(",");
                data.add(values);
            }
            System.out.println("Dataset berhasil dibaca! Jumlah baris : " + data.size());
            
            //Menampilkan 7 baris pertama
            for (int i = 0 ; i < 7; i++){
                System.out.println(Arrays.toString(data.get(i)));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
