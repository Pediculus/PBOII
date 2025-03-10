# Facade Design Pattern

## Introduction
Facade (/fəˈsɑːd/) merupakan sebuah kata dari bahasa Prancis "façade" yang berarti "depan" atau "muka". Facade adalah design pattern struktural yang menyediakan interface yang disederhanakan untuk sebuah library, framework, atau class yang kompleks lainnya. Sebagai sebuah kelas, Facade menyediakan antarmuka sederhana ke subsistem kompleks yang berisi banyak komponen. Meskipun Facade menawarkan fungsionalitas yang lebih terbatas dibandingkan dengan bekerja langsung dengan subsistem, ia tetap mencakup fitur-fitur yang hanya dibutuhkan oleh klien.

## Table of Contents
1. [Kapan Digunakan](#kapan-digunakan)
2. [Section 1](#section-1)
3. [Conclusion](#conclusion)

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

2. Mengurangi ketergantungan antara klien dan subsistem dengan menyembunyikan implementasi subsistem, facade membantu memisahkan kode klien dari perubahan yang mungkin terjadi pada subsistem tersebut.

3. Saat kode terlalu kompleks dan sulit dipahami karena memiliki banyak dependensi langsung dengan subsistem.

## Section 1
Describe the first section here.

### Subsection 1.1
Details about the subsection.

## Section 2
Describe the second section here.

### Subsection 2.1
More details.

## Conclusion
Summarize the document here.

## References
- [Link 1](https://example.com)
- [Link 2](https://example.com)

## Additional Notes
- Bullet point 1
- Bullet point 2

**Bold Text** and *Italic Text* example.



