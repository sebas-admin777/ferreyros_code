package ENTIDAD;
import ENTIDAD.pedido;
import DATOS.OracleHelper;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
public class Pedido_B {
    // 1. LISTADO GENERAL
    public ArrayList<pedido> Listado2() {
        ArrayList<pedido> lista = new ArrayList<>();
        String sql = "SELECT id_pedido, id_cliente, estado, "
                   + "TO_CHAR(fecha, 'DAY', 'NLS_DATE_LANGUAGE=SPANISH') as dia_semana "
                   + "FROM pedido ORDER BY id_pedido ASC";

        try (Connection cn = OracleHelper.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                pedido obj = new pedido();
                obj.setID_Pedido(rs.getString("id_pedido").trim());
                obj.setID_Cliente(rs.getString("id_cliente").trim());
                obj.setEstado(rs.getString("estado").trim());
                obj.setFecha(rs.getString("dia_semana").trim()); 
                lista.add(obj);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en Listado: " + e.getMessage());
        }
        return lista;
    }
    // 2. BUSCAR CON FILTRO
    public List<pedido> BuscarFiltro(String columna, String valor) {
        List<pedido> lista = new ArrayList<>();
        
        try (Connection cn = OracleHelper.getConexion()) {
            String sql;
            if (columna.equalsIgnoreCase("fecha")) {
                sql = "SELECT id_pedido, id_cliente, estado, "
                    + "TO_CHAR(fecha, 'DAY', 'NLS_DATE_LANGUAGE=SPANISH') as dia_semana "
                    + "FROM pedido WHERE TO_CHAR(fecha, 'DAY', 'NLS_DATE_LANGUAGE=SPANISH') LIKE ?";
            } else {
                sql = "SELECT id_pedido, id_cliente, estado, "
                    + "TO_CHAR(fecha, 'DAY', 'NLS_DATE_LANGUAGE=SPANISH') as dia_semana "
                    + "FROM pedido WHERE UPPER(" + columna + ") LIKE ?";
            }
            
            try (PreparedStatement ps = cn.prepareStatement(sql)) {
                ps.setString(1, "%" + valor.toUpperCase().trim() + "%"); 
                ResultSet rs = ps.executeQuery();
                
                while (rs.next()) {
                    pedido obj = new pedido();
                    obj.setID_Pedido(rs.getString("id_pedido").trim());
                    obj.setID_Cliente(rs.getString("id_cliente").trim());
                    obj.setEstado(rs.getString("estado").trim());
                    obj.setFecha(rs.getString("dia_semana").trim());
                    lista.add(obj);
                }
            }
        } catch (Exception e) {
            System.out.println("Error en BuscarFiltro: " + e.getMessage());
        }
        return lista;
    }
    // 3. GRABAR PEDIDO
    public void Graba(pedido obj) {
        try (Connection cn = OracleHelper.getConexion();
             CallableStatement cmd = cn.prepareCall("{call SpGrabarPedido(?,?,?,?,?)}")) {
            cmd.setString(1, obj.getID_Pedido().trim());
            cmd.setString(2, obj.getID_Cliente().trim());
            cmd.setString(3, obj.getFecha().trim()); 
            cmd.setString(4, obj.getEstado().trim());
            cmd.registerOutParameter(5, java.sql.Types.VARCHAR);
            cmd.execute();
            String resultado = cmd.getString(5);
            JOptionPane.showMessageDialog(null, "Resultado: " + resultado);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al Grabar: " + e.getMessage());
        }
    }
    // 4. ELIMINAR PEDIDO
    public void Eliminar(pedido obj) {
        try (Connection cn = OracleHelper.getConexion();
             CallableStatement cmd = cn.prepareCall("{call SpEliminarPedido(?,?)}")) {
            cmd.setString(1, obj.getID_Pedido().trim());
            cmd.registerOutParameter(2, java.sql.Types.VARCHAR);
            cmd.execute();
            String resultado = cmd.getString(2);
            JOptionPane.showMessageDialog(null, "Resultado: " + resultado);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al Eliminar: " + e.getMessage());
        }
    }
    // 5. MODIFICAR PEDIDO
    public void modificar(pedido obj) {
        try (Connection cn = OracleHelper.getConexion();
             CallableStatement cmd = cn.prepareCall("{call SpModificarPedido(?,?,?,?,?)}")) {
            cmd.setString(1, obj.getID_Pedido().trim());
            cmd.setString(2, obj.getID_Cliente().trim());
            cmd.setString(3, obj.getFecha().trim());
            cmd.setString(4, obj.getEstado().trim());
            cmd.registerOutParameter(5, java.sql.Types.VARCHAR);
            cmd.execute();
            String resultado = cmd.getString(5);
            JOptionPane.showMessageDialog(null, "Resultado: " + resultado);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al Modificar: " + e.getMessage());
        }
    }
}