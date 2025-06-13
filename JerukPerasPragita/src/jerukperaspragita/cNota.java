package jerukperaspragita;

import java.util.ArrayList;

public class cNota {
    private String kode;
    private cPembeli pembeli;
    private ArrayList<cSimpul> keranjang; // Menggunakan ArrayList untuk kemudahan
    private int status; 

    cNota(String kode, cPembeli p) {
        this.kode = kode;
        this.pembeli = p;
        this.keranjang = new ArrayList<>();
        this.status = 0;
    }

    public void tambahBarang(cSimpul barangBaru) {
        keranjang.add(barangBaru);
        System.out.println(barangBaru.getMinuman().getNama() + " berhasil ditambahkan.");
    }
    
    public void lihatNota() {
        System.out.println("\nNota : " + kode + " | Pembeli: " + pembeli.getNama());
        System.out.println("-------------------------------------------------");
        if (keranjang.isEmpty()) {
            System.out.println("Keranjang Kosong.");
        } else {
            for (int i = 0; i < keranjang.size(); i++) {
                cSimpul s = keranjang.get(i);
                double subtotal = (double)s.getMinuman().getHarga() * s.getJumlah();
                System.out.printf(" %d. %-15s %d x Rp%,d = Rp%,.0f\n", 
                    (i+1), s.getMinuman().getNama(), s.getJumlah(), s.getMinuman().getHarga(), subtotal);
            }
        }
        System.out.println("-------------------------------------------------");
        System.out.printf("   Total : Rp%,.0f\n", hitungTotal());
    }
    
    public double hitungTotal() {
        double grandTotal = 0;
        for (cSimpul s : keranjang) {
            grandTotal += (double)s.getMinuman().getHarga() * s.getJumlah();
        }
        return grandTotal;
    }
    
    public void hapusItem(int nomorItem) {
        if(nomorItem > 0 && nomorItem <= keranjang.size()){
            keranjang.remove(nomorItem - 1);
            System.out.println("Barang berhasil dihapus.");
        } else {
            System.out.println("Nomor tidak valid.");
        }
    }

    public String getKode() { return kode; }
    public cPembeli getPembeli() { return pembeli; }
    public int getStatus() { return status; }
    public void setStatus(int s) { status = s; }
    public ArrayList<cSimpul> getKeranjang() { return keranjang; }
}