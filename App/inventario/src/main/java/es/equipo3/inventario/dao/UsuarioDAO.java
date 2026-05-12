/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.equipo3.inventario.dao;

import es.equipo3.inventario.db.AccesoBase;
import es.equipo3.inventario.model.Usuario;
import es.equipo3.inventario.util.Teclado;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author DAW104
 * 
 * login(); insertar; modificar; borrar();
 */
public class UsuarioDAO {
    
    // clase auxiliar para la conexión
    private Connection getConn(){
        return AccesoBase.getInstance().getConn();
    }
    
    /**
    *   login
    *   @param user Nombre de usuario
    *   @param password
    *   @return usuario si las credenciales son correctas
    */
    public Usuario login(String user, String password){
        String sql = "SELECT * FROM usuario WHERE nombre = ? AND contrasenia = ?";
        
        try(PreparedStatement ps = getConn().prepareStatement(sql)){
            ps.setString(1, user);
            ps.setString(2, password);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Usuario(
                        rs.getInt("idUsuario"),
                        rs.getString("nombre"),
                        rs.getString("contrasenia"),
                        rs.getString("rol")
                );
            }
        } catch (SQLException e) {
            Teclado.error("Error al introducir el user o la contraseña");
        }
        return null;
    }
    
    /**
    *   Insertar un usuario
    *   @param u Usuario a insertar
    *   @return true si se insertó 
    */
    public boolean insertar(Usuario u){
        String sql = "INSERT INTO usuario (nombre, contrasenia, rol) VALUES (?,?,?)";
        
        try(PreparedStatement ps = getConn().prepareStatement(sql)){
            ps.setString(1, u.getUsuario());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getRol());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            Teclado.error("Error al insertar un usuario nuevo");
            return false;
        }
    }
    
    /**
    *   modificar un usuario existente
    *   @param Usuario u
    *   @return usuario si las credenciales son correctas
    */
    public boolean modificar(Usuario u){
        String sql = "UPDATE usuario SET nombre = ?, contrasenia = ?, rol = ? "
                + "WHERE idUsuario = ?";
        
        try(PreparedStatement ps = getConn().prepareStatement(sql)){
            ps.setString(1, u.getUsuario());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getRol());
            ps.setInt(4, u.getIdUsuario());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            Teclado.error("Error al actualizar el usuario");
            return false;
        }
    }
    
    /**
    *   eliminar por id
    *   @param id del usuario
    *   @return true si lo borra
    */
    public boolean eliminar(int id){
        String sql = "DELETE FROM usuario WHERE idUsuario = ?";
        
        try(PreparedStatement ps = getConn().prepareStatement(sql)){
            ps.setInt(1, id);
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            Teclado.error("Error al eliminar usuario: " + e.getMessage());
            return false;
        }
    }
    
    /**
    *   Listar usuarios
    *   @return ArrayList de usuarios
    */   
    public List<Usuario> listarUsuarios(){
        List<Usuario> lista = new ArrayList<>();        
        String sql = "SELECT * FROM usuario";
        // Uso Statement porque la consulta es fija y no va a cambiar
        try(Statement st = getConn().createStatement()){
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                Usuario u = new Usuario(
                rs.getInt("idUsuario"),
                rs.getString("nombre"),
                rs.getString("contrasenia"),
                rs.getString("rol")
                );
                lista.add(u);
            }
        } catch (SQLException e) {
            Teclado.error("Error al listar los usuarios disponibles");
            Teclado.error(e.getMessage());
        }
        return lista;
    }
}
