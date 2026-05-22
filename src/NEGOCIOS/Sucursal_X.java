package NEGOCIOS;
import ENTIDAD.Sucursal;
import DATOS.OracleHelper;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.CallableStatement;
public class Sucursal_X {
    OracleHelper cc=new OracleHelper();
    Connection cn=cc.getConexion();
    
    public ArrayList<Sucursal> Listado(){
        ArrayList<Sucursal> lista=new ArrayList<>();
        try {
            PreparedStatement cmd;
            cmd=cn.prepareCall("SELECT * from Sucursal_X");
            ResultSet tabla = cmd.executeQuery();
            Sucursal fac;
            while(tabla.next()){
                fac=new Sucursal();
                fac.setCodigo(tabla.getString("codigo"));
                fac.setDni(tabla.getString("dni"));
                fac.setNombre(tabla.getString("nombre"));
                fac.setTelefono(tabla.getInt("telefono"));
                lista.add(fac);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    e.getMessage());
        }
        return lista;
    }
    
    
    public void Insertar(Sucursal obj){
        CallableStatement cmd=null;
        try {
            cmd=cn.prepareCall("{call SpGrabarSucursal(?,?,?,?,?)}");
            cmd.setString(1, obj.getCodigo());
            cmd.setString(2, obj.getDni());
            cmd.setString(3, obj.getNombre());
            cmd.setInt(4, obj.getTelefono());
            
            cmd.registerOutParameter(5, java.sql.Types.VARCHAR);
            
            cmd.execute();
            
            String resultado = cmd.getString(5);
            JOptionPane.showMessageDialog(null,
                    "GUARDADO" + resultado);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Error " + e.getMessage());
        } finally{
            try {
                if(cmd!=null)cmd.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    
      public void Desechar(Sucursal obj){
        CallableStatement cmd=null;
        try {
            cmd=cn.prepareCall("{call SpEliminarSucursal(?,?)}");
            cmd.setString(1, obj.getCodigo());
   
            
            cmd.registerOutParameter(2, java.sql.Types.VARCHAR);
            
            cmd.execute();
            
            String resultado = cmd.getString(2);
            JOptionPane.showMessageDialog(null,
                    "DESECHADO EXITOSAMENTE" + resultado);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Error " + e.getMessage());
        } finally{
            try {
                if(cmd!=null)cmd.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
      
       public void Editar(Sucursal obj){
        CallableStatement cmd=null;
        try {
            cmd=cn.prepareCall("{call SpModificarSucursal(?,?,?,?,?)}");
            cmd.setString(1, obj.getCodigo());
            cmd.setString(2, obj.getDni());
            cmd.setString(3, obj.getNombre());
            cmd.setInt(4, obj.getTelefono());
            
            cmd.registerOutParameter(5, java.sql.Types.VARCHAR);
            
            cmd.execute();
            
            String resultado = cmd.getString(5);
            JOptionPane.showMessageDialog(null,
                    "EDITADO EXITOSAMENTE" + resultado);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Error " + e.getMessage());
        } finally{
            try {
                if(cmd!=null)cmd.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }    
}
