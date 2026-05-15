import java.util.Stack;

public class Pizza {
    private Base base;
    private Salsa salsa;
    private Topping topping;
    private String Nombre;
    private double precio;
    private String Estado;
    private Stack<String> pilaIngredientes = new Stack<>();

    public Pizza(Base base, Salsa salsa, Topping topping, String Nombre, double precio, String Estado) {
        this.base = base;
        this.salsa = salsa;
        this.topping = topping;
        this.Nombre = Nombre;
        this.precio = precio;
        this.Estado = Estado;
    }

    public double CookProgress () {
        double progress = 0;
        if (base != null) {
            progress += 0.3;
        }
        if (salsa != null) {
            progress += 0.2;
        }
        if (topping != null) {
            progress += 0.5;
        }
        return progress * 100;
    }

    public enum EstadoCoccion {
    CRUDO,
    MEDIO,
    COCIDO,
    QUEMADO
}

    public EstadoCoccion estado;

    Pizza() {
    estado = EstadoCoccion.CRUDO;
}

    Pizza(EstadoCoccion estadoActual) {
    estado = estadoActual;
}

    public boolean estaQuemada() {
    return estado == EstadoCoccion.QUEMADO;
}

    public Temporizador temporizador = new Temporizador(0L, 60000L);


    
    public void actualizarCoccion() {
    long tiempoCoccion = temporizador.getElapsed();
    if (tiempoCoccion < 3000) {
        estado = EstadoCoccion.CRUDO;
    } else if (tiempoCoccion < 6000) {
        estado = EstadoCoccion.MEDIO;
    } else if (tiempoCoccion <= 10000) {
        estado = EstadoCoccion.COCIDO;
    } else {
        estado = EstadoCoccion.QUEMADO;
    }


}
    public double getPrecio() {
        return precio;
    }

        public void pushIngrediente(String nombre) {
        pilaIngredientes.push(nombre);
        System.out.println("Ingrediente agregado: " + nombre);
        }
 

    public String getNombre() {
    return this.Nombre;
}
    public String popIngrediente() {
        if (!pilaIngredientes.isEmpty()) {
            System.out.println("Ingrediente removido: " + pilaIngredientes.peek());
            return null;
        }
            String ingredienteRemovido = pilaIngredientes.pop();
            System.out.println("Ingrediente removido: " + ingredienteRemovido);
            return ingredienteRemovido;
        }

        public void mostrarPizza() {
        System.out.println("Pizza: " + Nombre);
        System.out.println("Base: " + (base != null ? base.getGrosor() : "Sin base"));
        System.out.println("Salsa: " + (salsa != null ? salsa.isPicante() : "Sin salsa"));
        System.out.println("Topping: " + (topping != null ? topping.getNombre() : "Sin topping"));
        System.out.println("Precio: $" + precio);
        System.out.println("Estado: " + Estado);  }


        public Salsa getSalsa() {
    return this.salsa;
    }

    public Topping getTopping() {
    return this.topping;
    }

    public Base getBase() {
    return this.base;
    }

    public String getEstado() {
    return this.estado.toString();
    }

    public void setEstado(String estado) {
    this.estado = EstadoCoccion.valueOf(estado);
    }

    public void setBase(Base base) {
    this.base = base;
    }

    public void setSalsa(Salsa salsa) {
    this.salsa = salsa;
    }

    public void setTopping(Topping topping) {
    this.topping = topping;
    }

}