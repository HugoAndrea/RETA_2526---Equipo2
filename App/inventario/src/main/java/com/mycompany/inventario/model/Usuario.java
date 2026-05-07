/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventario.model;

/**
 *
 * @author DAW104
 * representa un usuario (id, nombre, contraseña, rol: admin/profesor)
 */
public class Usuario {
    private int idUsuario;
    private String usuario;
    private String password;
    private String rol; // administrador o profesor

    public Usuario() {
    }

    public Usuario(int idUsuario, String usuario, String password, String rol) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.password = password;
        this.rol = rol;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Usuario:\n ");
        sb.append("Id Usuario: ").append(idUsuario).append("\n");
        sb.append("Usuario: ").append(usuario).append("\n");
        sb.append("password: ").append(password).append("\n");
        sb.append("Cargo: ").append(rol).append("\n");
        return sb.toString();
    }
    
    
    
}
