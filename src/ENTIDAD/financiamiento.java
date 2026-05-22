package ENTIDAD;
/*** @author Aaron culli lazarte*/
public class financiamiento {
    
    private int ID_Financiamiento;
    private String ID_Cliente;
    private String Tipo;
    private double Monto;
    
    public int getID_Financiamiento() {
        return ID_Financiamiento;
    }

    public void setID_Financiamiento(int ID_Financiamiento) {
        this.ID_Financiamiento = ID_Financiamiento;
    }

    public String getID_Cliente() {
        return ID_Cliente;
    }

    public void setID_Cliente(String ID_Cliente) {
        this.ID_Cliente = ID_Cliente;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public double getMonto() {
        return Monto;
    }

    public void setMonto(double Monto) {
        this.Monto = Monto;
    }
}