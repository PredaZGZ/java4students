package view;

import dao.ServicioDao;
import model.Servicio;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

public class ServicioView {
    private final ServicioDao dao = new ServicioDao();
    private final Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n----- GESTIÓN DE SERVICIOS -----");
            System.out.println("1. Registrar nuevo servicio");
            System.out.println("2. Ver todos los servicios");
            System.out.println("3. Buscar servicio por ID");
            System.out.println("4. Actualizar servicio");
            System.out.println("5. Eliminar servicio");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1 -> registrarServicio();
                case 2 -> mostrarServicios();
                case 3 -> buscarServicioPorId();
                case 4 -> actualizarServicio();
                case 5 -> eliminarServicio();
                case 0 -> System.out.println("Volviendo...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private void registrarServicio() {
        Servicio servicio = new Servicio();

        System.out.print("ID de la cita asociada: ");
        servicio.setCitaId(scanner.nextInt());

        scanner.nextLine(); // limpiar buffer
        System.out.print("Descripción del servicio: ");
        servicio.setDescripcion(scanner.nextLine());

        System.out.print("Precio: ");
        servicio.setPrecio(scanner.nextDouble());

        System.out.print("Fecha (YYYY-MM-DD): ");
        servicio.setFecha(Date.valueOf(scanner.next()));

        dao.agregarServicio(servicio);
        System.out.println("Servicio registrado con ID: " + servicio.getId());
    }

    private void mostrarServicios() {
        ArrayList<Servicio> servicios = dao.obtenerServicios();
        if (servicios.isEmpty()) {
            System.out.println("No hay servicios registrados.");
        } else {
            for (Servicio s : servicios) {
                System.out.println(s);
            }
        }
    }

    private void buscarServicioPorId() {
        System.out.print("Ingrese ID del servicio: ");
        int id = scanner.nextInt();
        Servicio servicio = dao.obtenerServicioPorId(id);
        if (servicio != null) {
            System.out.println(servicio);
        } else {
            System.out.println("Servicio no encontrado.");
        }
    }

    private void actualizarServicio() {
        System.out.print("ID del servicio a actualizar: ");
        int id = scanner.nextInt();

        if (!dao.existeServicio(id)) {
            System.out.println("No existe un servicio con ese ID.");
            return;
        }

        Servicio servicio = new Servicio();
        servicio.setId(id);

        System.out.print("Nuevo ID de cita: ");
        servicio.setCitaId(scanner.nextInt());

        scanner.nextLine(); // limpiar
        System.out.print("Nueva descripción: ");
        servicio.setDescripcion(scanner.nextLine());

        System.out.print("Nuevo precio: ");
        servicio.setPrecio(scanner.nextDouble());

        System.out.print("Nueva fecha (YYYY-MM-DD): ");
        servicio.setFecha(Date.valueOf(scanner.next()));

        dao.actualizarServicio(servicio);
        System.out.println("Servicio actualizado.");
    }

    private void eliminarServicio() {
        System.out.print("ID del servicio a eliminar: ");
        int id = scanner.nextInt();
        if (dao.eliminarServicio(id)) {
            System.out.println("Servicio eliminado.");
        } else {
            System.out.println("No se pudo eliminar el servicio (verifique el ID).");
        }
    }
}
