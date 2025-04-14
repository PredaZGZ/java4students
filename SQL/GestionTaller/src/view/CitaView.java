package view;

import dao.CitaDao;
import model.Cita;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Scanner;

public class CitaView {
    private final CitaDao dao = new CitaDao();
    private final Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n----- GESTIÓN DE CITAS -----");
            System.out.println("1. Agendar nueva cita");
            System.out.println("2. Ver todas las citas");
            System.out.println("3. Buscar cita por ID");
            System.out.println("4. Actualizar cita");
            System.out.println("5. Cancelar cita");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> agendarCita();
                case 2 -> mostrarCitas();
                case 3 -> buscarCitaPorId();
                case 4 -> actualizarCita();
                case 5 -> cancelarCita();
                case 0 -> System.out.println("Volviendo...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private void agendarCita() {
        Cita cita = new Cita();
        System.out.print("ID del vehículo: ");
        cita.setVehiculoId(scanner.nextInt());

        System.out.print("ID del empleado asignado: ");
        cita.setEmpleadoId(scanner.nextInt());

        System.out.print("Fecha (YYYY-MM-DD): ");
        cita.setFecha(Date.valueOf(scanner.next()));

        System.out.print("Hora (HH:MM:SS): ");
        cita.setHora(Time.valueOf(scanner.next()));

        scanner.nextLine(); // limpiar
        System.out.print("Descripción: ");
        cita.setDescripcion(scanner.nextLine());

        dao.agregarCita(cita);
        System.out.println("Cita registrada con ID: " + cita.getId());
    }

    private void mostrarCitas() {
        ArrayList<Cita> citas = dao.obtenerCitas();
        if (citas.isEmpty()) {
            System.out.println("No hay citas registradas.");
        } else {
            for (Cita cita : citas) {
                System.out.println(cita);
            }
        }
    }

    private void buscarCitaPorId() {
        System.out.print("Ingrese ID de la cita: ");
        int id = scanner.nextInt();
        Cita cita = dao.obtenerCitaPorId(id);
        if (cita != null) {
            System.out.println(cita);
        } else {
            System.out.println("Cita no encontrada.");
        }
    }

    private void actualizarCita() {
        System.out.print("ID de la cita a actualizar: ");
        int id = scanner.nextInt();
        if (!dao.existeCita(id)) {
            System.out.println("No existe una cita con ese ID.");
            return;
        }

        Cita cita = new Cita();
        cita.setId(id);

        System.out.print("Nuevo ID de vehículo: ");
        cita.setVehiculoId(scanner.nextInt());

        System.out.print("Nuevo ID de empleado: ");
        cita.setEmpleadoId(scanner.nextInt());

        System.out.print("Nueva fecha (YYYY-MM-DD): ");
        cita.setFecha(Date.valueOf(scanner.next()));

        System.out.print("Nueva hora (HH:MM:SS): ");
        cita.setHora(Time.valueOf(scanner.next()));

        scanner.nextLine(); // limpiar buffer
        System.out.print("Nueva descripción: ");
        cita.setDescripcion(scanner.nextLine());

        dao.actualizarCita(cita);
        System.out.println("Cita actualizada.");
    }

    private void cancelarCita() {
        System.out.print("ID de la cita a cancelar: ");
        int id = scanner.nextInt();
        if (dao.eliminarCita(id)) {
            System.out.println("Cita cancelada.");
        } else {
            System.out.println("No se pudo cancelar (verifique el ID).");
        }
    }
}
