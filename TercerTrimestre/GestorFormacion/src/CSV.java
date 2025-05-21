import java.io.*;
import java.util.*;

public class CSV {

    public static final String CABECERA_CURSOS = "id,nombre,duracionHoras";
    public static final String CABECERA_ESTUDIANTES = "dni,nombre,email";
    public static final String CABECERA_MATRICULAS = "dniEstudiante,idCurso,fecha";

    public static ArrayList<String> leer(String nombreArchivo) {
        ArrayList<String> lineas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea = br.readLine(); // omitir cabecera
            while ((linea = br.readLine()) != null) {
                lineas.add(linea);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + nombreArchivo);
        }
        return lineas;
    }

    public static void escribir(String nombreArchivo, List<String> lineas, String cabecera) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo))) {
            bw.write(cabecera);
            bw.newLine();
            for (String l : lineas) {
                bw.write(l);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar en archivo: " + nombreArchivo);
        }
    }
}
