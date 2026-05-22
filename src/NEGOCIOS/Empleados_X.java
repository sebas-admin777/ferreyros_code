package NEGOCIOS;
import DATOS.OracleHelper;
import ENTIDAD.Empleados;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.CallableStatement;
public class Empleados_X {
    OracleHelper cc=new OracleHelper();
    Connection cn=cc.getConexion();
    
    public ArrayList<Empleados> Listado(){
    ArrayList<Empleados> lista = new ArrayList<>();
    try {
        PreparedStatement cmd;
        cmd = cn.prepareStatement("SELECT * FROM Empleados_X ORDER BY id"); // ✅
        ResultSet tabla = cmd.executeQuery();
        Empleados fac;
        while(tabla.next()){
            fac = new Empleados();
            fac.setCodigo(tabla.getString("codigo"));
            fac.setDni(tabla.getString("dni"));
            fac.setNombre(tabla.getString("nombre"));
            fac.setSueldo(tabla.getInt("sueldo"));
            lista.add(fac);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
    return lista;
}
    //funcion grabar
    
    public void Graba(Empleados obj){
        CallableStatement cmd=null;
        try {
            cmd=cn.prepareCall("{call SpGrabarMantenimiento(?,?,?,?,?)}");
            cmd.setString(1, obj.getCodigo());
            cmd.setString(2, obj.getDni());
            cmd.setString(3, obj.getNombre());
            cmd.setInt(4, obj.getSueldo());
            
            cmd.registerOutParameter(5, java.sql.Types.VARCHAR);
            
            cmd.execute();
            
            String resultado = cmd.getString(5);
            JOptionPane.showMessageDialog(null,
                    "IMFORMACION GUARDADA" + resultado);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "ERROR DE SISTEMA" + e.getMessage());
        } finally{
            try {
                if(cmd!=null)cmd.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
   
    
      public void Eliminar(Empleados obj){
        CallableStatement cmd=null;
        try {
            cmd=cn.prepareCall("{call SpEliminarMantenimiento(?,?)}");
            cmd.setString(1, obj.getCodigo());
   
            
            cmd.registerOutParameter(2, java.sql.Types.VARCHAR);
            
            cmd.execute();
            
            String resultado = cmd.getString(2);
            JOptionPane.showMessageDialog(null,
                    "ELIMINACION COMPLETA" + resultado);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "ERROR DE SISTEMA" + e.getMessage());
        } finally{
            try {
                if(cmd!=null)cmd.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
   
      
       public void modificar(Empleados obj){
        CallableStatement cmd=null;
        try {
            cmd=cn.prepareCall("{call SpModificarMantenimiento(?,?,?,?,?)}");
            cmd.setString(1, obj.getCodigo());
            cmd.setString(2, obj.getDni());
            cmd.setString(3, obj.getNombre());
            cmd.setInt(4, obj.getSueldo());
            
            cmd.registerOutParameter(5, java.sql.Types.VARCHAR);
            
            cmd.execute();
            
            String resultado = cmd.getString(5);
            JOptionPane.showMessageDialog(null,
                    "MODIFICACION EXITOSA" + resultado);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "ERROR DE SISTEMA" + e.getMessage());
        } finally{
            try {
                if(cmd!=null)cmd.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

