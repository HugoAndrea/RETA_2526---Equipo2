/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.equipo2.inventario.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import es.equipo2.inventario.model.Objeto;
import java.awt.Font;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.List;
/**
 *
 * @author DAW104
 * 
 * crea el archivo pdf usando libreria iText
 */
public class ExportarPDF {
    
        public boolean exportar(List<Objeto> datos, String rutaDestino) {
        Document doc = new Document();
 
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(rutaDestino));
            doc.open();
 
            // Titulo e informacion general
            doc.add(new Paragraph("INFORME DE INVENTARIO"));
            doc.add(new Paragraph("Fecha: " + LocalDate.now()));
            doc.add(new Paragraph("Total objetos: " + datos.size()));
            doc.add(new Paragraph(" "));
 
            // Cabecera de columnas
            doc.add(new Paragraph(
                "ID | Nombre | Descripcion | Cant. | Fecha Alta | Categoria | Ubicacion | Estado | Observaciones"
            ));
            doc.add(new Paragraph("------------------------------------------------------------"));
 
            // Una linea por objeto
            for (Objeto o : datos) {
                doc.add(new Paragraph(
                    o.getIdObjeto()            + " | " +
                    nulo(o.getNombre())        + " | " +
                    nulo(o.getDescripcion())   + " | " +
                    o.getCantidad()            + " | " +
                    nulo(o.getFechaAlta())     + " | " +
                    nulo(o.getNombreCategoria())   + " | " +
                    nulo(o.getPosicionUbicacion()) + " | " +
                    nulo(o.getEstadoActual())      + " | " +
                    nulo(o.getObservaciones())
                ));
            }
 
            Teclado.imprimir("PDF exportado: " + rutaDestino
                + " (" + datos.size() + " objetos)");
            return true;
 
        } catch (Exception e) {
            Teclado.error("Error al exportar PDF: " + e.getMessage());
            return false;
        } finally {
            if (doc.isOpen()) doc.close();
        }
    }
 
    /** Devuelve cadena vacia si el valor es null. */
    private String nulo(Object valor) {
        return valor != null ? valor.toString() : "";
    }
}
