import java.util.ArrayList;

public class Orden {

    private int             idOrden;
    private ArrayList<Pizza> pizzas;
    private String          cliente;
    private boolean         estaEntregada; 
    private int             recompenza;

    public Orden(int idOrden, String cliente, int recompenza) {
        this.idOrden       = idOrden;
        this.cliente       = cliente;
        this.recompenza    = recompenza;
        this.pizzas        = new ArrayList<>();
        this.estaEntregada = false;
    }

    public void agregarPizza(Pizza pizza) {
        pizzas.add(pizza);
    }

    public double calcularTotal() {
        double total = 0;
        for (Pizza p : pizzas) total += p.calcularPrecio();
        return total;
    }

    public int             getIdOrden()      { return idOrden; }
    public ArrayList<Pizza> getPizzas()      { return pizzas; }
    public String          getCliente()      { return cliente; }
    public boolean         isEntregada()     { return estaEntregada; }
    public int             getRecompenza()   { return recompenza; }
    public void            setEntregada()    { this.estaEntregada = true; }

    public Pizza getPizzaEsperada() {
        if (pizzas.isEmpty()) return null;
        return pizzas.get(0);
    }

    public void mostrarOrden() {
        System.out.println("\n  --- Orden #" + idOrden + " ---");
        System.out.println("  Cliente   : " + cliente);
        System.out.println("  Estado    : " + (estaEntregada ? "ENTREGADA" : "PENDIENTE"));
        System.out.println("  Recompenza: " + recompenza + " puntos");
        System.out.println("  Pizza esperada:");
        if (pizzas.isEmpty()) {
            System.out.println("    [sin pizza definida]");
        } else {
            Pizza p = getPizzaEsperada();
            System.out.println("    Base  : " + (p.getBase()  != null ? p.getBase()  : "ninguna"));
            System.out.println("    Salsa : " + (p.getSalsa() != null ? p.getSalsa() : "ninguna"));
            System.out.println("    Toppings: " + p.getToppingNombres());
        }
    }
}