package DATOS;
import ENTIDAD.Auditoria;
import DATOS.OracleHelper;
import java.sql.*;
import java.util.*;

/**** @author Sebastián Huaripaucar Anaya */

public class AuditoriaDAO {
    public void insertar(Auditoria a) throws SQLException {
        Connection con = OracleHelper.getConexion();
        String sql = "INSERT INTO AUDITORIA (ID_AUDITORIA, IP_ORIGEN, USUARIO, ACCION) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, a.getIdAuditoria());
        ps.setString(2, a.getIpOrigen());
        ps.setString(3, a.getUsuario());
        ps.setString(4, a.getAccion());
        ps.executeUpdate();
        ps.close();
        con.close();
    }

    public void eliminar(int idAuditoria) throws SQLException {
        Connection con = OracleHelper.getConexion();
        String sql = "DELETE FROM AUDITORIA WHERE ID_AUDITORIA = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idAuditoria);
        ps.executeUpdate();
        ps.close();
        con.close();
    }

    public Auditoria buscar(int idAuditoria) throws SQLException {
        Connection con = OracleHelper.getConexion();
        String sql = "SELECT * FROM AUDITORIA WHERE ID_AUDITORIA = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idAuditoria);
        ResultSet rs = ps.executeQuery();
        Auditoria a = null;
        if (rs.next()) {
            a = new Auditoria(
                rs.getInt("ID_AUDITORIA"),
                rs.getString("IP_ORIGEN"),
                rs.getString("USUARIO"),
                rs.getString("ACCION")
            );
        }
        rs.close();
        ps.close();
        con.close();
        return a;
    }

    public List<Auditoria> listar() throws SQLException {
        Connection con = OracleHelper.getConexion();
        String sql = "SELECT * FROM AUDITORIA";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        List<Auditoria> lista = new ArrayList<>();
        while (rs.next()) {
            Auditoria a = new Auditoria(
                rs.getInt("ID_AUDITORIA"),
                rs.getString("IP_ORIGEN"),
                rs.getString("USUARIO"),
                rs.getString("ACCION")
            );
            lista.add(a);
        }
        rs.close();
        st.close();
        con.close();
        return lista;
    }
}
