package dao;

import model.Cita;
import java.sql.*;
import java.util.ArrayList;

public class CitaDao {

    public void agregarCita(Cita cita) {
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "INSERT INTO citas (vehiculo_id, empleado_id, fecha, hora, descripcion) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, cita.getVehiculoId());
            stmt.setInt(2, cita.getEmpleadoId());
            stmt.setDate(3, cita.getFecha());
            stmt.setTime(4, cita.getHora());
            stmt.setString(5, cita.getDescripcion());
            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                cita.setId(keys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Cita> obtenerCitas() {
        ArrayList<Cita> lista = new ArrayList<>();
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "SELECT * FROM citas";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Cita cita = new Cita();
                cita.setId(rs.getInt("id"));
                cita.setVehiculoId(rs.getInt("vehiculo_id"));
                cita.setEmpleadoId(rs.getInt("empleado_id"));
                cita.setFecha(rs.getDate("fecha"));
                cita.setHora(rs.getTime("hora"));
                cita.setDescripcion(rs.getString("descripcion"));
                lista.add(cita);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Cita obtenerCitaPorId(int id) {
        Cita cita = null;
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "SELECT * FROM citas WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                cita = new Cita();
                cita.setId(rs.getInt("id"));
                cita.setVehiculoId(rs.getInt("vehiculo_id"));
                cita.setEmpleadoId(rs.getInt("empleado_id"));
                cita.setFecha(rs.getDate("fecha"));
                cita.setHora(rs.getTime("hora"));
                cita.setDescripcion(rs.getString("descripcion"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cita;
    }

    public void actualizarCita(Cita cita) {
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "UPDATE citas SET vehiculo_id = ?, empleado_id = ?, fecha = ?, hora = ?, descripcion = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, cita.getVehiculoId());
            stmt.setInt(2, cita.getEmpleadoId());
            stmt.setDate(3, cita.getFecha());
            stmt.setTime(4, cita.getHora());
            stmt.setString(5, cita.getDescripcion());
            stmt.setInt(6, cita.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean eliminarCita(int id) {
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "DELETE FROM citas WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean existeCita(int id) {
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "SELECT COUNT(*) FROM citas WHERE id = ?";
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
