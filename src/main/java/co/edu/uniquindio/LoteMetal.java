package co.edu.uniquindio;

public class LoteMetal {
    //Atributos
    private TipoMetal tipoMetal;

    //Metodo constructor
    public LoteMetal(TipoMetal tipoMetal) {
        this.tipoMetal = tipoMetal;
    }

    //Getters
    public TipoMetal getTipoMetal() {
        return tipoMetal;
    }

    //Setters
    public void setTipoMetal(TipoMetal tipoMetal) {
        this.tipoMetal = tipoMetal;
    }
}
