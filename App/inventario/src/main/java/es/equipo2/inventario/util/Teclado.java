/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.equipo2.inventario.util;

import java.util.Scanner;

/**
 *
 * @author DAW104
 */
public class Teclado {

    private static Scanner sc = new Scanner(System.in);

    //--------------------------------------------------------------------------
    // validaciones se puede usar desde swing
    
    /**
     * 
     * @param nombre
     * @return falso si el nombre es nulo
     */
    public static boolean nombreValido(String nombre) {
        if (nombre == null) {
            return false;
        }
        return nombre.matches("[A-Za-zÁÉÍÓÚáéíóú ]{3,25}") && nombre.replace(" ", "").trim().length() >= 3;
    }
    
    public static boolean passwordValido(String password){
        if (password == null) {
            return false;
        }
        return password.length() >= 4;
    }
    
    /**
     * 
     * @param texto el texto que nos pasan
     * @param max maximo de letras que puede tener, sin contar los espacios
     * @return true o false
     */
    public static boolean textoValido(String texto, int max){
        if (texto == null) {
            return false;
        }
        String limpio = texto.trim();
        return !limpio.isEmpty() && limpio.length() <= max;
    }
    
    public boolean positivo(int num){
        return num > 0;
    }

    public static boolean estaEnRango(int num, int min, int max){
        return num >= min && num <= max;
    }
    
    public static boolean rolValido(String rol){
        if (rol == null) {
            return false;
        }
        return rol.equalsIgnoreCase("administrador") || rol.equalsIgnoreCase("profesor");
    }
    
    public static boolean soloNumero(String texto){
        if (texto == null) {
            return false;
        }
        return texto.matches("\\d+");
    }
    //--------------------------------------------------------------------------
    //entradas con validaciones por consola
    
    
    public static String pedirNombre(){
        while(true){
            String nombre = leerString("Introduce el nombre (3-25 letras)");
            if (nombreValido(nombre)) {
                return nombre;
            }
            error("El nombre debe tener entre 3 y 25 letras");
        }
    }
    
    public static String pedirPassword(){
        while(true){
            String pass = leerString("Introduce la contraseña (4-25 caracteres)");
            
            if (passwordValido(pass)) {
                return pass;
            }
            error("Contraseña debe tener enter 4 y 25 caracteres");
        }
    }
    
    public static String pedirTexto(String mensaje, int max){
        while(true){
            String texto = leerString(mensaje);
            
            if (textoValido(texto, max)) {
                return texto;
            }
            error("El texto no puede estar vacio y debe tener maximo " + max + "caracteres");
        }
    }
    
    public static int pedirRango(String mensaje, int min, int max){
        while(true){
            int num = leerInt(mensaje + "(" + min + " - " + max + "): ");
            
            if (estaEnRango(num, min, max)) {
                return num;
            }
            error("El valor debe estar entre" + min + " y " + max);
        }
    }
    
    public static String pedirRol(){
        while(true){
            String rol = leerString("Introduce el rol (administrador/profesor)").toLowerCase();
            
            if (rolValido(rol)) {
                return rol;
            }
            error("El rol debe ser 'administrador' o 'profesor' ");
        }
    }
    
    //--------------------------------------------------------------------------
    // entradas
    
    public static String leerString(Object mensaje) {
        System.out.println(mensaje);
        return sc.nextLine().trim();
    }

    public static int leerInt(Object mensaje) {
        System.out.println(mensaje);
        return Integer.parseInt(sc.nextLine().trim());
    }

    public static void imprimir(Object mensaje) {
        System.out.println(mensaje);
    }

    public static void error(Object mensaje) {
        System.out.println(">>> ERROR: " + mensaje);
    }
}
