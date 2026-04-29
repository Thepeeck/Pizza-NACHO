public class Base extends Ingrediente {

    private String grosor;
    private String tipoHarina;

    public Base(String nombre, String descripcion, int calorias,
                String grosor, String tipoHarina) {
        super(nombre, descripcion, calorias);
        this.grosor     = grosor;
        this.tipoHarina = tipoHarina;
    }

    public String getGrosor()     { return grosor; }
    public String getTipoHarina() { return tipoHarina; }

    @Override
    public void preparar() {
        System.out.println("  >> Extendiendo masa " + grosor + " de " + tipoHarina + "...");
    }

    @Override
    public String toString() {
        return getNombre() + " [Base - " + grosor + "]";
    }
}
