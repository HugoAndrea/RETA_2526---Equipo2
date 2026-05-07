/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventario.model;

/**
 *
 * @author DAW104
    representa un armario/balda (id, número, balda)
*/
public class Armario {
    private int id;
    private String nombre;
    private int baldas; // en futuro se actualiza la base de datos

    // dos constructores, uno para sacar el armario con id 
    public Armario(int id, String nombre, int baldas) {
        this.id = id;
        this.nombre = nombre;
        this.baldas = baldas;
    }

    // otro para insertar sin id porque es autoincrement
    public Armario(String nombre, int baldas) {
        this.nombre = nombre;
        this.baldas = baldas;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getBaldas() {
        return baldas;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Armario: \n");
        sb.append("Id: ").append(id).append("\n");
        sb.append("Nombre: ").append(nombre).append("\n");
        sb.append("Baldas: ").append(baldas).append("\n");
        return sb.toString();
    }
    
    
}
