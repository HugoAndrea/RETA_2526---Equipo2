/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.equipo2.inventario.util;
import org.mindrot.jbcrypt.BCrypt;
/**
 * con ayuda de ia 
 * @author DAW104
 */
public class Encriptador {
    
    private static final int COSTE = 12;
    
    /**
     * Genera el hash BCrypt de una contrasenia en texto plano
     * 
     * @param textoPlano contraseña en texto plano
     * @return hash BCrypt listo para guardar en la BD (60 caracteres) o null si el texto es nulo
     */
    public static String hashear(String textoPlano){
        if (textoPlano == null || textoPlano.isEmpty()) {
            return null;
        }
        return BCrypt.hashpw(textoPlano, BCrypt.gensalt(COSTE));
    }
    
    /**
     * Comprueba si una contrasenia en texto plano coincide con un hash BCrypt.
     * No se puede comparar directamente los hashes porque BCrypt incluye salt.
     * BCrypt.checkpw() extrae el salt del hash guardado y lo usa para comparar.
     *
     * @param textoPlano   contrasenia introducida por el usuario
     * @param hashGuardado hash almacenado en la BD
     * @return true si la contrasenia es correcta
     */
    public static boolean comprobar(String textoPlano, String hashGuardado){
        if (textoPlano == null || hashGuardado == null) {
            return false;
        }
        try {
            return BCrypt.checkpw(textoPlano, hashGuardado);
        } catch (IllegalArgumentException e) {
            Teclado.error("Hash de contraseña invalido: ");
            Teclado.error(e.getMessage());
            return false;
        }
    }
}
