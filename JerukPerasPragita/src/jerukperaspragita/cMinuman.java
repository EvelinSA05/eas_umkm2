package jerukperaspragita;

/**
 *
 * @author EVELIN
 */
public class cMinuman {

    public String nama;
    public int harga;
    public int stok;

    // Constructor
    public cMinuman(String nama, int harga, int stok) {
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }

    public cMinuman() {
        // constructor kosong
    }

    //setter
    public void tampilkanInfo() {
        System.out.println("\nNama    : " + getNama());
        System.out.println("Harga   : " + getHarga());
        System.out.println("Stok    : " + getStok());
    }

    // Getter dan Setter
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    @Override
    public String toString() {
        return "Nama Minuman : " + nama
                + "\nHarga     : " + harga
                + "\nStok     : " + stok;
    }
}
