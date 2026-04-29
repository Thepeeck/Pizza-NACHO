import java.util.ArrayList;

public class JuegoController {

    private Cocina    cocina;
    private Inventario inventario;
    private int       puntaje;
    private int       errores;
    private int       streak;

    private Orden ordenActual;

    public JuegoController(Cocina cocina, Inventario inventario) {
        this.cocina     = cocina;
        this.inventario = inventario;
        this.puntaje    = 0;
        this.errores    = 0;
        this.streak     = 0;
    }

    public void iniciarJuego() {
        System.out.println("\n  Juego iniciado. Puntaje: 0");
    }

    public void generarOrdenes() {
        System.out.println("\n Generando nuevas ordenes...");

 
        Orden o1 = new Orden(1001, " Bob", 100);
        Pizza p1 = new Pizza("Margherita");
        p1.agregarBase(new Base("Masa Delgada", "Masa crujiente", 200, "Delgada", "Trigo"));
        p1.agregarSalsa(new Salsa("Salsa de Tomate", "Salsa clasica", 80, false, 0));
        p1.agregarTopping(new Topping("Queso Mozzarella", "Queso fresco", 120, "queso", false));
        o1.agregarPizza(p1);

        Orden o2 = new Orden(1002, " Maria", 150);
        Pizza p2 = new Pizza("Pescado Especial");
        p2.agregarBase(new Base("Masa Normal", "Masa esponjosa", 250, "Normal", "Trigo"));
        p2.agregarSalsa(new Salsa("Salsa Picante", "Salsa con chile", 70, true, 3));
        p2.agregarTopping(new Topping("Pescado", "Filetes de pescado", 90, "mariscos", true));
        p2.agregarTopping(new Topping("Alga Marina", "Alga fresca", 30, "vegetal", false));
        o2.agregarPizza(p2);

        Orden o3 = new Orden(1003, "Rex", 120);
        Pizza p3 = new Pizza("Arctic Special");
        p3.agregarBase(new Base("Masa Gruesa", "Masa con mucho pan", 300, "Gruesa", "Integral"));
        p3.agregarSalsa(new Salsa("Salsa Normal", "Salsa suave", 60, false, 0));
        p3.agregarTopping(new Topping("Camaron", "Camarones frescos", 100, "mariscos", true));
        p3.agregarTopping(new Topping("Queso", "Queso gratinado", 130, "queso", false));
        o3.agregarPizza(p3);

        cocina.recibirOrden(o1);
        cocina.recibirOrden(o2);
        cocina.recibirOrden(o3);

        System.out.println("\n  Generadas " + 3 + " ordenes:");
        System.out.println("  Orden #1001 - Status: PENDING - Items: [Masa Delgada, Salsa de Tomate, Queso Mozzarella]");
        System.out.println("  Orden #1002 - Status: NEW     - Items: [Masa Normal, Salsa Picante, Pescado, Alga Marina]");
        System.out.println("  Orden #1003 - Status: NEW     - Items: [Masa Gruesa, Salsa Normal, Camaron, Queso]");
    }

    public Orden tomarSiguienteOrden() {
        ordenActual = cocina.procesarSiguiente();
        return ordenActual;
    }

    public Orden getOrdenActual() { return ordenActual; }

