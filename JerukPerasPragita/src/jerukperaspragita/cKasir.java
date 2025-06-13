package jerukperaspragita;

import java.util.ArrayList;

/**
 *
 * @author EVELIN
 */
public class cKasir extends cOrang {

    private String shift;
    private double totalPendapatan;
    private String historyPesanan;

    // Konstruktor tanpa parameter (default)
    public cKasir() {
        super("", "", "", ""); // Memanggil konstruktor parent dengan nilai default
        this.shift = "Tidak Diketahui";
        this.totalPendapatan = 0.0;
        this.historyPesanan = "Belum ada transaksi";
    }

    public cKasir(String id, String nama, String email, String password, String s, double tp, String hp) { //parameter rized
        super(id, nama, password, email);
        this.shift = s;
        this.totalPendapatan = tp;
        this.historyPesanan = hp;
    }

    public boolean cocokLogin(String email, String password) {
        return getEmail().equals(email) && getPassword().equals(password);
    }

    //setter
    public void tampilkanInfo() {
        System.out.println("ID Kasir         : " + getId());
        System.out.println("Nama Kasir       : " + getNama());
        System.out.println("Email Kasir      : " + getEmail());
        System.out.println("Shift            : " + shift);
        System.out.println("Total Pendapatan : " + totalPendapatan);
        System.out.println("History Pesanan  : " + historyPesanan);
        System.out.println("------------------------------");
    }

    void setShift(String s) {
        shift = s;
    }

    void setTotalPendapatan(double tp) {
        totalPendapatan = tp;
    }

    void setHistoryPesanan(String hp) {
        historyPesanan = hp;
    }

    //getter
    String getShift() {
        return shift;
    }

    double getTotalPendapatan() {
        return totalPendapatan;
    }

    String getHistoryPesanan() {
        return historyPesanan;
    }

    //to String
    @Override
    public String toString() {
        return "\nShift Kerja     : " + shift
                + "\nPendapatan      : Rp" + totalPendapatan
                + "\nHistory Pesanan : " + historyPesanan;
    }
}
