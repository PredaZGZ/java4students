public class Plato {
    
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
    public String getNombre() {return this.nombre;}

}
