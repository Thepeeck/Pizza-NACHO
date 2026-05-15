import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Cocina {

    private Queue<Orden> colaOrdenes;
    private boolean enProduccion;
    private Stack<Pizza> historialPizzas;


    public Cocina () {
        this.colaOrdenes    = new LinkedList<>();
        this.historialPizzas = new Stack<>();
        this.enProduccion   = false;
    }

    public void recibirOrden(Orden orden) {
        if (orden == null) {
            throw new IllegalArgumentException("La orden no puede ser registrada");
        }
        colaOrdenes.offer(orden);
        System.out.println("Orden recibida: #" + orden.getInOrden() + " de " + orden.getCliente() + " agregada.");
    }

    public Pizza ProcesosSiguientes(){
        if (enProduccion) {
            System.out.println("La cocina ya está ocupada.");
            return null;
        }

        if (colaOrdenes.isEmpty()) {
            System.out.println("No hay órdenes ");
            return null;
        }

        Orden ordenActual = colaOrdenes.poll();
        enProduccion = true;

        try {
            Pizza pizzaEnProceso = prepararPizza(ordenActual);
            historialPizzas.push(pizzaEnProceso);
            return pizzaEnProceso;
        } finally {
            enProduccion = false;
        }
    }
    public Pizza prepararPizza(Orden orden) {
        System.out.println("Preparando pizza para la orden #" + orden.getInOrden());
        try {
            Thread.sleep(2000); // Simula el tiempo de preparación
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        Pizza pizzaPreparada = orden.getPizzaEsperada();
        System.out.println("Pizza preparada para la orden #" + orden.getInOrden());
        return pizzaPreparada;
    }
    
    public Pizza pizzaPreparada() {
        if (historialPizzas.isEmpty()) {
            System.out.println("No hay pizzas preparadas en el historial.");
            return null;
        }
        return historialPizzas.peek();
    }

    public Pizza deshacerPizza() {
        if (historialPizzas.isEmpty()) {
            System.out.println("No hay pizzas para deshacer en el historial.");
            return null;
        }
        Pizza pizzaDeshecha = historialPizzas.pop();
        System.out.println("Pizza deshecha: " + pizzaDeshecha.getBase());
        return pizzaDeshecha;
    }

    public int OrdenesPendientes() {
        return colaOrdenes.size();
    }
        public boolean estaOcupada() {
            return enProduccion;
        }

        
}

