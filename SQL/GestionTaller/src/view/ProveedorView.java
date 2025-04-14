package view;

import dao.ProveedorDao;
import model.Proveedor;

import java.util.ArrayList;
import java.util.Scanner;

public class ProveedorView {
    private final ProveedorDao dao = new ProveedorDao();
    private final Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n----- GESTIÓN DE PROVEEDORES -----");
            System.out.println("1. Agregar proveedor");
            System.out.println("2. Ver todos los proveedores");
            System.out.println("3. Buscar proveedor por ID");
            System.out.println("4. Actualizar proveedor");
            System.out.println("5. Eliminar proveedor");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1 -> agregarProveedor();
                case 2 -> mostrarProveedores();
                case 3 -> buscarProveedorPorId();
                case 4 -> actualizarProveedor();
                case 5 -> eliminarProveedor();
                case 0 -> System.out.println("Volviendo...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private void agregarProveedor() {
        Proveedor p = new Proveedor();

        System.out.print("Nombre: ");
        p.setNombre(scanner.nextLine());

        System.out.print("Teléfono: ");
        p.setTelefono(scanner.nextLine());

        System.out.print("Email: ");
        p.setEmail(scanner.nextLine());

        dao.agregarProveedor(p);
        System.out.println("Proveedor agregado con ID: " + p.getId());
    }

    private void mostrarProveedores() {
        ArrayList<Proveedor> lista = dao.obtenerProveedores();
        if (lista.isEmpty()) {
            System.out.println("No hay proveedores registrados.");
        } else {
            for (Proveedor p : lista) {
                System.out.println(p);
            }
        }
    }

    private void buscarProveedorPorId() {
        System.out.print("Ingrese el ID del proveedor: ");
        int id = scanner.nextInt();
        Proveedor p = dao.obtenerProveedorPorId(id);
        if (p != null) {
            System.out.println(p);
        } else {
            System.out.println("Proveedor no encontrado.");
        }
    }

    private void actualizarProveedor() {
        System.out.print("ID del proveedor a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (!dao.existeProveedor(id)) {
            System.out.println("No existe un proveedor con ese ID.");
            return;
        }

        Proveedor p = new Proveedor();
        p.setId(id);

        System.out.print("Nuevo nombre: ");
        p.setNombre(scanner.nextLine());

        System.out.print("Nuevo teléfono: ");
        p.setTelefono(scanner.nextLine());

        System.out.print("Nuevo email: ");
        p.setEmail(scanner.nextLine());

        dao.actualizarProveedor(p);
        System.out.println("Proveedor actualizado.");
    }

    private void eliminarProveedor() {
        System.out.print("ID del proveedor a eliminar: ");
        int id = scanner.nextInt();
        if (dao.eliminarProveedor(id)) {
            System.out.println("Proveedor eliminado.");
        } else {
            System.out.println("No se pudo eliminar (verifique el ID).");
        }
    }
}
