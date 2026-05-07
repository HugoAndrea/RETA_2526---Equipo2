/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventario.util;

import java.util.Scanner;

/**
 *
 * @author DAW104
 */
public class Teclado {

    private static Scanner sc = new Scanner(System.in);

    //--------------------------------------------------------------------------
    // validaciones
    
    public static String validarNombre() {
        String nombre;
        while (true) {
            nombre = leerString("Introduce el nombre");
            if (nombre.matches("[A-Za-zÁÉÍÓÚáéíóú ]{3,25}") && nombre.replace(" ", "").trim().length() >= 3) {
                return nombre;
            } else {
                error("El nombre tiene que tener mas de 2 y menos 26 caracteres");
            }
        }
    }

    //--------------------------------------------------------------------------
    //entradas
    
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
