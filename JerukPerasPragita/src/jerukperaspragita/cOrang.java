package jerukperaspragita;

/**
 *
 * @author EVELIN
 */
public class cOrang {

    public String id;
    public String nama;
    public String password;
    public String email;

    // Constructor
    public cOrang(String id, String nama, String password, String email) {
        this.id = id;
        this.nama = nama;
        this.password = password;
        this.email = email;
    }

    public cOrang(String nama) {
        this.nama = nama;
    }

    // Getter dan Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
