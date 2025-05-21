import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        GestorDatos gestor = new GestorDatos();
        gestor.cargarDatos();

        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\nMENÚ:");
            System.out.println("1. Registrar estudiante");
            System.out.println("2. Crear curso");
            System.out.println("3. Matricular estudiante");
            System.out.println("4. Listar estudiantes");
            System.out.println("5. Listar cursos");
            System.out.println("6. Ver matrículas");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = Integer.parseInt(sc.nextLine()); // asi me evito el salto de linea y que me metan una cosa que no es un int y saltará error


            switch (opcion) {
                case 1 -> {
                    System.out.print("DNI: ");
                    String dni = sc.nextLine();
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    gestor.registrarEstudiante(new Estudiante(dni, nombre, email));
                    System.out.println("Estudiante registrado.");
                }
                case 2 -> {
                    System.out.print("ID curso: ");
                    String id = sc.nextLine();
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Duración (h): ");
                    int duracion = Integer.parseInt(sc.nextLine());
                    gestor.registrarCurso(new Curso(id, nombre, duracion));
                    System.out.println("Curso creado.");
                }
                case 3 -> {
                    System.out.print("DNI estudiante: ");
                    String dni = sc.nextLine();
                    System.out.print("ID curso: ");
                    String id = sc.nextLine();
                    if (gestor.matricular(dni, id))
                        System.out.println("Matriculación realizada.");
                    else
                        System.out.println("Error: Verifique datos o estudiante ya matriculado.");
                }
                case 4 -> {
                    System.out.println("Lista de estudiantes:");
                    for (Estudiante estudiante : gestor.estudiantes) {
                        System.out.println(estudiante.getDni() + " - " + estudiante.getNombre() + " - " + estudiante.getEmail());
                    }
                }
                case 5 -> {
                    System.out.println("Lista de cursos:");
                    for (Curso curso : gestor.cursos) {
                        System.out.println(curso.getId() + " - " + curso.getNombre() + " - " + curso.getDuracionHoras() + " horas");
                    }
                }
                case 6 -> gestor.mostrarMatriculas();
            }
        } while (opcion != 0);

        gestor.guardarDatos();
        System.out.println("Datos guardados. Fin del programa.");
    }
}
