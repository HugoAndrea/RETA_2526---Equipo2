/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.equipo3.inventario.model;

/**
 *
 * @author DAW104
 * Tabla: balda (idBalda, numBalda, idArmario)
 */
public class Balda {
    private int idBalda;
    private int numBalda;
    private int idArmario;
    
    public Balda(){
        
    }

    public Balda(int idBalda, int numBalda, int idArmario) {
        this.idBalda = idBalda;
        this.numBalda = numBalda;
        this.idArmario = idArmario;
    }

    public int getIdBalda() {
        return idBalda;
    }

    public void setIdBalda(int idBalda) {
        this.idBalda = idBalda;
    }

    public int getNumBalda() {
        return numBalda;
    }

    public void setNumBalda(int numBalda) {
        this.numBalda = numBalda;
    }

    public int getIdArmario() {
        return idArmario;
    }

    public void setIdArmario(int idArmario) {
        this.idArmario = idArmario;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Balda: \n");
        sb.append("Numero Balda: ").append(numBalda).append("\n");
        sb.append("Id Armario: ").append(idArmario).append("\n");
        return sb.toString();
    }
    
    
}
