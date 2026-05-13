/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.equipo2.inventario.dao;

import es.equipo2.inventario.db.AccesoBase;
import es.equipo2.inventario.model.Usuario;
import es.equipo2.inventario.util.Encriptador;
import es.equipo2.inventario.util.Teclado;
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
    *   login: busca por el nombre y compara el password con BCrypt
    *   @param user Nombre de usuario
    *   @param passwordPlano
    *   @return usuario si las credenciales son correctas
    */
    public Usuario login(String user, String passwordPlano) {
        String sql = "SELECT * FROM usuario WHERE nombre = ?";
 
        try (PreparedStatement ps = getConn().prepareStatement(sql)) {
            ps.setString(1, user);
            ResultSet rs = ps.executeQuery();
 
            if (rs.next()) {
                String hashGuardado = rs.getString("contrasenia");
 
                // Comparamos la contrasenia introducida con el hash de la BD
                if (Encriptador.comprobar(passwordPlano, hashGuardado)) {
                    return new Usuario(
                        rs.getInt("idUsuario"),
                        rs.getString("nombre"),
                        hashGuardado,
                        rs.getString("rol")
                    );
                }
            }
        } catch (SQLException e) {
            Teclado.error("Error en login: " + e.getMessage());
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
            ps.setString(2, Encriptador.hashear(u.getPassword()));
            ps.setString(3, u.getRol());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            Teclado.error("Error al insertar un usuario nuevo");
            return false;
        }
    }
    
    /**
    *   modificar un usuario existente
    *   Si la contrasenia viene vacia, no se modifica (se mantiene la actual)
    *   Si viene rellena, se hashea y se actualiza
    *   @param Usuario u
    *   @return usuario si las credenciales son correctas
    */
    public boolean modificar(Usuario u) {
        boolean cambiarPassword = u.getPassword() != null && !u.getPassword().isEmpty();
 
        String sql = cambiarPassword
            ? "UPDATE usuario SET nombre = ?, contrasenia = ?, rol = ? WHERE idUsuario = ?"
            : "UPDATE usuario SET nombre = ?, rol = ? WHERE idUsuario = ?";
 
        try (PreparedStatement ps = getConn().prepareStatement(sql)) {
            ps.setString(1, u.getUsuario());
            if (cambiarPassword) {
                ps.setString(2, Encriptador.hashear(u.getPassword()));
                ps.setString(3, u.getRol());
                ps.setInt(4, u.getIdUsuario());
            } else {
                ps.setString(2, u.getRol());
                ps.setInt(3, u.getIdUsuario());
            }
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            Teclado.error("Error al modificar usuario: " + e.getMessage());
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
