/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.equipo3.inventario.model;

import java.time.LocalDate;

/**
 *
 * @author DAW104
 */
public class Visita {
    private int numVisita;
    private LocalDate fecha;
    private int idUsuario;

    public Visita() {
    }

    public Visita(int numVisita, LocalDate fecha, int idUsuario) {
        this.numVisita = numVisita;
        this.fecha = fecha;
        this.idUsuario = idUsuario;
    }

    public int getNumVisita() {
        return numVisita;
    }

    public void setNumVisita(int numVisita) {
        this.numVisita = numVisita;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    
    
}
