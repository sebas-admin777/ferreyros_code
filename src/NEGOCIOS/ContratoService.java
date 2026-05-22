package NEGOCIOS;
import DATOS.ContratoDAO;
import ENTIDAD.Contrato;
import java.util.List;

/**** @author Sebastián Huaripaucar Anaya */

public class ContratoService {
    public ContratoDAO dao = new ContratoDAO();

    public boolean guardar(Contrato c) {
        return dao.insertar(c);
    }

    public List<Contrato> listar() {
        return dao.listar();
    }

    public boolean eliminar(int id) {
        return dao.eliminar(id);
    }

    public boolean actualizar(Contrato c) {
        return dao.actualizar(c);
    }

    public Contrato buscar(int id) {
        return dao.buscar(id);
    }

    public List<Contrato> obtener() {
        return dao.listar();
    }
    
    public List<Contrato> listarPorCliente(int idCliente) {
        return dao.listarPorCliente(idCliente);
    }
}
