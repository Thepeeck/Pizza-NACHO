 enum EstadoCoccion {
    Crudo,
    CRUDO,
    COCIDO,
    QUEMADO
  }
  
class Pizza {
   public EstadoCoccion estado;
   
   Pizza() {
   	estado = EstadoCoccion.CRUDO;
   }
   
   Pizza(EstadoCoccion estadoActual) {
   	  estado = estadoActual;
   }
   
   public boolean estaQuemada(){
   		if (estado == EstadoCoccion.QUEMADO) {
            return true;
        }
        else {
            return false;
        }
   }
   
  }

public class Main { 
  public static void main(String[] args) { 
  
    Pizza estadoActual = new Pizza(EstadoCoccion.Crudo);
    System.out.println(estadoActual.estado);
    System.out.println(estadoActual.estaQuemada());
    
    
    
  } 
}