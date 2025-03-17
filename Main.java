import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class Main {
    private static final String FILE_NAME = "dataBuku.txt"; // Nama file tempat data disimpan
    private static final List<Buku> daftarBuku = new ArrayList<>(); // List untuk menyimpan daftar buku

    public static void main(String[] args) {
        bacaDariFile(); // Membaca data dari file saat program dimulai
        Scanner scanner = new Scanner(System.in);
        int pilihan;

        do {
            // Menampilkan menu kepada pengguna
            System.out.println("\nMenu:");
            System.out.println("1. Tambah Buku");
            System.out.println("2. Tampilkan Daftar Buku");
            System.out.println("3. Keluar");
            System.out.print("Opsi: ");
            pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    tambahBuku(scanner); // Menambahkan buku baru
                    break;
                case 2:
                    tampilkanDaftarBuku(scanner); // Menampilkan daftar buku
                    break;
                case 3:
                    System.out.println("Keluar dari program."); // Keluar dari program
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 3);
    }

    // Method untuk menambahkan buku baru
    private static void tambahBuku(Scanner scanner) {
        System.out.print("Masukkan judul buku: ");
        String judul = scanner.nextLine();
        System.out.print("Masukkan penulis buku: ");
        String penulis = scanner.nextLine();
        System.out.print("Masukkan tahun terbit: ");
        int tahunTerbit = scanner.nextInt();
        scanner.nextLine();

        Buku bukuBaru = new Buku(judul, penulis, tahunTerbit); // Membuat objek buku baru
        daftarBuku.add(bukuBaru); // Menambahkannya ke daftar
        simpanKeFile(bukuBaru); // Menyimpan buku ke file
        System.out.println("Buku berhasil ditambahkan.");
    }

    // Method untuk menampilkan daftar buku
    private static void tampilkanDaftarBuku(Scanner scanner) {
        if (daftarBuku.isEmpty()) {
            System.out.println("Tidak ada buku dalam daftar.");
            return;
        }

        System.out.print("Tampilkan secara ringkas? (y/n): ");
        boolean ringkas = scanner.nextLine().equalsIgnoreCase("y");

        for (Buku buku : daftarBuku) {
            buku.tampilkanInfo(ringkas); // Memanggil method tampilkanInfo dengan parameter ringkas
        }
    }

    // Method untuk menyimpan buku ke dalam file
    private static void simpanKeFile(Buku buku) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(buku.toFileFormat() + "\n"); // Menulis data buku ke file dalam format yang sesuai
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat menyimpan data.");
        }
    }

    // Method untuk membaca data buku dari file saat program dijalankan
    private static void bacaDariFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return; // Jika file tidak ada, tidak perlu membaca

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                daftarBuku.add(Buku.fromFileFormat(line)); // Membaca setiap baris dari file dan mengonversinya menjadi objek Buku
            }
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat membaca data.");
        }
    }
}
