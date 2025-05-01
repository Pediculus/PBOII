import java.util.*;  // Importing the utility package to use collections such as Set and HashSet.

public class Latihan2 {
    public static void main(String[] args) {
        // Array of sentences (strings).
        String[] sentences = {"sistem", "informasi", "dan", "teknologi", "informasi"};
        
        // Creating a HashSet to store unique words. HashSet automatically handles duplicates.
        Set<String> s = new HashSet<String>();
        
        // Looping through the array of sentences.
        for (String a : sentences) {
            // Attempt to add each word into the HashSet.
            // The add() method returns false if the element is already in the set (i.e., a duplicate).
            if (!s.add(a)) {
                // If add() returns false, it means the word is a duplicate, so print a message.
                System.out.println("Duplicate detected: " + a);
            }
        }
        
        // Print the total number of distinct words and the contents of the HashSet.
        System.out.println(s.size() + " distinct words: " + s);
    }
}
