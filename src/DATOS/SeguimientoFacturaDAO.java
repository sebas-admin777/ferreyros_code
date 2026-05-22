package DATOS;
import java.sql.*;
import java.util.ArrayList;
import ENTIDAD.SeguimientoFactura;
import DATOS.OracleHelper;
public class SeguimientoFacturaDAO {
    OracleHelper conectar = new OracleHelper();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    // 1. MÉTODO PARA LISTAR TODO (6 columnas)
    public ArrayList<SeguimientoFactura> listarTodos() {
        ArrayList<SeguimientoFactura> lista = new ArrayList<>();
        String sql = "SELECT * FROM SEGUIMIENTO_FACTURA";
        try {
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                SeguimientoFactura sf = new SeguimientoFactura();
                sf.setIdFactura(rs.getString(1));
                sf.setIdPedido(rs.getString(2));
                sf.setIdTransaccion(rs.getString(3));
                sf.setIdEmpleado(rs.getString(4));
                sf.setEstado(rs.getString(5));
                sf.setSucursal(rs.getString(6));
                lista.add(sf);
            }
        } catch (SQLException e) {
            System.err.println("Error listarTodos: " + e.getMessage());
        }
        return lista;
    }
    // 2. MÉTODO PARA AGREGAR (INSERT)
    public boolean agregar(SeguimientoFactura s) {
        String sql = "INSERT INTO SEGUIMIENTO_FACTURA (ID_FACTURA, ID_PEDIDO, ID_TRANSACCION, ID_EMPLEADO, ESTADO, SUCURSAL) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, s.getIdFactura());
            ps.setString(2, s.getIdPedido());
            ps.setString(3, s.getIdTransaccion());
            ps.setString(4, s.getIdEmpleado());
            ps.setString(5, s.getEstado());
            ps.setString(6, s.getSucursal());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error agregar: " + e.getMessage());
            return false;
        }
    }
    // 3. MÉTODO PARA ACTUALIZAR (UPDATE)
    public boolean actualizar(SeguimientoFactura s) {
        String sql = "UPDATE SEGUIMIENTO_FACTURA SET ID_PEDIDO=?, ID_TRANSACCION=?, ID_EMPLEADO=?, ESTADO=?, SUCURSAL=? WHERE ID_FACTURA=?";
        try {
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, s.getIdPedido());
            ps.setString(2, s.getIdTransaccion());
            ps.setString(3, s.getIdEmpleado());
            ps.setString(4, s.getEstado());
            ps.setString(5, s.getSucursal());
            ps.setString(6, s.getIdFactura());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error actualizar: " + e.getMessage());
            return false;
        }
    }
    // 4. MÉTODO PARA ELIMINAR (DELETE)
    public boolean eliminar(String idFactura) {
        String sql = "DELETE FROM SEGUIMIENTO_FACTURA WHERE ID_FACTURA = ?";
        try {
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, idFactura);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error eliminar: " + e.getMessage());
            return false;
        }
    }
    // 5. MÉTODO PARA BUSCAR POR ID (FILTRAR)
    public ArrayList<SeguimientoFactura> listarBusqueda(String idFactura) {
        ArrayList<SeguimientoFactura> lista = new ArrayList<>();
        String sql = "SELECT * FROM SEGUIMIENTO_FACTURA WHERE ID_FACTURA = ?";
        try {
            con = conectar.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, idFactura);
            rs = ps.executeQuery();
            while (rs.next()) {
                SeguimientoFactura sf = new SeguimientoFactura();
                sf.setIdFactura(rs.getString(1));
                sf.setIdPedido(rs.getString(2));
                sf.setIdTransaccion(rs.getString(3));
                sf.setIdEmpleado(rs.getString(4));
                sf.setEstado(rs.getString(5));
                sf.setSucursal(rs.getString(6));
                lista.add(sf);
            }
        } catch (SQLException e) {
            System.err.println("Error búsqueda: " + e.getMessage());
        }
        return lista;
    }
    // 6. MÉTODO GENÉRICO PARA COMBOS (Sustituye los UnsupportedOperation)
    public ArrayList<String> obtenerIds(String nombreColumna) {
        ArrayList<String> lista = new ArrayList<>();
        String sql = "SELECT DISTINCT " + nombreColumna + " FROM SEGUIMIENTO_FACTURA";
        try {
            con = conectar.getConexion();
            if (con != null) {
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (rs.getString(1) != null) {
                        lista.add(rs.getString(1));
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error obtenerIds: " + e.getMessage());
        }
        return lista;
    }
    // 7. MÉTODO ESPECÍFICO PARA COMBO FACTURAS (Corregido de int[] a ArrayList)
    public ArrayList<String> listarFacturas() {
        return obtenerIds("ID_FACTURA");
    }    
}
