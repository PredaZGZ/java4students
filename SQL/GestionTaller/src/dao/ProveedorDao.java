package dao;

import model.Proveedor;

import java.sql.*;
import java.util.ArrayList;

public class ProveedorDao {

    public void agregarProveedor(Proveedor proveedor) {
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "INSERT INTO proveedores (nombre, telefono, email) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, proveedor.getNombre());
            stmt.setString(2, proveedor.getTelefono());
            stmt.setString(3, proveedor.getEmail());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                proveedor.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Proveedor> obtenerProveedores() {
        ArrayList<Proveedor> lista = new ArrayList<>();
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "SELECT * FROM proveedores";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Proveedor p = new Proveedor();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setTelefono(rs.getString("telefono"));
                p.setEmail(rs.getString("email"));
                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Proveedor obtenerProveedorPorId(int id) {
        Proveedor p = null;
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "SELECT * FROM proveedores WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                p = new Proveedor();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setTelefono(rs.getString("telefono"));
                p.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    public void actualizarProveedor(Proveedor proveedor) {
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "UPDATE proveedores SET nombre = ?, telefono = ?, email = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, proveedor.getNombre());
            stmt.setString(2, proveedor.getTelefono());
            stmt.setString(3, proveedor.getEmail());
            stmt.setInt(4, proveedor.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean eliminarProveedor(int id) {
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "DELETE FROM proveedores WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean existeProveedor(int id) {
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "SELECT COUNT(*) FROM proveedores WHERE id = ?";
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
