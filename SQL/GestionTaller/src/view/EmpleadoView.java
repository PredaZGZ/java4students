package view;

import dao.EmpleadoDao;
import model.Empleado;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

public class EmpleadoView {
    private final EmpleadoDao dao = new EmpleadoDao();
    private final Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n----- GESTIÓN DE EMPLEADOS -----");
            System.out.println("1. Agregar empleado");
            System.out.println("2. Ver empleados");
            System.out.println("3. Buscar empleado por ID");
            System.out.println("4. Actualizar empleado");
            System.out.println("5. Eliminar empleado");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1 -> agregarEmpleado();
                case 2 -> mostrarEmpleados();
                case 3 -> buscarPorId();
                case 4 -> actualizarEmpleado();
                case 5 -> eliminarEmpleado();
                case 0 -> System.out.println("Volviendo...");
                default -> System.out.println("Opción inválida");
            }
        } while (opcion != 0);
    }

    private void agregarEmpleado() {
        Empleado emp = new Empleado();
        System.out.print("Nombre: ");
        emp.setNombre(scanner.nextLine());

        System.out.print("Teléfono: ");
        emp.setTelefono(scanner.nextLine());

        System.out.print("Email: ");
        emp.setEmail(scanner.nextLine());

        System.out.print("Puesto: ");
        emp.setPuesto(scanner.nextLine());

        System.out.print("Salario: ");
        emp.setSalario(scanner.nextDouble());

        System.out.print("Fecha de contratación (YYYY-MM-DD): ");
        emp.setFechaContratacion(Date.valueOf(scanner.next()));

        dao.agregarEmpleado(emp);
        System.out.println("Empleado agregado con ID: " + emp.getId());
    }

    private void mostrarEmpleados() {
        ArrayList<Empleado> empleados = dao.obtenerEmpleados();
        System.out.println("\nLista de empleados:");
        for (Empleado emp : empleados) {
            System.out.println(emp);
        }
    }

    private void buscarPorId() {
        System.out.print("Ingrese el ID del empleado: ");
        int id = scanner.nextInt();
        Empleado emp = dao.obtenerEmpleadoPorId(id);
        if (emp != null) {
            System.out.println(emp);
        } else {
            System.out.println("Empleado no encontrado.");
        }
    }

    private void actualizarEmpleado() {
        System.out.print("ID del empleado a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (!dao.existeEmpleado(id)) {
            System.out.println("No existe un empleado con ese ID.");
            return;
        }

        Empleado emp = new Empleado();
        emp.setId(id);

        System.out.print("Nuevo nombre: ");
        emp.setNombre(scanner.nextLine());

        System.out.print("Nuevo teléfono: ");
        emp.setTelefono(scanner.nextLine());

        System.out.print("Nuevo email: ");
        emp.setEmail(scanner.nextLine());

        System.out.print("Nuevo puesto: ");
        emp.setPuesto(scanner.nextLine());

        System.out.print("Nuevo salario: ");
        emp.setSalario(scanner.nextDouble());

        System.out.print("Nueva fecha de contratación (YYYY-MM-DD): ");
        emp.setFechaContratacion(Date.valueOf(scanner.next()));

        dao.actualizarEmpleado(emp);
        System.out.println("Empleado actualizado.");
    }

    private void eliminarEmpleado() {
        System.out.print("ID del empleado a eliminar: ");
        int id = scanner.nextInt();
        if (dao.eliminarEmpleado(id)) {
            System.out.println("Empleado eliminado.");
        } else {
            System.out.println("No se pudo eliminar (verifique el ID).");
        }
    }
}
