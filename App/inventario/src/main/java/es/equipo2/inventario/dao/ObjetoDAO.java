/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.equipo2.inventario.dao;

import es.equipo2.inventario.db.AccesoBase;
import es.equipo2.inventario.model.Objeto;
import es.equipo2.inventario.util.Teclado;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DAW104
 * 
 * 
 * inserta(); modificar(); eliminar(); buscarPorId();
 */
public class ObjetoDAO {
 
    private Connection getConn() {
        return AccesoBase.getInstance().getConn();
    }
 
    /**
     * Inserta un objeto nuevo.
     */
    public boolean insertar(Objeto o) {
        // Intentamos primero con descripcion y observaciones
        String sql = "INSERT INTO objetoInventario "
                   + "(nombre, descripcion, cantidad, fechaAlta, observaciones, "
                   + " idUbicacion, idCategoria) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        System.out.println(">>> DEBUG insertar(): nombre=" + o.getNombre()
            + " desc=" + o.getDescripcion()
            + " cant=" + o.getCantidad()
            + " fecha=" + o.getFechaAlta()
            + " idUbi=" + o.getIdUbicacion()
            + " idCat=" + o.getIdCategoria());

        try (PreparedStatement ps = getConn().prepareStatement(sql)) {
            ps.setString(1, o.getNombre());
            ps.setString(2, o.getDescripcion());
            ps.setInt(3, o.getCantidad());
            ps.setDate(4, o.getFechaAlta() != null
                ? Date.valueOf(o.getFechaAlta())
                : Date.valueOf(java.time.LocalDate.now()));
            ps.setString(5, o.getObservaciones());

            if (o.getIdUbicacion() > 0) ps.setInt(6, o.getIdUbicacion());
            else                         ps.setNull(6, Types.INTEGER);

            if (o.getIdCategoria() > 0) ps.setInt(7, o.getIdCategoria());
            else                         ps.setNull(7, Types.INTEGER);

            boolean ok = ps.executeUpdate() > 0;
            System.out.println(">>> DEBUG insertar() resultado: " + ok);
            return ok;
        } catch (SQLException e) {
            System.out.println(">>> DEBUG insertar() excepcion: " + e.getMessage());
            // Si falla por columnas inexistentes, reintentamos sin descripcion/observaciones
            if (e.getMessage() != null && e.getMessage().contains("Unknown column")) {
                System.out.println(">>> Reintentando sin columnas opcionales...");
                return insertarBasico(o);
            }
            Teclado.error("Error al insertar objeto: " + e.getMessage());
            return false;
        }
    }

    /** Fallback: inserta solo las columnas obligatorias si descripcion/observaciones no existen aun en la BD */
    private boolean insertarBasico(Objeto o) {
        String sql = "INSERT INTO objetoInventario "
                   + "(nombre, cantidad, fechaAlta, idUbicacion, idCategoria) "
                   + "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = getConn().prepareStatement(sql)) {
            ps.setString(1, o.getNombre());
            ps.setInt(2, o.getCantidad() > 0 ? o.getCantidad() : 1);
            ps.setDate(3, Date.valueOf(o.getFechaAlta() != null
                    ? o.getFechaAlta() : java.time.LocalDate.now()));

            if (o.getIdUbicacion() > 0) ps.setInt(4, o.getIdUbicacion());
            else                         ps.setNull(4, Types.INTEGER);

            if (o.getIdCategoria() > 0) ps.setInt(5, o.getIdCategoria());
            else                         ps.setNull(5, Types.INTEGER);

            boolean ok = ps.executeUpdate() > 0;
            System.out.println(">>> insertarBasico() resultado: " + ok);
            return ok;
        } catch (SQLException e) {
            System.out.println(">>> insertarBasico() excepcion: " + e.getMessage());
            Teclado.error("Error al insertar objeto (basico): " + e.getMessage());
            return false;
        }
    }
 
    /**
     * Modifica un objeto existente.
     */
    public boolean modificar(Objeto o) {
        String sql = "UPDATE objetoInventario "
                   + "SET nombre = ?, descripcion = ?, cantidad = ?, observaciones = ?, "
                   + "    idUbicacion = ?, idCategoria = ? "
                   + "WHERE idObjetoInventario = ?";
 
        try (PreparedStatement ps = getConn().prepareStatement(sql)) {
            ps.setString(1, o.getNombre());
            ps.setString(2, o.getDescripcion());
            ps.setInt(3, o.getCantidad());
            ps.setString(4, o.getObservaciones());
 
            if (o.getIdUbicacion() > 0) ps.setInt(5, o.getIdUbicacion());
            else                         ps.setNull(5, Types.INTEGER);
 
            if (o.getIdCategoria() > 0) ps.setInt(6, o.getIdCategoria());
            else                         ps.setNull(6, Types.INTEGER);
 
            ps.setInt(7, o.getIdObjeto());
 
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            Teclado.error("Error al modificar objeto: " + e.getMessage());
            return false;
        }
    }
 
    /**
     * Elimina un objeto por su id.
     */
    public boolean eliminar(int id) {
        String sql = "DELETE FROM objetoInventario WHERE idObjetoInventario = ?";
 
        try (PreparedStatement ps = getConn().prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            Teclado.error("Error al eliminar objeto: " + e.getMessage());
            return false;
        }
    }
 
    /**
     * Busqueda por nombre, codigo, categoria y estado.
     * El "codigo" lo interpretamos como el ID del objeto.
     *
     * @param nombre    texto a buscar en el nombre (vacio = todos)
     * @param codigo    texto a buscar en el ID (vacio = todos)
     * @param categoria texto a buscar en la categoria (vacio = todas)
     * @param estado    texto a buscar en el estado (vacio = todos)
     * @return lista de objetos que cumplen los criterios
     */
    public List<Objeto> buscar(String nombre, String codigo, String categoria, String estado) {
        List<Objeto> lista = new ArrayList<>();
        String sql =
            "SELECT o.*, c.nombre AS nombreCategoria, " +
            "       CONCAT(a.nombre, ' - Balda ', b.numBalda, ' - ', u.posicion) AS posicionUbicacion, " +
            "       e.nombre AS estadoActual " +
            "FROM objetoInventario o " +
            "LEFT JOIN categoria c ON o.idCategoria = c.idCategoria " +
            "LEFT JOIN ubicacion u ON o.idUbicacion = u.idUbicacion " +
            "LEFT JOIN balda b ON u.idBalda = b.idBalda " +
            "LEFT JOIN armario a ON b.idArmario = a.idArmario " +
            "LEFT JOIN historialEstado h ON h.idObjetoInventario = o.idObjetoInventario " +
            "  AND h.fecha = (SELECT MAX(h2.fecha) FROM historialEstado h2 " +
            "                 WHERE h2.idObjetoInventario = o.idObjetoInventario) " +
            "LEFT JOIN estado e ON h.idEstado = e.idEstado " +
            "WHERE o.nombre LIKE ? " +
            "  AND CAST(o.idObjetoInventario AS CHAR) LIKE ? " +
            "  AND (c.nombre LIKE ? OR c.nombre IS NULL) " +
            "  AND (e.nombre LIKE ? OR e.nombre IS NULL) " +
            "ORDER BY o.nombre";
 
        try (PreparedStatement ps = getConn().prepareStatement(sql)) {
            ps.setString(1, "%" + nombre + "%");
            ps.setString(2, "%" + codigo + "%");
            ps.setString(3, "%" + categoria + "%");
            ps.setString(4, "%" + estado + "%");
 
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(construirObjeto(rs));
            }
        } catch (SQLException e) {
            Teclado.error("Error al buscar objetos: " + e.getMessage());
        }
        return lista;
    }
 
    /**
     * Lista todos los objetos del inventario.
     */
    public List<Objeto> listarTodos() {
        List<Objeto> lista = new ArrayList<>();
        String sql =
            "SELECT o.*, c.nombre AS nombreCategoria, " +
            "       CONCAT(a.nombre, ' - Balda ', b.numBalda, ' - ', u.posicion) AS posicionUbicacion, " +
            "       e.nombre AS estadoActual " +
            "FROM objetoInventario o " +
            "LEFT JOIN categoria c ON o.idCategoria = c.idCategoria " +
            "LEFT JOIN ubicacion u ON o.idUbicacion = u.idUbicacion " +
            "LEFT JOIN balda b ON u.idBalda = b.idBalda " +
            "LEFT JOIN armario a ON b.idArmario = a.idArmario " +
            "LEFT JOIN historialEstado h ON h.idObjetoInventario = o.idObjetoInventario " +
            "  AND h.fecha = (SELECT MAX(h2.fecha) FROM historialEstado h2 " +
            "                 WHERE h2.idObjetoInventario = o.idObjetoInventario) " +
            "LEFT JOIN estado e ON h.idEstado = e.idEstado " +
            "ORDER BY o.nombre";
 
        try (Statement st = getConn().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(construirObjeto(rs));
            }
        } catch (SQLException e) {
            Teclado.error("Error al listar objetos: " + e.getMessage());
        }
        return lista;
    }
 
    /**
     * Lista los objetos de un armario y balda concretos.
     * Necesario para el informe por armario/balda (enunciado 4.2).
     */
    public List<Objeto> listarPorArmarioBalda(int idArmario, int numBalda) {
        List<Objeto> lista = new ArrayList<>();
        String sql =
            "SELECT o.*, c.nombre AS nombreCategoria, " +
            "       CONCAT(a.nombre, ' - Balda ', b.numBalda, ' - ', u.posicion) AS posicionUbicacion, " +
            "       e.nombre AS estadoActual " +
            "FROM objetoInventario o " +
            "LEFT JOIN categoria c ON o.idCategoria = c.idCategoria " +
            "LEFT JOIN ubicacion u ON o.idUbicacion = u.idUbicacion " +
            "LEFT JOIN balda b ON u.idBalda = b.idBalda " +
            "LEFT JOIN armario a ON b.idArmario = a.idArmario " +
            "LEFT JOIN historialEstado h ON h.idObjetoInventario = o.idObjetoInventario " +
            "  AND h.fecha = (SELECT MAX(h2.fecha) FROM historialEstado h2 " +
            "                 WHERE h2.idObjetoInventario = o.idObjetoInventario) " +
            "LEFT JOIN estado e ON h.idEstado = e.idEstado " +
            "WHERE b.idArmario = ? AND b.numBalda = ? " +
            "ORDER BY o.nombre";
 
        try (PreparedStatement ps = getConn().prepareStatement(sql)) {
            ps.setInt(1, idArmario);
            ps.setInt(2, numBalda);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(construirObjeto(rs));
            }
        } catch (SQLException e) {
            Teclado.error("Error al listar por armario/balda: " + e.getMessage());
        }
        return lista;
    }
 
    /**
     * Metodo auxiliar privado para construir un Objeto a partir del ResultSet.
     * Centraliza la conversion para no repetir codigo.
     */
    private Objeto construirObjeto(ResultSet rs) throws SQLException {
        Objeto o = new Objeto();
        o.setIdObjeto(rs.getInt("idObjetoInventario"));
        o.setNombre(rs.getString("nombre"));
        o.setDescripcion(rs.getString("descripcion"));
        o.setCantidad(rs.getInt("cantidad"));
 
        Date fa = rs.getDate("fechaAlta");
        if (fa != null) o.setFechaAlta(fa.toLocalDate());
 
        o.setObservaciones(rs.getString("observaciones"));
        o.setIdUbicacion(rs.getInt("idUbicacion"));
        o.setIdCategoria(rs.getInt("idCategoria"));
        o.setNombreCategoria(rs.getString("nombreCategoria"));
        o.setPosicionUbicacion(rs.getString("posicionUbicacion"));
        o.setEstadoActual(rs.getString("estadoActual"));
        return o;
    }
}