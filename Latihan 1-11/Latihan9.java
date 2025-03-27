import java.util.*;

public class Latihan9 {
    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("1");
        arrayList.add("4");
        arrayList.add("2");
        arrayList.add("3");
        arrayList.add("5");

        Vector v = new Vector();
        v.add("A");
        v.add("B");
        v.add("D");
        v.add("E");
        v.add("F");
        v.add("G");
        v.add("H");
        System.out.println("Before Copy, Vector contains : " + v);

        Collections.copy(v, arrayList);
        System.out.println("After copy, vector contains : " + v);
    }
}
