package ENTIDAD;
/**** @author Sebastián Huaripaucar Anaya */
public class Equipo {
    private int idSucursal;
    private int idEquipo;
    private String serie;
    private String modelo;
    private String estado;
    
    public Equipo(int idSucursal, int idEquipo, String serie, String modelo, String estado) {
        this.idSucursal = idSucursal;
        this.idEquipo = idEquipo;
        this.serie = serie;
        this.modelo = modelo;
        this.estado = estado;
    }

    // Getters y Setters
    public int getIdSucursal() { return idSucursal; }
    public void setIdSucursal(int idSucursal) { this.idSucursal = idSucursal; }

    public int getIdEquipo() { return idEquipo; }
    public void setIdEquipo(int idEquipo) { this.idEquipo = idEquipo; }

    public String getSerie() { return serie; }
    public void setSerie(String serie) { this.serie = serie; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}