package co.edu.uniquindio;

import java.time.LocalDate;
import java.util.List;

public class PruebasEcoRuta {
    private static int pasadas = 0;
    private static int total = 0;
    // Texto corto con el resultado de cada verificacion (se muestra en JOptionPane)
    private static String resumen = "";

    public static String ejecutar() {
        pasadas = 0;
        total = 0;
        resumen = "";
        System.out.println("\nPRUEBAS FUNCIONALES ECORUTA");
        prueba1RegistroEntrega();
        prueba2ValidacionLotes();
        prueba3EntregaVacia();
        prueba4y5AceptacionYEtiquetas();
        System.out.println("\nResumen: " + pasadas + " de " + total + " verificaciones correctas.");
        resumen = resumen + "\nResumen: " + pasadas + " de " + total + " verificaciones correctas.";
        return resumen;
    }

    private static EcoRutaServicio crearServicio() {
        EcoRutaServicio s = new EcoRutaServicio();
        s.registrarAportante(new Aportante("1001", "Ana Gomez", "ana@mail.com"));
        return s;
    }

    private static String textoComprobante(Comprobante c) {
        if (c == null) {
            return "sin comprobante";
        }
        return "con comprobante";
    }

    private static void prueba1RegistroEntrega() {
        System.out.println("\n--- Prueba 1: Registro de una entrega ---");
        resumen = resumen + "\n" + "Prueba 1: Registro de una entrega" + "\n";
        EcoRutaServicio s = crearServicio();
        LocalDate hoy = LocalDate.of(2026, 11, 10);

        boolean crea = s.registrarEntrega("E01", hoy, "1001");
        Entrega e01 = s.buscarEntrega("E01");
        boolean ok = crea && e01.getEstado() == EstadoEntrega.ABIERTA && e01.getTheListLote().size() == 0 && e01.getComprobante() == null;
        verificar("Crear E01 con aportante 1001", "E01, aportante 1001", "true, ABIERTA, 0 lotes, sin comprobante", crea + ", " + e01.getEstado() + ", " + e01.getTheListLote().size() + " lotes, " + textoComprobante(e01.getComprobante()), ok);

        boolean repetido = s.registrarEntrega("E01", hoy, "1001");
        verificar("Registrar codigo repetido", "E01, aportante 1001", "false", String.valueOf(repetido), repetido == false);

        boolean desconocido = s.registrarEntrega("E02", hoy, "9999");
        verificar("Registrar aportante desconocido", "E02, aportante 9999", "false", String.valueOf(desconocido), desconocido == false);

        boolean conservada = s.getTheListEntrega().size() == 1 && s.buscarEntrega("E01") == e01;
        verificar("E01 se conserva", "-", "1 entrega registrada (E01)", s.getTheListEntrega().size() + " entrega(s)", conservada);
    }

    private static void prueba2ValidacionLotes() {
        System.out.println("\n--- Prueba 2: Validacion de lotes ---");
        resumen = resumen + "\n" + "Prueba 2: Validacion de lotes" + "\n";
        EcoRutaServicio s = crearServicio();s.registrarEntrega("E01", LocalDate.of(2026, 11, 10), "1001");

        boolean agrega = s.agregarLote("E01", new LotePapelCarton(false, "L01", "Carton", 5));
        verificar("Agregar L01 con 5 kg", "L01, 5 kg", "true", String.valueOf(agrega), agrega);

        boolean repetido = s.agregarLote("E01", new LotePapelCarton(false, "L01", "Otro", 3));
        verificar("Agregar codigo L01 repetido", "L01, 3 kg", "false", String.valueOf(repetido), repetido == false);

        boolean cero = s.agregarLote("E01", new LotePapelCarton(false, "L02", "Cero", 0));
        verificar("Agregar lote con 0 kg", "L02, 0 kg", "false", String.valueOf(cero), cero == false);

        List<Lote> lotes = s.buscarEntrega("E01").getTheListLote();
        boolean soloUno = lotes.size() == 1 && lotes.get(0).getCodigoLote().equals("L01");
        verificar("Solo se conserva el primer lote", "-", "1 lote (L01)", lotes.size() + " lote(s)", soloUno);
    }

