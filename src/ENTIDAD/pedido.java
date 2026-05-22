package ENTIDAD;
public class pedido {

    private String ID_Pedido;     
    private String ID_Cliente;    
    private String Fecha;     
    private String Estado;

    public String getID_Pedido() {
        return ID_Pedido;
    }

    public void setID_Pedido(String ID_Pedido) {
        this.ID_Pedido = ID_Pedido;
    }

    public String getID_Cliente() {
        return ID_Cliente;
    }

    public void setID_Cliente(String ID_Cliente) {
        this.ID_Cliente = ID_Cliente;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }
}       