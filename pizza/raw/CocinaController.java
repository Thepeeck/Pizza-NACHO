public class CocinaController {
    private Cocina cocina;
    private Inventario inventario;

    public CocinaController(Cocina cocina, Inventario inventario) {
        this.cocina = cocina;
        this.inventario = inventario;
    }

    public void  recibirPedido(Orden orden) {
        System.out.println("Recibiendo orden #" + orden.getInOrden());
        for (Pizza pizza : orden.getPizzas()) {
            cocina.recibirOrden(new Orden(orden.getInOrden(), orden.getCliente(), orden.getEstado(), orden.getRecompeza()));
        }
    }
    
    public void notificarListo(Orden orden) {
        System.out.println("Orden #" + orden.getInOrden() + " está lista para entregar.");
    }
    
}
