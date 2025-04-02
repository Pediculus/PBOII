# Penjelasan Multithreading, Thread, Runnable, dan Thread.sleep()

## Apa itu Multithreading?
Multithreading adalah teknik pemrograman yang memungkinkan eksekusi beberapa thread secara bersamaan dalam satu program. Setiap thread dalam sebuah program dapat berjalan secara independen dan dapat menyelesaikan tugas yang berbeda pada waktu yang sama. Teknik ini sangat berguna untuk memaksimalkan penggunaan CPU, mempercepat proses eksekusi, serta membuat program lebih responsif.

## Apa Perbedaan antara Thread dan Runnable?
- **Thread** adalah sebuah unit eksekusi terkecil dalam sebuah program yang dapat dijalankan secara independen. Sebuah objek `Thread` mewakili satu thread yang berjalan dalam program Java.
- **Runnable** adalah sebuah antarmuka (interface) yang digunakan untuk mendefinisikan tugas atau pekerjaan yang akan dijalankan dalam sebuah thread. `Runnable` berisi method `run()` yang harus diimplementasikan untuk menjalankan tugas tertentu.

Perbedaan utamanya adalah:
- `Thread` adalah kelas, sedangkan `Runnable` adalah antarmuka (interface).
- `Thread` digunakan untuk membuat thread baru, sedangkan `Runnable` digunakan untuk mendefinisikan tugas yang akan dijalankan oleh thread.

## Thread.sleep()
`Thread.sleep()` digunakan untuk menghentikan sementara eksekusi thread saat ini untuk jangka waktu tertentu. Method ini menerima waktu dalam milidetik (ms) dan memungkinkan thread untuk tidur sementara waktu tanpa menggunakan CPU.

Contoh penggunaan:
```java
public class ThreadExample {
    public static void main(String[] args) {
        // Membuat thread pertama
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Menampilkan pesan bahwa Thread 1 dimulai
                    System.out.println("Thread 1 mulai...");
                    // Thread 1 tidur selama 2 detik (2000 milidetik)
                    Thread.sleep(2000); // Tidur selama 2000 milidetik (2 detik)
                    // Menampilkan pesan bahwa Thread 1 selesai setelah 2 detik
                    System.out.println("Thread 1 selesai setelah 2 detik");
                } catch (InterruptedException e) {
                    // Jika Thread 1 terganggu, tangani exception ini
                    System.out.println("Thread 1 terganggu");
                }
            }
        });

        // Membuat thread kedua
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Menampilkan pesan bahwa Thread 2 dimulai
                    System.out.println("Thread 2 mulai...");
                    // Thread 2 tidur selama 1 detik (1000 milidetik)
                    Thread.sleep(1000); // Tidur selama 1000 milidetik (1 detik)
                    // Menampilkan pesan bahwa Thread 2 selesai setelah 1 detik
                    System.out.println("Thread 2 selesai setelah 1 detik");
                } catch (InterruptedException e) {
                    // Jika Thread 2 terganggu, tangani exception ini
                    System.out.println("Thread 2 terganggu");
                }
            }
        });

        // Menjalankan kedua thread
        thread1.start(); // Thread 1 mulai dieksekusi
        thread2.start(); // Thread 2 mulai dieksekusi
    }
}

