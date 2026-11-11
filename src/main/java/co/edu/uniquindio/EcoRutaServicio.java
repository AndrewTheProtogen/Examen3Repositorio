package co.edu.uniquindio;

import java.util.ArrayList;
import java.util.List;

public class EcoRutaServicio {
    //Atributos
    private List<Aportante> theListAportantes;
    private List<Entrega> theListEntrega;

    //Metodo constructor
    public EcoRutaServicio(){
        this.theListAportantes = new ArrayList<>();
        this.theListEntrega = new ArrayList<>();
    }

    //Metodos

    public boolean registrarAportante(Aportante){

    }
    public boolean registrarEntrega(codigo, fecha, identificacion/*la id es del aportante*/){

    }
    public boolean agregarLote(codigoEntrega, theListLote){

    }
    public boolean aceptarEntrega(codigoEntrega){

    }
    public Comprobante consultarComprobante(codigoEntrega){

    }
    public List<String> consultarEtiquetas(codigoEntrega){

    }
    public long valorAcumulado(identificacion){

    }
    public Entrega buscarEntrega(codigo){

    }
    public Aportante buscarAportante(identificacion){

    }

    //Getters
    public List<Aportante> getTheListAportantes() {
        return theListAportantes;
    }
    public List<Entrega> getTheListEntrega() {
        return theListEntrega;
    }

    //Setters
    public void setTheListAportantes(List<Aportante> theListAportantes) {
        this.theListAportantes = theListAportantes;
    }
    public void setTheListEntrega(List<Entrega> theListEntrega) {
        this.theListEntrega = theListEntrega;
    }
}
