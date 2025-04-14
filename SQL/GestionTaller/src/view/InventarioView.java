package view;

import dao.InventarioDao;
import model.Inventario;

import java.util.ArrayList;
import java.util.Scanner;

public class InventarioView {
    private final InventarioDao dao = new InventarioDao();
    private final Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n----- GESTIÓN DE INVENTARIO -----");
            System.out.println("1. Agregar producto");
            System.out.println("2. Ver inventario");
            System.out.println("3. Buscar producto por ID");
            System.out.println("4. Actualizar producto");
            System.out.println("5. Eliminar producto");
            System.out.println("6. Ver productos bajo mínimo");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> agregarProducto();
                case 2 -> mostrarInventario();
                case 3 -> buscarProductoPorId();
                case 4 -> actualizarProducto();
                case 5 -> eliminarProducto();
                case 6 -> mostrarProductosBajoMinimo();
                case 0 -> System.out.println("Volviendo...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private void agregarProducto() {
        Inventario inv = new Inventario();

        System.out.print("Nombre del producto: ");
        inv.setNombreProducto(scanner.nextLine());

        System.out.print("Cantidad actual: ");
        inv.setCantidad(scanner.nextInt());

        System.out.print("Cantidad mínima: ");
        inv.setCantidadMinima(scanner.nextInt());

        System.out.print("ID del proveedor: ");
        inv.setProveedorId(scanner.nextInt());

        dao.agregarProducto(inv);
        System.out.println("Producto registrado con ID: " + inv.getId());
    }

    private void mostrarInventario() {
        ArrayList<Inventario> productos = dao.obtenerInventario();
        if (productos.isEmpty()) {
            System.out.println("No hay productos en el inventario.");
        } else {
            for (Inventario i : productos) {
                System.out.println(i);
            }
        }
    }

    private void buscarProductoPorId() {
        System.out.print("ID del producto: ");
        int id = scanner.nextInt();
        Inventario inv = dao.obtenerProductoPorId(id);
        if (inv != null) {
            System.out.println(inv);
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    private void actualizarProducto() {
        System.out.print("ID del producto a actualizar: ");
        int id = scanner.nextInt();

        if (!dao.existeProducto(id)) {
            System.out.println("No existe un producto con ese ID.");
            return;
        }

        Inventario inv = new Inventario();
        inv.setId(id);

        scanner.nextLine(); // limpiar
        System.out.print("Nuevo nombre: ");
        inv.setNombreProducto(scanner.nextLine());

        System.out.print("Nueva cantidad: ");
        inv.setCantidad(scanner.nextInt());

        System.out.print("Nueva cantidad mínima: ");
        inv.setCantidadMinima(scanner.nextInt());

        System.out.print("Nuevo ID de proveedor: ");
        inv.setProveedorId(scanner.nextInt());

        dao.actualizarProducto(inv);
        System.out.println("Producto actualizado.");
    }

    private void eliminarProducto() {
        System.out.print("ID del producto a eliminar: ");
        int id = scanner.nextInt();
        if (dao.eliminarProducto(id)) {
            System.out.println("Producto eliminado.");
        } else {
            System.out.println("No se pudo eliminar (verifique el ID).");
        }
    }

    private void mostrarProductosBajoMinimo() {
        ArrayList<Inventario> bajoMinimo = dao.obtenerProductosBajoMinimo();
        if (bajoMinimo.isEmpty()) {
            System.out.println("No hay productos por debajo del mínimo.");
        } else {
            System.out.println("Productos por debajo del stock mínimo:");
            for (Inventario i : bajoMinimo) {
                System.out.println(i);
            }
        }
    }
}
