package NEGOCIOS;
import DATOS.EquipoDAO;
import ENTIDAD.Equipo;
import java.sql.Connection;
import java.util.List;

/**** @author Sebastián Huaripaucar Anaya */

public class EquipoService {
    private EquipoDAO dao;

    public EquipoService(Connection con) {
        this.dao = new EquipoDAO(con);
    }

    public void guardar(Equipo e) {
        try { dao.guardar(e); } catch (Exception ex) { ex.printStackTrace(); }
    }

    public void eliminar(int idEquipo) {
        try { dao.eliminar(idEquipo); } catch (Exception ex) { ex.printStackTrace(); }
    }

    public List<Equipo> obtener() {
        try { return dao.listar(); } catch (Exception ex) { ex.printStackTrace(); }
        return null;
    }

    public List<Equipo> obtenerOrdenadosPorIdDesc() {
        try { return dao.listarOrdenadosDesc(); } catch (Exception ex) { ex.printStackTrace(); }
        return null;
    }
}