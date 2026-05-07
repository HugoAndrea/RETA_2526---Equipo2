package com.mycompany.inventario.model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author DAW104
 * representa un objeto del inventario (id, nombre, categoría, estado, armario, balda...)
 */
public class Objeto {
    private int idObjeto;
    private String nombre;
    private int idUbicacion;
    private int idCategoria;
    
    // campos extras para ui 
    private String nombreCategoria;
    private String posicionUbicacion;
    private String estadoActual;

    public Objeto() {
    }

    public Objeto(int idObjeto, String nombre, int idUbicacion, int idCategoria, String nombreCategoria, String posicionUbicacion, String estadoActual) {
        this.idObjeto = idObjeto;
        this.nombre = nombre;
        this.idUbicacion = idUbicacion;
        this.idCategoria = idCategoria;
        this.nombreCategoria = nombreCategoria;
        this.posicionUbicacion = posicionUbicacion;
        this.estadoActual = estadoActual;
    }

    public int getIdObjeto() {
        return idObjeto;
    }

    public void setIdObjeto(int idObjeto) {
        this.idObjeto = idObjeto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(int idUbicacion) {
        this.idUbicacion = idUbicacion;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getPosicionUbicacion() {
        return posicionUbicacion;
    }

    public void setPosicionUbicacion(String posicionUbicacion) {
        this.posicionUbicacion = posicionUbicacion;
    }

    public String getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(String estadoActual) {
        this.estadoActual = estadoActual;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Objeto: \n");
        sb.append("idObjeto: ").append(idObjeto).append("\n");
        sb.append(", nombre: ").append(nombre).append("\n");
        sb.append(", nombreCategoria: ").append(nombreCategoria).append("\n");
        sb.append(", posicionUbicacion: ").append(posicionUbicacion).append("\n");
        sb.append(", estadoActual: ").append(estadoActual).append("\n");
        return sb.toString();
    }
    
    
   
}
