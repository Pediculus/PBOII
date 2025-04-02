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
Thread.sleep(1000); // Thread akan berhenti selama 1 detik
