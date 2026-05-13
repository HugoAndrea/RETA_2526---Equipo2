/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.equipo2.inventario.dao;

import es.equipo2.inventario.db.AccesoBase;
import es.equipo2.inventario.model.Movimiento;
import es.equipo2.inventario.model.TipoMovimiento;
import es.equipo2.inventario.util.Teclado;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DAW104
 * Los metodos que manejan/acceden a la base de datos del objeto movimiento 
 */
public class MovimientoDAO {
    
    private Connection conn = AccesoBase.getInstance().getConn();

    /**
     *
     * @param m insertar un nuevo objeto
     * @return true si se ha insertado
     */
    public boolean insertar(Movimiento m) {
        String sql = "INSERT INTO movimiento (tipo, cantidad, fecha, idUsuario, idObjetoInventario)"
                + "VALUES (?,?,?,?,?)";
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, m.getTipo().name());
            ps.setInt(2, m.getCantidad());
            ps.setDate(3, Date.valueOf(m.getFecha()));
            ps.setInt(4, m.getIdUsuario());
            ps.setInt(5, m.getIdObj());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            Teclado.error("Error al insertar el movimiento");
            Teclado.error(e.getMessage());
            return false;
        }
    }
    
    /**
     * Lista todos los movimientos del sistema ordenados por fecha desc.
     * @return lista
     */
    public List<Movimiento> listarTodos() {
        List<Movimiento> lista = new ArrayList<>();
        String sql = "SELECT m.*, u.nombre AS nombreUsuario, o.nombre AS nombreObjeto "
                + "FROM movimiento m "
                + "JOIN usuario u ON m.idUsuario = u.idUsuario "
                + "JOIN objetoInventario o ON m.idObjetoInventario = o.idObjetoInventario "
                + "ORDER BY m.fecha DESC";
        
        try (Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Movimiento m = new Movimiento(
                        rs.getInt("idMovimiento"),
                        TipoMovimiento.desde(rs.getString("tipo")),
                        rs.getInt("cantidad"),
                        rs.getDate("fecha").toLocalDate(),
                        rs.getInt("idUsuario"),
                        rs.getInt("idObjetoInventario")
                );
                m.setNombreUs(rs.getString("nombreUsuario"));
                m.setNombreObj(rs.getString("nombreObjeto"));
                lista.add(m);
            }
        } catch (SQLException e) {
            Teclado.error("Error al listar movimientos: " + e.getMessage());
        }
        return lista;
    }
    
    /**
     * 
     * @param idObjeto el id del objeto del que vamos a listar los movimientos
     * @return lista de mocimientos
     */
    public List<Movimiento> listarPorObjeto(int idObjeto){
        List<Movimiento> lista = new ArrayList<>();
        String sql = "SELECT m.*, u.nombre AS nombreUsuario, o.nombre AS nombreObjeto "
                   + "FROM movimiento m "
                   + "JOIN usuario u ON m.idUsuario = u.idUsuario "
                   + "JOIN objetoInventario o ON m.idObjetoInventario = o.idObjetoInventario "
                   + "WHERE m.idObjetoInventario = ? "
                   + "ORDER BY m.fecha DESC";
        
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, idObjeto);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Movimiento m = new Movimiento(
                        rs.getInt("idMovimiento"),
                        TipoMovimiento.desde(rs.getString("tipo")),
                        rs.getInt("cantidad"),
                        rs.getDate("fecha").toLocalDate(),
                        rs.getInt("idUsuario"),
                        rs.getInt("idObjetoInventario")
                );
                m.setNombreUs(rs.getString("nombreUsuario"));
                m.setNombreObj(rs.getString("nombreObjeto"));
                lista.add(m);
            }
        } catch(SQLException e) {
            Teclado.error("Error al listar movimientos por objeto");
            Teclado.error(e.getMessage());
        }
        return lista;
    }
}
