package es.equipo2.inventario.model;

import java.time.LocalDate;

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
    
    private int       idObjeto;
    private String    nombre;
    private String    descripcion;   
    private int       cantidad;       
    private LocalDate fechaAlta;      
    private String    observaciones;  
    private int       idUbicacion;
    private int       idCategoria;
    
    // campos extras para ui 
    private String nombreCategoria;
    private String posicionUbicacion;
    private String estadoActual;

    public Objeto() {
    }

    public Objeto(int idObjeto, String nombre, String descripcion, int cantidad, LocalDate fechaAlta, String observaciones, int idUbicacion, int idCategoria) {
        this.idObjeto = idObjeto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.fechaAlta = fechaAlta;
        this.observaciones = observaciones;
        this.idUbicacion = idUbicacion;
        this.idCategoria = idCategoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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
        sb.append("Objeto:\n");
        sb.append("Id: ").append(idObjeto).append("\n");
        sb.append("Nombre: ").append(nombre).append("\n");
        sb.append("Descripcion: ").append(descripcion).append("\n");
        sb.append("Cantidad: ").append(cantidad).append("\n");
        sb.append("Fecha alta: ").append(fechaAlta).append("\n");
        sb.append("Categoria: ").append(nombreCategoria).append("\n");
        sb.append("Ubicacion: ").append(posicionUbicacion).append("\n");
        sb.append("Estado: ").append(estadoActual).append("\n");
        return sb.toString();
    }

    
    
   
}
