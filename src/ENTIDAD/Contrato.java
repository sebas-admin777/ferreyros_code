package ENTIDAD;
/**** @author Sebastián Huaripaucar Anaya */
public class Contrato {
    private int idContrato;
    private int idCliente;
    private String tipo;
    private String fechaInicio;
    private String fechaFin;

    public Contrato() {}

    public Contrato(int idContrato, int idCliente, String tipo, String fechaInicio, String fechaFin) {
        this.idContrato = idContrato;
        this.idCliente = idCliente;
        this.tipo = tipo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public int getIdContrato() { return idContrato; }
    public void setIdContrato(int idContrato) { this.idContrato = idContrato; }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(String fechaInicio) { this.fechaInicio = fechaInicio; }

    public String getFechaFin() { return fechaFin; }
    public void setFechaFin(String fechaFin) { this.fechaFin = fechaFin; }
}