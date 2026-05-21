/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.equipo2.inventario.util;

import es.equipo2.inventario.dao.ObjetoDAO;
import es.equipo2.inventario.model.Objeto;
import static java.awt.EventQueue.invokeLater;
import java.util.List;

/**
 *
 * @author DAW104
 * hace la importacion en segundo plano para que la ventana no se congele
 */
public class HiloImportacion extends Thread{
    private String rutaArchivo;
    private ObjetoDAO dao;  
    private Runnable callback; // para la ui cuando termina

    public HiloImportacion(String rutaArchivo, ObjetoDAO dao, Runnable callback) {
        this.rutaArchivo = rutaArchivo;
        this.dao = dao;
        this.callback = callback;
    }
    
    public void run(){
        Teclado.error("Iniciando importación: " + rutaArchivo);
        
        ImportadorCSV lector = new ImportadorCSV();
        List<Objeto> obj = lector.leer(rutaArchivo);
        
        if (obj.isEmpty()) {
            Teclado.error("No se encontron objetos validos en el CSV");
            return;
        }
        
        int insertados = 0;
        int errores = 0;
        
        for (Objeto objeto : obj) {
            if (dao.insertar(objeto)) {
                insertados++;
            }else{
                errores++;
                Teclado.error("Error al insertar: " + objeto.getNombre());
            }
        }
        
        Teclado.imprimir("Importacion completada: " + insertados + " insertados, " + errores + " errores");
        
        if (callback != null) {
            invokeLater(callback);
        }
    }
}
