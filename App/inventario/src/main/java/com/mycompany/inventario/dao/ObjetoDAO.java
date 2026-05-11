/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventario.dao;

import com.mycompany.inventario.db.AccesoBase;
import com.mycompany.inventario.model.Objeto;
import com.mycompany.inventario.util.Teclado;
import java.sql.Connection;
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
    
    private Connection getConn(){
        return AccesoBase.getInstance().getConn();
    }
            
    
    /**
     * 
     * @param o objeto a insertar
     * @return devuelve true si se ha insertado
     */
    public boolean insertar(Objeto o){
        String sql = "INSERT INTO objetoInventario(nombre, idUbicacion, idCategoria)"
                    + "VALUES (?,?,?,?,?)";
        
        try(PreparedStatement ps = getConn().prepareStatement(sql)){
            ps.setString(1, o.getNombre());
            
            if (o.getIdUbicacion() > 0) {
                ps.setInt(2, o.getIdUbicacion());
            }else{
                ps.setNull(2, Types.INTEGER);
            }
            
            if (o.getIdCategoria() > 0) {
                ps.setInt(3, o.getIdCategoria());
            }else{
                ps.setNull(3, Types.INTEGER);
            }
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            Teclado.error("error al insertar el objeto");
            Teclado.error(e.getMessage());
            return false;
        }
    }
    
    /**
     * 
     * @param o pasamos el objeto que se tendra que actualizae
     * @return devuelve trrue si se ha actualizado 
     */
    public boolean  modificar(Objeto o){
        String sql = "UPDATE objetoInventario SET nombre = ?, idUbicacion = ?, idCategoria = ?"
                + "WHERE idObjetoInventario = ?";
        
        try(PreparedStatement ps = getConn().prepareStatement(sql)){
            ps.setString(1, o.getNombre());
            
            if (o.getIdUbicacion() > 0) {
                ps.setInt(2, o.getIdUbicacion());
            }else{
                ps.setNull(2, Types.INTEGER);
            }
            
            if (o.getIdCategoria() > 0) {
                ps.setInt(3, o.getIdCategoria());
            }else{
                ps.setNull(3, Types.INTEGER);
            }
            
            ps.setInt(4, o.getIdObjeto());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            Teclado.error("Error al actualizar el objeto");
            Teclado.error(e.getMessage());
            return false;
        }
    }
    
    /**
     * 
     * @param id del objeto a eliminar
     * @return true si lo ha eliminado
     */
    public boolean eliminar(int id){
        String sql = "DELETE from objetoInventario where idObjeto = ?";
        
        try(PreparedStatement ps = getConn().prepareStatement(sql)){
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }catch(SQLException e){
            Teclado.error("Error al eliminar el objeto");
            Teclado.error(e.getMessage());
            return false;
        }
    }
    
    /**
     * 
     * @param nombre
     * @param categoria
     * @param estado
     * @return retorna la lista de 
     */
    public List<Objeto> buscar(String nombre, String categoria, String estado){
        List<Objeto> lista = new ArrayList<>();
        
        String sql = "SELECT o.idObjetoInventario, o.nombre, o.idUbicacion, o.idCategoria, " +
            "       c.nombre AS nombreCategoria, " +
            "       CONCAT(a.nombre, ' - Balda ', b.numBalda, ' - ', u.posicion) AS posicionCompleta, " +
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
            "  AND (c.nombre LIKE ? OR c.nombre IS NULL) " +
            "  AND (e.nombre LIKE ? OR e.nombre IS NULL) " +
            "ORDER BY o.nombre";
        try(PreparedStatement ps = getConn().prepareStatement(sql)){
            ps.setString(1, "%" + nombre + "%");
            ps.setString(2, "%" + categoria + "%");
            ps.setString(3, "%" + estado + "%");
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Objeto o = new Objeto(
                rs.getInt("idObjetoInventario"),
                rs.getString("nombre"),
                rs.getInt("idUbicacion"),
                rs.getInt("idCategoria")
                );
                o.setNombreCategoria(rs.getString("nombreCategoria"));
                o.setPosicionUbicacion(rs.getString("posicionUbicacion"));
                o.setEstadoActual("estadoActual");
                lista.add(o);
            }
        } catch (SQLException e) {
            Teclado.error("Error al buscar el objeto");
            Teclado.error(e.getMessage());
        }
        return lista;
    }
    
     public List<Objeto> listarTodos() {
        List<Objeto> lista = new ArrayList<>();
        String sql =
            "SELECT o.idObjetoInventario, o.nombre, o.idUbicacion, o.idCategoria, " +
            "       c.nombre AS nombreCategoria, " +
            "       CONCAT(a.nombre, ' - Balda ', b.numBalda, ' - ', u.posicion) AS posicionCompleta, " +
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
 
        try (Statement st = getConn().createStatement(); ResultSet rs = st.executeQuery(sql)) {
            
            while (rs.next()) {
                Objeto o = new Objeto(
                    rs.getInt("idObjetoInventario"),
                    rs.getString("nombre"),
                    rs.getInt("idUbicacion"),
                    rs.getInt("idCategoria")
                );
                o.setNombreCategoria(rs.getString("nombreCategoria"));
                o.setPosicionUbicacion(rs.getString("posicionCompleta"));
                o.setEstadoActual(rs.getString("estadoActual"));
                lista.add(o);
            }
        } catch (SQLException e) {
            Teclado.error("Error al listar los objeto");
            Teclado.error(e.getMessage());
        }
        return lista;
    }
    
}
