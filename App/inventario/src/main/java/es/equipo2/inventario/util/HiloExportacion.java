/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.equipo2.inventario.util;

import es.equipo2.inventario.model.Objeto;
import java.util.List;

/**
 *
 * @author DAW104
 * 
 * Hace la exportacion en segundo plano 
 */
public class HiloExportacion extends Thread{
    private String       formato;       // "csv" | "excel" | "pdf"
    private String       rutaDestino;
    private List<Objeto> datos;
 
    /**
     * @param formato      formato de exportacion: "csv", "excel" o "pdf"
     * @param rutaDestino  ruta completa del archivo a generar
     * @param datos        lista de objetos a exportar
     */
    public HiloExportacion(String formato, String rutaDestino, List<Objeto> datos) {
        this.formato     = formato;
        this.rutaDestino = rutaDestino;
        this.datos       = datos;
    }
 
    /**
     * Ejecuta la exportacion en segundo plano.
     * Delega en ExportarCSV, ExportarExcel o ExportarPDF segun el formato.
     */
    @Override
    public void run() {
        Teclado.imprimir("Iniciando exportacion " + formato.toUpperCase()
            + " -> " + rutaDestino);
 
        switch (formato.toLowerCase()) {
            case "csv":
                new ExportarCSV().exportar(datos, rutaDestino);
                break;
            case "excel":
                new ExportarExcel().exportar(datos, rutaDestino);
                break;
            case "pdf":
                new ExportarPDF().exportar(datos, rutaDestino);
                break;
            default:
                Teclado.error("Formato no reconocido: " + formato
                    + ". Usa 'csv', 'excel' o 'pdf'");
        }
    }
    
    
}
