import java.util.Scanner;

public class Plato {

    Scanner sc = new Scanner(System.in);
    private String codigo;
    private String nombre;
    private Double precio;

    public Plato(String codigo, String nombre, Double precio) {

        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;

    }

    public Double getPrecio() {return this.precio;}
    public String getCodigo() {return this.codigo;}

    public void setNombrePlato(String nombre){
        
        System.out.println("Introduzca el nombre que quiere asignar al plato: ");
        this.nombre = sc.nextLine();
        sc.close();

        System.out.println("Nombre del plato actualizado correctamente!");
        return;
    }

    public void setPrecioPlato(Double precio){
        
        System.out.println("Introduzca el precio que quiere asignar al plato: ");
        this.precio = sc.nextDouble();
        sc.close();
        System.out.println("Precio del plato actualizado correctamente!");
        return;
    }

    

}
