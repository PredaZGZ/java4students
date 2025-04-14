package dao;

import model.FlujoCaja;

import java.sql.*;
import java.time.LocalDate;


public class FlujoCajaDao {

    public void registrarMovimiento(FlujoCaja movimiento) {
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "INSERT INTO flujo_caja (tipo, descripcion, cantidad, fecha, categoria) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, movimiento.getTipo());
            stmt.setString(2, movimiento.getDescripcion());
            stmt.setDouble(3, movimiento.getCantidad());
            stmt.setDate(4, movimiento.getFecha());
            stmt.setString(5, movimiento.getCategoria());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                movimiento.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double calcularSaldoActual() {
        double ingresos = 0, gastos = 0;
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "SELECT tipo, SUM(cantidad) FROM flujo_caja GROUP BY tipo";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                String tipo = rs.getString("tipo");
                double suma = rs.getDouble(2);
                if ("INGRESO".equalsIgnoreCase(tipo)) {
                    ingresos += suma;
                } else if ("GASTO".equalsIgnoreCase(tipo)) {
                    gastos += suma;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ingresos - gastos;
    }

    public double ingresosDelMes() {
        return sumarPorTipoYMes("INGRESO");
    }

    public double gastosDelMes() {
        return sumarPorTipoYMes("GASTO");
    }

    private double sumarPorTipoYMes(String tipo) {
        double total = 0;
        LocalDate hoy = LocalDate.now();
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "SELECT SUM(cantidad) FROM flujo_caja WHERE tipo = ? AND MONTH(fecha) = ? AND YEAR(fecha) = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, tipo);
            stmt.setInt(2, hoy.getMonthValue());
            stmt.setInt(3, hoy.getYear());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                total = rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    public void mostrarResumenPorCategoria() {
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "SELECT categoria, tipo, SUM(cantidad) as total FROM flujo_caja GROUP BY categoria, tipo ORDER BY tipo, categoria";
            ResultSet rs = conn.createStatement().executeQuery(sql);

            System.out.println("\nResumen por categoría:");
            while (rs.next()) {
                String cat = rs.getString("categoria");
                String tipo = rs.getString("tipo");
                double total = rs.getDouble("total");
                System.out.printf("Tipo: %-8s | Categoría: %-12s | Total: %.2f €\n", tipo, cat, total);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void generarGastosNominas() {
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "SELECT id, nombre, salario FROM empleados";
            ResultSet rs = conn.createStatement().executeQuery(sql);

            String insertSql = "INSERT INTO flujo_caja (tipo, descripcion, cantidad, fecha, categoria) VALUES ('GASTO', ?, ?, ?, 'NOMINA')";
            PreparedStatement insertStmt = conn.prepareStatement(insertSql);

            Date fechaActual = Date.valueOf(LocalDate.now());
            int contador = 0;

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                double salario = rs.getDouble("salario");
                String descripcion = "Pago de nómina a " + nombre;

                insertStmt.setString(1, descripcion);
                insertStmt.setDouble(2, salario);
                insertStmt.setDate(3, fechaActual);
                insertStmt.addBatch();
                contador++;
            }

            insertStmt.executeBatch();
            // Se podia hacer con batch que sirve para unir varias consultas en una sola
            // O se puede hacer concatenando las consultas, pero puede ser un poco más complicado
            // que usar batch que te lo hace todo y optimizado.
            System.out.println(contador + " gastos por nómina generados automáticamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
