/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.equipo2.inventario.util;
import com.mysql.cj.result.Row;
import es.equipo2.inventario.model.Objeto;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author DAW104
 * 
 * crea un archivo excel con libreria POI
 */
public class ExportarExcel {

    
    private String nulo(String texto){
        return texto != null ? texto : "";
    }   
    
    public boolean exportar(List<Objeto> datos, String rutaDestino) {
        try (Workbook wb = new XSSFWorkbook();
             FileOutputStream fos = new FileOutputStream(rutaDestino)) {
 
            Sheet sheet = wb.createSheet("Inventario");
 
            // Fila de cabecera
            Row cabecera = sheet.createRow(0);
            cabecera.createCell(0).setCellValue("ID");
            cabecera.createCell(1).setCellValue("Nombre");
            cabecera.createCell(2).setCellValue("Descripcion");
            cabecera.createCell(3).setCellValue("Cantidad");
            cabecera.createCell(4).setCellValue("Fecha Alta");
            cabecera.createCell(5).setCellValue("Categoria");
            cabecera.createCell(6).setCellValue("Ubicacion");
            cabecera.createCell(7).setCellValue("Estado");
            cabecera.createCell(8).setCellValue("Observaciones");
 
            // Filas de datos
            int numFila = 1;
            for (Objeto o : datos) {
                Row fila = sheet.createRow(numFila++);
                fila.createCell(0).setCellValue(o.getIdObjeto());
                fila.createCell(1).setCellValue(nulo(o.getNombre()));
                fila.createCell(2).setCellValue(nulo(o.getDescripcion()));
                fila.createCell(3).setCellValue(o.getCantidad());
                fila.createCell(4).setCellValue(
                    o.getFechaAlta() != null ? o.getFechaAlta().toString() : "");
                fila.createCell(5).setCellValue(nulo(o.getNombreCategoria()));
                fila.createCell(6).setCellValue(nulo(o.getPosicionUbicacion()));
                fila.createCell(7).setCellValue(nulo(o.getEstadoActual()));
                fila.createCell(8).setCellValue(nulo(o.getObservaciones()));
            }
 
            wb.write(fos);
            Teclado.imprimir("Excel exportado: " + rutaDestino
                + " (" + datos.size() + " objetos)");
            return true;
 
        } catch (IOException e) {
            Teclado.error("Error al exportar Excel: " + e.getMessage());
            return false;
        }
    }
}

