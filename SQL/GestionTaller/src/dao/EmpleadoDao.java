package dao;

import model.Empleado;
import java.sql.*;
import java.util.ArrayList;

public class EmpleadoDao {

    public void agregarEmpleado(Empleado empleado) {
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "INSERT INTO empleados (nombre, telefono, email, puesto, salario, fecha_contratacion) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, empleado.getNombre());
            stmt.setString(2, empleado.getTelefono());
            stmt.setString(3, empleado.getEmail());
            stmt.setString(4, empleado.getPuesto());
            stmt.setDouble(5, empleado.getSalario());
            stmt.setDate(6, empleado.getFechaContratacion());
            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                empleado.setId(keys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Empleado> obtenerEmpleados() {
        ArrayList<Empleado> empleados = new ArrayList<>();
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "SELECT * FROM empleados";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Empleado emp = new Empleado();
                emp.setId(rs.getInt("id"));
                emp.setNombre(rs.getString("nombre"));
                emp.setTelefono(rs.getString("telefono"));
                emp.setEmail(rs.getString("email"));
                emp.setPuesto(rs.getString("puesto"));
                emp.setSalario(rs.getDouble("salario"));
                emp.setFechaContratacion(rs.getDate("fecha_contratacion"));
                empleados.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empleados;
    }

    public Empleado obtenerEmpleadoPorId(int id) {
        Empleado emp = null;
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "SELECT * FROM empleados WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                emp = new Empleado();
                emp.setId(rs.getInt("id"));
                emp.setNombre(rs.getString("nombre"));
                emp.setTelefono(rs.getString("telefono"));
                emp.setEmail(rs.getString("email"));
                emp.setPuesto(rs.getString("puesto"));
                emp.setSalario(rs.getDouble("salario"));
                emp.setFechaContratacion(rs.getDate("fecha_contratacion"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emp;
    }

    public void actualizarEmpleado(Empleado empleado) {
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "UPDATE empleados SET nombre = ?, telefono = ?, email = ?, puesto = ?, salario = ?, fecha_contratacion = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, empleado.getNombre());
            stmt.setString(2, empleado.getTelefono());
            stmt.setString(3, empleado.getEmail());
            stmt.setString(4, empleado.getPuesto());
            stmt.setDouble(5, empleado.getSalario());
            stmt.setDate(6, empleado.getFechaContratacion());
            stmt.setInt(7, empleado.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean eliminarEmpleado(int id) {
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "DELETE FROM empleados WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean existeEmpleado(int id) {
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "SELECT COUNT(*) FROM empleados WHERE id = ?";
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
