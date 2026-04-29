import java.util.HashMap;
import java.util.Map;

public class Inventario {
    private HashMap<String, Ingrediente> ingredientes;

    public Inventario() {
        this.ingredientes = new HashMap<>();
    }

    public void setIngrediente(String nombre, Ingrediente ing) {
        ingredientes.put(nombre.toLowerCase(), ing);
    }

    public Ingrediente getIngrediente(String nombre) {
        return ingredientes.get(nombre.toLowerCase());
    }

    public boolean hayStock(String nombre) {
        return ingredientes.containsKey(nombre.toLowerCase());
    }

    public void reducirStock(String nombre) {
        System.out.println("  [Inventario] Usando: " + nombre);
    }

    public void reponer(String nombre) {
        System.out.println("  [Inventario] Reponiendo: " + nombre);
    }

    public void mostrarInventario() {
        System.out.println("\n  === INVENTARIO DISPONIBLE ===");
        System.out.println("\n  BASES:");
        for (Map.Entry<String, Ingrediente> e : ingredientes.entrySet()) {
            if (e.getValue() instanceof Base)
                System.out.println("    - " + e.getValue());
        }
        System.out.println("\n  SALSAS:");
        for (Map.Entry<String, Ingrediente> e : ingredientes.entrySet()) {
            if (e.getValue() instanceof Salsa)
                System.out.println("    - " + e.getValue());
        }
        System.out.println("\n  TOPPINGS:");
        for (Map.Entry<String, Ingrediente> e : ingredientes.entrySet()) {
            if (e.getValue() instanceof Topping)
                System.out.println("    - " + e.getValue());
        }
    }
}