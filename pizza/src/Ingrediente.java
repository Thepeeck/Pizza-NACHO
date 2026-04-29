public abstract class Ingrediente {

    private String nombre;
    private String descripcion;
    private int calorias;

    public Ingrediente(String nombre, String descripcion, int calorias) {
        this.nombre      = nombre;
        this.descripcion = descripcion;
        this.calorias    = calorias;
    }

     public String getNombre()      { return nombre; }
    public String getDescripcion() { return descripcion; }
    public int    getCalorias()    { return calorias; }
    public abstract void preparar();

    @Override
    public String toString() {
        return nombre + " (" + calorias + " cal)";
    }
}