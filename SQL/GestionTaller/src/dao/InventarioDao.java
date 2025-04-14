package dao;

import model.Inventario;

import java.sql.*;
import java.util.ArrayList;

public class InventarioDao {

    public void agregarProducto(Inventario inv) {
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "INSERT INTO inventario (nombre_producto, cantidad, cantidad_minima, proveedores_id) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, inv.getNombreProducto());
            stmt.setInt(2, inv.getCantidad());
            stmt.setInt(3, inv.getCantidadMinima());
            stmt.setInt(4, inv.getProveedorId());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                inv.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Inventario> obtenerInventario() {
        ArrayList<Inventario> lista = new ArrayList<>();
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "SELECT * FROM inventario";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Inventario inv = new Inventario();
                inv.setId(rs.getInt("id"));
                inv.setNombreProducto(rs.getString("nombre_producto"));
                inv.setCantidad(rs.getInt("cantidad"));
                inv.setCantidadMinima(rs.getInt("cantidad_minima"));
                inv.setProveedorId(rs.getInt("proveedores_id"));
                lista.add(inv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Inventario obtenerProductoPorId(int id) {
        Inventario inv = null;
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "SELECT * FROM inventario WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                inv = new Inventario();
                inv.setId(rs.getInt("id"));
                inv.setNombreProducto(rs.getString("nombre_producto"));
                inv.setCantidad(rs.getInt("cantidad"));
                inv.setCantidadMinima(rs.getInt("cantidad_minima"));
                inv.setProveedorId(rs.getInt("proveedores_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inv;
    }

    public void actualizarProducto(Inventario inv) {
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "UPDATE inventario SET nombre_producto = ?, cantidad = ?, cantidad_minima = ?, proveedores_id = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, inv.getNombreProducto());
            stmt.setInt(2, inv.getCantidad());
            stmt.setInt(3, inv.getCantidadMinima());
            stmt.setInt(4, inv.getProveedorId());
            stmt.setInt(5, inv.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean eliminarProducto(int id) {
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "DELETE FROM inventario WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean existeProducto(int id) {
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "SELECT COUNT(*) FROM inventario WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Inventario> obtenerProductosBajoMinimo() {
        ArrayList<Inventario> lista = new ArrayList<>();
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "SELECT * FROM inventario WHERE cantidad < cantidad_minima";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Inventario inv = new Inventario();
                inv.setId(rs.getInt("id"));
                inv.setNombreProducto(rs.getString("nombre_producto"));
                inv.setCantidad(rs.getInt("cantidad"));
                inv.setCantidadMinima(rs.getInt("cantidad_minima"));
                inv.setProveedorId(rs.getInt("proveedores_id"));
                lista.add(inv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
