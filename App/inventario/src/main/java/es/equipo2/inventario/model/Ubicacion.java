/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.equipo2.inventario.model;

/**
 *
 * @author DAW104
 */
public class Ubicacion {
    private int idUbicacion;
    private String posicion;
    private int idBalda;

    public Ubicacion() {
    }

    public Ubicacion(int idUbicacion, String posicion, int idBalda) {
        this.idUbicacion = idUbicacion;
        this.posicion = posicion;
        this.idBalda = idBalda;
    }

    public int getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(int idUbicacion) {
        this.idUbicacion = idUbicacion;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public int getIdBalda() {
        return idBalda;
    }

    public void setIdBalda(int idBalda) {
        this.idBalda = idBalda;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Ubicacion: \n");
        sb.append("Id Ubicacion: ").append(idUbicacion).append("\n");
        sb.append("Posicion: ").append(posicion).append("\n");
        sb.append("Id Balda: ").append(idBalda).append("\n");
        return sb.toString();
    }
    
    
}
