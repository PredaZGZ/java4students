public class Curso {
    private String id;
    private String nombre;
    private int duracionHoras;

    public Curso(String id, String nombre, int duracionHoras) {
        this.id = id;
        this.nombre = nombre;
        this.duracionHoras = duracionHoras;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public int getDuracionHoras() { return duracionHoras; }

    @Override
    public String toString() {
        return id + "," + nombre + "," + duracionHoras;
    }

    public static Curso fromCSV(String linea) {
        String[] partes = linea.split(",");
        return new Curso(partes[0], partes[1], Integer.parseInt(partes[2]));
    }
}
