// Class that contains the print method
class TwoStrings{
    // Method to print the two strings with a simulated delay
    static void print(String str1, String str2){
        // Print the first string without a new line
        System.out.print(str1);
        try{
            // Simulate a delay of 500 milliseconds
            Thread.sleep(500);
        } catch(InterruptedException ie) {
            // Handle any interruptions during the sleep (optional in this case)
        }
        // Print the second string with a new line at the end
        System.out.println(str2);
    }
}

// This class implements Runnable, which allows creating threads to print strings
class PrintStringsThread implements Runnable{
    Thread thread;  // Thread instance for the new thread
    String str1, str2;  // The two strings to print
    TwoStrings ts;  // Reference to the TwoStrings object for synchronized access

    // Constructor to initialize the strings and start the thread
    PrintStringsThread(String str1, String str2, TwoStrings ts){
        this.str1 = str1;  // Set the first string
        this.str2 = str2;  // Set the second string
        this.ts = ts;      // Set the reference to the TwoStrings object
        thread = new Thread(this);  // Create a new thread with this object as the task
        thread.start();  // Start the thread immediately
    }

    @Override
    public void run(){
        // Synchronize access to the print method using the TwoStrings object
        synchronized (ts) {
            ts.print(str1, str2);  // Call the print method on the TwoStrings object
        }
    }
}

// Main class to run the program
class Synchronized2 {
    public static void main(String args[]){
        // Create a new TwoStrings object to be shared among threads
        TwoStrings ts = new TwoStrings();
        
        // Create and start threads that print different string pairs
        new PrintStringsThread("Hello ", "there.", ts);
        new PrintStringsThread("How are ", "you?", ts);
        new PrintStringsThread("Thank you ", "very much!", ts);
    }
}
