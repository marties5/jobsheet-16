import java.util.Scanner;

public class CimutKafe_kelompok_5 {
    static String[] daftarMenu = { "Kopi Hitam", "Latte", "Cappuccino", "Espresso", "Green Tea" };
    static int[] hargaMenu = { 10000, 15000, 17000, 20000, 12000 };
    static String[] namaPelanggan = new String[100];
    static int[] nomorMeja = new int[100];
    static String[] namaMenuPesanan = new String[100];
    static int[] jumlahItemPesanan = new int[100];
    static int[] totalHargaPesanan = new int[100];
    static int jumlahPesanan = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int pilihan;
        do {
            tampilkanMenuUtama();
            System.out.print("Pilih menu Di Cimut Kafe: ");
            pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    tambahPesanan(scanner);
                    break;
                case 2:
                    tampilkanPesanan();
                    break;
                case 3:
                    System.out.println("Terima kasih telah menggunakan sistem manajemen Cimut Kafe!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid, coba lagi !!!!!!!!!!!!!!!");
            }
        } while (pilihan != 3);
    }

    public static void tampilkanMenuUtama() {
        System.out.println("\n=== Sistem Manajemen Cimut Kafe ===");
        System.out.println("1. Tambah Pesanan");
        System.out.println("2. Tampilkan Semua Pesanan");
        System.out.println("3. Keluar");
    }

    public static void tambahPesanan(Scanner scanner) {
        if (jumlahPesanan >= 100) {
            System.out.println("Pesanan penuh! Tidak dapat menambah pesanan baru.");
            return;
        }
        int jumlahItem;
        System.out.print("Masukkan nama pelanggan: ");
        String nama = scanner.nextLine();

        System.out.print("Masukkan nomor meja: ");
        int meja = scanner.nextInt();
        scanner.nextLine();

        do {

            System.out.println("\n====== Menu Kafe Cimut ======");
            for (int i = 0; i < daftarMenu.length; i++) {
                System.out.println((i + 1) + ". " + daftarMenu[i] + " - Rp " + hargaMenu[i]);
            }
            System.out.print("Masukkan nomor menu (1-5) atau ketik '0' untuk selesai: ");
            int menu = scanner.nextInt();
            scanner.nextLine();

            if (menu == 0) {
                System.out.println("Proses menambahkan menu selesai.");
                return;
            }

            if (menu < 1 || menu > daftarMenu.length) {
                System.out.println("Nomor menu tidak valid. Masukkan nomor yang sesuai daftar.");
            } else {

                do {
                    System.out.print("Masukkan jumlah item (lebih dari 0): ");
                    jumlahItem = scanner.nextInt();
                    scanner.nextLine();
                    if (jumlahItem <= 0) {
                        System.out.println("Jumlah item harus lebih dari 0.");
                    }
                } while (jumlahItem <= 0);

                namaPelanggan[jumlahPesanan] = nama;
                nomorMeja[jumlahPesanan] = meja;
                namaMenuPesanan[jumlahPesanan] = daftarMenu[menu - 1];
                jumlahItemPesanan[jumlahPesanan] = jumlahItem;
                totalHargaPesanan[jumlahPesanan] = jumlahItem * hargaMenu[menu - 1];
                jumlahPesanan++;
                System.out.println("Pesanan berhasil ditambahkan!");
            }
        } while (true);
    }

    public static int cariMenu(String menu) {
        for (int i = 0; i < daftarMenu.length; i++) {
            if (daftarMenu[i].equalsIgnoreCase(menu)) {
                return i;
            }
        }
        return -1;
    }

    public static void tampilkanPesanan() {
        if (jumlahPesanan == 0) {
            System.out.println("Belum ada pesanan yang tercatat.");
            return;
        }
        System.out.println("\n=== Daftar Semua Pesanan ===");
        boolean[] sudahDitampilkan = new boolean[jumlahPesanan];
        for (int i = 0; i < jumlahPesanan; i++) {
            if (!sudahDitampilkan[i]) {
                String nama = namaPelanggan[i];
                System.out.println("Nama Pelanggan: " + nama);
                System.out.println("Nomor Meja: " + nomorMeja[i]);
                System.out.println("Pesanan:");
                int totalPembayaran = 0;
                for (int j = 0; j < jumlahPesanan; j++) {
                    if (namaPelanggan[j].equals(nama) && !sudahDitampilkan[j]) {
                        System.out.println("- " + namaMenuPesanan[j] + " x " + jumlahItemPesanan[j] + " @ Rp "
                                + (totalHargaPesanan[j] / jumlahItemPesanan[j]) + " = Rp " + totalHargaPesanan[j]);
                        totalPembayaran += totalHargaPesanan[j];
                        sudahDitampilkan[j] = true;
                    }
                }
                System.out.println("Total Pembayaran: Rp " + totalPembayaran);
                System.out.println("------------------------");
            }
        }
    }
}
