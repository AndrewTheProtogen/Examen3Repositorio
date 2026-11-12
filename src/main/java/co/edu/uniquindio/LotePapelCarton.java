package co.edu.uniquindio;

public class LotePapelCarton extends Lote {
    //Constantes (precio por kilogramo)
    private static final long PRECIO_KG = 500;
    private static final long ADICIONAL_CLASIFICADO_KG = 100;

    private boolean clasificado;

    public LotePapelCarton(boolean clasificado, String codigo, String descripcion, int pesoKg) {
        super(codigo, descripcion, pesoKg);
        this.clasificado = clasificado;
    }

    @Override
    public long calcularValor() {
        long precio = PRECIO_KG;
        if (clasificado) {
            precio = precio + ADICIONAL_CLASIFICADO_KG;
        }
        return getPesoKg() * precio;
    }

    public boolean isClasificado() {
        return clasificado;
    }

    public void setClasificado(boolean clasificado) {
        this.clasificado = clasificado;
    }
}