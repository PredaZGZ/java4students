package model;

import java.sql.Date;

public class Servicio {
    private int id;
    private int citaId;
    private String descripcion;
    private double precio;
    private Date fecha;

    // Getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCitaId() {
        return citaId;
    }

    public void setCitaId(int citaId) {
        this.citaId = citaId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Servicio{" +
                "id=" + id +
                ", citaId=" + citaId +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", fecha=" + fecha +
                '}';
    }
}
