import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);


        Pizza estadoActual = new Pizza(EstadoCoccion.Crudo);
        System.out.println("  DEMO:");
        System.out.println("  estadoActual.estado   = " + estadoActual.estado);
        System.out.println("  estadoActual.estaQuemada() = " + estadoActual.estaQuemada());

        Inventario       inventario = new Inventario();
        poblarInventario(inventario);

        Cocina           cocina     = new Cocina();
        CocinaController cocinaCtrl = new CocinaController(cocina, inventario);
        JuegoController  juego      = new JuegoController(cocina, inventario);

        juego.iniciarJuego();

        Pizza pizzaActual = null;

        boolean corriendo = true;

        while (corriendo) {
            imprimirMenu();
            System.out.print("Please enter your choice (1-10): ");
            int opcion = leerEntero(scanner);

            switch (opcion) {

                case 1:
                    System.out.println("\nExecuting choice 1...");
                    juego.generarOrdenes();
                    break;

                case 2:
                    System.out.println("\nExecuting choice 2...");
                    if (!cocina.hayOrdenesPendientes()) {
                        System.out.println("  [!] No hay ordenes. Genera primero (opcion 1).");
                    } else {
                        Orden sig = juego.tomarSiguienteOrden();
                        if (sig != null) {
                            sig.mostrarOrden();
                            System.out.println("\n  Crea una pizza que coincida (opcion 3).");
                        }
                    }
                    break;

                case 3:
                    System.out.println("\nExecuting choice 3...");
                    if (juego.getOrdenActual() == null) {
                        System.out.println("  [!] Toma una orden primero (opcion 2).");
                        break;
                    }
                    pizzaActual = menuCrearPizza(scanner, inventario, pizzaActual);
                    break;

                case 4:
                    System.out.println("\nExecuting choice 4...");
                    if (pizzaActual == null || pizzaActual.getPilaIngredientes().isEmpty()) {
                        System.out.println("  [!] No hay pizza activa o no tiene ingredientes.");
                    } else {
                        pizzaActual.deshacerUltimoIngrediente();
                        pizzaActual.mostrarPizza();
                    }
                    break;

                case 5:
                    System.out.println("\nExecuting choice 5...");
                    if (pizzaActual == null) {
                        System.out.println("  [!] Crea una pizza primero (opcion 3).");
                        break;
                    }

                    System.out.println("\n  Verificando estado de coccion...");
                    System.out.println("  pizzaActual.estado = " + pizzaActual.estado);
                    System.out.println("  estaQuemada()      = " + pizzaActual.estaQuemada());

                    if (pizzaActual.estaQuemada()) {
                        System.out.println("\n  [X] No puedes entregar una pizza QUEMADA.");
                        System.out.println("      Usa opcion 10 para reiniciar y volver a cocinar.");
                        break;
                    }
                    if (pizzaActual.estaCruda()) {
                        System.out.println("\n  [X] La pizza esta cruda. Cocina primero (opcion 9).");
                        break;
                    }

                    pizzaActual.mostrarPizza();
                    juego.entregarPizza(pizzaActual);
                    pizzaActual = null;
                    break;

                case 6:
                    System.out.println("\nExecuting choice 6...");
                    cocina.mostrarEstado();
                    if (pizzaActual != null) pizzaActual.mostrarPizza();
                    break;

                case 7:
                    System.out.println("\nExecuting choice 7...");
                    juego.mostrarEstadisticas();
                    break;

                case 8:
                    System.out.println("\nExecuting choice 8...");
                    if (pizzaActual == null) {
                        System.out.println("  [Demo del Main.java del estudiante]");
                        Pizza demo = new Pizza(EstadoCoccion.Crudo);
                        System.out.println("  Pizza demo creada con EstadoCoccion.Crudo");
                        System.out.println("  demo.estado       = " + demo.estado);
                        System.out.println("  demo.estaQuemada()= " + demo.estaQuemada());

                        demo.cocinar(8);
                        System.out.println("  Despues de cocinar(8):");
                        System.out.println("  demo.estado       = " + demo.estado);
                        System.out.println("  demo.estaQuemada()= " + demo.estaQuemada());
                        System.out.println("  demo.estaCocida() = " + demo.estaCocida());

                        demo.cocinar(15);
                        System.out.println("  Despues de cocinar(15):");
                        System.out.println("  demo.estado       = " + demo.estado);
                        System.out.println("  demo.estaQuemada()= " + demo.estaQuemada());
                    } else {
                        System.out.println("\n  Estado de coccion de tu pizza:");
                        System.out.println("  pizzaActual.estado        = " + pizzaActual.estado);
                        System.out.println("  estaQuemada()             = " + pizzaActual.estaQuemada());
                        System.out.println("  estaCruda()               = " + pizzaActual.estaCruda());
                        System.out.println("  estaCocida()              = " + pizzaActual.estaCocida());
                        System.out.println("  getDescripcionEstado()    = " + pizzaActual.getDescripcionEstado());
                        System.out.println("  estaListaParaEntregar()   = " + pizzaActual.estaListaParaEntregar());
                        System.out.println("  contarToppings()          = " + pizzaActual.contarToppings());
                        System.out.println("  tieneBase()               = " + pizzaActual.tieneBase());
                        System.out.println("  tieneSalsa()              = " + pizzaActual.tieneSalsa());
                    }
                    break;

                case 9:
                    System.out.println("\nExecuting choice 9...");
                    if (pizzaActual == null) {
                        System.out.println("  [!] Crea una pizza primero (opcion 3).");
                        break;
                    }
                    System.out.println("  Estado actual: " + pizzaActual.estado);
                    System.out.println("  Cuantos segundos cocinar?");
                    System.out.println("    0-2  seg -> Crudo   (recien creada)");
                    System.out.println("    3-5  seg -> CRUDO   (poco tiempo)");
                    System.out.println("    6-10 seg -> COCIDO  (perfecta!)");
                    System.out.println("    11+  seg -> QUEMADO (se quemo!)");
                    System.out.print("  Segundos: ");
                    int segundos = leerEntero(scanner);
                    if (segundos < 0) {
                        System.out.println("  [!] Valor invalido.");
                    } else {
                        pizzaActual.cocinar(segundos);
                        System.out.println("\n  Resultado:");
                        System.out.println("  pizzaActual.estado = " + pizzaActual.estado);
                        System.out.println("  estaQuemada()      = " + pizzaActual.estaQuemada());
                        System.out.println("  estaCocida()       = " + pizzaActual.estaCocida());
                        if (pizzaActual.estaQuemada()) {
                            System.out.println("  [!] QUEMADA. Usa opcion 10 para reiniciar.");
                        }
                    }
                    break;

                    case 10:
                    System.out.println("\nExecuting choice 10...");
                    if (pizzaActual == null) {
                        System.out.println("  [!] No hay pizza activa.");
                    } else {
                        pizzaActual.resetearCoccion();
                        System.out.println("  Estado reiniciado: " + pizzaActual.estado);
                        System.out.println("  estaQuemada() = " + pizzaActual.estaQuemada());
                    }
                    break;
                case 0:
                    System.out.println("\n  Gracias por jugar! Puntaje final: " + juego.getPuntaje());
                    corriendo = false;
                    break;

                default:
                    System.out.println("  [!] Opcion invalida. Elige entre 0 y 10.");
            }
        }
        scanner.close();
    }


    private static Pizza menuCrearPizza(Scanner scanner, Inventario inventario, Pizza pizzaActual) {

        if (pizzaActual == null) {
            System.out.print("\n  Nombre de tu pizza: ");
            String nombre = scanner.nextLine().trim();
            pizzaActual = new Pizza(nombre.isEmpty() ? "Mi Pizza" : nombre);
            System.out.println("  Pizza '" + pizzaActual.getNombre() + "' creada.");
            System.out.println("  Estado inicial: " + pizzaActual.estado);     
            System.out.println("  estaQuemada(): " + pizzaActual.estaQuemada()); 
        }

        boolean editando = true;
        while (editando) {
            System.out.println("\n  --- Armando: " + pizzaActual.getNombre() +
                " [" + pizzaActual.estado + "] ---");
            System.out.println("  a. Agregar Base");
            System.out.println("  b. Agregar Salsa");
            System.out.println("  c. Agregar Topping");
            System.out.println("  d. Ver pizza actual");
            System.out.println("  e. Ver inventario");
            System.out.println("  f. Volver al menu");
            System.out.print("  Opcion: ");

            String sub = scanner.nextLine().trim().toLowerCase();

            switch (sub) {
                case "a":
                    System.out.println("\n  Bases:  1.Masa Delgada  2.Masa Normal  3.Masa Gruesa");
                    System.out.print("  Elige (1-3): ");
                    int bo = leerEntero(scanner);
                    Base base = switch (bo) {
                        case 1 -> (Base) inventario.getIngrediente("masa delgada");
                        case 2 -> (Base) inventario.getIngrediente("masa normal");
                        case 3 -> (Base) inventario.getIngrediente("masa gruesa");
                        default -> null;
                    };
                    if (base != null) pizzaActual.agregarBase(base);
                    else System.out.println("  [!] Opcion invalida.");
                    break;

                case "b":
                    System.out.println("\n  Salsas: 1.Salsa de Tomate  2.Salsa Picante  3.Salsa Normal");
                    System.out.print("  Elige (1-3): ");
                    int so = leerEntero(scanner);
                    Salsa salsa = switch (so) {
                        case 1 -> (Salsa) inventario.getIngrediente("salsa de tomate");
                        case 2 -> (Salsa) inventario.getIngrediente("salsa picante");
                        case 3 -> (Salsa) inventario.getIngrediente("salsa normal");
                        default -> null;
                    };
                    if (salsa != null) pizzaActual.agregarSalsa(salsa);
                    else System.out.println("  [!] Opcion invalida.");
                    break;

                case "c":
                    System.out.println("\n  Toppings:");
                    System.out.println("    1.Queso Mozzarella  2.Pescado  3.Camaron");
                    System.out.println("    4.Alga Marina       5.Queso    6.Squid");
                    System.out.print("  Elige (1-6): ");
                    int to = leerEntero(scanner);
                    Topping top = switch (to) {
                        case 1 -> (Topping) inventario.getIngrediente("queso mozzarella");
                        case 2 -> (Topping) inventario.getIngrediente("pescado");
                        case 3 -> (Topping) inventario.getIngrediente("camaron");
                        case 4 -> (Topping) inventario.getIngrediente("alga marina");
                        case 5 -> (Topping) inventario.getIngrediente("queso");
                        case 6 -> (Topping) inventario.getIngrediente("squid");
                        default -> null;
                    };
                    if (top != null) pizzaActual.agregarTopping(top);
                    else System.out.println("  [!] Opcion invalida.");
                    break;

                case "d":
                    pizzaActual.mostrarPizza();
                    break;

                case "e":
                    inventario.mostrarInventario();
                    break;

                case "f":
                    editando = false;
                    break;

                default:
                    System.out.println("  [!] Opcion invalida.");
            }
        }
        return pizzaActual;
    }



    private static void imprimirMenu() {
        System.out.println("    PIZZAS NACHOOO ");
        System.out.println("MAIN MENU OPTIONS:");
        System.out.println("  1.  Generar nuevas ordenes");
        System.out.println("  2.  Tomar siguiente orden  ");
        System.out.println("  3.  Crear / armar pizza    ");
        System.out.println("  4.  Deshacer ultimo        ");
        System.out.println("  5.  Enviar pizza           ");
        System.out.println("  6.  Ver estado cocina");
        System.out.println("  7.  Ver estadisticas");
        System.out.println("  8.  Ver estado de coccion  ");
        System.out.println("  9.  Cocinar pizza          ");
        System.out.println("  10. Reiniciar coccion      ");
        System.out.println("  0.  Salir");
    }

    private static void poblarInventario(Inventario inv) {
        inv.setIngrediente("masa delgada",    new Base("Masa Delgada",    "Masa crujiente",      200, "Delgada", "Trigo"));
        inv.setIngrediente("masa normal",     new Base("Masa Normal",     "Masa esponjosa",      250, "Normal",  "Trigo"));
        inv.setIngrediente("masa gruesa",     new Base("Masa Gruesa",     "Masa con mucho pan",  300, "Gruesa",  "Integral"));
        inv.setIngrediente("salsa de tomate", new Salsa("Salsa de Tomate","Salsa clasica",        80, false, 0));
        inv.setIngrediente("salsa picante",   new Salsa("Salsa Picante",  "Salsa con chile",      70, true,  3));
        inv.setIngrediente("salsa normal",    new Salsa("Salsa Normal",   "Salsa suave",          60, false, 0));
        inv.setIngrediente("queso mozzarella",new Topping("Queso Mozzarella","Queso fresco",     120, "queso",    false));
        inv.setIngrediente("pescado",         new Topping("Pescado",         "Filetes frescos",   90, "mariscos", true));
        inv.setIngrediente("camaron",         new Topping("Camaron",         "Camarones frescos", 100, "mariscos", true));
        inv.setIngrediente("alga marina",     new Topping("Alga Marina",     "Alga fresca",        30, "vegetal",  false));
        inv.setIngrediente("queso",           new Topping("Queso",           "Queso gratinado",   130, "queso",    false));
        inv.setIngrediente("squid",           new Topping("Squid",           "Calamar fresco",     85, "mariscos", true));
    }

    private static int leerEntero(Scanner scanner) {
        try { return Integer.parseInt(scanner.nextLine().trim()); }
        catch (NumberFormatException e) { return -1; }
    }
}