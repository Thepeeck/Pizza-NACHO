public class interfazJuego {

    public String getNombre(){
        return this.getNombre();
    }
    public void mostrarOrden(Orden orden) {
        System.out.println("Orden #" + orden.getInOrden());
        for (Pizza pizza : orden.getPizzas()) {
            System.out.println("- Pizza: " + pizza.getNombre() + " - Precio: $" + pizza.getPrecio());
        }
        System.out.println("Total: $" + orden.calcularTotal());
    }
    public void mostrarTimer(int segundos) {
        System.out.println("Tiempo restante: " + segundos + " segundos");
    }

    public void mostrarPuntaje(int puntaje) {
        System.out.println("Puntaje actual: " + puntaje);
    }

    public void mostrarCoccion(Pizza pizza) {
        System.out.println("Cocinando " + pizza.getNombre() + "...");
    }

    public void mostrarFinalizacion(Orden orden) {
        System.out.println("¡Orden #" + orden.getInOrden() + " lista para entregar!");
    }

}
