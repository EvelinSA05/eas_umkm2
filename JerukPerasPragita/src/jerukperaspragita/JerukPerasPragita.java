package jerukperaspragita;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class JerukPerasPragita {

    private static ArrayList<cAdmin> daftarAdmin = new ArrayList<>();
    private static ArrayList<cPembeli> daftarPembeli = new ArrayList<>();
    private static ArrayList<cOwner> daftarOwner = new ArrayList<>();
    private static ArrayList<cMinuman> daftarMinuman = new ArrayList<>();
    private static cLinkedList antreanTransaksi = new cLinkedList();
    private static Scanner sc = new Scanner(System.in);
    private static int counterTransaksi = 1;
    
    private static ArrayList<cMinuman> getSemuaMinuman(){
        return daftarMinuman;
    }

    public static void main(String[] args) {
        // Inisialisasi Data Master
        daftarAdmin.add(new cAdmin("A01", "Admin", "admin@gmail.com", "123", "manager"));
        daftarOwner.add(new cOwner("O01", "Pemilik", "owner@gmail.com", "123"));
        daftarPembeli.add(new cPembeli("M01", "Budi", "123", "081", "P01"));
        daftarPembeli.add(new cPembeli("M02", "Wati", "123", "082", "P02"));
        daftarPembeli.add(new cPembeli("M03", "Iwan", "123", "083", "P03"));

        // Inisialisasi 5 jenis barang sesuai tema
        daftarMinuman.add(new cMinuman("Jus Jeruk Original", 8000, 50));
        daftarMinuman.add(new cMinuman("Jus Jeruk Madu", 10000, 40));
        daftarMinuman.add(new cMinuman("Jus Jeruk Susu", 12000, 40));
        daftarMinuman.add(new cMinuman("Jus Jeruk Kelapa", 12000, 30));
        daftarMinuman.add(new cMinuman("Jus Jeruk Sirup", 9000, 30));
        
        while (true) {
            System.out.println("\n===== Aplikasi Jeruk Peras Pragita =====");
            System.out.println("1. Pembeli");
            System.out.println("2. Member");
            System.out.println("3. Admin");
            System.out.println("4. Pemilik");
            System.out.println("5. Keluar");
            System.out.print("Pilihan: ");
            String pilihan = sc.nextLine();
            
            switch(pilihan) {
                case "1":
                    MenuPembeli.menu(false, null);
                    break;
                case "2":
                    System.out.print("ID Member: "); String id = sc.nextLine();
                    System.out.print("Password: "); String pass = sc.nextLine();
                    cPembeli member = null;
                    for (cPembeli p : daftarPembeli) {
                        if (p.getId() != null && p.getPassword() != null && p.getId().equals(id) && p.getPassword().equals(pass)) {
                            member = p;
                            break;
                        }
                    }
                    if (member != null) {
                        MenuPembeli.menu(true, member);
                    } else {
                        System.out.println("Login Gagal!");
                    }
                    break;
                case "3":
                    System.out.print("Email Admin: "); String email = sc.nextLine();
                    System.out.print("Password: "); String pw = sc.nextLine();
                    boolean loginAdmin = false;
                    for(cAdmin a : daftarAdmin) {
                        if (a.cocokLogin(email, pw)) {
                            loginAdmin = true;
                            break;
                        }
                    }
                    if(loginAdmin) MenuAdmin.menu();
                    else System.out.println("Login Gagal!");
                    break;
                case "4":
                    System.out.print("Email Pemilik: "); String emailP = sc.nextLine();
                    System.out.print("Password: "); String pwP = sc.nextLine();
                    boolean loginOwner = false;
                    for(cOwner o : daftarOwner) {
                        if (o.cocokLogin(emailP, pwP)) {
                            loginOwner = true;
                            break;
                        }
                    }
                    if(loginOwner) MenuOwner.menu();
                    else System.out.println("Login Gagal!");
                    break;
                case "5": System.exit(0);
                default: System.out.println("Pilihan tidak valid!");
            }
        }
    }
    
    // Kelas-kelas Menu sekarang menjadi Nested Static Class
    static class MenuPembeli {
        public static void menu(boolean isMember, cPembeli dataMember) {
            String tgl = "120624";
            String kode = tgl + String.format("%03d", counterTransaksi++);
            
            cPembeli pembeli;
            if (isMember) {
                pembeli = dataMember;
                System.out.println("\nSelamat Datang Kembali, " + pembeli.getNama() + " (Member)");
            } else {
                System.out.print("Masukkan Nama Anda: ");
                String nama = sc.nextLine();
                pembeli = new cPembeli(nama);
            }
            
            cNota notaBaru = new cNota(kode, pembeli);

            boolean selesai = false;
            while (!selesai) {
                System.out.println("\n--- Keranjang Belanja [" + kode + "] ---");
                System.out.println("1. Tambah Barang");
                System.out.println("2. Hapus Keranjang");
                System.out.println("3. Lihat Keranjang");
                System.out.println("4. Selesai");
                if(isMember) System.out.println("5. Ubah Password");
                
                System.out.print("Pilihan: ");
                String pilihan = sc.nextLine();
                switch(pilihan) {
                    case "1":
                        ArrayList<cMinuman> semuaMinuman = getSemuaMinuman();
                        System.out.println("\n--- Daftar Barang ---");
                        for (int i = 0; i < semuaMinuman.size(); i++) {
                            cMinuman m = semuaMinuman.get(i);
                            int hargaTampil = isMember ? (int)(m.getHarga()*0.95) : m.getHarga();
                            System.out.printf("%d. %-25s Rp%,d\n", (i+1), m.getNama(), hargaTampil);
                        }
                        System.out.print("Pilih Nomor Barang: ");
                        int idx = sc.nextInt(); sc.nextLine();
                        System.out.print("Masukkan Jumlah: ");
                        int jumlah = sc.nextInt(); sc.nextLine();
                        
                        cMinuman minumanDipilih = semuaMinuman.get(idx-1);
                        if(isMember) {
                            int hargaDiskon = (int)(minumanDipilih.getHarga() * 0.95);
                            cMinuman itemDiskon = new cMinuman(minumanDipilih.getNama(), hargaDiskon, 0);
                            notaBaru.tambahBarang(itemDiskon, jumlah);
                        } else {
                            notaBaru.tambahBarang(minumanDipilih, jumlah);
                        }
                        break;
                    case "2":
                        notaBaru = new cNota(kode, pembeli);
                        System.out.println("Keranjang belanja berhasil dikosongkan.");
                        break;
                    case "3":
                        notaBaru.lihatNota();
                        break;
                    case "4":
                        if(notaBaru.getJumlahBarang() > 0) {
                            antreanTransaksi.enqueue(new cSimpul(notaBaru));
                        } else {
                            System.out.println("Transaksi kosong, tidak ditambahkan ke antrean.");
                        }
                        selesai = true;
                        break;
                    case "5":
                        if(isMember) {
                            System.out.print("Password Lama: "); String lama = sc.nextLine();
                            if(lama.equals(pembeli.getPassword())) {
                                System.out.print("Password Baru: "); String baru = sc.nextLine();
                                pembeli.setPassword(baru);
                                System.out.println("Password berhasil diubah!");
                            } else {
                                System.out.println("Password lama salah!");
                            }
                        }
                        break;
                    default:
                        System.out.println("Pilihan tidak valid!");
                }
            }
        }
    }
    
    static class MenuAdmin {
        public static void menu() {
             boolean logout = false;
            while(!logout) {
                int belumDiproses = 0;
                cSimpul t = antreanTransaksi.getHead();
                while(t != null) {
                    if(t.data.getStatus() == 0) belumDiproses++;
                    t = t.next;
                }
                
                System.out.println("\n--- Menu Admin ---");
                System.out.println(belumDiproses + " transaksi belum diproses.");
                System.out.println("1. Proses Transaksi");
                System.out.println("2. Tampilkan Transaksi Belum Diproses");
                System.out.println("3. Logout");
                System.out.print("Pilihan: ");
                String pilihan = sc.nextLine();
                
                switch (pilihan) {
                    case "1":
                        cSimpul temp = antreanTransaksi.getHead();
                        boolean adaTransaksi = false;
                        while(temp != null) {
                            if(temp.data.getStatus() == 0) {
                                adaTransaksi = true;
                                System.out.println("\nMEMPROSES TRANSAKSI " + temp.data.getKode());
                                temp.data.lihatNota();
                                System.out.print("1.Proses | 2.Lewati\nPilihan: ");
                                String aksi = sc.nextLine();
                                if(aksi.equals("1")){
                                    temp.data.setStatus(1);
                                    cPembeli p = temp.data.getPembeli();
                                    if(!p.getKode().equals("Non-Member")) {
                                        p.tambahTotalBelanja(temp.data.hitungTotal());
                                    }
                                    System.out.println("Transaksi Berhasil Diproses!");
                                }
                            }
                            temp = temp.next;
                        }
                        if(!adaTransaksi) System.out.println("Tidak ada transaksi untuk diproses.");
                        break;
                    case "2":
                        System.out.println("\n--- Transaksi Belum Diproses ---");
                        cSimpul temp2 = antreanTransaksi.getHead();
                        int count = 0;
                        while(temp2 != null) {
                            if(temp2.data.getStatus()==0){
                                temp2.data.lihatNota();
                                System.out.println("---------------------------------");
                                count++;
                            }
                            temp2 = temp2.next;
                        }
                        System.out.println("Total: "+count+" transaksi.");
                        break;
                    case "3":
                        logout = true;
                        break;
                }
            }
        }
    }
    
    static class MenuOwner {
        public static void menu() {
            boolean logout = false;
            while (!logout) {
                System.out.println("\n--- Menu Pemilik ---");
                System.out.println("1. Laporan Transaksi (Sudah/Belum Diproses)");
                System.out.println("2. Laporan Penjualan per Barang");
                System.out.println("3. Laporan Total Belanja Member");
                System.out.println("4. Grafik Penjualan Barang");
                System.out.println("5. Ubah Harga Barang");
                System.out.println("6. Logout");
                System.out.print("Pilih: ");
                String pilihan = sc.nextLine();

                switch (pilihan) {
                    case "1":
                        laporanByStatus();
                        break;
                    case "2":
                        laporanHarian();
                        break;
                    case "3":
                        laporanMember();
                        break;
                    case "4":
                        grafikPenjualan();
                        break;
                    case "5":
                        ubahHarga();
                        break;
                    case "6":
                        logout = true;
                        break;
                    default:
                        System.out.println("Pilihan tidak valid!");
                }
            }
        }
        
        private static void laporanByStatus() {
            double totalDiproses = 0, totalBelumDiproses = 0;
            cSimpul t = antreanTransaksi.getHead();
            while(t != null) {
                if(t.data.getStatus() == 1) totalDiproses += t.data.hitungTotal();
                else totalBelumDiproses += t.data.hitungTotal();
                t = t.next;
            }
            System.out.println("\nLAPORAN NILAI ORDER");
            System.out.printf("Total Sudah Diproses: Rp%,.0f\n", totalDiproses);
            System.out.printf("Total Belum Diproses: Rp%,.0f\n", totalBelumDiproses);
        }

        private static void laporanHarian() {
            Map<String, Double> pendapatanPerBarang = new HashMap<>();
            double totalPendapatan = 0;
            
            cSimpul t = antreanTransaksi.getHead();
            while(t != null) {
                if(t.data.getStatus() == 1) { 
                    cNota nota = t.data;
                    for (int i = 0; i < nota.getJumlahBarang(); i++) {
                        cMinuman m = nota.getMinuman(i);
                        int j = nota.getJumlah(i);
                        double subtotal = (double)m.getHarga() * j;
                        pendapatanPerBarang.merge(m.getNama(), subtotal, Double::sum);
                    }
                    totalPendapatan += nota.hitungTotal();
                }
                t = t.next;
            }

            System.out.println("\nLAPORAN PENJUALAN HARIAN");
            System.out.printf("Total Pendapatan: Rp%,.0f\n", totalPendapatan);
            int nomor = 1;
            for(Map.Entry<String, Double> entry : pendapatanPerBarang.entrySet()) {
                System.out.printf("%d. %-25s: Rp%,.0f\n", nomor++, entry.getKey(), entry.getValue());
            }
        }

        private static void laporanMember() {
            System.out.println("\nLAPORAN TOTAL BELANJA MEMBER");
            int no = 1;
            for (cPembeli member : daftarPembeli) {
                System.out.printf("%d. %-15s : Rp%,.0f\n", no++, member.getNama(), member.getTotalBelanja());
            }
        }

        private static void grafikPenjualan() {
            System.out.println("\nGRAFIK PENJUALAN (1 'X' = Rp 10.000)");
            Map<String, Double> pendapatanGrafik = new HashMap<>();
            cSimpul t = antreanTransaksi.getHead();
             while(t != null) {
                if(t.data.getStatus() == 1) {
                    cNota nota = t.data;
                    for (int i = 0; i < nota.getJumlahBarang(); i++) {
                        cMinuman m = nota.getMinuman(i);
                        int j = nota.getJumlah(i);
                        double subtotal = (double)m.getHarga() * j;
                        pendapatanGrafik.merge(m.getNama(), subtotal, Double::sum);
                    }
                }
                t = t.next;
            }

            for(cMinuman m : getSemuaMinuman()) {
                double totalJual = pendapatanGrafik.getOrDefault(m.getNama(), 0.0);
                int jumlahX = (int)totalJual / 10000;
                String bar = "X".repeat(jumlahX);
                System.out.printf("%-25s: %s Rp%,.0f\n", m.getNama(), bar, totalJual);
            }
        }

        private static void ubahHarga() {
            ArrayList<cMinuman> semuaMinuman = getSemuaMinuman();
            System.out.println("\nUBAH HARGA BARANG");
            for (int i = 0; i < semuaMinuman.size(); i++) {
                cMinuman m = semuaMinuman.get(i);
                System.out.printf("%2d. %-25s - Rp%,7d\n", i + 1, m.getNama(), m.getHarga());
            }
            System.out.print("Pilih nomor barang: ");
            int pilihanUbah = sc.nextInt(); sc.nextLine();
            if (pilihanUbah > 0 && pilihanUbah <= semuaMinuman.size()) {
                cMinuman m = semuaMinuman.get(pilihanUbah-1);
                System.out.print("Masukkan Harga Baru: Rp");
                int hargaBaru = sc.nextInt(); sc.nextLine();
                m.setHarga(hargaBaru);
                System.out.println("Harga berhasil diubah!");
            } else {
                System.out.println("Pilihan tidak valid.");
            }
        }
    }
}