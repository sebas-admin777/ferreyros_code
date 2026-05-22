package ENTIDAD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Empleados {     

    private String codigo;
    private String dni;
    private String nombre;
    private int sueldo; // Mantener como int si así lo prefieres, pero usaremos casting
    
    // Getters y Setters (Mantén los que ya tienes)
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getSueldo() { return sueldo; }
    public void setSueldo(int sueldo) { this.sueldo = sueldo; }
    
    // MÉTODO LISTAR CORREGIDO
    public ArrayList<Empleados> listar() { // Quitamos el String para que coincida con tu interfaz
        ArrayList<Empleados> lista = new ArrayList<>();
        String sql = "SELECT * FROM Empleados"; 
        
        try (Connection cn = DriverManager.getConnection("jdbc:oracle:thin:@//192.168.0.27:1521/XE",
                "system",
                "aaron25");
             PreparedStatement ps = cn.prepareStatement(sql)) {
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Empleados obj = new Empleados();
                obj.setCodigo(rs.getString("codigo")); 
                obj.setDni(rs.getString("dni"));
                obj.setNombre(rs.getString("nombre"));
                // Casting de float (BD) a int (Java)
                obj.setSueldo((int) rs.getFloat("sueldo"));
                lista.add(obj);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar: " + e.getMessage());
        }
        return lista;
    }
}
    