    public void entregarPizza(Pizza pizzaJugador) {
        System.out.println("\n[JuegoController] Evaluando entrega...");

        if (ordenActual == null) {
            System.out.println("  [!] No hay orden actual. Toma una orden primero (opcion 2).");
            return;
        }
        if (ordenActual.isEntregada()) {
            System.out.println("  [!] Esta orden ya fue entregada. Toma la siguiente.");
            return;
        }

        boolean todoBien = verificarIngredientes(pizzaJugador, ordenActual.getPizzaEsperada());

        if (todoBien) {
            // Exito
            int bonus = calcularBonus();
            int ganado = ordenActual.getRecompenza() + bonus;
            puntaje += ganado;
            streak++;
            ordenActual.setEntregada();
            cocina.registrarPizzaEntregada(pizzaJugador);
            
            System.out.println("   Pizza correcta!");
            System.out.println("  Recompenza base : " + ordenActual.getRecompenza() + " pts");
            if (bonus > 0)
            System.out.println("  Bonus racha x"  + streak + "  : +" + bonus + " pts");
            System.out.println("  Total ganado    : +" + ganado + " pts");
            System.out.println("  Puntaje total   : " + puntaje + " pts");
        } else {

            errores++;
            streak = 0;
            int penalizacion = 20;
            puntaje = Math.max(0, puntaje - penalizacion);
            System.out.println("  PIZZA INCORRECTA. -" + penalizacion + " puntos.");
            System.out.println("  Puntaje total: " + puntaje + " pts");
        }
    }

    public boolean verificarIngredientes(Pizza pizzaJugador, Pizza pizzaEsperada) {
        boolean correcto = true;

        System.out.println("\n  --- Comparando ingredientes ---");

        String baseJugador  = pizzaJugador.getBase()  != null ? pizzaJugador.getBase().getNombre().toLowerCase()  : "";
        String baseEsperada = pizzaEsperada.getBase() != null ? pizzaEsperada.getBase().getNombre().toLowerCase() : "";

        if (baseJugador.equals(baseEsperada)) {
            System.out.println("  [OK] Base correcta: " + pizzaJugador.getBase());
        } else {
            System.out.println("  [X]  Base incorrecta.");
            System.out.println("       Pusiste  : " + (pizzaJugador.getBase()  != null ? pizzaJugador.getBase()  : "ninguna"));
            System.out.println("       Esperaban: " + (pizzaEsperada.getBase() != null ? pizzaEsperada.getBase() : "ninguna"));
            correcto = false;
        }

        String salsaJugador  = pizzaJugador.getSalsa()  != null ? pizzaJugador.getSalsa().getNombre().toLowerCase()  : "";
        String salsaEsperada = pizzaEsperada.getSalsa() != null ? pizzaEsperada.getSalsa().getNombre().toLowerCase() : "";

        if (salsaJugador.equals(salsaEsperada)) {
            System.out.println("  [OK] Salsa correcta: " + pizzaJugador.getSalsa());
        } else {
            System.out.println("  [X]  Salsa incorrecta.");
            System.out.println("       Pusiste  : " + (pizzaJugador.getSalsa()  != null ? pizzaJugador.getSalsa()  : "ninguna"));
            System.out.println("       Esperaban: " + (pizzaEsperada.getSalsa() != null ? pizzaEsperada.getSalsa() : "ninguna"));
            correcto = false;
        }

        ArrayList<String> topsJugador  = pizzaJugador.getToppingNombres();
        ArrayList<String> topsEsperada = pizzaEsperada.getToppingNombres();

        for (String topEsperado : topsEsperada) {
            if (topsJugador.contains(topEsperado)) {
                System.out.println("  [OK] Topping encontrado: " + topEsperado);
            } else {
                System.out.println("  [X]  Te falto el ingrediente: " + topEsperado);
                correcto = false;
            }
        }
        for (String topSobrante : topsJugador) {
            if (!topsEsperada.contains(topSobrante)) {
                System.out.println("  [X]  Ingrediente de mas: " + topSobrante + " (no lo pedian)");
                correcto = false;
            }
        }

        return correcto;
    }

    public int calcularBonus() {
        if (streak >= 2) return 15 * (streak - 1);
        return 0;
    }

    public void actualizarTimer() {
        System.out.println("Modo consola: sin limite de tiempo.");
    }

    public int getPuntaje() { return puntaje; }
    public int getErrores() { return errores; }
    public int getStreak()  { return streak;  }

    public void mostrarEstadisticas() {
        System.out.println("  Puntaje : " + puntaje + " pts");
        System.out.println("  Errores : " + errores);
        System.out.println("  Racha   : " + streak);
        System.out.println("  Ordenes pendientes: " + cocina.totalOrdenesPendientes());
    }
}