public class Salsa extends Ingrediente {

    private boolean esPicante;
    private int     nivelPicante; 

    public Salsa(String nombre, String descripcion, int calorias,
                 boolean esPicante, int nivelPicante) {
        super(nombre, descripcion, calorias);
        this.esPicante    = esPicante;
        this.nivelPicante = nivelPicante;
    }

    public boolean getEsPicante()    { return esPicante; }
    public int     getNivelPicante() { return nivelPicante; }

    public void setEsPicante(boolean esPicante)    { this.esPicante    = esPicante; }
    public void setNivelPicante(int nivelPicante)  { this.nivelPicante = nivelPicante; }

    @Override
    public void preparar() {
        System.out.println("  >> Vertiendo " + getNombre() +
            (esPicante ? " PICANTE (nivel " + nivelPicante + ")" : "") + "...");
    }

    @Override
    public String toString() {
        return getNombre() + " [Salsa" + (esPicante ? " picante" : "") + "]";
    }
}