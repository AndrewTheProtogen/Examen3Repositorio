package co.edu.uniquindio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EcoRutaServicio {
    private List<Aportante> theListAportantes;
    private List<Entrega> theListEntrega;

    public EcoRutaServicio(){
        this.theListAportantes = new ArrayList<>();
        this.theListEntrega = new ArrayList<>();
    }

    public boolean registrarAportante(Aportante aportante){
        if (aportante == null) {
            return false;
        }
        if (buscarAportante(aportante.identificacion()) != null) {
            return false;
        }
        theListAportantes.add(aportante);
        return true;
    }

    public boolean registrarEntrega(String codigo, LocalDate fecha, String identificacion/*la id es del aportante*/){
        if (codigo == null || codigo.equals("")) {
            return false;
        }
        if (buscarEntrega(codigo) != null) {
            return false;
        }
        Aportante aportante = buscarAportante(identificacion);
        if (aportante == null) {
            return false;
        }
        theListEntrega.add(new Entrega(codigo, fecha, aportante));
        return true;
    }

    public boolean agregarLote(String codigoEntrega, Lote lote){
        Entrega entrega = buscarEntrega(codigoEntrega);
        if (entrega == null) {
            return false;
        }
        return entrega.agregarLote(lote);
    }

    public boolean aceptarEntrega(String codigoEntrega){
        Entrega entrega = buscarEntrega(codigoEntrega);
        if (entrega == null) {
            return false;
        }
        return entrega.aceptar();
    }

    public Comprobante consultarComprobante(String codigoEntrega){
        Entrega entrega = buscarEntrega(codigoEntrega);
        if (entrega == null) {
            return null;
        }
        return entrega.getComprobante();
    }

    public List<String> consultarEtiquetas(String codigoEntrega){
        Entrega entrega = buscarEntrega(codigoEntrega);
        if (entrega == null) {
            return new ArrayList<>();
        }
        if (entrega.getEstado() != EstadoEntrega.ACEPTADA) {
            return new ArrayList<>();
        }
        return entrega.obtenerEtiquetas();
    }

    public long valorAcumulado(String identificacion){
        long total = 0;
        for (Entrega entrega : theListEntrega) {
            Comprobante c = entrega.getComprobante();
            if (c != null && c.idAportante().equals(identificacion)) {
                total = total + c.valorTotal();
            }
        }
        return total;
    }

    public Entrega buscarEntrega(String codigo){
        for (Entrega entrega : theListEntrega) {
            if (entrega.getCodigo().equals(codigo)) {
                return entrega;
            }
        }
        return null;
    }

    public Aportante buscarAportante(String identificacion){
        for (Aportante aportante : theListAportantes) {
            if (aportante.identificacion().equals(identificacion)) {
                return aportante;
            }
        }
        return null;
    }

    public List<Aportante> getTheListAportantes() {
        return theListAportantes;
    }
    public List<Entrega> getTheListEntrega() {
        return theListEntrega;
    }

    public void setTheListAportantes(List<Aportante> theListAportantes) {
        this.theListAportantes = theListAportantes;
    }
    public void setTheListEntrega(List<Entrega> theListEntrega) {
        this.theListEntrega = theListEntrega;
    }
}