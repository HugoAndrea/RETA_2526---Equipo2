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
public class Movimiento {
    private int idMovimiento;
    private TipoMovimiento tipo;
    private int cantidad;
    private LocalDate fecha;
    private int idUsuario;
    private int idObj;
    
    // campos para ui
    private String nombreUs;
    private String nombreObj;

    public Movimiento() {
    }

    public Movimiento(int idMovimiento, TipoMovimiento tipo, int cantidad, LocalDate fecha, int idUsuario, int idObj) {
        this.idMovimiento = idMovimiento;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.idUsuario = idUsuario;
        this.idObj = idObj;

    }

    public TipoMovimiento getTipo() {
        return tipo;
    }

    public void setTipo(TipoMovimiento tipo) {
        this.tipo = tipo;
    }


    public int getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
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

    public int getIdObj() {
        return idObj;
    }

    public void setIdObj(int idObj) {
        this.idObj = idObj;
    }

    public String getNombreUs() {
        return nombreUs;
    }

    public void setNombreUs(String nombreUs) {
        this.nombreUs = nombreUs;
    }

    public String getNombreObj() {
        return nombreObj;
    }

    public void setNombreObj(String nombreObj) {
        this.nombreObj = nombreObj;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("cantidad - ").append(cantidad);
        sb.append(", fecha - ").append(fecha);
        return sb.toString();
    }
    
    
}
