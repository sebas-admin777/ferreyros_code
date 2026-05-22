package DATOS;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class OracleHelper {
    // EL CAMBIO CLAVE: Agregar "static"
    public static  Connection getConexion() { 
        Connection conect = null;
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            conect = DriverManager.getConnection(
                "jdbc:oracle:thin:@//192.168.0.27:1521/XE",
                "system",
                "aaron25"
            );
        } catch (Exception e) {
            System.out.println("Error de conexion: " + e.getMessage());
        }
        return conect;
    }
}