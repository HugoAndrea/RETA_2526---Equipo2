/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.equipo2.inventario.util;

import es.equipo2.inventario.model.Objeto;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author DAW104
 */
public class ExportarCSV {
    private static final String SEPARADOR = ";";
    
    public String limpiar(String texto){
        if (texto == null) {
            return "";
        }
        return texto.replace(";",",").replace("\n", " ").replace("\r", " ");
    }
    
    public boolean exportar(List<Objeto> datos, String rutaDestino){
        try(PrintWriter pw = new PrintWriter(new FileWriter(rutaDestino))){
            // cabecera
            pw.println("id;nombre;descripcion;cantidad;fechaAlta;categoria;ubicacion;estado;observaciones");
            
            for (Objeto o : datos) {
                StringBuilder linea = new StringBuilder();
                linea.append(o.getIdObjeto()).append(SEPARADOR);
                linea.append(limpiar(o.getNombre())).append(SEPARADOR);
                linea.append(limpiar(o.getDescripcion())).append(SEPARADOR);
                linea.append(o.getCantidad()).append(SEPARADOR);
                linea.append(o.getFechaAlta()).append(SEPARADOR);
                linea.append(limpiar(o.getNombreCategoria())).append(SEPARADOR);
                linea.append(limpiar(o.getPosicionUbicacion())).append(SEPARADOR);
                linea.append(limpiar(o.getObservaciones()));
                pw.println(linea.toString()); 
            }
            Teclado.imprimir("CSV exportado: " + rutaDestino + " (" + datos.size() + " objetos)");
            return true;
        } catch (IOException e) {
            Teclado.error("Error al exportar CSV: " + e.getMessage());
            return false;
        }
    }
}
