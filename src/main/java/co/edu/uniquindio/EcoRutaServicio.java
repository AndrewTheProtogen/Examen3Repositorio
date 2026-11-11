package co.edu.uniquindio;

public class EcoRutaServicio {
    //Atributos
    private Aportante[] theListAportantes = new Aportante[10];
    private Entrega[] theListEntrega = new Entrega[10];

    //Metodo

    //Getters
    public Aportante[] getTheListAportantes() {
        return theListAportantes;
    }
    public Entrega[] getTheListEntrega() {
        return theListEntrega;
    }

    //Setters
    public void setTheListAportantes(Aportante[] theListAportantes) {
        this.theListAportantes = theListAportantes;
    }
    public void setTheListEntrega(Entrega[] theListEntrega) {
        this.theListEntrega = theListEntrega;
    }
}
