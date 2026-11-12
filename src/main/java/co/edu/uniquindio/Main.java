package co.edu.uniquindio;

import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {
        EcoRutaServicio servicio = new EcoRutaServicio();

        servicio.registrarAportante(new Aportante("1001", "Ana Gomez", "ana@mail.com"));
        servicio.registrarAportante(new Aportante("1002", "Comercial Eco SAS", "contacto@eco.com"));
        servicio.registrarAportante(new Aportante("1003", "Colegio Central", "rectoria@colegio.edu"));

        String menu = "ECORUTA\n"
                + "1. Listar aportantes\n"
                + "2. Crear entrega\n"
                + "3. Agregar lote a una entrega\n"
                + "4. Aceptar entrega\n"
                + "5. Consultar comprobante\n"
                + "6. Consultar etiquetas\n"
                + "7. Valor acumulado por aportante\n"
                + "8. Ejecutar pruebas funcionales\n"
                + "0. Salir\n\n"
                + "Elija una opcion:";

        int opcion = -2;
        while (opcion != 0) {
            opcion = leerEntero(menu);
            if (opcion == -1) {
                opcion = 0;   // si el usuario cancela la ventana, se sale
            }

            switch (opcion) {
                case 1:
                    listarAportantes(servicio);
                    break;
                case 2:
                    crearEntrega(servicio);
                    break;
                case 3:
                    agregarLote(servicio);
                    break;
                case 4:
                    aceptarEntrega(servicio);
                    break;
                case 5:
                    consultarComprobante(servicio);
                    break;
                case 6:
                    consultarEtiquetas(servicio);
                    break;
                case 7:
                    valorAcumulado(servicio);
                    break;
                case 8:
                    ejecutarPruebas();
                    break;
                case 0:
                    mostrar("Hasta pronto.");
                    break;
                default:
                    mostrar("Opcion invalida.");
            }
        }
    }

    private static void listarAportantes(EcoRutaServicio servicio) {
        String texto = "APORTANTES REGISTRADOS\n\n";
        for (Aportante a : servicio.getTheListAportantes()) {
            texto = texto + a.identificacion() + " | " + a.nombre() + " | " + a.correo() + "\n";
        }
        mostrar(texto);
    }

    private static void crearEntrega(EcoRutaServicio servicio) {
        String codigo = leerTexto("Codigo de entrega:");
        String id = leerTexto("Identificacion del aportante:");
        boolean ok = servicio.registrarEntrega(codigo, LocalDate.now(), id);
        if (ok) {
            mostrar("Entrega " + codigo + " creada en estado ABIERTA.");
        } else {
            mostrar("Rechazado: codigo repetido, vacio o aportante desconocido.");
        }
    }

    private static void agregarLote(EcoRutaServicio servicio) {
        String codEntrega = leerTexto("Codigo de entrega:");
        int tipo = leerEntero("Tipo de lote:\n1. Papel y carton\n2. Metal\n3. Residuo electronico");
        String codLote = leerTexto("Codigo del lote:");
        String descripcion = leerTexto("Descripcion:");
        int peso = leerEntero("Peso (kg):");

        Lote lote = null;
        if (tipo == 1) {
            int respuesta = JOptionPane.showConfirmDialog(null, "El material esta clasificado?",
                    "EcoRuta", JOptionPane.YES_NO_OPTION);
            boolean clasificado = (respuesta == JOptionPane.YES_OPTION);
            lote = new LotePapelCarton(clasificado, codLote, descripcion, peso);
        } else if (tipo == 2) {
            int m = leerEntero("Tipo de metal:\n1. ALUMINIO\n2. ACERO");
            if (m == 1) {
                lote = new LoteMetal(TipoMetal.ALUMINIO, codLote, descripcion, peso);
            } else if (m == 2) {
                lote = new LoteMetal(TipoMetal.ACERO, codLote, descripcion, peso);
            } else {
                mostrar("Metal invalido.");
                return;
            }
        } else if (tipo == 3) {
            int equipos = leerEntero("Equipos completos (0 o mas):");
            if (equipos < 0) {
                mostrar("Rechazado: los equipos completos no pueden ser negativos.");
                return;
            }
            lote = new LoteResiduoElectronico(equipos, codLote, descripcion, peso);
        } else {
            mostrar("Tipo invalido.");
            return;
        }

        boolean ok = servicio.agregarLote(codEntrega, lote);
        if (ok) {
            mostrar("Lote agregado.\nValor reconocido: " + moneda(lote.calcularValor()));
        } else {
            mostrar("Rechazado: entrega inexistente o cerrada, codigo de lote repetido o peso invalido.");
        }
    }

    private static void aceptarEntrega(EcoRutaServicio servicio) {
        String codigo = leerTexto("Codigo de entrega:");
        boolean ok = servicio.aceptarEntrega(codigo);
        if (ok) {
            mostrar("Entrega ACEPTADA.");
        } else {
            mostrar("Rechazado: la entrega no existe, esta vacia o ya fue aceptada.");
        }
    }

    private static void consultarComprobante(EcoRutaServicio servicio) {
        String codigo = leerTexto("Codigo de entrega:");
        Comprobante c = servicio.consultarComprobante(codigo);
        if (c == null) {
            mostrar("No hay comprobante para esa entrega.");
        } else {
            mostrar("COMPROBANTE\n\n"
                    + "Entrega: " + c.codigoEntrega() + "\n"
                    + "Aportante: " + c.idAportante() + "\n"
                    + "Peso total: " + c.pesoTotal() + " kg\n"
                    + "Valor total: " + moneda(c.valorTotal()));
        }
    }

    private static void consultarEtiquetas(EcoRutaServicio servicio) {
        String codigo = leerTexto("Codigo de entrega:");
        List<String> etiquetas = servicio.consultarEtiquetas(codigo);
        if (etiquetas.size() == 0) {
            mostrar("Sin etiquetas (entrega inexistente, no aceptada o sin lotes etiquetables).");
        } else {
            String texto = "ETIQUETAS\n\n";
            for (String etiqueta : etiquetas) {
                texto = texto + etiqueta + "\n";
            }
            mostrar(texto);
        }
    }

    private static void valorAcumulado(EcoRutaServicio servicio) {
        String id = leerTexto("Identificacion del aportante:");
        mostrar("Valor acumulado: " + moneda(servicio.valorAcumulado(id)));
    }

    private static void ejecutarPruebas() {
        String resumen = PruebasEcoRuta.ejecutar();
        mostrar("PRUEBAS FUNCIONALES\n(el detalle completo esta en la consola)\n" + resumen);
    }


    public static String moneda(long valor) {
        String texto = String.format("%,d", valor);
        texto = texto.replace(",", ".");
        return "$" + texto;
    }

    private static void mostrar(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "EcoRuta", JOptionPane.INFORMATION_MESSAGE);
    }

    private static String leerTexto(String mensaje) {
        String texto = JOptionPane.showInputDialog(null, mensaje, "EcoRuta", JOptionPane.QUESTION_MESSAGE);
        if (texto == null) {
            return "";
        }
        return texto.trim();
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            String texto = JOptionPane.showInputDialog(null, mensaje, "EcoRuta", JOptionPane.QUESTION_MESSAGE);
            if (texto == null) {
                return -1;
            }
            try {
                return Integer.parseInt(texto.trim());
            } catch (NumberFormatException e) {
                mostrar("Ingrese un numero entero valido.");
            }
        }
    }
}