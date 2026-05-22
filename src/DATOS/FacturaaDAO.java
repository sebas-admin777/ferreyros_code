package DATOS;
import DATOS.OracleHelper;
import ENTIDAD.Facturaa;
import java.sql.*;
import java.util.ArrayList;
public class FacturaaDAO {
    public ArrayList<Facturaa> listar() {
        ArrayList<Facturaa> lista = new ArrayList<>();
        try {
            Connection cn = OracleHelper.getConexion();
            String sql = "SELECT * FROM FACTURA";

            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Facturaa f = new Facturaa();
                f.setId(rs.getString("ID"));
                f.setCliente(rs.getString("CLIENTE"));
                f.setMonto(rs.getDouble("MONTO"));
                lista.add(f);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public boolean insertar(Facturaa f) {
        try {
            Connection cn = OracleHelper.getConexion();
            String sql = "INSERT INTO FACTURA VALUES (?, ?, ?)";

            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, f.getId());
            ps.setString(2, f.getCliente());
            ps.setDouble(3, f.getMonto());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            return false;
        }
    }

    public boolean eliminar(String id) {
        try {
            Connection cn = OracleHelper.getConexion();
            String sql = "DELETE FROM FACTURA WHERE ID=?";

            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, id);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            return false;
        }
    }

    public boolean actualizar(Facturaa f) {
        try {
            Connection cn = OracleHelper.getConexion();
            String sql = "UPDATE FACTURA SET CLIENTE=?, MONTO=? WHERE ID=?";

            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, f.getCliente());
            ps.setDouble(2, f.getMonto());
            ps.setString(3, f.getId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            return false;
        }
    }
}
