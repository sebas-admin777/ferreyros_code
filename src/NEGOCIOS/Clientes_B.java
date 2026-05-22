package NEGOCIOS;
import ENTIDAD.clientes;
import DATOS.OracleHelper;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
/** * @autor AARON CULLI LAZARTE */
public class Clientes_B {

    // 1. LISTADO GENERAL
    public ArrayList<clientes> Listado2() {
        ArrayList<clientes> lista = new ArrayList<>();
        String sql = "SELECT id_clientes, nombre, ruc, direccion, telefono FROM clientes ORDER BY id_clientes ASC";

        try (Connection cn = OracleHelper.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                clientes obj = new clientes();
                obj.setID_Clientes(rs.getString("id_clientes").trim());
                obj.setNombre(rs.getString("nombre").trim());
                obj.setRuc(rs.getString("ruc").trim());
                obj.setDireccion(rs.getString("direccion").trim());
                obj.setTelefono(rs.getString("telefono").trim());
                lista.add(obj);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en Listado Clientes: " + e.getMessage());
        }
        return lista;
    }

    // 2. BUSCAR CON FILTRO
    public List<clientes> BuscarFiltro(String columna, String valor) {
        List<clientes> lista = new ArrayList<>();
        String sql = "SELECT * FROM clientes WHERE UPPER(" + columna + ") LIKE ?";

        try (Connection cn = OracleHelper.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, "%" + valor.toUpperCase().trim() + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                clientes obj = new clientes();
                obj.setID_Clientes(rs.getString("id_clientes").trim());
                obj.setNombre(rs.getString("nombre").trim());
                obj.setRuc(rs.getString("ruc").trim());
                obj.setDireccion(rs.getString("direccion").trim());
                obj.setTelefono(rs.getString("telefono").trim());
                lista.add(obj);
            }
        } catch (Exception e) {
            System.out.println("Error en BuscarFiltro Clientes: " + e.getMessage());
        }
        return lista;
    }

    // 3. GRABAR CLIENTE
    public void Graba(clientes obj) {
        try (Connection cn = OracleHelper.getConexion();
             CallableStatement cmd = cn.prepareCall("{call SPGRABARCLIENTES(?,?,?,?,?)}")) {

            cmd.setString(1, obj.getID_Clientes().trim());
            cmd.setString(2, obj.getNombre().trim());
            cmd.setString(3, obj.getRuc().trim());
            cmd.setString(4, obj.getDireccion().trim());
            cmd.setString(5, obj.getTelefono().trim());

            if (cmd.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "¡Cliente registrado con éxito!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al Grabar Cliente: " + e.getMessage());
        }
    }

    // 4. ELIMINAR CLIENTE
    public void Eliminar(clientes obj) {
        String sql = "DELETE FROM clientes WHERE id_clientes = ?";
        try (Connection cn = OracleHelper.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, obj.getID_Clientes());
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "¡Cliente eliminado con éxito!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al Eliminar Cliente: " + e.getMessage());
        }
    }

    // 5. MODIFICAR CLIENTE
    public void modificar(clientes obj) {
        String sql = "UPDATE clientes SET nombre=?, ruc=?, direccion=?, telefono=? WHERE id_clientes=?";
        try (Connection cn = OracleHelper.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, obj.getNombre());
            ps.setString(2, obj.getRuc());
            ps.setString(3, obj.getDireccion());
            ps.setString(4, obj.getTelefono());
            ps.setString(5, obj.getID_Clientes());
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "¡Cliente modificado con éxito!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al Modificar Cliente: " + e.getMessage());
        }
    }
}