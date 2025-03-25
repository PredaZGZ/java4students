import java.util.LinkedList;
import java.util.Scanner;

public class Restaurante {
    // Atributos especiales
    Scanner sc = new Scanner(System.in);

    // Atributos Restaurante
    LinkedList<Mesa> mesas = new LinkedList<>();  
    LinkedList<Plato> cartaPlatos = new LinkedList<>();
    LinkedList<Pedido> pedidos = new LinkedList<>();
    
    public void registrarMesa() {
        int numeroMesa;
        int capacidad;

        do { 
            System.out.println("Ingresa un número de mesa: ");
            numeroMesa = sc.nextInt();
        } while (numeroMesa <= 0);

        do { 
            System.out.println("Ingresa la capacidad de la mesa: ");
            capacidad = sc.nextInt();
        } while (capacidad <= 0);

        Mesa mesa = new Mesa(numeroMesa, capacidad);
        mesas.add(mesa);

        System.out.println("Mesa creada correctamente!");
        
    }

    private Plato crearPlato() {
        String codigoPlato; // P-01
        String nombrePlato;
        double precioPlato;

        System.out.println("Ingresa un código del plato: ");
        codigoPlato = sc.nextLine();
        sc.next();

        System.out.println("Ingresa un nombre del plato: ");
        nombrePlato = sc.nextLine();
        sc.next();

        do { 
            System.out.println("Ingresa un precio del plato: ");
            precioPlato = sc.nextDouble();
        } while (precioPlato <= 0);

        Plato plato = new Plato(codigoPlato, nombrePlato, precioPlato);
        return plato;
    }

    public void registrarPlatoCarta() {
        
        Plato plato = this.crearPlato();

        this.cartaPlatos.add(plato);

        System.out.println("Plato creado correctamente!");
    }

    public void registrarPedido() {
        Mesa mesaEncontrada = null;
        int numeroMesa;
        do { 
            System.out.println("Introduce el número de una mesa existente: ");
            numeroMesa = sc.nextInt();
            for (Mesa mesa : this.mesas) {
                if (numeroMesa == mesa.getNumero()) {
                    mesaEncontrada = mesa;
                    break;
                }
            }
            if (mesaEncontrada == null) {
                System.out.println("No se ha encontrado esa mesa");
            }
        } while (mesaEncontrada == null);

        LinkedList<Plato> listaPlatosPedidos = new LinkedList<Plato>();
        String codigo;
        boolean terminar = false;

        while(!terminar) {
            System.out.println("Introduce el código de los platos: ");
            System.out.println("Si se introduce un 0, parará de preguntar: ");
            codigo = sc.next();

            if(codigo.equals("0")) {
                terminar = true;
            } else {
                for (Plato plato : this.cartaPlatos) {
                    if (codigo.equals(plato.getCodigo())) {
                        listaPlatosPedidos.add(plato);
                        break;
                    }
                }
            }
        }
        
        Pedido pedido = new Pedido(mesaEncontrada, listaPlatosPedidos);
        pedidos.add(pedido);
        System.out.println("Pedido añadido correctamente");
    }

    private Pedido getPedidoNumeroMesa() {
        if (!this.pedidos.isEmpty()) {
            do {
                System.out.println("Introduce un número de mesa asociado al pedido: ");
                int numero = sc.nextInt();
                for (Pedido pedido : this.pedidos) {
                    if (numero == pedido.getNumeroMesa()) {
                        return pedido;
                    }
                }
                System.out.println("Introduce un número válido");
            } while (true);

        }
        return null;
    }

    private Plato platoByCodigo() {
        String codigo;
        if (!this.cartaPlatos.isEmpty()) {
            do{
                System.out.println("Introduce un código de un plato de la carta: ");
                codigo = sc.nextLine();sc.next();
                for (Plato plato : this.cartaPlatos) {
                    if(plato.getCodigo().equals(codigo)) {
                        return plato;
                    }
                }
                System.out.println("No se ha encontrado el plato.");
            } while(true);
        }
        return null;
    }

    private Mesa mesaByNumber() {
        int numero;
        if (!this.mesas.isEmpty()) {
            do { 
                System.out.println("Introduce el número de la nueva mesa: ");
                numero = sc.nextInt();
                for (Mesa mesa : this.mesas) {
                    if (mesa.getNumero() == numero) {
                        return mesa;
                    }
                }
                System.out.println("No se ha encontrado la mesa");
            } while (true);
        }
        return null;
    }

