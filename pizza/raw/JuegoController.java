public class JuegoController {
    private Cocina cocina;
    private Inventario inventario;
    private int puntaje;
    private int errores;
    private int streak;

    public JuegoController(Cocina cocina, Inventario inventario) {
        this.cocina = cocina;
        this.inventario = inventario;
        this.puntaje = 0;
        this.errores = 0;
        this.streak = 0;
    }

    public void iniciarJuego() {
        System.out.println("¡Bienvenido a la Pizzería NACHOOOOO!");
        System.out.println("aca hay que preparar las pizzas lo mas rapido posible, TU PUEDES ;)");
    }

    public void procesarOrden(Orden orden) {
        cocina.recibirOrden(orden);
        Pizza resultado = cocina.ProcesosSiguientes();
        if (resultado != null) {
            puntaje += 10;
            streak++;
            System.out.println("¡Pizza preparada! Puntaje: " + puntaje + " Streak: " + streak);
        } else {
            errores++;
            streak = 0;
            System.out.println("Error al preparar la pizza. Errores: " + errores);
        }
    }

    public int getPuntaje() {
        return puntaje;
    }

    public int getErrores() {
        return errores;
    }

    public int getStreak() {
        return streak;
    }


    public void actualizarTimer(int segundos) {
        System.out.println("Tiempo restante: " + segundos + " segundos");
    }

    public void finalizarJuego() {
        System.out.println("¡Juego terminado! Puntaje final: " + puntaje + " Errores: " + errores);
    }

    public Orden generarOrden() {
        Orden orden = new Orden(1001, "bob", "Pendiente", 100);
        Pizza pizza = new Pizza(
            new Base("Delgada", "Trigo"),
            new Salsa(false, 0),
            new Topping("Queso Mozzarella", 0),
            "Margarita", 8.99, "NUEVA"
        );
        orden.agregarPizza(pizza);
        return orden;
    }

    public void entregarPizza(Orden orden) {
        System.out.println("Entregando orden #" + orden.getInOrden());
    }

    public boolean verificarIngredientes(Pizza pizza) {
        return inventario.hayStack("queso");
    }

    public int calcularBonus() {
        return puntaje / 10;
    }

    public void sumarPuntaje(int puntos) {
    this.puntaje += puntos;
    }

    public void mostrarPuntaje() {
    System.out.println("Puntaje total: " + this.puntaje);
    }

    public int getpuntaje() {
        return puntaje;
    }

    public void getPuntajeFinal() {
        int bonus = calcularBonus();
        int puntajeFinal = puntaje + bonus;
        System.out.println("Puntaje final con bonus: " + puntajeFinal);
    }
    public boolean compararPizzas(Pizza pizzaJugador, Pizza pizzaEsperada) {
        boolean correcto = true;

        String baseJ = pizzaJugador.getBase() != null ? pizzaJugador.getBase().getGrosor() : "  ";
        String baseE = pizzaEsperada.getBase() != null ? pizzaEsperada.getBase().getGrosor() : "  ";
        if (baseJ.equals(baseE)) {
            System.out.println("  Base correcta: " + baseJ);
        } else {
            System.out.println("  Base incorrecta: " + baseJ);
            correcto = false;
        
        }
        boolean salsaJ = pizzaJugador.getSalsa() != null && pizzaJugador.getSalsa().isPicante();
        boolean salsaE = pizzaEsperada.getSalsa() != null && pizzaEsperada.getSalsa().isPicante();
        if (salsaJ == salsaE) {
            System.out.println("  Salsa correcta: " + (salsaJ ? "Picante" : "No picante"));
        } else {
            System.out.println("  Salsa incorrecta: " + (salsaJ ? "Picante" : "No picante"));
            correcto = false;
        }

        String topJ = pizzaJugador.getTopping() != null ? pizzaJugador.getTopping().getNombre() : " ";
        String topE = pizzaEsperada.getTopping() != null ? pizzaEsperada.getTopping().getNombre() : " ";
        if (topJ.equalsIgnoreCase(topE)) {
            System.out.println("  Topping correcto: " + topJ);
        } else {
            System.out.println("  Topping incorrecto: " + topJ);
            correcto = false;
        }
        return correcto;
}

}