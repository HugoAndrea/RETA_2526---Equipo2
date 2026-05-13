/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package es.equipo2.inventario.model;

/**
 *
 * @author DAW104
 * Los enums de Movimiento
 */
public enum TipoMovimiento {
    ENTRADA, SALIDA, PRESTAMO, DEVOLUCION;
    
    /**
     * Convierte el String que devuelve mysql al enum correspondiente
     * @param mov valor de texto devuelto por rs.getString("tipo")
     * @return el TipoMovimiento correspondiente
     */
    public static TipoMovimiento desde(String mov){
        for (TipoMovimiento t : values()) {
            if (t.name().equalsIgnoreCase(mov)) {
                return t;
            }
        }
        throw new IllegalArgumentException("Tipo de movimiento desconocido: " + mov);
    }
}
