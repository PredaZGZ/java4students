
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
    public void setPrecio(Double precio) {this.precio=precio;}
    public void setNombre (String nombre) {this.nombre=nombre;}

    
   
    
}
