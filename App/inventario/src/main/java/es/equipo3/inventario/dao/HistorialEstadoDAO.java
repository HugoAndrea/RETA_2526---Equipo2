/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.equipo3.inventario.dao;

import es.equipo3.inventario.db.AccesoBase;
import es.equipo3.inventario.model.HistorialEstado;
import es.equipo3.inventario.util.Teclado;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DAW104
 */
public class HistorialEstadoDAO {

    private Connection conn = AccesoBase.getInstance().getConn();

    /**
     * 
     * @param h HistorialEstado
     * @return true o false, depende de si se ha insertado o no
     */
    public boolean insertar(HistorialEstado h) {
        String sql = "INSERT INTO hisotrialEstado (fecha, idObjetoInventario, idEstado, idUsuario)"
                + "VALUES (?,?,?,?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(h.getFecha()));
            ps.setInt(2, h.getIdObjeto());
            ps.setInt(3, h.getIdEstado());
            ps.setInt(4, h.getIdUsuario());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            Teclado.error("Error al registrar cambio de estado ");
            Teclado.error(e.getMessage());
            return false;
        }
    }

    /**
     * 
     * @param idObjeto listar historial de estado de objeto que eliga
     * @return lista
     */
    public List<HistorialEstado> listarPorObjeto(int idObjeto) {
        List<HistorialEstado> lista = new ArrayList<>();
        String sql = "SELECT h.*, e.nombre AS nombreEstado, u.nombre AS nombreUsuario "
                + "FROM historialEstado h "
                + "JOIN estado e ON h.idEstado = e.idEstado "
                + "JOIN usuario u ON h.idUsuario = u.idUsuario "
                + "WHERE h.idObjetoInventario = ? "
                + "ORDER BY h.fecha DESC";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idObjeto);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HistorialEstado h = new HistorialEstado(
                        rs.getInt("idHistorialEstado"),
                        rs.getDate("fecha").toLocalDate(),
                        rs.getInt("idObjetoInventario"),
                        rs.getInt("idEstado"),
                        rs.getInt("idUsuario")
                );
                h.setNombreEstado(rs.getString("nombreEstado"));
                h.setNombreUs(rs.getString("nombreUsuario"));
                lista.add(h);
            }
        } catch (SQLException e) {
            Teclado.error("Error al listar historial: " + e.getMessage());
        }
        return lista;
    }

    /**
     * 
     * @param idObjeto 
     * @return retorna el objeto HistorialEstado, el ultimo que se ha emitido
     */
    public HistorialEstado listarUltimoEstado(int idObjeto){
        String sql = "SELECT h.*, e.nombre AS nombreEstado, u.nombre AS nombreUsuario"
                + "FROM historialEstado h"
                + "JOIN estado e ON h.idEstado = e.idEstado"
                + "JOIN usuario u ON h.idUsuario = idUsuario"
                + "WHERE h.idObjetoInventario = ?"
                + "ORDER BY h.fecha DESC LIMIT 1";
        
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, idObjeto);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                HistorialEstado h = new HistorialEstado(
                        rs.getInt("idHistorialEstado"),
                        rs.getDate("fecha").toLocalDate(),
                        rs.getInt("idObjetoInventario"),
                        rs.getInt("idEstado"),
                        rs.getInt("idUsuario")
                );
                h.setNombreEstado(rs.getString("nombreEstado"));
                h.setNombreUs(rs.getString("nombreUsuario"));
                return h;
            }
        } catch (SQLException e) {
            Teclado.error("Error al obtener ultimo estado");
            Teclado.error(e.getMessage());
        }
        return null;
    }
}
