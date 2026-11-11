package co.edu.uniquindio;

import java.time.LocalDate;

public class Entrega {
    //Atributos
    private String codigo;
    private LocalDate fecha;
    private EstadoEntrega estado;
    private Lote[] theListLote = new Lote[10];
    private Comprobante comprobante;

    //Metodo constructor
    public Entrega(String codigo, LocalDate fecha, EstadoEntrega estado, Comprobante comprobante){
        this.codigo = codigo;
        this.fecha = fecha;
        this.estado = estado;
        this.comprobante = comprobante;
    }

    //Getters
    public String getCodigo() {
        return codigo;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public EstadoEntrega getEstado() {
        return estado;
    }
    public Lote[] getTheListLote() {
        return theListLote;
    }

    //Setters
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public void setEstado(EstadoEntrega estado) {
        this.estado = estado;
    }
    public void setTheListLote(Lote[] theListLote) {
        this.theListLote = theListLote;
    }
}
