package NEGOCIOS;
import ENTIDAD.login;
import DATOS.OracleHelper; // Asegúrate de que el nombre sea exacto (minúscula)
import java.sql.*;
import javax.swing.JOptionPane;

/** * @author Daniel Huaman Corregido */
public class login_B {
    // Quitamos la variable 'cn' de aquí arriba para evitar que sea null
    OracleHelper cc = new OracleHelper();
    public String login(login obj) {
        String resultado = "";
        // CORRECCIÓN: Pedimos la conexión JUSTO AQUÍ, dentro del método
        try (Connection cn = cc.getConexion()) { 
            
            if (cn == null) {
                return "Error: No se pudo establecer conexión con Oracle.";
            }

            try (CallableStatement cmd = cn.prepareCall("{call sp_login(?,?,?)}")) {
                cmd.setString(1, obj.getLogin());
                cmd.setString(2, obj.getClave());
                cmd.registerOutParameter(3, java.sql.Types.VARCHAR);
                
                cmd.execute();
                resultado = cmd.getString(3);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error General: " + e.getMessage());
        }
        
        return resultado;
    }
}