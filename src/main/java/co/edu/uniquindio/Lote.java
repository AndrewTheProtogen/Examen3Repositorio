package co.edu.uniquindio;

public abstract class Lote {
    //Atributos
    protected String codigoLote;
    protected String descripcion;
    protected int pesoKg;

    //Metodo constructor
    public Lote(String descripcion, String codigoLote, int pesoKg) {
        this.descripcion = descripcion;
        this.codigoLote = codigoLote;
        this.pesoKg = pesoKg;
    }
    //Metodos
    public long calcularValor(){

    }

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
