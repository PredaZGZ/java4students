import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Biblioteca {

    private ArrayList<Libro> libros;
    private final String fichero = "libros.txt";

    public Biblioteca() {
        this.libros = new ArrayList<>();
        this.cargarDesdeFichero();
    }

    // Cargar libros desde el fichero
    public void cargarDesdeFichero() {
        this.libros.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(fichero))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 4) {
                    String titulo = partes[0];
                    String autor = partes[1];
                    String isbn = partes[2];
                    int year = Integer.parseInt(partes[3]);
                    Libro libro = new Libro(titulo, autor, isbn, year);
                    this.libros.add(libro);
                }
            }
        } catch (IOException e) {
            System.err.println("No se pudo leer el fichero: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error al convertir el año a número.");
        }
    }

    // Guardar un libro en el fichero (añadir al final)
    public void guardarLibro(Libro libro) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichero, true))) {
            writer.write(libro.toLineaFichero());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("No se pudo escribir en el fichero: " + e.getMessage());
        }
    }

    // Añadir libro si no existe ISBN
    public boolean addLibro(Libro libro) {
        cargarDesdeFichero();
        for (Libro l : this.libros) {
            if (l.getIsbn().equals(libro.getIsbn())) {
                return false; // ISBN duplicado
            }
        }
        this.libros.add(libro);
        guardarLibro(libro);
        return true;
    }

    // Buscar por ISBN
    public Libro buscarPorIsbn(String isbn) {
        cargarDesdeFichero();
        for (Libro libro : this.libros) {
            if (libro.getIsbn().equalsIgnoreCase(isbn)) {
                return libro;
            }
        }
        return null;
    }

    // Buscar por autor (contiene texto)
    public ArrayList<Libro> buscarPorAutor(String autor) {
        cargarDesdeFichero();
        ArrayList<Libro> resultado = new ArrayList<>();
        for (Libro libro : this.libros) {
            if (libro.getAutor().toLowerCase().contains(autor.toLowerCase())) {
                resultado.add(libro);
            }
        }
        return resultado;
    }

    // Buscar por título (contiene texto)
    public ArrayList<Libro> buscarPorTitulo(String titulo) {
        cargarDesdeFichero();
        ArrayList<Libro> resultado = new ArrayList<>();
        for (Libro libro : this.libros) {
            if (libro.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                resultado.add(libro);
            }
        }
        return resultado;
    }

    // Obtener todos los libros
    public ArrayList<Libro> obtenerTodos() {
        cargarDesdeFichero();
        return this.libros;
    }
}
