package ENTIDAD;
/**** @author Sebastián Huaripaucar Anaya */
public class Auditoria {
    private int idAuditoria;
    private String ipOrigen;
    private String usuario;
    private String accion;

    // Constructor vacío
    public Auditoria() {}

    // Constructor con parámetros
    public Auditoria(int idAuditoria, String ipOrigen, String usuario, String accion) {
        this.idAuditoria = idAuditoria;
        this.ipOrigen = ipOrigen;
        this.usuario = usuario;
        this.accion = accion;
    }

    // Getters y Setters
    public int getIdAuditoria() {
        return idAuditoria;
    }

    public void setIdAuditoria(int idAuditoria) {
        this.idAuditoria = idAuditoria;
    }

    public String getIpOrigen() {
        return ipOrigen;
    }

    public void setIpOrigen(String ipOrigen) {
        this.ipOrigen = ipOrigen;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
}
