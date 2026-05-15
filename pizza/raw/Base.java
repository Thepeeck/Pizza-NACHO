public class Base {
    private String grosor;
    private String TipohHarina;


    public Base(String grosor, String TipohHarina) {
        this.grosor = grosor;
        this.TipohHarina = TipohHarina;
    }

    public String getGrosor() {
        return this.grosor;
    }

    public String getTipohHarina() {
        return this.TipohHarina;
    }

    public void preparar () {
        System.out.println("Preparando la base con grosor " + grosor + " y tipo de harina " + TipohHarina);
    }
}
