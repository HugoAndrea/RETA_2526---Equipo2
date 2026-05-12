            /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.equipo3.inventario.controller;

import es.equipo3.inventario.dao.UsuarioDAO;
import es.equipo3.inventario.dao.VistaDAO;
import es.equipo3.inventario.model.Usuario;
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
            usuarioSesion = null;
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
    public boolean registrarUsuario(Usuario u){
        return usuarioDAO.modificar(u);
    }
    
    public boolean esAdmin(){
        return usuarioSesion != null && "administrador".equalsIgnoreCase(usuarioSesion.getRol());
    }
    
    /**
     * 
     * @param id del usuario a eliminar
     * @return el metodo de usuarioDAO que elimina el usuario
     */
    public boolean eliminarUsuario(int id){
        return usuarioDAO.eliminar(id);
    }
    
    
    /**
     * 
     * @return metodo de usuarioDAO que devuelve una lista
     */
    public List<Usuario> listarUsuarios(){
        return usuarioDAO.listarUsuarios();
    }

}
