package co.edu.uniquindio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Entrega {
    private String codigo;
    private LocalDate fecha;
    private EstadoEntrega estado;
    private List<Lote> theListLote;
    private Comprobante comprobante;
    private Aportante aportante;

    public Entrega(String codigo, LocalDate fecha, Aportante aportante) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.aportante = aportante;
        this.estado = EstadoEntrega.ABIERTA;
        this.theListLote = new ArrayList<>();
        this.comprobante = null;
    }

    public boolean agregarLote(Lote lote) {
        if (lote == null) {
            return false;
        }
        if (estado != EstadoEntrega.ABIERTA) {
            return false;
        }
        if (lote.getPesoKg() <= 0) {
            return false;
        }
        if (lote.getCodigoLote().equals("")) {
            return false;
        }
        for (Lote existente : theListLote) {
            if (existente.getCodigoLote().equals(lote.getCodigoLote())) {
                return false;   // codigo repetido
            }
        }
        theListLote.add(lote);
        return true;
    }

    public boolean aceptar() {
        if (estado != EstadoEntrega.ABIERTA) {
            return false;
        }
        if (theListLote.size() == 0) {
            return false;
        }
        comprobante = new Comprobante(codigo, aportante.identificacion(),
                calcularPesoTotal(), calcularValorTotal());
        estado = EstadoEntrega.ACEPTADA;
        return true;
    }

    public int calcularPesoTotal() {
        int total = 0;
        for (Lote lote : theListLote) {
            total = total + lote.getPesoKg();
        }
        return total;
    }

    public long calcularValorTotal() {
        long total = 0;
        for (Lote lote : theListLote) {
            total = total + lote.calcularValor();
        }
        return total;
    }

    public List<String> obtenerEtiquetas() {
        List<String> etiquetas = new ArrayList<>();
        for (Lote lote : theListLote) {
            if (lote instanceof Etiquetable) {
                Etiquetable etiquetable = (Etiquetable) lote;
                etiquetas.add(etiquetable.generarEtiqueta());
            }
        }
        return etiquetas;
    }

    public String getCodigo() {
        return codigo;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public EstadoEntrega getEstado() {
        return estado;
    }
    public List<Lote> getTheListLote() {
        return theListLote;
    }
    public Comprobante getComprobante() {
        return comprobante;
    }
    public Aportante getAportante() {
        return aportante;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public void setEstado(EstadoEntrega estado) {
        this.estado = estado;
    }
    public void setTheListLote(List<Lote> theListLote) {
        this.theListLote = theListLote;
    }
}