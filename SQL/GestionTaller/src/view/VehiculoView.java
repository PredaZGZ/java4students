package view;

import java.util.Scanner;

import dao.ClienteDao;
import dao.VehiculoDao;
import java.util.ArrayList;
import model.Vehiculo;

public class VehiculoView {

    Scanner scanner = new Scanner(System.in, "UTF-8");

    public void menuVehiculo() {
        int opcion;
        do {
            System.out.println("Menu de Vehiculos:");
            System.out.println("1. Agregar Vehiculo");
            System.out.println("2. Listar Vehiculos");
            System.out.println("3. Buscar Vehiculo por matricula");
            System.out.println("4. Actualizar Vehiculo");
            System.out.println("5. Asociar Vehiculo a Cliente");
            System.out.println("6. Eliminar Vehiculo");
            System.out.println("7. Salir");

            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer del scanner

            switch (opcion) {
                case 1 -> this.agregarVehiculo();
                case 2 -> this.listarVehiculos();
                case 3 -> this.buscarVehiculoPorMatricula();
                case 4 -> this.actualizarVehiculo();
                case 5 -> this.asociarVehiculoACliente();
                case 6 -> this.eliminarVehiculo();
                case 7 -> System.out.println("Saliendo del menú de vehículos.");
                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 7);
    }

    private void agregarVehiculo() {
        ClienteDao clienteDao = new ClienteDao();

        System.out.println("Ingrese la matricula del vehiculo:");
        String matricula = scanner.nextLine();
        System.out.println("Ingrese la marca del vehiculo:");
        String marca = scanner.nextLine();
        System.out.println("Ingrese el modelo del vehiculo:");
        String modelo = scanner.nextLine();
        scanner.nextLine(); // Limpiar el buffer

        Vehiculo vehiculo = new Vehiculo(matricula, marca, modelo);

        int clienteId;
        do { 
            System.out.println("Introduce el ID del cliente asociado al vehiculo:");
            clienteId = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer
        } while (!clienteDao.existeCliente(clienteId));

        vehiculo.setClienteId(clienteId);

        VehiculoDao vehiculoDao = new VehiculoDao();
        vehiculoDao.agregarVehiculo(vehiculo);
        System.out.println("Vehiculo agregado exitosamente.");
    }
    private void listarVehiculos() {
        VehiculoDao vehiculoDao = new VehiculoDao();
        ArrayList<Vehiculo> listaVehiculos = vehiculoDao.listarVehiculos();
        for (Vehiculo vehiculo : listaVehiculos) {
            System.out.println("ID: " + vehiculo.getId() + ", Matricula: " + vehiculo.getMatricula() + ", Marca: " + vehiculo.getMarca() + ", Modelo: " + vehiculo.getModelo());
        }
    }
    
    private void buscarVehiculoPorMatricula() {
        System.out.println("Ingrese la matricula del vehiculo a buscar:");
        String matricula = scanner.nextLine();
        VehiculoDao vehiculoDao = new VehiculoDao();
        Vehiculo vehiculo = vehiculoDao.buscarVehiculoPorMatricula(matricula);
        if (vehiculo != null) {
            System.out.println("Vehículo encontrado: " + vehiculo.getMatricula() + ", " + vehiculo.getMarca() + ", " + vehiculo.getModelo());
        } else {
            System.out.println("Vehículo no encontrado.");
        }
    }

    private void actualizarVehiculo() {
        System.out.println("Ingrese la matricula del vehiculo a actualizar:");
        String matricula = scanner.nextLine();
        VehiculoDao vehiculoDao = new VehiculoDao();
        Vehiculo vehiculo = vehiculoDao.buscarVehiculoPorMatricula(matricula);
        if (vehiculo != null) {
            System.out.println("Ingrese la nueva matricula del vehiculo:");
            String nuevaMatricula = scanner.nextLine();
            System.out.println("Ingrese la nueva marca del vehiculo:");
            String nuevaMarca = scanner.nextLine();
            System.out.println("Ingrese el nuevo modelo del vehiculo:");
            String nuevoModelo = scanner.nextLine();

            vehiculo.setMatricula(nuevaMatricula);
            vehiculo.setMarca(nuevaMarca);
            vehiculo.setModelo(nuevoModelo);

            vehiculoDao.actualizarVehiculo(vehiculo);
            System.out.println("Vehículo actualizado exitosamente.");
        } else {
            System.out.println("Vehículo no encontrado.");
        }
    }

    private void asociarVehiculoACliente() {
        System.out.println("Ingrese la matricula del vehiculo a asociar:");
        String matricula = scanner.nextLine();
        VehiculoDao vehiculoDao = new VehiculoDao();
        Vehiculo vehiculo = vehiculoDao.buscarVehiculoPorMatricula(matricula);
        if (vehiculo != null) {
            System.out.println("Ingrese el ID del cliente al que desea asociar el vehiculo:");
            int clienteId = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer
            vehiculo.setClienteId(clienteId);
            vehiculoDao.actualizarVehiculo(vehiculo);
            System.out.println("Vehículo asociado al cliente exitosamente.");
        } else {
            System.out.println("Vehículo no encontrado.");
        }
    }
    private void eliminarVehiculo() {
        System.out.println("Ingrese la matricula del vehiculo a eliminar:");
        String matricula = scanner.nextLine();
        VehiculoDao vehiculoDao = new VehiculoDao();
        if (vehiculoDao.eliminarVehiculo(matricula)) {
            System.out.println("Vehículo eliminado exitosamente.");
        } else {
            System.out.println("Vehículo no encontrado.");
        }
    }


}
