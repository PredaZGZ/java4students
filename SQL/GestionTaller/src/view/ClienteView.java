package view;

import dao.ClienteDao;
import java.util.Scanner;
import model.Cliente;

public class ClienteView {

    Scanner scanner = new Scanner(System.in, "UTF-8");
    
    public void menuCliente() {
        int opcion = 0;
        do { 
            System.out.println("Menu de Clientes:");
            System.out.println("1. Agregar Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("3. Buscar Cliente por ID");
            System.out.println("4. Actualizar Cliente");
            System.out.println("5. Eliminar Cliente");
            System.out.println("6. Salir");
            
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer del scanner

            switch (opcion) {
                case 1 -> this.agregarCliente();
                case 2 -> this.listarClientes();
                case 3 -> this.buscarClientePorId();
                case 4 -> this.actualizarCliente();
                case 5 -> this.eliminarCliente();
                case 6 -> System.out.println("Saliendo del menú de clientes.");
                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 6);
    }

    private void agregarCliente() {
        System.out.println("Ingrese el nombre del cliente:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese el apellido del cliente:");
        String apellido = scanner.nextLine();
        System.out.println("Ingrese el telefono del cliente:");
        String telefono = scanner.nextLine();
        System.out.println("Ingrese el email del cliente:");
        String email = scanner.nextLine();

        Cliente cliente = new Cliente(nombre, apellido, telefono, email);
        ClienteDao clienteDao = new ClienteDao();
        clienteDao.agregarCliente(cliente);
        System.out.println("Cliente agregado exitosamente con ID: " + cliente.getId());
        // Esto tiene id porque se le asigna en ClienteDAO a ese objeto cliente
        // al momento de agregarlo a la base de datos.
    }

    private void listarClientes() {
        System.out.println("Lista de Clientes:");
        ClienteDao clienteDao = new ClienteDao();
        for (Cliente cliente : clienteDao.obtenerClientes()) {
            System.out.println(cliente);
        }
    }

    private void buscarClientePorId() {
        System.out.println("Ingrese el ID del cliente a buscar:");
        int id = scanner.nextInt();
        ClienteDao clienteDao = new ClienteDao();
        Cliente cliente = clienteDao.obtenerClientePorId(id);
        if (cliente != null) {
            System.out.println(cliente);
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    private void actualizarCliente() {
        System.out.println("Ingrese el ID del cliente a actualizar:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer del scanner
        ClienteDao clienteDao = new ClienteDao();
        Cliente cliente = clienteDao.obtenerClientePorId(id);
        if (cliente != null) {
            System.out.println("Ingrese el nuevo nombre del cliente:");
            String nombre = scanner.nextLine();
            System.out.println("Ingrese el nuevo apellido del cliente:");
            String apellido = scanner.nextLine();
            System.out.println("Ingrese el nuevo telefono del cliente:");
            String telefono = scanner.nextLine();
            System.out.println("Ingrese el nuevo email del cliente:");
            String email = scanner.nextLine();

            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setTelefono(telefono);
            cliente.setEmail(email);

            clienteDao.actualizarCliente(cliente);
            System.out.println("Cliente actualizado exitosamente.");
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    private void eliminarCliente() {
        System.out.println("Ingrese el ID del cliente a eliminar:");
        int id = scanner.nextInt();
        ClienteDao clienteDao = new ClienteDao();
        if (clienteDao.eliminarCliente(id)) {
            System.out.println("Cliente eliminado exitosamente.");
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }
}