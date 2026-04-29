import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Cocina {

    private Queue<Orden> colaOrdenes;

    private Stack<Pizza> historialPizzas;

    private boolean enProduccion;

    public Cocina() {
        this.colaOrdenes    = new LinkedList<>();
        this.historialPizzas = new Stack<>();
        this.enProduccion   = false;
    }

     public void recibirOrden(Orden o) {
        colaOrdenes.add(o); 
        System.out.println("  [Cocina] Orden #" + o.getIdOrden() +
            " de " + o.getCliente() + " agregada a la cola.");
    }

     public Orden procesarSiguiente() {
        if (colaOrdenes.isEmpty()) {
            System.out.println("  [Cocina] No hay ordenes en la cola.");
            return null;
        }
        Orden siguiente = colaOrdenes.poll(); 
        enProduccion = true;
        System.out.println("  [Cocina] Procesando orden #" + siguiente.getIdOrden() +
            " de " + siguiente.getCliente());
        return siguiente;
    }

    public Orden verProximaOrden() {
        return colaOrdenes.peek();
    }

    public boolean estaOcupada()           { return enProduccion; }
    public void    setEnProduccion(boolean b){ enProduccion = b; }
    public int     totalOrdenesPendientes() { return colaOrdenes.size(); }
    public boolean hayOrdenesPendientes()   { return !colaOrdenes.isEmpty(); }

    public void registrarPizzaEntregada(Pizza p) {
        historialPizzas.push(p); 
        System.out.println("  [Cocina] Pizza '" + p.getNombre() + "' registrada en historial.");
    }

    public Pizza verUltimaPizzaEntregada() {
        if (historialPizzas.isEmpty()) return null;
        return historialPizzas.peek();
    }

    public Stack<Pizza> getHistorialPizzas() { return historialPizzas; }

     public void mostrarEstado() {
        System.out.println("\n  === ESTADO DE LA COCINA ===");
        System.out.println("  Ordenes en cola  : " + colaOrdenes.size());
        System.out.println("  En produccion    : " + (enProduccion ? "Si" : "No"));
        System.out.println("  Pizzas entregadas: " + historialPizzas.size());

        if (!colaOrdenes.isEmpty()) {
            System.out.println("\n  Ordenes pendientes (Queue - FIFO):");
            int i = 1;
            for (Orden o : colaOrdenes) {
                System.out.println("    " + i + ". Orden #" + o.getIdOrden() +
                    " - " + o.getCliente() +
                    (i == 1 ? " <- SIGUIENTE (frente)" : ""));
                i++;
            }
        }

        if (!historialPizzas.isEmpty()) {
            System.out.println("\n  Historial de pizzas (Stack - LIFO):");
            Object[] arr = historialPizzas.toArray();
            for (int j = arr.length - 1; j >= 0; j--) {
                Pizza p = (Pizza) arr[j];
                System.out.println("    - " + p.getNombre() +
                    (j == arr.length - 1 ? " <- TOPE (ultima)" : ""));
            }
        }
    }
}