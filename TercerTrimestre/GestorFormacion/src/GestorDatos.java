import java.time.LocalDate;
import java.util.*;

public class GestorDatos {
    public ArrayList<Curso> cursos = new ArrayList<>();
    public ArrayList<Estudiante> estudiantes = new ArrayList<>();
    public ArrayList<Matricula> matriculas = new ArrayList<>();

    public void cargarDatos() {
        for (String linea : CSV.leer("cursos.csv"))
            cursos.add(Curso.fromCSV(linea));
        for (String linea : CSV.leer("estudiantes.csv"))
            estudiantes.add(Estudiante.fromCSV(linea));
        for (String linea : CSV.leer("matriculas.csv"))
            matriculas.add(Matricula.fromCSV(linea));
    }

    public void guardarDatos() {
        ArrayList<String> outCursos = new ArrayList<>();
        for (Curso c : cursos) {
            outCursos.add(c.toString());
        }
        CSV.escribir("cursos.csv", outCursos, CSV.CABECERA_CURSOS);

        ArrayList<String> outEstudiantes = new ArrayList<>();
        for (Estudiante e : estudiantes) {
            outEstudiantes.add(e.toString());
        }
        CSV.escribir("estudiantes.csv", outEstudiantes, CSV.CABECERA_ESTUDIANTES);

        ArrayList<String> outMatriculas = new ArrayList<>();
        for (Matricula m : matriculas) {
            outMatriculas.add(m.toString());
        }
        CSV.escribir("matriculas.csv", outMatriculas, CSV.CABECERA_MATRICULAS);
    }

    public Curso buscarCurso(String id) {
        for (Curso c : cursos) if (c.getId().equals(id)) return c;
        return null;
    }

    public Estudiante buscarEstudiante(String dni) {
        for (Estudiante e : estudiantes) if (e.getDni().equals(dni)) return e;
        return null;
    }

    public boolean yaMatriculado(String dni, String idCurso) {
        for (Matricula m : matriculas)
            if (m.getDniEstudiante().equals(dni) && m.getIdCurso().equals(idCurso))
                return true;
        return false;
    }

    public void registrarCurso(Curso c) {
        cursos.add(c);
    }

    public void registrarEstudiante(Estudiante e) {
        estudiantes.add(e);
    }

    public boolean matricular(String dni, String idCurso) {
        if (buscarEstudiante(dni) == null || buscarCurso(idCurso) == null || yaMatriculado(dni, idCurso))
            return false;
        matriculas.add(new Matricula(dni, idCurso, LocalDate.now()));
        return true;
    }

    public void mostrarMatriculas() {
        for (Matricula m : matriculas) {
            Estudiante e = buscarEstudiante(m.getDniEstudiante());
            Curso c = buscarCurso(m.getIdCurso());
            System.out.println("[" + m.getFecha() + "] " + e.getNombre() + " → " + c.getNombre());
        }
    }
}
