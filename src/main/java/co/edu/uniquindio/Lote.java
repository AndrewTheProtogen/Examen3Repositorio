package co.edu.uniquindio;

public abstract class Lote {
    protected String codigoLote;
    protected String descripcion;
    protected int pesoKg;

    public Lote(String codigoLote, String descripcion, int pesoKg) {
        this.codigoLote = codigoLote;
        this.descripcion = descripcion;
        this.pesoKg = pesoKg;
    }

    public abstract long calcularValor();

    public String getCodigoLote() {
        return codigoLote;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public int getPesoKg() {
        return pesoKg;
    }

    public void setCodigoLote(String codigoLote) {
        this.codigoLote = codigoLote;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void setPesoKg(int pesoKg) {
        this.pesoKg = pesoKg;
    }
}