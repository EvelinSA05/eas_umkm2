package jerukperaspragita;

/**
 *
 * @author EVELIN
 */
public class cAdmin extends cOrang {

    private String hakAkses;
    private cKasir kasir;
    private cMinuman minuman;
    private cPembeli pembeli;

    public cAdmin() {  //default konstruktor
        super("", "", "", "");
        this.hakAkses = "";
    }

    public cAdmin(String id, String nama, String email, String password, String ha) { //parameter rized
        super(id, nama, password, email);
        this.hakAkses = ha;
    }

    public boolean cocokLogin(String email, String password) {
        return getEmail().equals(email) && getPassword().equals(password);
    }

    //setter
    public void tampilkanInfo() {
        System.out.println("\nID Admin         : " + getId());
        System.out.println("Nama Admin       : " + getNama());
        System.out.println("Email            : " + getEmail());
        System.out.println("Hak Akses        : " + hakAkses);
    }

    //setter
    public void setKasir(cKasir k) {
        kasir = k;
    }

    public void setMinuman(cMinuman m) {
        minuman = m;
    }

    public void setPembeli(cPembeli p) {
        pembeli = p;
    }

    void setHakAkses(String ha) {
        hakAkses = ha;
    }

    //getter
    public cKasir getKasir() {
        return kasir;
    }

    public cMinuman getMinuman() {
        return minuman;
    }

    public cPembeli getPembeli() {
        return pembeli;
    }

    public void deleteKasir() {
        kasir = null;
    }

    public void deleteMinuman() {
        minuman = null;
    }

    public void deletePembeli() {
        pembeli = null;
    }

    String getHakAkses() {
        return hakAkses;
    }

    //to String
    @Override
    public String toString() {
        return "ID Admin                    : " + id
                + "\nNama Admin                  : " + nama
                + "\nEmail                       : " + email
                + "\nHak Akses                   : " + hakAkses;
    }
}
