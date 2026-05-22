package DATOS;
import ENTIDAD.Equipo;
import java.sql.*;
import java.util.*;

/**** @author Sebastián Huaripaucar Anaya */

public class EquipoDAO {
    private Connection con;

    public EquipoDAO(Connection con) {
        this.con = con;
    }

    public void guardar(Equipo e) throws SQLException {
        String sql = "INSERT INTO EQUIPO (ID_SUCURSAL, ID_EQUIPO, SERIE, MODELO, ESTADO) VALUES (?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, e.getIdSucursal());
        ps.setInt(2, e.getIdEquipo());
        ps.setString(3, e.getSerie());
        ps.setString(4, e.getModelo());
        ps.setString(5, e.getEstado());
        ps.executeUpdate();
    }

    public void eliminar(int idEquipo) throws SQLException {
        String sql = "DELETE FROM EQUIPO WHERE ID_EQUIPO=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idEquipo);
        ps.executeUpdate();
    }

    public List<Equipo> listar() throws SQLException {
        List<Equipo> lista = new ArrayList<>();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM EQUIPO");
        while (rs.next()) {
            lista.add(new Equipo(
                rs.getInt("ID_SUCURSAL"),
                rs.getInt("ID_EQUIPO"),
                rs.getString("SERIE"),
                rs.getString("MODELO"),
                rs.getString("ESTADO")
            ));
        }
        return lista;
    }

    public List<Equipo> listarOrdenadosDesc() throws SQLException {
        List<Equipo> lista = new ArrayList<>();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM EQUIPO ORDER BY ID_EQUIPO DESC");
        while (rs.next()) {
            lista.add(new Equipo(
                rs.getInt("ID_SUCURSAL"),
                rs.getInt("ID_EQUIPO"),
                rs.getString("SERIE"),
                rs.getString("MODELO"),
                rs.getString("ESTADO")
            ));
        }
        return lista;
    }
}