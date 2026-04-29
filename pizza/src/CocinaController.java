public class CocinaController {

    private Cocina    cocina;
    private Inventario inventario;

    public CocinaController(Cocina cocina, Inventario inventario) {
        this.cocina     = cocina;
        this.inventario = inventario;
    }

    public void recibirPedido(Orden o) {
        System.out.println("\n[CocinaController] Recibiendo orden #" + o.getIdOrden() + "...");
        cocina.recibirOrden(o);
    }

    public boolean verificarIngredientes(Pizza p) {
        if (p.getBase() == null) {
            System.out.println("  [!] La pizza no tiene base.");
            return false;
        }
        if (p.getSalsa() == null) {
            System.out.println("  [!] La pizza no tiene salsa.");
            return false;
        }
        return true;
    }

    public void notificarListo(Pizza p) {
        System.out.println("\n[CocinaController] Pizza '" + p.getNombre() + "' lista para entregar.");
        cocina.setEnProduccion(false);
    }

    public Cocina     getCocina()     { return cocina; }
    public Inventario getInventario() { return inventario; }
}