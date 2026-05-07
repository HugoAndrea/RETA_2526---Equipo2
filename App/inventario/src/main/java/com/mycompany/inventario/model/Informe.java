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
public class Informe {
    private int numInforme;
    private LocalDate fecha;
    private String descripcion;
    private int idUsuario;

    public Informe() {
    }

    public Informe(int numInforme, LocalDate fecha, String descripcion, int idUsuario) {
        this.numInforme = numInforme;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.idUsuario = idUsuario;
    }

    public int getNumInforme() {
        return numInforme;
    }

    public void setNumInforme(int numInforme) {
        this.numInforme = numInforme;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    
}
