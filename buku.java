class Buku {
    private final String judul;
    private final String penulis;
    private final int tahunTerbit;

   // Constructor untuk inisialisasi objek Buku
   public Buku(String judul, String penulis, int tahunTerbit) {
    this.judul = judul;
    this.penulis = penulis;
    this.tahunTerbit = tahunTerbit;
}

// Method untuk mendapatkan judul buku
public String getJudul() {
    return judul;
}

// Method untuk menampilkan informasi buku secara lengkap
public void tampilkanInfo() {
    System.out.println("Judul: " + judul + ", Penulis: " + penulis + ", Tahun: " + tahunTerbit);
}

// Method overloading untuk menampilkan informasi secara ringkas atau lengkap
public void tampilkanInfo(boolean ringkas) {
    if (ringkas) {
        System.out.println("Judul: " + judul);
    } else {
        tampilkanInfo();
    }
}

// Method untuk mengubah objek Buku menjadi format string untuk disimpan ke file
public String toFileFormat() {
    return judul + "," + penulis + "," + tahunTerbit;
}

// Method untuk membuat objek Buku dari format string yang dibaca dari file
public static Buku fromFileFormat(String data) {
    String[] parts = data.split(","); // Memisahkan data yang dipisahkan oleh koma
    return new Buku(parts[0], parts[1], Integer.parseInt(parts[2])); // Mengembalikan objek Buku dari data
    }
}
