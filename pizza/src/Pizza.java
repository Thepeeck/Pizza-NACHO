import java.util.ArrayList;
import java.util.Stack;

enum EstadoCoccion {
    Crudo,    
    CRUDO,    
    COCIDO,   
    QUEMADO   
}

public class Pizza {

    public EstadoCoccion estado;

    private Base   base;
    private Salsa  salsa;
    private String nombre;
    private double precio;


    private Stack<Ingrediente> pilaIngredientes;

  
    Pizza() {
        this.estado           = EstadoCoccion.Crudo;
        this.nombre           = "Pizza";
        this.precio           = 0.0;
        this.pilaIngredientes = new Stack<>();
    }


    Pizza(EstadoCoccion estadoActual) {
        this.estado           = estadoActual;
        this.nombre           = "Pizza";
        this.precio           = 0.0;
        this.pilaIngredientes = new Stack<>();
    }


    public Pizza(String nombre) {
        this.estado           = EstadoCoccion.Crudo;
        this.nombre           = nombre;
        this.precio           = 0.0;
        this.pilaIngredientes = new Stack<>();
    }


    public boolean estaQuemada() {
        if (estado == EstadoCoccion.QUEMADO) {
            return true;
        } else {
            return false;
        }
    }

    public boolean estaCruda() {
        return estado == EstadoCoccion.Crudo || estado == EstadoCoccion.CRUDO;
    }

    public boolean estaCocida() {
        return estado == EstadoCoccion.COCIDO;
    }

    public void cocinar(int segundos) {
        EstadoCoccion anterior = estado;

        if      (segundos <= 2)  estado = EstadoCoccion.Crudo;
        else if (segundos <= 5)  estado = EstadoCoccion.CRUDO;
        else if (segundos <= 10) estado = EstadoCoccion.COCIDO;
        else                     estado = EstadoCoccion.QUEMADO;

        System.out.println("  >> Horneando '" + nombre + "' por " + segundos + " segundo(s)...");
        System.out.println("     Antes  : " + anterior);
        System.out.println("     Ahora  : " + estado + "  " + getDescripcionEstado());
    }

  
    public void resetearCoccion() {
        estado = EstadoCoccion.Crudo;
        System.out.println("  >> Coccion reiniciada -> " + estado);
    }

    public String getDescripcionEstado() {
        if (estaQuemada())        return "(QUEMADA - perdiste puntos!)";
        if (estaCocida())         return "(perfecta!)";
        if (estado == EstadoCoccion.CRUDO) return "(necesita mas tiempo)";
        return "(recien creada)";
    }


    public boolean estaListaParaEntregar() {
        boolean lista = true;
        if (!tieneBase()) {
            System.out.println("  [!] Falta la base.");
            lista = false;
        }
        if (!tieneSalsa()) {
            System.out.println("  [!] Falta la salsa.");
            lista = false;
        }
        if (estaCruda()) {
            System.out.println("  [!] Esta cruda. Cocina mas tiempo (opcion 9).");
            lista = false;
        }
        if (estaQuemada()) {   
            System.out.println("  [!] Esta quemada. No se puede entregar.");
            lista = false;
        }
        return lista;
    }

    public boolean tieneBase()  { return base  != null; }

    public boolean tieneSalsa() { return salsa != null; }

    public int contarToppings() {
        int count = 0;
        for (Ingrediente ing : pilaIngredientes)
            if (ing instanceof Topping) count++;
        return count;
    }

    public void agregarBase(Base base) {
        this.base = base;
        pilaIngredientes.push(base);   
        base.preparar();
        calcularPrecio();
    }

    public void agregarSalsa(Salsa salsa) {
        this.salsa = salsa;
        pilaIngredientes.push(salsa);  
        salsa.preparar();
        calcularPrecio();
    }

    public void agregarTopping(Topping t) {
        pilaIngredientes.push(t);      
        t.preparar();
        calcularPrecio();
    }

    public Ingrediente deshacerUltimoIngrediente() {
        if (pilaIngredientes.isEmpty()) {
            System.out.println("  [!] No hay ingredientes que deshacer.");
            return null;
        }
        Ingrediente removido = pilaIngredientes.pop();  
        if (removido instanceof Base)  this.base  = null;
        if (removido instanceof Salsa) this.salsa = null;
        System.out.println("  >> [Stack POP] Deshecho: " + removido.getNombre());
        calcularPrecio();
        return removido;
    }

    public Ingrediente verUltimoIngrediente() {
        if (pilaIngredientes.isEmpty()) return null;
        return pilaIngredientes.peek();  
    }

    public double calcularPrecio() {
        double total = 0;
        for (Ingrediente ing : pilaIngredientes)
            total += ing.getCalorias() * 0.05;
        this.precio = Math.round(total * 100.0) / 100.0;
        return precio;
    }

    public void mostrarPizza() {
        System.out.println("\n  === PIZZA: " + nombre + " ===");
        System.out.println("  Coccion: " + estado + "  " + getDescripcionEstado());

        if (pilaIngredientes.isEmpty()) {
            System.out.println("  [vacia - sin ingredientes]");
            return;
        }
        Object[] arr = pilaIngredientes.toArray();
        System.out.println("  Ingredientes en el Stack (base -> tope):");
        for (int i = 0; i < arr.length; i++) {
            String marca = (i == arr.length - 1) ? " <- TOPE" : "";
            System.out.println("    " + (i+1) + ". " + arr[i] + marca);
        }
        System.out.println("  Toppings    : " + contarToppings());
        System.out.printf( "  Precio      : Q%.2f%n", precio);
        System.out.println("  Para entregar: " + (estaListaParaEntregar() ? "SI" : "NO"));
    }

    public Base               getBase()            { return base; }
    public Salsa              getSalsa()            { return salsa; }
    public Stack<Ingrediente> getPilaIngredientes() { return pilaIngredientes; }
    public String             getNombre()           { return nombre; }
    public double             getPrecio()           { return precio; }

    public ArrayList<String> getToppingNombres() {
        ArrayList<String> lista = new ArrayList<>();
        for (Ingrediente ing : pilaIngredientes)
            if (ing instanceof Topping)
                lista.add(ing.getNombre().toLowerCase());
        return lista;
    }
}