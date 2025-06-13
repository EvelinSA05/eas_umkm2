package jerukperaspragita;

import java.util.ArrayList;

/**
 *
 * @author EVELIN
 */
public class cOwner extends cOrang {

    // Konstruktor tanpa parameter (default)
    public cOwner() {
        super("", "", "", ""); // Memanggil konstruktor parent dengan nilai default
    }

    public cOwner(String id, String nama, String email, String password) { //parameter rized
        super(id, nama, password, email);
    }

    public boolean cocokLogin(String email, String password) {
        return getEmail().equals(email) && getPassword().equals(password);
    }

    //setter
    public void tampilkanInfo() {
        System.out.println("ID Owner         : " + getId());
        System.out.println("Nama Owner       : " + getNama());
        System.out.println("Email Owner      : " + getEmail());
        System.out.println("------------------------------");
    }

}
