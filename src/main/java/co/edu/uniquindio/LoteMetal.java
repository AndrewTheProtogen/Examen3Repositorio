package co.edu.uniquindio;

public class LoteMetal extends Lote implements Etiquetable {
    private static final long PRECIO_ALUMINIO_KG = 2200;
    private static final long PRECIO_ACERO_KG = 900;

    private TipoMetal tipoMetal;


    public LoteMetal(TipoMetal tipoMetal, String codigo, String descripcion, int pesoKg) {
        super(codigo, descripcion, pesoKg);
        this.tipoMetal = tipoMetal;
    }

    @Override
    public String generarEtiqueta() {
        return "Lote " + getCodigoLote() + " | Metal: " + tipoMetal;
    }

    @Override
    public long calcularValor() {
        if (tipoMetal == TipoMetal.ALUMINIO) {
            return getPesoKg() * PRECIO_ALUMINIO_KG;
        } else {
            return getPesoKg() * PRECIO_ACERO_KG;
        }
    }

    public TipoMetal getTipoMetal() {
        return tipoMetal;
    }

    public void setTipoMetal(TipoMetal tipoMetal) {
        this.tipoMetal = tipoMetal;
    }
}