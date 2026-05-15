import java.util.ArrayList;
import java.util.List;

public class Orden {
    private int inOrden;
    private List<Pizza> pizzas;
    private String cliente;
    private String estado;
    private int recompeza;

    public Orden(int inOrden, String cliente, String estado, int recompeza) {
        this.inOrden = inOrden;
        this.cliente = cliente;
        this.estado = estado;
        this.recompeza = recompeza;
        this.pizzas = new ArrayList<>();
    }

    public int getInOrden() {
        return inOrden;
    }

    public void agregarPizza(Pizza pizza) {
        pizzas.add(pizza);
    }

    public double calcularTotal() {
        double total = 0;
        for (Pizza pizza : pizzas) {
            total += pizza.getPrecio();
        }
        return total;
    }

    public Pizza getPizzaEsperada() {
        if (pizzas.isEmpty()) return null;
        return pizzas.get(0);
    }

    public void mostrarOrden() {
        System.out.println("\n  --- Orden #" + inOrden + " ---");
        System.out.println("  Cliente   : " + cliente);
        System.out.println("  Estado    : " + estado);
        System.out.println("  Recompeza : " + recompeza + " pts");
        if (!pizzas.isEmpty()) {
            Pizza p = getPizzaEsperada();
            System.out.println("  Pizza esperada: " + p.getNombre());
            System.out.println("    Base    : " + (p.getBase() != null ? p.getBase().getGrosor() : "ninguna"));
            System.out.println("    Salsa   : " + (p.getSalsa() != null ? p.getSalsa().isPicante() : "ninguna"));
            System.out.println("    Topping : " + (p.getTopping() != null ? p.getTopping().getTipo() : "ninguno"));
        }
    }

        public List<Pizza> getPizzas() {
        return pizzas;
    }
    public String getCliente() {
        return cliente;
    }
    public String getEstado() {
        return estado;
    }
    public int getRecompeza() {
        return recompeza;
    }
    

}
