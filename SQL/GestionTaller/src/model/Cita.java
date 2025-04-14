package model;

import java.sql.Date;
import java.sql.Time;

public class Cita {
    private int id;
    private int vehiculoId;
    private int empleadoId;
    private Date fecha;
    private Time hora;
    private String descripcion;

    // Getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(int vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    public int getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Cita{" +
                "id=" + id +
                ", vehiculoId=" + vehiculoId +
                ", empleadoId=" + empleadoId +
                ", fecha=" + fecha +
                ", hora=" + hora +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
