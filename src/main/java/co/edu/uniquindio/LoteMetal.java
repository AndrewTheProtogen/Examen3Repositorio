package co.edu.uniquindio;

public class LoteMetal extends Lote implements Etiquetable{
    //Atributos
    private TipoMetal tipoMetal;

    //Metodo constructor
    public LoteMetal(TipoMetal tipoMetal, String codigo, String descripcion, int pesoKg) {
        super(codigo, descripcion, pesoKg);
        this.tipoMetal = tipoMetal;
    }
    //Metodos
    public String generarEtiqueta(){
        return "cambiar esto por codigo";
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
