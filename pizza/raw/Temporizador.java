public class Temporizador {
    private Long Inicio;
    private Long limite;
    private boolean activo;

    public Temporizador(Long Inicio, Long limite) {
        this.Inicio = Inicio;
        this.limite = limite;
        this.activo = false;
    }

    public void iniciar() {
        this.Inicio = System.currentTimeMillis();
        this.activo = true;
    }

    public Long getElapsed () {
        if (activo) {
            return System.currentTimeMillis() - Inicio;
        } else {
            return 0L;
        }
    }

    public double getpct() {
        if (activo) {
            return (double) getElapsed() / limite * 100;
        } else {
            return 0.0;
        }
    }

    public Long getInicio() {
        return Inicio;
    }

    public Long getLimite() {
        return limite;
    }

    public long getTiempoRestante() {
        if (activo) {
            long tiempoRestante = limite - getElapsed();
            return Math.max(tiempoRestante, 0L);
        } else {
            return limite;
        }
    }
    public void reset() {
        this.Inicio = 0L;
        this.activo = false;
    }
}