    private static void prueba3EntregaVacia() {
        System.out.println("\n--- Prueba 3: Rechazo de una entrega vacia ---");
        resumen = resumen + "\n" + "Prueba 3: Rechazo de una entrega vacia" + "\n";
        EcoRutaServicio s = crearServicio();
        s.registrarEntrega("E01", LocalDate.of(2026, 11, 10), "1001");

        boolean acepta = s.aceptarEntrega("E01");
        Entrega e = s.buscarEntrega("E01");
        boolean ok = acepta == false && e.getEstado() == EstadoEntrega.ABIERTA && e.getComprobante() == null;
        verificar("Aceptar entrega sin lotes", "E01 sin lotes", "false, ABIERTA, sin comprobante", acepta + ", " + e.getEstado() + ", " + textoComprobante(e.getComprobante()), ok);
    }

    private static void prueba4y5AceptacionYEtiquetas() {
        System.out.println("\n--- Prueba 4: Aceptacion y calculo con los tres tipos de lote ---");
        resumen = resumen + "\n" + "Prueba 4: Aceptacion y calculo con los tres tipos de lote" + "\n";
        EcoRutaServicio s = crearServicio();
        s.registrarEntrega("E01", LocalDate.of(2026, 11, 10), "1001");

        LotePapelCarton papel = new LotePapelCarton(true, "L01", "Papel clasificado", 10);
        LoteMetal metal = new LoteMetal(TipoMetal.ALUMINIO, "L02", "Aluminio", 5);
        LoteResiduoElectronico elec = new LoteResiduoElectronico(2, "L03", "Equipos", 4);
        s.agregarLote("E01", papel);
        s.agregarLote("E01", metal);
        s.agregarLote("E01", elec);

        verificar("Valor del lote de papel", "10 kg clasificado", "$6.000", Main.moneda(papel.calcularValor()), papel.calcularValor() == 6000);
        verificar("Valor del lote de metal", "5 kg aluminio", "$11.000", Main.moneda(metal.calcularValor()), metal.calcularValor() == 11000);
        verificar("Valor del lote electronico", "4 kg, 2 equipos", "$7.200", Main.moneda(elec.calcularValor()), elec.calcularValor() == 7200);

        boolean acepta = s.aceptarEntrega("E01");
        Comprobante c = s.consultarComprobante("E01");
        Entrega e = s.buscarEntrega("E01");
        boolean ok = acepta && e.getEstado() == EstadoEntrega.ACEPTADA && c.pesoTotal() == 19 && c.valorTotal() == 24200;
        verificar("Aceptar E01", "E01 con 3 lotes", "true, ACEPTADA, 19 kg, $24.200", acepta + ", " + e.getEstado() + ", " + c.pesoTotal() + " kg, " + Main.moneda(c.valorTotal()), ok);

        System.out.println("\n--- Prueba 5: Consulta de etiquetas ---");
        resumen = resumen + "\n" + "Prueba 5: Consulta de etiquetas" + "\n";
        List<String> etiquetas = s.consultarEtiquetas("E01");
        verificar("Cantidad de etiquetas", "E01 aceptada", "2", String.valueOf(etiquetas.size()), etiquetas.size() == 2);

        String etiquetaMetal = "-";
        String etiquetaElec = "-";
        if (etiquetas.size() == 2) {
            etiquetaMetal = etiquetas.get(0);
            etiquetaElec = etiquetas.get(1);
        }
        boolean metalOk = etiquetaMetal.contains("L02") && etiquetaMetal.contains("ALUMINIO"); verificar("Etiqueta del metal", "L02", "incluye L02 y ALUMINIO", etiquetaMetal, metalOk);

        boolean elecOk = etiquetaElec.contains("L03") && etiquetaElec.contains("2"); verificar("Etiqueta del electronico", "L03", "incluye L03 y 2 equipos", etiquetaElec, elecOk);
    }

    // Imprime una verificacion y cuenta si paso o fallo
    private static void verificar(String nombre, String entrada, String esperado, String obtenido, boolean ok) {
        total = total + 1;
        System.out.println(nombre);
        System.out.println("   Entrada:   " + entrada);
        System.out.println("   Esperado:  " + esperado);
        System.out.println("   Obtenido:  " + obtenido);
        if (ok) {
            pasadas = pasadas + 1;
            System.out.println(" Resultado: PASA");
            resumen = resumen + "PASA - " + nombre + "\n";
        } else {
            System.out.println(" Resultado: FALLA");
            resumen = resumen + "FALLA - " + nombre + "\n";
        }
    }
}