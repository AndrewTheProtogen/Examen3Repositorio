package co.edu.uniquindio;

import java.time.LocalDate;
import java.util.List;

public class Entrega {
    //Atributos
    private String codigo;
    private LocalDate fecha;
    private EstadoEntrega estado;
    private List<Lote> theListLote;
    private Comprobante comprobante;

    //Metodo constructor
    public Entrega(String codigo, LocalDate fecha, EstadoEntrega estado, Comprobante comprobante){
        this.codigo = codigo;
        this.fecha = fecha;
        this.estado = estado;
        this.comprobante = comprobante;
    }

    //Metodos
    public boolean agregarLote(Lote){

    }
    public boolean aceptar(){

    }
    public int calcularPesoTotal(){

    }
    public long calcularValorTotal(){

    }
    public List<String> obtenerEtiquetas(){

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
