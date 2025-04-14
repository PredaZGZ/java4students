package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Cliente;

public class ClienteDao {

    public void agregarCliente(Cliente cliente) {
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "INSERT INTO clientes (nombre, apellido, telefono, email) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, cliente.getNombre());
            statement.setString(2, cliente.getApellido());
            statement.setString(3, cliente.getTelefono());
            statement.setString(4, cliente.getEmail());
            statement.executeUpdate();

            // Obtener el ID generado
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int idGenerado = generatedKeys.getInt(1);
                cliente.setId(idGenerado); // Actualizas el objeto cliente
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    
    public ArrayList<Cliente> obtenerClientes() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "SELECT * FROM clientes";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(resultSet.getInt("id"));
                cliente.setNombre(resultSet.getString("nombre"));
                cliente.setApellido(resultSet.getString("apellido"));
                cliente.setTelefono(resultSet.getString("telefono"));
                cliente.setEmail(resultSet.getString("email"));
                clientes.add(cliente);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clientes;

    }

    public Cliente obtenerClientePorId(int id) {
        Cliente cliente = null;
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "SELECT * FROM clientes WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                cliente = new Cliente();
                cliente.setId(resultSet.getInt("id"));
                cliente.setNombre(resultSet.getString("nombre"));
                cliente.setApellido(resultSet.getString("apellido"));
                cliente.setTelefono(resultSet.getString("telefono"));
                cliente.setEmail(resultSet.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cliente;
    }

    public void actualizarCliente(Cliente cliente) {
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "UPDATE clientes SET nombre = ?, apellido = ?, telefono = ?, email = ? WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, cliente.getNombre());
            statement.setString(2, cliente.getApellido());
            statement.setString(3, cliente.getTelefono());
            statement.setString(4, cliente.getEmail());
            statement.setInt(5, cliente.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean eliminarCliente(int id) {
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "DELETE FROM clientes WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0; // Devuelve true si se eliminó al menos un registro
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean existeCliente(int id) {
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "SELECT COUNT(*) FROM clientes WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true; 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
