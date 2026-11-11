package co.edu.uniquindio;

public class LotePapelCarton {
    //Atributos
    private boolean clasificado;

    //Metodo constructor
    public LotePapelCarton(boolean clasificado) {
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
