            /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.equipo2.inventario.controller;

import es.equipo2.inventario.dao.UsuarioDAO;
import es.equipo2.inventario.dao.VistaDAO;
import es.equipo2.inventario.model.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DAW104
 * 
 * gestiona el login y sabe qué perfil tiene el usuario (admin o profesor)
 */
public class UsuarioController {
    
    //para mantener la sesion del usuario logueado en la aplicacion
    private static Usuario usuarioSesion;
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private VistaDAO vista = new VistaDAO();
    

    
    
    /**
    *   intenta iniciar sesion y guarda el usuario en memoria 
    *   @param user nombre del usuario
    *   @param password contraseña
    *   @return true si el login es correcto
    */
    public boolean login(String user, String password){
        Usuario u = usuarioDAO.login(user, password);
        
        if (u != null) {
            usuarioSesion = u;
            vista.registrarAcceso(u.getIdUsuario()); // registramos el acceso
            return true;
        }
        return false;
    }
    
    /** 
    * 
    * @return el usuario que tiene la sesion iniciada
    */
    public static Usuario getUsuarioSesion(){
        return usuarioSesion;
    }
    
    /**
     * cerrar la sesion poniendo usuariosSesion a null, osea el objeto usuario a null
     */
    public void cerrarSesion(){
        usuarioSesion = null;
    }
    
    
    /**
     * 
     * @param u usuario
     * @return modificar la informacion del usuario 
     */
    public boolean registrarUsuario(Usuario nuevo, String passAdmin){
        if (!esAdmin()) {
            return false;
        }
        Usuario verificacion = usuarioDAO.login(usuarioSesion.getUsuario(), passAdmin);
        if (verificacion == null) {
            return false;
        }
        return usuarioDAO.insertar(nuevo);
    }
    
    public boolean esAdmin(){
        return usuarioSesion != null && "administrador".equalsIgnoreCase(usuarioSesion.getRol());
    }
    
    public boolean modificarUsuario(Usuario u){
        if (!esAdmin()) {
            return false;
        }
        return usuarioDAO.modificar(u);
    }
    
    /**
     * 
     * @param id del usuario a eliminar
     * @return el metodo de usuarioDAO que elimina el usuario
     */
    public boolean eliminarUsuario(int id){
        if (!esAdmin()) {
            return false;
        }
        return usuarioDAO.eliminar(id);
    }
    
    
    /**
     * 
     * @return metodo de usuarioDAO que devuelve una lista de usuarios, si no es admin una lista vacia
     */
    public List<Usuario> listarUsuarios(){
        if (!esAdmin()) {
            return new ArrayList<>();
        }
        return usuarioDAO.listarUsuarios();
    }

}
