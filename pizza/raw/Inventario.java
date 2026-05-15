import java.util.List;

public class Inventario {
    private List<String> ingrediente;

    public Inventario(List<String> ingrediente) {
        this.ingrediente = ingrediente;
    }

    public boolean hayStack(String ingrediente) {
        return this.ingrediente.contains(ingrediente);
    }

    public void reducirStock(String ingrediente) {
        if (hayStack(ingrediente)) {
            System.out.println("Reducción de stock para " + ingrediente);
        } else {
            System.out.println("No hay suficiente stock para " + ingrediente);
        }
    
}
    public void reducirStock(String ingrediente, int cantidad) {
        if (hayStack(ingrediente)) {
            System.out.println("Reducción de stock para " + ingrediente + " en cantidad " + cantidad);
        } else {
            System.out.println("No hay suficiente stock para " + ingrediente);
        }
    }
public List<String> setIngrediente (List<String> ingrediente) {
    this.ingrediente = ingrediente;
    return this.ingrediente;
}
    public List<String> getIngrediente() {
        return this.ingrediente;
    }

    public void reponerStock(String ingrediente, int cantidad) {
        System.out.println("Reponiendo stock para " + ingrediente + " en cantidad " + cantidad);
    }

    public boolean tieneIngredientes(Pizza pizza) {
        boolean ok = true;
        if (pizza.getBase() != null &&
            !hayStack(pizza.getBase().getGrosor())) {
            System.out.println("  [!] Sin stock: " + pizza.getBase().getGrosor());
            ok = false;
        }
        return ok;
    }
}