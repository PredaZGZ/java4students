import java.util.ArrayList;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Biblioteca biblioteca = new Biblioteca();

        int opcion;

        do {
            System.out.println("\n----- GESTIÓN DE BIBLIOTECA -----");
            System.out.println("1. Añadir libro");
            System.out.println("2. Buscar por ISBN");
            System.out.println("3. Buscar por autor");
            System.out.println("4. Buscar por título");
            System.out.println("5. Mostrar todos los libros");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            // Leer opción como número
            while (!scanner.hasNextInt()) {
                System.out.print("Por favor, introduzca un número válido: ");
                scanner.next(); // consumir entrada no válida
            }

            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar el salto de línea pendiente

            switch (opcion) {
                case 1 -> {
                    añadirLibro(biblioteca, scanner);
                }
                case 2 -> {
                    buscarPorIsbn(biblioteca, scanner);
                }
                case 3 -> {
                    buscarPorAutor(biblioteca, scanner);
                }
                case 4 -> {
                    buscarPorTitulo(biblioteca, scanner);
                }
                case 5 -> {
                    mostrarTodos(biblioteca);
                }
                case 6 -> {
                    System.out.println("Programa finalizado.");
                }
                default -> {
                    System.out.println("Opción no válida.");
                }
            }

        } while (opcion != 6);

        scanner.close();
    }

    private static void añadirLibro(Biblioteca biblioteca, Scanner scanner) {
        System.out.print("Título: ");
        String titulo = scanner.nextLine().trim();

        System.out.print("Autor: ");
        String autor = scanner.nextLine().trim();

        System.out.print("ISBN: ");
        String isbn = scanner.nextLine().trim();

        System.out.print("Año de publicación: ");
        String yearStr = scanner.nextLine().trim();

        if (titulo.isEmpty() || autor.isEmpty() || isbn.isEmpty() || yearStr.isEmpty()) {
            System.out.println("Ningún campo puede estar vacío.");
            return;
        }

        int year;
        try {
            year = Integer.parseInt(yearStr);
        } catch (NumberFormatException e) {
            System.out.println("El año debe ser un número.");
            return;
        }

        Libro libro = new Libro(titulo, autor, isbn, year);
        boolean añadido = biblioteca.addLibro(libro);

        if (añadido) {
            System.out.println("Libro añadido correctamente.");
        } else {
            System.out.println("Ya existe un libro con ese ISBN.");
        }
    }

    private static void buscarPorIsbn(Biblioteca biblioteca, Scanner scanner) {
        System.out.print("Introduce el ISBN a buscar: ");
        String isbn = scanner.nextLine().trim();

        Libro libro = biblioteca.buscarPorIsbn(isbn);
        if (libro != null) {
            System.out.println("\nLibro encontrado:\n" + libro);
        } else {
            System.out.println("No se encontró ningún libro con ese ISBN.");
        }
    }

    private static void buscarPorAutor(Biblioteca biblioteca, Scanner scanner) {
        System.out.print("Introduce parte del nombre del autor: ");
        String autor = scanner.nextLine().trim();

        ArrayList<Libro> resultados = biblioteca.buscarPorAutor(autor);
        if (resultados.isEmpty()) {
            System.out.println("No se encontraron libros con ese autor.");
        } else {
            for (Libro libro : resultados) {
                System.out.println("\n" + libro);
            }
        }
    }

    private static void buscarPorTitulo(Biblioteca biblioteca, Scanner scanner) {
        System.out.print("Introduce parte del título: ");
        String titulo = scanner.nextLine().trim();

        ArrayList<Libro> resultados = biblioteca.buscarPorTitulo(titulo);
        if (resultados.isEmpty()) {
            System.out.println("No se encontraron libros con ese título.");
        } else {
            for (Libro libro : resultados) {
                System.out.println("\n" + libro);
            }
        }
    }

    private static void mostrarTodos(Biblioteca biblioteca) {
        ArrayList<Libro> todos = biblioteca.obtenerTodos();
        if (todos.isEmpty()) {
            System.out.println("No hay libros registrados.");
        } else {
            for (Libro libro : todos) {
                System.out.println("\n" + libro);
            }
        }
    }
}
