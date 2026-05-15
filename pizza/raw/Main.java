import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> listaInventario = new ArrayList<>();
        listaInventario.add("Delgada");
        listaInventario.add("Gruesa");
        listaInventario.add("Normal");
        listaInventario.add("Salsa Normal");
        listaInventario.add("Salsa Picante");
        listaInventario.add("Queso Mozzarella");
        listaInventario.add("Pepperoni");
        listaInventario.add("pescado");
        listaInventario.add("camaron");
        listaInventario.add("Alga Marina");

        Inventario inventario = new Inventario(listaInventario);
        Cocina cocina = new Cocina();
        JuegoController juego = new JuegoController(cocina, inventario);

        juego.iniciarJuego();

        Pizza pizzaActual = null;
        Orden ordenActual = null;
        boolean Corriendo = true; 

        while (Corriendo) {
            imprimirMenu();
            
            if (pizzaActual != null) {
                pizzaActual.actualizarCoccion();
                System.out.println(">>> ESTADO ACTUAL: " + pizzaActual.estado + " <<<");
            }
            
            System.out.print("Selecciona una opcion: ");
            int opcion = leerEntero(scanner);

            switch (opcion) {
                case 1:
                    System.out.println("Cargando ordenes pendientes:");
                    Orden o1 = new Orden(1, "Agus", "Pendiente", 10);
                    Orden o2 = new Orden(2, "Peper", "Pendiente", 15);
                    Orden o3 = new Orden(3, "Brownie", "Pendiente", 20);
                    Orden o4 = new Orden(4, "thiago", "Pendiente", 25);
                    Orden o5 = new Orden(5, "Ibate", "Pendiente", 30);

                    o1.agregarPizza(new Pizza(new Base("Delgada", "Trigo"), new Salsa(false, 0), new Topping("Queso Mozzarella", 0), "Margarita", 8.99, "Nueva"));
                    o2.agregarPizza(new Pizza(new Base("Gruesa", "Trigo"), new Salsa(true, 3), new Topping("Pepperoni", 0), "Pepperoni ATTACK", 12.99, "Nueva"));
                    o3.agregarPizza(new Pizza(new Base("Normal", "Integral"), new Salsa(true, 2), new Topping("pescado", 0), "como se dice pescado en frances?", 15.99, "Nueva"));
                    o4.agregarPizza(new Pizza(new Base("Delgada", "Trigo"), new Salsa(true, 3), new Topping("camaron", 0), "ALERGIC CAMARON", 18.99, "Nueva"));
                    o5.agregarPizza(new Pizza(new Base("Gruesa", "Integral"), new Salsa(false, 0), new Topping("Alga Marina", 0), " THE DEEP", 20.99, "Nueva"));

                    cocina.recibirOrden(o1);
                    cocina.recibirOrden(o2);
                    cocina.recibirOrden(o3);
                    cocina.recibirOrden(o4);
                    cocina.recibirOrden(o5);
                    System.out.println("Se han cargado las 5 ordenes con exito");
                    break;

                case 2:
                    if (cocina.OrdenesPendientes() > 0) {
                        pizzaActual = cocina.ProcesosSiguientes();
                        ordenActual = new Orden(99, "Cliente", "Preparando", 20);
                        // Tiempo ajustado a 40 segundos (40000L) para que sea mas lento
                        pizzaActual.temporizador = new Temporizador(0L, 40000L);
                        pizzaActual.temporizador.iniciar();
                        System.out.println("Has tomado la orden: " + pizzaActual.getNombre());
                    } else {
                        System.out.println("No hay ordenes para cocinar");
                    }
                    break;

                case 3:
                    if (pizzaActual != null) {
                        pizzaActual = menuAgregaIngredientes(scanner, pizzaActual, inventario);
                    } else {
                        System.out.println("Necesitas una pizza primero");
                    }
                    break;

                case 4:
                    if (pizzaActual != null) {
                        pizzaActual.popIngrediente();
                    } else {
                        System.out.println("No hay pizza para quitar cosas");
                    }
                    break;

                case 5:
                    if (pizzaActual != null && ordenActual != null) {
                        pizzaActual.actualizarCoccion();
                        evaluaEntrega(ordenActual, pizzaActual, juego);
                        pizzaActual = null;
                        ordenActual = null;
                    } else {
                        System.out.println("No tienes nada que entregar");
                    }
                    break;

                case 6:
                    System.out.println("Puntaje: " + juego.getPuntaje());
                    if (pizzaActual != null) {
                        pizzaActual.actualizarCoccion();
                        System.out.println("Estado de coccion: " + pizzaActual.estado);
                        pizzaActual.mostrarPizza();
                    } else {
                        System.out.println("No hay pizza en el horno ahora mismo.");
                    }
                    break;

                case 7:
                    System.out.println("Saliendo del juego...");
                    Corriendo = false;
                    break;
            }
        }
    }

    private static Pizza menuAgregaIngredientes(Scanner scanner, Pizza pizzaActual, Inventario inventario) {
        System.out.println("\nQue categoria agregar?");
        System.out.println("1. Base  2. Salsa  3. Topping");
        int tipo = leerEntero(scanner);
        
        List<String> disponibles = inventario.getIngrediente();
        System.out.println("Selecciona el ingrediente de la lista:");
        for (int i = 0; i < disponibles.size(); i++) {
            System.out.println((i + 1) + ". " + disponibles.get(i));
        }
        
        int sel = leerEntero(scanner);
        if (sel > 0 && sel <= disponibles.size()) {
            String nombreIng = disponibles.get(sel - 1);
            
            // Asignacion por categoria segun lo que elijas
            if (tipo == 1) {
                pizzaActual.setBase(new Base(nombreIng, "Trigo"));
            } else if (tipo == 2) {
                pizzaActual.setSalsa(new Salsa(nombreIng.toLowerCase().contains("picante"), 5));
            } else if (tipo == 3) {
                pizzaActual.setTopping(new Topping(nombreIng, 0));
            }
            
            pizzaActual.pushIngrediente(nombreIng);
            System.out.println("Agregado correctamente: " + nombreIng);
        } else {
            System.out.println("Esa opcion no vale.");
        }
        return pizzaActual;
    }

    private static void evaluaEntrega(Orden orden, Pizza pizza, JuegoController juego) {
        if (pizza.estado == Pizza.EstadoCoccion.COCIDO) {
            System.out.println("Excelente entrega, ganaste puntos");
            juego.procesarOrden(orden);
        } else {
            System.out.println("Entrega fallida. La pizza estaba: " + pizza.estado);
        }
    }

    private static void imprimirMenu() {
        System.out.println("\n MENU PIZZERIA ");
        System.out.println("1. Ver ordenes");
        System.out.println("2. Cocinar orden");
        System.out.println("3. Poner ingredientes");
        System.out.println("4. Quitar ingrediente");
        System.out.println("5. Entregar");
        System.out.println("6. Ver estado y puntaje");
        System.out.println("7. Salir");
    }

    private static int leerEntero(Scanner scanner) {
        try { 
            return Integer.parseInt(scanner.nextLine().trim()); 
        } catch (Exception e) { 
            return -1; 
        }
    }
}