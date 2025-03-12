# Facade Design Pattern

## Introduction
Facade (/fəˈsɑːd/) merupakan sebuah kata dari bahasa Prancis "façade" yang berarti "depan" atau "muka". Facade adalah design pattern struktural yang menyediakan interface yang disederhanakan untuk sebuah library, framework, atau class yang kompleks lainnya. Sebagai sebuah kelas, Facade menyediakan antarmuka sederhana ke subsistem kompleks yang berisi banyak komponen. Meskipun Facade menawarkan fungsionalitas yang lebih terbatas dibandingkan dengan bekerja langsung dengan subsistem, ia tetap mencakup fitur-fitur yang hanya dibutuhkan oleh klien.

![GitHub Logo]([github.githubassets.com/images/modules/logos_page/GitHub-Mark.png](https://media.geeksforgeeks.org/wp-content/uploads/20240118172253/facade-method-banner.jpg))

## Table of Contents
1. [Kapan Digunakan](#kapan-digunakan)
2. [Kelebihan](#kelebihan)
3. [Kekurangan](#kekurangan)
4. [Contoh Kode](#contoh-kode)

## Kapan Digunakan
### 1. Menyederhanakan Sistem yang Kompleks (Sistem Pemesanan Tiket Pesawat)
Dalam sistem pemesanan tiket pesawat, terdapat berbagai layanan yang harus diakses oleh pengguna, seperti pencarian penerbangan, pemrosesan pembayaran, dan penerbitan tiket. Jika pengguna harus berinteraksi langsung dengan masing-masing layanan, sistem akan menjadi lebih rumit dan sulit digunakan.

Dengan menggunakan **Facade Pattern**, sebuah kelas khusus (misalnya `TicketBookingFacade`) bertindak sebagai antarmuka tunggal yang menangani semua proses di belakang layar. Pengguna hanya perlu memanggil satu metode (`bookTicket()`), tanpa perlu memahami bagaimana setiap layanan bekerja di dalamnya.



### 2. Meningkatkan Keterbacaan dan Manajemen Kode (Aplikasi E-Commerce - Checkout)
Dalam aplikasi e-commerce, ada banyak aspek yang harus dikelola saat pelanggan melakukan checkout, seperti pengecekan stok, penerapan diskon, dan perhitungan pajak. Jika setiap bagian ini dipanggil secara langsung oleh klien, kode akan menjadi lebih panjang, berantakan, dan sulit dikelola.

Dengan **Facade Pattern**, dibuat sebuah antarmuka tunggal (`CheckoutFacade`) yang menangani semua langkah tersebut. Klien cukup memanggil `processCheckout()`, yang secara otomatis akan memeriksa stok, menghitung harga setelah diskon, dan menambahkan pajak. Ini membuat kode lebih bersih, mudah dipahami, dan lebih mudah dikelola.


### 3. Mengurangi Ketergantungan antara Klien dan Subsistem (Sistem Manajemen Rumah Pintar)
Dalam sistem rumah pintar, berbagai perangkat seperti lampu, AC, dan kamera keamanan harus dikendalikan. Jika pengguna harus mengaktifkan atau menonaktifkan setiap perangkat satu per satu, sistem akan menjadi lebih sulit digunakan.

Dengan **Facade Pattern**, dibuat sebuah antarmuka (`SmartHomeFacade`) yang mengelola semua perangkat sekaligus. Misalnya, pengguna cukup memanggil `activateNightMode()`, yang secara otomatis akan mematikan lampu, mengatur suhu AC, dan mengaktifkan kamera keamanan. Dengan cara ini, klien tidak perlu memahami detail cara kerja setiap perangkat, sehingga sistem menjadi lebih fleksibel dan mudah digunakan.



## Kelebihan
Kelebihan dari design pattern Facade:
### 1. Membuat interface yang jauh lebih sederhana bagi klien
### 2. Memungkinkan perubahan subsistem tanpa mempengaruhi klien
### 3. Mempermudah maintanance dan perubahan sistem tanpa mempengaruhi kode klien

## Kekurangan
Kekurangan dari design pattern Facade:
### 1. Meningkatkan kompleksitas kode terutama pada bagian abstraksi
### 2. Fleksibilitas yang berkurang bagi klien yang ingin mengakses fungsi diluar Facade
### 3. Tidak diperlukan bagi sistem yang tergolong sederhana

## Contoh Kode
Summarize the document here.

## References
- [Design Pattern Facade](https://www.geeksforgeeks.org/facade-design-pattern-introduction/)
- [Design Pattern](https://www.google.co.id/books/edition/Mastering_Design_Patterns_in_Java/oK8pEQAAQBAJ?hl=id&gbpv=1&dq=design+patterns&pg=PA8&printsec=frontcover)

## Additional Notes
- Bullet point 1
- Bullet point 2

**Bold Text** and *Italic Text* example.



