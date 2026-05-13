/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.equipo2.inventario.util;

import es.equipo2.inventario.model.Objeto;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DAW104
 * 
 * lee un csv y lo convierte en un objeto "elemento"
 */
public class ImportadorCSV {
        private static final String SEPARADOR = ";";
 
    /**
     * lee el archivo CSV y devuelve una lista de objetos listos para insertar
     * las lineas con formato incorrecto registran un error
     * @param rutaArchivo ruta completa del archivo CSV
     * @return lista de objetos parseados, vacia si hay error grave
     */
    public List<Objeto> leer(String rutaArchivo) {
        List<Objeto> lista = new ArrayList<>();
        int lineasLeidas  = 0;
        int lineasError   = 0;
 
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            br.readLine(); // salta la cabecera
 
            String linea;
            while ((linea = br.readLine()) != null) {
                lineasLeidas++;
                String[] campos = linea.split(SEPARADOR, -1); // -1 para incluir vacios al final
 
                if (campos.length < 7) {
                    Teclado.error("Linea " + lineasLeidas
                        + " ignorada (faltan columnas): " + linea);
                    lineasError++;
                    continue;
                }
 
                try {
                    Objeto o = new Objeto();
                    o.setNombre(campos[0].trim());
                    o.setDescripcion(campos[1].trim().isEmpty() ? null : campos[1].trim());
                    o.setCantidad(Integer.parseInt(campos[2].trim()));
                    o.setFechaAlta(parseFecha(campos[3].trim()));
                    o.setObservaciones(campos[4].trim().isEmpty() ? null : campos[4].trim());
                    o.setIdUbicacion(Integer.parseInt(campos[5].trim()));
                    o.setIdCategoria(Integer.parseInt(campos[6].trim()));
 
                    // validacion minima
                    if (o.getNombre().isEmpty()) {
                        Teclado.error("Linea " + lineasLeidas
                            + " ignorada (nombre vacio)");
                        lineasError++;
                        continue;
                    }
 
                    lista.add(o);
 
                } catch (NumberFormatException e) {
                    Teclado.error("Linea " + lineasLeidas
                        + " ignorada (numero invalido): " + linea);
                    lineasError++;
                }
            }
 
            Teclado.imprimir("CSV leido: " + lista.size() + " objetos validos, "
                + lineasError + " lineas con error");
 
        } catch (IOException e) {
            Teclado.error("Error al leer el CSV: " + e.getMessage());
        }
        return lista;
    }
 
    /**
     * convierte una fecha en formato yyyy-MM-dd a LocalDate
     * si el formato es incorrecto devuelve la fecha actual
     */
    private LocalDate parseFecha(String texto) {
        if (texto == null || texto.isEmpty()) {
            return LocalDate.now();
        }
        try {
            return LocalDate.parse(texto);
        } catch (DateTimeParseException e) {
            Teclado.error("Fecha invalida '" + texto + "', se usa fecha actual");
            return LocalDate.now();
        }
    }
}
