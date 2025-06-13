package jerukperaspragita;

public class cPembeli extends cOrang {
    private String telp;
    private String kode;
    private double totalBelanja;

    public cPembeli() {
        super("","","","");
    }
    
    public cPembeli(String nama) {
        super("", nama, "", "");
        this.kode = "Non-Member";
    }

    public cPembeli(String id, String nama, String password, String telp, String kode) {
        super(id, nama, password, "");
        this.telp = telp;
        this.kode = kode;
        this.totalBelanja = 0;
    }

    public void tambahTotalBelanja(double jumlah) {
        this.totalBelanja += jumlah;
    }
    public double getTotalBelanja() {
        return totalBelanja;
    }
    public String getKode() {
        return kode;
    }
}