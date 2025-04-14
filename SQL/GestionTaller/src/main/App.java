package main;

import dao.ConexionDB;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import view.*;

public class App {
    public static void main(String[] args) throws Exception {
        // Verificación de conexión inicial
        try (Connection conn = ConexionDB.getConnection()) {
            if (conn != null) {
                System.out.println("Conexión exitosa a la base de datos.");
            } else {
                System.out.println("Error al conectar a la base de datos.");
                return;
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            return;
        }

        // Instancias de las vistas
        ClienteView clienteView = new ClienteView();
        VehiculoView vehiculoView = new VehiculoView();
        EmpleadoView empleadoView = new EmpleadoView();
        CitaView citaView = new CitaView();
        ServicioView servicioView = new ServicioView();
        ProveedorView proveedorView = new ProveedorView();
        InventarioView inventarioView = new InventarioView();
        FlujoCajaView flujoCajaView = new FlujoCajaView();

        int opcion;
        Scanner sc = new Scanner(System.in, "UTF-8");

        do {
            System.out.println("\n======= SISTEMA DE GESTIÓN DE TALLER MECÁNICO =======");
            System.out.println("1. Gestión de Clientes");
            System.out.println("2. Gestión de Vehículos");
            System.out.println("3. Gestión de Servicios");
            System.out.println("4. Gestión de Citas");
            System.out.println("5. Gestión de Empleados");
            System.out.println("6. Gestión de Proveedores");
            System.out.println("7. Gestión de Inventario");
            System.out.println("8. Gestión Económica / Flujo de Caja");
            System.out.println("12. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1 -> clienteView.menuCliente();
                case 2 -> vehiculoView.menuVehiculo();
                case 3 -> servicioView.mostrarMenu();
                case 4 -> citaView.mostrarMenu();
                case 5 -> empleadoView.mostrarMenu();
                case 6 -> proveedorView.mostrarMenu();
                case 7 -> inventarioView.mostrarMenu();
                case 8 -> flujoCajaView.mostrarMenu();
                case 12 -> System.out.println("Saliendo del sistema. ¡Hasta luego!");
                default -> System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }

        } while (opcion != 12);
    }
}
