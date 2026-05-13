/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.equipo2.inventario.model;

/**
 *
 * @author DAW104
    representa un armario/balda (id, número, balda)
*/
public class Armario {
    private int id;
    private String nombre;

    // dos constructores, uno para sacar el armario con id 
    public Armario(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // otro para insertar sin id porque es autoincrement
    public Armario(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Armario: \n");
        sb.append("Id: ").append(id).append("\n");
        sb.append("Nombre: ").append(nombre).append("\n");
        return sb.toString();
    }
    
    
}
