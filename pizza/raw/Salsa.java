public class Salsa {
    private boolean esPicante;
    private int NivelPicante;

    public Salsa(boolean esPicante, int NivelPicante) {
        this.esPicante = esPicante;
        this.NivelPicante = NivelPicante;
    }

    public int setNivelPicante(int NivelPicante) {
        if (NivelPicante < 0) {
            this.NivelPicante = 0;
        } else if (NivelPicante > 10) {
            this.NivelPicante = 10;
        } else {
            this.NivelPicante = NivelPicante;
        }
        return this.NivelPicante;

    }
public boolean getEsPicante() { 
    return esPicante;
}

public int getNivelPicante() {
    return NivelPicante;
}

public boolean setEsPicante(boolean esPicante) {
    this.esPicante = esPicante;
    return this.esPicante;
}

public boolean isPicante() {
    return this.esPicante;
}

    public void preparar() {
        if (esPicante) {
            System.out.println("Preparando salsa picante con nivel de picante " + NivelPicante);
        } else {
            System.out.println("usando salsa no picante");
        }
    }


}

