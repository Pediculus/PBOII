import java.util.*;  // Importing the utility package to use collections such as Set and HashSet.

public class Latihan3 {
    public static void main(String[] args) {
        // Array of words
        String[] words = {"saya", "ingin", "lulus", "saya", "akan", "berusaha"};

        // Set to store unique words
        Set<String> uniques = new HashSet<String>();

        // Set to store duplicate words
        Set<String> dups = new HashSet<String>();

        // Loop through each word in the array
        for (String a : words) {
            // Attempt to add the word to the uniques set
            // If add() returns false, the word is a duplicate (already exists in uniques)
            if (!uniques.add(a)) {
                // Add the duplicate word to the dups set
                dups.add(a);
            }
        }

        // Destructive set-difference: remove all duplicates from the uniques set
        uniques.removeAll(dups);
        
        // Print unique words and duplicate words
        System.out.println("Unique words: " + uniques);
        System.out.println("Duplicate words: " + dups);
    }
}
