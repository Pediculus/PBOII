import java.util.*;

public class Latihan5 {
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add("Jennie");
        list.add("Sherly");
        list.add("Nopeliba");
        list.add("Oktria");
        list.add("Angelyne");
        list.add("Michella");

        System.out.println(list);

        System.out.println("2 : "+ list.get(2));
        System.out.println("0 : "+ list.get(0));

        LinkedList queue = new LinkedList();
        queue.add("Oktria");
        queue.add("Nopeliba");
        queue.add("Jennie");
        queue.add("Michella");
        queue.add("Sherly");
        queue.add("Angelyne");
        System.out.println(queue);

        queue.removeLast();
        queue.removeLast();
        System.out.println(queue);


    }
}
