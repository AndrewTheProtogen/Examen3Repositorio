package co.edu.uniquindio;

public abstract class Lote {
    //Atributos
    protected String codigo;
    protected String descripcion;
    protected int pesoKg;

    //Metodo constructor
    public Lote(String descripcion, String codigo, int pesoKg) {
        this.descripcion = descripcion;
        this.codigo = codigo;
        this.pesoKg = pesoKg;
    }
    //Metodos
    public long calcularValor(){

    }

    //Getters
    public String getCodigo() {
        return codigo;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public int getPesoKg() {
        return pesoKg;
    }

    //Setters
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void setPesoKg(int pesoKg) {
        this.pesoKg = pesoKg;
    }
}
