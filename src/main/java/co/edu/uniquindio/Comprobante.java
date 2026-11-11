package co.edu.uniquindio;

public record Comprobante(

        String codigoEntrega,
        String idAportante,
        int pesoTotal,
        long valorTotal

) {
    //Metodo constructor
    public Comprobante(String codigoEntrega, String idAportante, int pesoTotal, long valorTotal){
        this.codigoEntrega = codigoEntrega;
        this.idAportante = idAportante;
        this.pesoTotal = pesoTotal;
        this.valorTotal = valorTotal;
    }


}
