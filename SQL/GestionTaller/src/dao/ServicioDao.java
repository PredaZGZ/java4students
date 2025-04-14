package dao;

import model.Servicio;

import java.sql.*;
import java.util.ArrayList;

public class ServicioDao {

    public void agregarServicio(Servicio servicio) {
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "INSERT INTO servicios (cita_id, descripcion, precio, fecha) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, servicio.getCitaId());
            stmt.setString(2, servicio.getDescripcion());
            stmt.setDouble(3, servicio.getPrecio());
            stmt.setDate(4, servicio.getFecha());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                servicio.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Servicio> obtenerServicios() {
        ArrayList<Servicio> lista = new ArrayList<>();
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "SELECT * FROM servicios";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Servicio servicio = new Servicio();
                servicio.setId(rs.getInt("id"));
                servicio.setCitaId(rs.getInt("cita_id"));
                servicio.setDescripcion(rs.getString("descripcion"));
                servicio.setPrecio(rs.getDouble("precio"));
                servicio.setFecha(rs.getDate("fecha"));
                lista.add(servicio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Servicio obtenerServicioPorId(int id) {
        Servicio servicio = null;
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "SELECT * FROM servicios WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                servicio = new Servicio();
                servicio.setId(rs.getInt("id"));
                servicio.setCitaId(rs.getInt("cita_id"));
                servicio.setDescripcion(rs.getString("descripcion"));
                servicio.setPrecio(rs.getDouble("precio"));
                servicio.setFecha(rs.getDate("fecha"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return servicio;
    }

    public void actualizarServicio(Servicio servicio) {
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "UPDATE servicios SET cita_id = ?, descripcion = ?, precio = ?, fecha = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, servicio.getCitaId());
            stmt.setString(2, servicio.getDescripcion());
            stmt.setDouble(3, servicio.getPrecio());
            stmt.setDate(4, servicio.getFecha());
            stmt.setInt(5, servicio.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean eliminarServicio(int id) {
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "DELETE FROM servicios WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean existeServicio(int id) {
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "SELECT COUNT(*) FROM servicios WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
