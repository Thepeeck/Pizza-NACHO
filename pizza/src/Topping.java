public class Topping extends Ingrediente {

    private String  tipo;
    private boolean esMariscos;

    public Topping(String nombre, String descripcion, int calorias,
                   String tipo, boolean esMariscos) {
        super(nombre, descripcion, calorias);
        this.tipo       = tipo;
        this.esMariscos = esMariscos;
    }

    public String  getTipo()       { return tipo; }
    public boolean getEsMariscos() { return esMariscos; }

    @Override
    public void preparar() {
        System.out.println("  >> Colocando " + getNombre() +
            (esMariscos ? " (mariscos)" : "") + " sobre la pizza...");
    }

    @Override
    public String toString() {
        return getNombre() + " [Topping - " + tipo + "]";
    }
}