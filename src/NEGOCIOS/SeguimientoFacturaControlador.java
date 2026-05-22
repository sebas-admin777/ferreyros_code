package NEGOCIOS;
import DATOS.SeguimientoFacturaDAO;
import ENTIDAD.SeguimientoFactura;
import java.util.ArrayList;

public class SeguimientoFacturaControlador {

    // Instancia del DAO para conectar con la base de datos
    SeguimientoFacturaDAO dao = new SeguimientoFacturaDAO();

    // 1. Método para listar todos (Carga la tabla al inicio)
    public ArrayList<SeguimientoFactura> listar() {
        return dao.listarTodos();
    }

    // 2. Método para buscar por ID (Arregla el error de la línea 380 en la vista)
    public ArrayList<SeguimientoFactura> listarPorFactura(String id) {
        return dao.listarBusqueda(id);
    }

    // 3. Método para guardar (Arregla el error de la línea 344 en la vista)
    public boolean guardar(SeguimientoFactura s) {
        return dao.agregar(s);
    }

    // 4. Métodos para llenar los ComboBox de la vista
    public ArrayList<String> listarFacturas() {
        return dao.listarFacturas();
    }

    public ArrayList<String> listarPedidos() {
        return dao.obtenerIds("ID_PEDIDO");
    }

    public ArrayList<String> listarTransacciones() {
        return dao.obtenerIds("ID_TRANSACCION");
    }

    public ArrayList<String> listarEmpleados() {
        return dao.obtenerIds("ID_EMPLEADO");
    }

    // 5. Otros métodos necesarios
    public boolean actualizar(SeguimientoFactura s) {
        return dao.actualizar(s);
    }

    public boolean eliminar(String id) {
        return dao.eliminar(id);
    }
}
