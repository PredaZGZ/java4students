public class Libro {

    private String titulo;
    private String autor;
    private String isbn;
    private int year;

    // Constructor
    public Libro(String titulo, String autor, String isbn, int year) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.year = year;
    }

    // Getters
    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getYear() {
        return year;
    }

    // Representación del libro como línea de texto para fichero
    public String toLineaFichero() {
        return titulo + ";" + autor + ";" + isbn + ";" + year;
    }

    // Método toString para mostrar por consola
    @Override
    public String toString() {
        return "Título: " + titulo + "\nAutor: " + autor + "\nISBN: " + isbn + "\nAño: " + year;
    }

}
