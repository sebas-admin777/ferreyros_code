package NEGOCIOS;
import DATOS.AuditoriaDAO;
import ENTIDAD.Auditoria;
import java.sql.SQLException;
import java.util.List;

/**** @author Sebastián Huaripaucar Anaya */

public class AuditoriaService {
    private AuditoriaDAO dao = new AuditoriaDAO();

    public void registrarAuditoria(Auditoria a) throws SQLException {
        dao.insertar(a);
    }

    public void eliminarAuditoria(int idAuditoria) throws SQLException {
        dao.eliminar(idAuditoria);
    }

    public Auditoria buscarAuditoria(int idAuditoria) throws SQLException {
        return dao.buscar(idAuditoria);
    }

    public List<Auditoria> listarAuditorias() throws SQLException {
        return dao.listar();
    }
}
