/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventario.model;

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Categoria: \n");
        sb.append("Id: ").append(id).append("\n");
        sb.append("Nombre: ").append(nombre).append("\n");
        return sb.toString();
    }
    
    
    
}
