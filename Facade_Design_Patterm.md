# Facade Design Pattern
## Anggota
1. Lim Hak Pheng             (23100004)
2. Felix Hartono Chiesa      (23100005)
3. Juven Fidelis Chiesa      (23100017)

## Introduction
Facade (/fəˈsɑːd/) merupakan sebuah kata dari bahasa Prancis "façade" yang berarti "depan" atau "muka". Facade adalah design pattern struktural yang menyediakan interface yang disederhanakan untuk sebuah library, framework, atau class yang kompleks lainnya. Sebagai sebuah kelas, Facade menyediakan antarmuka sederhana ke subsistem kompleks yang berisi banyak komponen. Meskipun Facade menawarkan fungsionalitas yang lebih terbatas dibandingkan dengan bekerja langsung dengan subsistem, ia tetap mencakup fitur-fitur yang hanya dibutuhkan oleh klien.

![GitHub Logo](https://media.geeksforgeeks.org/wp-content/uploads/20240118172253/facade-method-banner.jpg)

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
Interface yang akan ditampilkan ke klien hanya berupa tampilan sederhana dan bukan isi dari seluruh sistem dan subsistem yang dibangun

### 2. Memungkinkan perubahan subsistem tanpa mempengaruhi klien
Klien hanya berinteraksi dengan antarmuka yang ditampilkan pada Facade, sehingga sistem dapat dimodifikasi tanpa mempengaruhi akses klien

### 3. Mempermudah maintanance dan perubahan sistem tanpa mempengaruhi kode klien
Sistem dapat mengubah fungsi dan meenjalankan maintanance tanpa perlu mengbah kode ataupun antarmuka klien

## Kekurangan
Kekurangan dari design pattern Facade:
### 1. Meningkatkan kompleksitas kode terutama pada bagian abstraksi
Penambahan Facade juga ikut menambahkan kompleksitas pada abstraksi karena menambah layer baru

### 2. Fleksibilitas yang berkurang bagi klien yang ingin mengakses fungsi diluar Facade
Facade secara tidak langsung memblokir / membatasi klien untuk melakukan suatu perintah / aksi diluar fungsionalitas Facade

### 3. Tidak diperlukan bagi sistem yang tergolong sederhana
Pada sistem yang sederhana, Facade hanya berupa over-engineering yang hanya menyembunyikan proses yang masih sederhana

## Contoh Desain Facade Methode

![GitHub Logo](https://media.geeksforgeeks.org/wp-content/uploads/20240118172434/Problem-Statement-of-Facade-Method-Design-Pattern.jpg).

## Contoh Kode
Terdapat `HomeAutomationFacade` yang menyederhanakan interaksi dengan beberapa subsistem `Lights`, `AirConditioner`, dan `Television` dengan menyediakan satu antarmuka untuk menyalakan atau mematikan semuanya sekaligus. Facade design pattern menyembunyikan logika yang kompleks dan memberikan antarmuka yang lebih bersih bagi klien.
``` java
// Subsystem 1
class Lights {
    void turnOn() { System.out.println("Lights are ON"); }
    void turnOff() { System.out.println("Lights are OFF"); }
}

// Subsystem 2
class AirConditioner {
    void turnOn() { System.out.println("Air Conditioner is ON"); }
    void turnOff() { System.out.println("Air Conditioner is OFF"); }
}

// Subsystem 3
class Television {
    void turnOn() { System.out.println("Television is ON"); }
    void turnOff() { System.out.println("Television is OFF"); }
}

// Facade
class HomeAutomationFacade {
    private Lights lights;
    private AirConditioner ac;
    private Television tv;

    public HomeAutomationFacade() {
        this.lights = new Lights();
        this.ac = new AirConditioner();
        this.tv = new Television();
    }

    void activate() {
        lights.turnOn();
        ac.turnOn();
        tv.turnOn();
        System.out.println("Home is ready!");
    }

    void deactivate() {
        lights.turnOff();
        ac.turnOff();
        tv.turnOff();
        System.out.println("Home is shut down!");
    }
}

// Client
public class FacadePatternDemo {
    public static void main(String[] args) {
        HomeAutomationFacade home = new HomeAutomationFacade();
        
        System.out.println("--- Activating Home ---");
        home.activate();
        
        System.out.println("\n--- Deactivating Home ---");
        home.deactivate();
    }
}
```
### Subsystem Class (`Lights`, `AirConditioner`, `Television`)
Setiap kelas menyediakan metode untuk menyalakan dan mematikan perangkat masing-masing.
### Facade Class (`HomeAutomationFacade`)
Bertindak sebagai perantara yang menggabungkan semua fungsi dari subsistem dan menyediakan metode sederhana untuk menyalakan atau mematikan semuanya sekaligus.
### Klien (`FacadePatternDemo`)
Menggunakan `HomeAutomationFacade` untuk berinteraksi dengan perangkat tanpa harus memanggil setiap subsistem secara langsung.

### Output
``` java
--- Activating Home ---
Lights are ON
Air Conditioner is ON
Television is ON
Home is ready!

--- Deactivating Home ---
Lights are OFF
Air Conditioner is OFF
Television is OFF
Home is shut down!
```
![output](https://cdn.discordapp.com/attachments/1039426101866405940/1349346532062662676/Screenshot_2025-03-12_183956.png?ex=67d2c462&is=67d172e2&hm=40f49d82345ff13249c127564586a1209a859a52dc1b23a718bb09131a95c65d&)


## References
- [Design Pattern Facade](https://www.geeksforgeeks.org/facade-design-pattern-introduction/)
- [Design Pattern](https://www.google.co.id/books/edition/Mastering_Design_Patterns_in_Java/oK8pEQAAQBAJ?hl=id&gbpv=1&dq=design+patterns&pg=PA8&printsec=frontcover)
