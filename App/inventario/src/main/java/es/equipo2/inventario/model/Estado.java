/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.equipo2.inventario.model;

/**
 *
 * @author DAW104
 */
public class Estado {
    private int idEstado;
    private String nombre;

    public Estado() {
    }

    public Estado(int idEstado, String nombre) {
        this.idEstado = idEstado;
        this.nombre = nombre;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Estado: \n");
        sb.append("Id Estado: ").append(idEstado).append("\n");
        sb.append("Nombre: ").append(nombre).append("\n");
        return sb.toString();
    }
    
    
    
}
