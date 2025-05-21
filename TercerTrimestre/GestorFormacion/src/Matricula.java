import java.time.LocalDate;

public class Matricula {
    private String dniEstudiante;
    private String idCurso;
    private LocalDate fecha;

    public Matricula(String dniEstudiante, String idCurso, LocalDate fecha) {
        this.dniEstudiante = dniEstudiante;
        this.idCurso = idCurso;
        this.fecha = fecha;
    }

    public String getDniEstudiante() { return dniEstudiante; }
    public String getIdCurso() { return idCurso; }
    public LocalDate getFecha() { return fecha; }

    @Override
    public String toString() {
        return dniEstudiante + "," + idCurso + "," + fecha;
    }

    public static Matricula fromCSV(String linea) {
        String[] partes = linea.split(",");
        return new Matricula(partes[0], partes[1], LocalDate.parse(partes[2]));
    }
}
