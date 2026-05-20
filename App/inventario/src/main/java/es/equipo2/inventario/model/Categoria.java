/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.equipo2.inventario.model;

/**
 *
 * @author DAW104
 * representa una categoría (id, nombre)
 */
public class Categoria {
    private int id;
    private String nombre;

    // con id para imprimir
    public Categoria(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // sin id para insertar
    public Categoria(String nombre) {
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
        sb.append(nombre).append("\n");
        return sb.toString();
    }
    
    
    
}
