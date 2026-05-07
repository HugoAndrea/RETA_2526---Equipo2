/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventario.model;

import java.time.LocalDate;

/**
 *
 * @author DAW104
 */
public class HistorialEstado {
    private int idHistorialEstado;
    private LocalDate fecha;
    private int idObjeto;
    private int idEstado;
    private int idUsuario;
    
    // campos para mostrar en la interfaz
    private String nombreObj;
    private String nombreEstado;
    private String nombreUs;

    public HistorialEstado() {
    }

    public HistorialEstado(int idHistorialEstado, LocalDate fecha, int idObjeto, int idEstado, int idUsuario) {
        this.idHistorialEstado = idHistorialEstado;
        this.fecha = fecha;
        this.idObjeto = idObjeto;
        this.idEstado = idEstado;
        this.idUsuario = idUsuario;
    }

    public int getIdHistorialEstado() {
        return idHistorialEstado;
    }

    public void setIdHistorialEstado(int idHistorialEstado) {
        this.idHistorialEstado = idHistorialEstado;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getIdObjeto() {
        return idObjeto;
    }

    public void setIdObjeto(int idObjeto) {
        this.idObjeto = idObjeto;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreObj() {
        return nombreObj;
    }

    public void setNombreObj(String nombreObj) {
        this.nombreObj = nombreObj;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    public String getNombreUs() {
        return nombreUs;
    }

    public void setNombreUs(String nombreUs) {
        this.nombreUs = nombreUs;
    }
    
    
}
