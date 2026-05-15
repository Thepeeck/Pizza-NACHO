public class Ingrediente {
    private String nombre;
    private String descrpcion;
    private int calorias;

    public Ingrediente(String nombre, String descrpcion, int calorias) {
        this.nombre = nombre;
        this.descrpcion = descrpcion;
        this.calorias = calorias;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescrpcion() {
        return descrpcion;
    }

    public int getCalorias() {
        return calorias;
    }

    public void preparar() {
        System.out.println("Preparando " + nombre);
    }
}
