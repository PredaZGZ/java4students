public class Estudiante {
    private String dni;
    private String nombre;
    private String email;

    public Estudiante(String dni, String nombre, String email) {
        this.dni = dni;
        this.nombre = nombre;
        this.email = email;
    }

    public String getDni() { return dni; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return dni + "," + nombre + "," + email;
    }

    public static Estudiante fromCSV(String linea) {
        String[] partes = linea.split(",");
        return new Estudiante(partes[0], partes[1], partes[2]);
    }
}