    public void menuModificarPedido() {
        Pedido pedido = this.getPedidoNumeroMesa();
        int opcion;
        do {        
            System.out.println("Que quieres hacer?:");
            System.out.println("1. Cambiar estado (Completado/No Completado).");
            System.out.println("2. Añadir Plato.");
            System.out.println("3. Borrar Plato.");
            System.out.println("4. Cambiar Mesa");
            System.out.println("5. Aplicar Descuento.");
            System.out.println("6. Borrar Pedido.");
            System.out.println("7. Salir");
            System.out.println("Introduce una opción: ");
            opcion = sc.nextInt();
        } while (opcion != 7);

        switch (opcion) {
            case 1 -> pedido.cambiarCompletado();
            case 2 -> {
                System.out.println("1. Nuevo Plato");
                System.out.println("2. Añadir Plato desde Carta");
                int subopcion = sc.nextInt();
                switch(subopcion) {
                    case 1 -> pedido.addPlato(this.crearPlato()); 
                    case 2 -> pedido.addPlato(this.platoByCodigo()); 
                }
            }
            case 3 -> {
                String codigo; Boolean control;
                do {
                    System.out.println("Introduce un código de un plato: ");
                    codigo = sc.nextLine();sc.next();
                    control = pedido.removePlato(codigo);
                    if (control) {
                        System.out.println("Se ha borrado el plato.");
                    } else {
                        System.out.println("No se ha borrado el plato.");
                    }
                } while (!control);
            }
            case 4 -> pedido.setMesa(this.mesaByNumber());
            case 5 -> pedido.aplicarDescuento(10);
            case 6 -> {
                this.pedidos.remove(pedido);
                System.out.println("Pedido borrado correctamente");
            }
        }
    }

    public void menuGestionPlatos() {
        int opcion;
        do {
            System.out.println("--- GESTIÓN DE PLATOS ---");
            System.out.println("1. Modificar plato");
            System.out.println("2. Borrar plato");
            System.out.println("3. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); 

            switch (opcion) {
                case 1 -> modificarPlato();
                case 2 -> borrarPlato();
                case 3 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 3);
    }

    private void modificarPlato() {
        if (cartaPlatos.isEmpty()) {
            System.out.println("No hay platos en la carta.");
            return;
        }

        System.out.println("--- MODIFICAR PLATO ---");
        mostrarPlatos();
        
        System.out.print("Ingrese el código del plato a modificar: ");
        String codigo = sc.nextLine();

        Plato plato = buscarPlatoPorCodigo(codigo);
        if (plato == null) {
            System.out.println("No se encontró un plato con ese código.");
            return;
        }

        System.out.println("\nPlato seleccionado:");
        System.out.println(plato);

        int opcion;
        do {
            System.out.println("¿Qué desea modificar?");
            System.out.println("1. Nombre");
            System.out.println("2. Precio");
            System.out.println("3. Cancelar");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); 

            switch (opcion) {
                case 1 -> modificarNombre(plato);
                case 2 -> modificarPrecio(plato);
                case 3 -> System.out.println("Modificación cancelada.");
                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 3);
    }

    private void modificarNombre(Plato plato) {
        System.out.print("Ingrese el nuevo nombre: ");
        String nuevoNombre = sc.nextLine();
        
        
    }

    private void modificarPrecio(Plato plato) {
        System.out.print("Ingrese el nuevo precio: ");
        double nuevoPrecio;
        
       
            nuevoPrecio = sc.nextDouble();
            sc.nextLine(); 
            
            if (nuevoPrecio <= 0) {
                System.out.println("El precio debe ser mayor que cero.");
                
            }
            
       
    }

    private void borrarPlato() {
        if (cartaPlatos.isEmpty()) {
            System.out.println("No hay platos en la carta.");
            return;
        }

        System.out.println("--- BORRAR PLATO ---");
        mostrarPlatos();
        
        System.out.print("Ingrese el código del plato a borrar: ");
        String codigo = sc.nextLine();

        Plato plato = buscarPlatoPorCodigo(codigo);
        if (plato == null) {
            System.out.println("No se encontró un plato con ese código.");
            return;
        }

        System.out.println("Plato seleccionado para borrar:");
        System.out.println(plato);


        cartaPlatos.remove(plato);
    }
    private Plato buscarPlatoPorCodigo(String codigo) {
        for (Plato plato : cartaPlatos) {
            if (plato.getCodigo().equalsIgnoreCase(codigo)) {
                return plato;
            }
        }
        return null;
    }

    private void mostrarPlatos() {
        if (cartaPlatos.isEmpty()) {
            System.out.println("No hay platos en la carta.");
            return;
        }

        System.out.println("LISTA DE PLATOS:");
        for (Plato plato : cartaPlatos) {
            System.out.println(plato);
        }
    }
}


