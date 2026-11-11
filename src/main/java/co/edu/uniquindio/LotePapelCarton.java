package co.edu.uniquindio;

public class LotePapelCarton extends Lote {
    //Atributos
    private boolean clasificado;

    //Metodo constructor
    public LotePapelCarton(boolean clasificado, String codigo, String descripcion, int pesoKg) {
        super(codigo, descripcion, pesoKg);
        this.clasificado = clasificado;
    }

    //Getters
    public boolean isClasificado() {
        return clasificado;
    }

    //Setters
    public void setClasificado(boolean clasificado) {
        this.clasificado = clasificado;
    }
}
