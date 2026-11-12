package co.edu.uniquindio;

public class LoteResiduoElectronico extends Lote implements Etiquetable {
    private static final long PRECIO_KG = 1400;
    private static final long ADICIONAL_EQUIPO = 800;

    private int equipoCompleto;

    public LoteResiduoElectronico(int equipoCompleto, String codigo, String descripcion, int pesoKg) {
        super(codigo, descripcion, pesoKg);
        this.equipoCompleto = equipoCompleto;
    }

    @Override
    public String generarEtiqueta() {
        return "Lote " + getCodigoLote() + " | Equipos completos: " + equipoCompleto;
    }

    @Override
    public long calcularValor() {
        return getPesoKg() * PRECIO_KG + equipoCompleto * ADICIONAL_EQUIPO;
    }

    public int getEquipoCompleto() {
        return equipoCompleto;
    }

    public void setEquipoCompleto(int equipoCompleto) {
        this.equipoCompleto = equipoCompleto;
    }
}