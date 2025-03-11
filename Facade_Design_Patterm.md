# Facade Design Pattern

## Introduction
Facade (/fəˈsɑːd/) merupakan sebuah kata dari bahasa Prancis "façade" yang berarti "depan" atau "muka". Facade adalah design pattern struktural yang menyediakan interface yang disederhanakan untuk sebuah library, framework, atau class yang kompleks lainnya. Sebagai sebuah kelas, Facade menyediakan antarmuka sederhana ke subsistem kompleks yang berisi banyak komponen. Meskipun Facade menawarkan fungsionalitas yang lebih terbatas dibandingkan dengan bekerja langsung dengan subsistem, ia tetap mencakup fitur-fitur yang hanya dibutuhkan oleh klien.

## Table of Contents
1. [Kapan Digunakan](#kapan-digunakan)
2. [Kelebihan](#section-1)
3. [Kekurangan](#section-2)
4. [Conclusion](#conclusion)

## Kapan Digunakan
### 1. Jika sebuah sistem memiliki banyak kelas atau subsistem yang harus digunakan bersama
Facade dapat menyediakan interface tunggal yang lebih mudah dipahami.

#### Kasus: Sistem Pemesanan Tiket Online
Dalam sistem pemesanan tiket pesawat, ada beberapa subsistem yang harus berinteraksi, seperti:

1. **Pencarian Penerbangan** (`FlightSearch`)
2. **Pemesanan Kursi** (`SeatReservation`)
3. **Pembayaran** (`PaymentProcessing`)
4. **Konfirmasi Tiket** (`TicketConfirmation`)

Jika pengguna harus berinteraksi dengan semua subsistem ini secara langsung, maka sistem menjadi terlalu kompleks dan sulit digunakan. 

**Solusi dengan Facade**: Membuat kelas `TicketBookingFacade` yang menyediakan satu metode:
```java
public class TicketBookingFacade {
    public void bookFlight(Customer customer, String flightID, PaymentDetails paymentDetails) {
        FlightSearch.search(flightID);
        SeatReservation.reserveSeat(customer, flightID);
        PaymentProcessing.processPayment(paymentDetails);
        TicketConfirmation.sendConfirmation(customer);
    }
}
```

### 2. Mengurangi ketergantungan antara klien dan subsistem dengan menyembunyikan implementasi subsistem, facade membantu memisahkan kode klien dari perubahan yang mungkin terjadi pada subsistem tersebut.

### 3. Saat kode terlalu kompleks dan sulit dipahami karena memiliki banyak dependensi langsung dengan subsistem.

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

## Conclusion
Summarize the document here.

## References
- [Link 1](https://www.geeksforgeeks.org/facade-design-pattern-introduction/)
- [Link 2](https://www.google.co.id/books/edition/Mastering_Design_Patterns_in_Java/oK8pEQAAQBAJ?hl=id&gbpv=1&dq=design+patterns&pg=PA8&printsec=frontcover)

## Additional Notes
- Bullet point 1
- Bullet point 2

**Bold Text** and *Italic Text* example.



