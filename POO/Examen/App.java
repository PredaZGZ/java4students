import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        Restaurante restaurante = new Restaurante();

        do {
            System.out.println("\nMenú de Gestión de Restaurante");
            System.out.println("1. Registrar Mesa");
            System.out.println("2. Registrar Plato");
            System.out.println("3. Registrar Pedido");
            System.out.println("4. Modificar Pedido");
            System.out.println("5. Modificar o Borrar Plato");
            System.out.println("6. Guardar Datos");
            System.out.println("7. Cargar Datos");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> restaurante.registrarMesa();
                case 2 -> restaurante.registrarPlatoCarta();
                case 3 -> restaurante.registrarPedido();
                case 4 -> restaurante.menuModificarPedido();
                case 5 -> restaurante.menuModificarPlato();
                case 6 -> {
                    //guardarDatos();
                }
                case 7 -> {
                    //cargarDatos();
                }
                case 8 -> {
                    //guardarDatos();
                    System.out.println("Saliendo del programa...");
                }
                default -> System.out.println("Opción no válida, intente nuevamente.");
            }
            
        } while (opcion != 8);
    }
}
