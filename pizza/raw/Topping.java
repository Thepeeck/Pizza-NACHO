public class Topping {
    private String Tipo;
    private int esMariscos;

    public Topping(String Tipo, int esMariscos) {
        this.Tipo = Tipo;
        this.esMariscos = esMariscos;
    }
    public String getTipo() {
        return Tipo;
    }

    public boolean getMariscos() {
        return esMariscos == 1;
    }

    public void preparar() {
        System.out.println("Preparando " + Tipo);
    }

    public String tipoTopping() {
        if (esMariscos == 1) {
            return "Mariscos";
        } else {
            return "No Mariscos";
        }
    } 

    public String getNombre() {
        return Tipo;
    }
    
}
