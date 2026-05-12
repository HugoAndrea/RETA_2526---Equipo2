/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.equipo3.inventario.model;

import java.time.LocalDate;

/**
 * Clase que contiene atributos del objeto Hisotrial estado
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

    /**
     * 
     * @param idHistorialEstado el id principal, no se cmbia, es autoincrementado
     * @param fecha fecha cuando ha ocurrido el cambio
     * @param idObjeto el id de objeto que ha cambiado de estado
     * @param idEstado el id de estado que ya existe
     * @param idUsuario usuario que emitió el cambio de estado
     */
    public HistorialEstado(int idHistorialEstado, LocalDate fecha, int idObjeto, int idEstado, int idUsuario) {
        this.idHistorialEstado = idHistorialEstado;
        this.fecha = fecha;
        this.idObjeto = idObjeto;
        this.idEstado = idEstado;
        this.idUsuario = idUsuario;
    }

    /**
     * 
     * @return 
     */
    public int getIdHistorialEstado() {
        return idHistorialEstado;
    }

    /**
     * 
     * @param idHistorialEstado 
     */
    public void setIdHistorialEstado(int idHistorialEstado) {
        this.idHistorialEstado = idHistorialEstado;
    }

    /**
     * 
     * @return 
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * 
     * @param fecha fecha de estado 
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    /**
     * 
     * @return idObjeto
     */
    public int getIdObjeto() {
        return idObjeto;
    }

    /**
     * 
     * @param idObjeto 
     */
    public void setIdObjeto(int idObjeto) {
        this.idObjeto = idObjeto;
    }

    /**
     * 
     * @return idEstado
     */
    public int getIdEstado() {
        return idEstado;
    }

    /**
     * 
     * @param idEstado 
     */
    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    /**
     * 
     * @return 
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * 
     * @param idUsuario 
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * 
     * @return 
     */
    public String getNombreObj() {
        return nombreObj;
    }

    /**
     * 
     * @param nombreObj 
     */
    public void setNombreObj(String nombreObj) {
        this.nombreObj = nombreObj;
    }

    /**
     * 
     * @return 
     */
    public String getNombreEstado() {
        return nombreEstado;
    }

    /**
     * 
     * @param nombreEstado 
     */
    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    /**
     * 
     * @return nombre de usuario que ha hecho algun cambio en el estdo
     */
    public String getNombreUs() {
        return nombreUs;
    }

    /**
     * 
     * @param nombreUs 
     */
    public void setNombreUs(String nombreUs) {
        this.nombreUs = nombreUs;
    }
    
    
}
