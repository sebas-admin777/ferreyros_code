package DATOS;
import ENTIDAD.Contrato;
import DATOS.OracleHelper;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContratoDAO {
    OracleHelper con = new OracleHelper();
    Connection cn = con.getConexion();

    // MÉTODO LISTAR GENERAL (Faltaba este para que el Service no falle)
    public List<Contrato> listar() {
        List<Contrato> lista = new ArrayList<>();
        String sql = "SELECT * FROM CONTRATO";
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Contrato c = new Contrato();
                c.setIdContrato(rs.getInt(1));
                c.setIdCliente(rs.getInt(2));
                c.setTipo(rs.getString(3));
                c.setFechaInicio(rs.getString(4));
                c.setFechaFin(rs.getString(5));
                lista.add(c);
            }
        } catch (Exception e) {
            System.err.println("Error al listar: " + e.getMessage());
        }
        return lista;
    }

    // LISTAR POR CLIENTE (Corregido el uso de la conexión)
    public List<Contrato> listarPorCliente(int idCliente) {
        List<Contrato> lista = new ArrayList<>();
        String sql = "SELECT * FROM CONTRATO WHERE ID_CLIENTE = ?"; 
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, idCliente);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Contrato c = new Contrato();
                c.setIdContrato(rs.getInt("ID_CONTRATO"));
                c.setIdCliente(rs.getInt("ID_CLIENTE"));
                c.setTipo(rs.getString("TIPO"));
                c.setFechaInicio(rs.getString("FECHA_INICIO"));
                c.setFechaFin(rs.getString("FECHA_FIN"));
                lista.add(c);
            }
        } catch (SQLException e) {
            System.err.println("Error al filtrar: " + e.getMessage());
        }
        return lista;
    }

    // INSERTAR
    public boolean insertar(Contrato c) {
        String sql = "INSERT INTO CONTRATO VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, c.getIdContrato());
            ps.setInt(2, c.getIdCliente());
            ps.setString(3, c.getTipo());
            ps.setString(4, c.getFechaInicio());
            ps.setString(5, c.getFechaFin());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    // ELIMINAR (Asegúrate que la columna sea ID_CONTRATO)
    public boolean eliminar(int id) {
        String sql = "DELETE FROM CONTRATO WHERE ID_CONTRATO=?";
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    // BUSCAR
    public Contrato buscar(int id) {
        String sql = "SELECT * FROM CONTRATO WHERE ID_CONTRATO=?";
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Contrato(
                    rs.getInt(1), rs.getInt(2), rs.getString(3),
                    rs.getString(4), rs.getString(5)
                );
            }
        } catch (Exception e) {}
        return null;
    }

    // ACTUALIZAR
    public boolean actualizar(Contrato c) {
        String sql = "UPDATE CONTRATO SET ID_CLIENTE=?, TIPO=?, FECHA_INICIO=?, FECHA_FIN=? WHERE ID_CONTRATO=?";
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, c.getIdCliente());
            ps.setString(2, c.getTipo());
            ps.setString(3, c.getFechaInicio());
            ps.setString(4, c.getFechaFin());
            ps.setInt(5, c.getIdContrato());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            return false;
        }
    }
}