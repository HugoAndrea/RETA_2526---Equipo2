/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventarop.controller;

import com.mycompany.inventario.dao.UsuarioDAO;
import com.mycompany.inventario.model.Usuario;
import java.util.List;

/**
 *
 * @author DAW104
 * 
 * gestiona el login y sabe qué perfil tiene el usuario (admin o profesor)
 */
public class UsuarioController {
    private final UsuarioDAO usuarioDAO;
    
    //para mantener la sesion del usuario logueado en la aplicacion
    private static Usuario usuarioSesion;
    
    public UsuarioController(){
        this.usuarioDAO = new UsuarioDAO();
    }
    
    
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
            return true;
        }
        return false;
    }
    
    /**
     * cerrar la sesion 
     */
    public void cerrarSesion(){
        usuarioSesion = null;
    }
    
    
    /** 
     * 
     * @return el usuario que tiene la sesion iniciada
     */
    public static Usuario getUsuarioSesion(){
        return usuarioSesion;
    }
    
    /**
     * 
     * @param u usuario
     * @return modificar la informacion del usuario 
     */
    public boolean registrarUsuario(Usuario u){
        return usuarioDAO.modificar(u);
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
