package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Vehiculo;
import java.util.ArrayList;

public class VehiculoDao {
        
    public void agregarVehiculo(Vehiculo vehiculo) {
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "INSERT INTO vehiculos (matricula, marca, modelo, cliente_id) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, vehiculo.getMatricula());
            statement.setString(2, vehiculo.getMarca());
            statement.setString(3, vehiculo.getModelo());
            statement.setInt(4, vehiculo.getClienteId());
            statement.executeUpdate();

            // Obtener el ID generado
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int idGenerado = generatedKeys.getInt(1);
                vehiculo.setId(idGenerado); // Actualizas el objeto vehiculo
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Vehiculo buscarVehiculoPorMatricula(String matricula) {
        Vehiculo vehiculo = null;
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "SELECT * FROM vehiculos WHERE matricula = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, matricula);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                vehiculo = new Vehiculo();
                vehiculo.setId(resultSet.getInt("id"));
                vehiculo.setMatricula(resultSet.getString("matricula"));
                vehiculo.setMarca(resultSet.getString("marca"));
                vehiculo.setModelo(resultSet.getString("modelo"));
                vehiculo.setClienteId(resultSet.getInt("cliente_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehiculo;
    }

    public ArrayList<Vehiculo> listarVehiculos() {

        ArrayList<Vehiculo> listaVehiculos = new ArrayList<>();
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "SELECT * FROM vehiculos";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            Vehiculo vehiculo = null;
            while (resultSet.next()) {
                vehiculo = new Vehiculo();
                vehiculo.setId(resultSet.getInt("id"));
                vehiculo.setMatricula(resultSet.getString("matricula"));
                vehiculo.setMarca(resultSet.getString("marca"));
                vehiculo.setModelo(resultSet.getString("modelo"));
                vehiculo.setClienteId(resultSet.getInt("cliente_id"));
                listaVehiculos.add(vehiculo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaVehiculos;
    }

    public void actualizarVehiculo(Vehiculo vehiculo) {
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "UPDATE vehiculos SET matricula = ?, marca = ?, modelo = ?, cliente_id = ? WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, vehiculo.getMatricula());
            statement.setString(2, vehiculo.getMarca());
            statement.setString(3, vehiculo.getModelo());
            statement.setInt(4, vehiculo.getClienteId());
            statement.setInt(5, vehiculo.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public boolean eliminarVehiculo(String matricula) {
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "DELETE FROM vehiculos WHERE matricula = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, matricula);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Vehiculo> obtenerVehiculosPorClienteId(int clienteId) {
        ArrayList<Vehiculo> listaVehiculos = new ArrayList<>();
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "SELECT * FROM vehiculos WHERE cliente_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, clienteId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Vehiculo vehiculo = new Vehiculo();
                vehiculo.setId(resultSet.getInt("id"));
                vehiculo.setMatricula(resultSet.getString("matricula"));
                vehiculo.setMarca(resultSet.getString("marca"));
                vehiculo.setModelo(resultSet.getString("modelo"));
                vehiculo.setClienteId(resultSet.getInt("cliente_id"));
                listaVehiculos.add(vehiculo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaVehiculos;
    }
    

}    
