// Class that contains the synchronized print method
class TwoStrings{
    // A synchronized static method ensures that only one thread can execute it at a time.
    synchronized static void print(String str1, String str2){
        // Print the first string
        System.out.print(str1);
        try{
            // Simulate a delay of 500 milliseconds to demonstrate synchronization
            Thread.sleep(500);
        } catch (InterruptedException ie){
            // Handle the interrupted exception if the thread is interrupted during sleep
        }
        // Print the second string after the delay
        System.out.println(str2);
    }
}

// This class implements Runnable and will be used to create threads that call the print method.
class PrintStringsThread implements Runnable{
    Thread thread;  // Thread instance
    String str1, str2;  // Strings to be printed

    // Constructor to initialize the strings and start the thread
    PrintStringsThread(String str1, String str2){
        this.str1 = str1;  // Set the first string
        this.str2 = str2;  // Set the second string
        thread = new Thread(this);  // Create a new thread with this object as the target
        thread.start();  // Start the thread immediately
    }

    @Override
    public void run(){
        // Call the synchronized static print method to print the strings
        TwoStrings.print(str1, str2);
    }
}

// Main class to start the program execution
public class Synchronized1{
    public static void main(String[] args) {
        // Create and start threads to print different string pairs
        new PrintStringsThread("Hello ", "there.");
        new PrintStringsThread("How are ", "you?");
        new PrintStringsThread("Thank you ", "very much!");
    }   
}
