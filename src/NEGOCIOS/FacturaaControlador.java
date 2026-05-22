package NEGOCIOS;
import DATOS.FacturaaDAO;
import ENTIDAD.Facturaa;
import java.util.ArrayList;

public class FacturaaControlador {

    FacturaaDAO dao = new FacturaaDAO();

    public ArrayList<Facturaa> listar() {
        return dao.listar();
    }

    public boolean guardar(Facturaa f) {
        return dao.insertar(f);
    }

    public boolean eliminar(String id) {
        return dao.eliminar(id);
    }

    public boolean actualizar(Facturaa f) {
        return dao.actualizar(f);
    }
}