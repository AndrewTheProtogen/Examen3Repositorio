package co.edu.uniquindio;

public class LoteResiduoElectronico extends Lote implements Etiquetable{
    //Atributos
    private int equipoCompleto;

    //Metodo constructor
    public LoteResiduoElectronico(int equipoCompleto, String codigo, String descripcion, int pesoKg) {
        super(codigo, descripcion, pesoKg);
        this.equipoCompleto = equipoCompleto;
    }
    //Metodos
    public String generarEtiqueta(){
        return "cambiar esto por codigo";
    }

    //Getters
    public int getEquipoCompleto() {
        return equipoCompleto;
    }

    //Setters
    public void setEquipoCompleto(int equipoCompleto) {
        this.equipoCompleto = equipoCompleto;
    }
}