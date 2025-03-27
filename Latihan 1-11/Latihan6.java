import java.util.*;

public class Latihan6 {
    public static void main(String[] args) {
        Vector<String> vc= new Vector<String>();

        vc.add("Vector object 1");
        vc.add("Vector object 2");
        vc.add("Vector object 3");
        vc.add("Vector object 4");
        vc.add("Vector object 5");

        vc.add(3, "Element at fix position");

        System.out.println("Vector size : " + vc.size());

        for(int i = 0; i < vc.size(); i ++){
            System.out.println("Vector Element: "+ i + " : "+vc.get(i));
        }
    }
}
