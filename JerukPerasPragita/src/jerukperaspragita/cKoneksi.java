package jerukperaspragita;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class cKoneksi {
    
    public static Connection getKoneksi() {
        try {
            // 1. Daftarkan Driver (cara modern)
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // 2. Siapkan detail koneksi
            String url = "jdbc:mysql://localhost:3306/dbjerukperas"; // Pastikan nama database benar
            String user = "root"; // User default XAMPP/Laragon
            String password = ""; // Password default XAMPP/Laragon (kosong)
            
            // 3. Buat Koneksi
            Connection koneksi = DriverManager.getConnection(url, user, password);
            System.out.println("Koneksi ke Database Berhasil.");
            return koneksi;

        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Driver MySQL tidak ditemukan! Pastikan file .jar sudah ditambahkan ke Libraries proyek Anda.");
            System.exit(0); // Keluar dari aplikasi jika driver tidak ada
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Koneksi ke Database Gagal! Pastikan XAMPP/MySQL sudah berjalan dan database 'dbjerukperas' ada.\nError: " + e.getMessage());
            System.exit(0); // Keluar dari aplikasi jika koneksi gagal
        }
        return null; // Seharusnya tidak pernah sampai sini
    }
}