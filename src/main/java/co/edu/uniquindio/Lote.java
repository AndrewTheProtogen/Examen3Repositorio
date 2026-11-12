package co.edu.uniquindio;

public abstract class Lote {
    //Atributos
    protected String codigoLote;
    protected String descripcion;
    protected int pesoKg;

    //Metodo constructor
    public Lote(String codigoLote, String descripcion, int pesoKg) {
        this.codigoLote = codigoLote;
        this.descripcion = descripcion;
        this.pesoKg = pesoKg;
    }

    //Metodos
    // Metodo abstracto: no tiene cuerpo, cada tipo de lote lo escribe a su manera
    public abstract long calcularValor();

    //Getters
    public String getCodigoLote() {
        return codigoLote;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public int getPesoKg() {
        return pesoKg;
    }

    //Setters
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