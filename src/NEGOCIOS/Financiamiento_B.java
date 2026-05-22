package NEGOCIOS;
import ENTIDAD.financiamiento;
import DATOS.OracleHelper;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
/* Autor: AARON CULLI LAZARTE - Versión Final Optimizada */
public class Financiamiento_B {
// 1. LISTADO GENERAL
    public ArrayList<financiamiento> Listado() {
        ArrayList<financiamiento> lista = new ArrayList<>();
        String sql = "SELECT id_financiamiento, id_cliente, tipo, monto FROM financiamiento ORDER BY id_financiamiento ASC";

        try (Connection cn = OracleHelper.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                financiamiento fac = new financiamiento();
                fac.setID_Financiamiento(rs.getInt("id_financiamiento"));
                fac.setID_Cliente(rs.getString("id_cliente").trim());
                fac.setTipo(rs.getString("tipo").trim());
                fac.setMonto(rs.getDouble("monto"));
                lista.add(fac);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en Listado Financiamiento: " + e.getMessage());
        }
        return lista;
    }
    // 2. BUSCAR CON FILTRO
    public List<financiamiento> BuscarFiltro(String columna, String valor) {
        List<financiamiento> lista = new ArrayList<>();
        String sql = "SELECT * FROM financiamiento WHERE UPPER(CAST(" + columna + " AS VARCHAR2(100))) LIKE ? "
                   + "ORDER BY id_financiamiento ASC";

        try (Connection cn = OracleHelper.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, "%" + valor.toUpperCase().trim() + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                financiamiento obj = new financiamiento();
                obj.setID_Financiamiento(rs.getInt("id_financiamiento"));
                obj.setID_Cliente(rs.getString("id_cliente").trim());
                obj.setTipo(rs.getString("tipo").trim());
                obj.setMonto(rs.getDouble("monto"));
                lista.add(obj);
            }
        } catch (Exception e) {
            System.out.println("Error en BuscarFiltro Financiamiento: " + e.getMessage());
        }
        return lista;
    }
    // 3. GRABAR FINANCIAMIENTO
    public void Graba(financiamiento obj) {
        try (Connection cn = OracleHelper.getConexion();
             CallableStatement cmd = cn.prepareCall("{call SpGrabarFinanciamiento(?,?,?,?,?)}")) {
            cmd.setInt(1, obj.getID_Financiamiento());
            cmd.setString(2, obj.getID_Cliente().trim());
            cmd.setString(3, obj.getTipo().trim());
            cmd.setDouble(4, obj.getMonto());
            cmd.registerOutParameter(5, java.sql.Types.VARCHAR);
            cmd.execute();
            String resultado = cmd.getString(5);
            JOptionPane.showMessageDialog(null, "Registro Guardado: " + resultado);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al grabar financiamiento: " + e.getMessage());
        }
    }
    // 4. ELIMINAR FINANCIAMIENTO
    public void Eliminar(financiamiento obj) {
        try (Connection cn = OracleHelper.getConexion();
             CallableStatement cmd = cn.prepareCall("{call SpEliminarFinanciamiento(?,?)}")) {           
            cmd.setInt(1, obj.getID_Financiamiento());
            cmd.registerOutParameter(2, java.sql.Types.VARCHAR);
            cmd.execute();
            String resultado = cmd.getString(2);
            JOptionPane.showMessageDialog(null, "Registro Eliminado: " + resultado);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar financiamiento: " + e.getMessage());
        }
    }
    // 5. MODIFICAR FINANCIAMIENTO
    public void modificar(financiamiento obj) {
        try (Connection cn = OracleHelper.getConexion();
             CallableStatement cmd = cn.prepareCall("{call SpModificarFinanciamiento(?,?,?,?,?)}")) {
            cmd.setInt(1, obj.getID_Financiamiento());
            cmd.setString(2, obj.getID_Cliente().trim());
            cmd.setString(3, obj.getTipo().trim());
            cmd.setDouble(4, obj.getMonto());
            cmd.registerOutParameter(5, java.sql.Types.VARCHAR);
            cmd.execute();
            String resultado = cmd.getString(5);
            JOptionPane.showMessageDialog(null, "Registro Modificado: " + resultado);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al modificar financiamiento: " + e.getMessage());
        }
    }
}