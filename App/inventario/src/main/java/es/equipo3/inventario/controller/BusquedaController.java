/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.equipo3.inventario.controller;

import es.equipo3.inventario.dao.ObjetoDAO;
import es.equipo3.inventario.model.Objeto;
import es.equipo3.inventario.util.Teclado;
import java.awt.Desktop;
import java.net.URI;
import java.util.List;

/**
 *
 * @author DAW104
 * 
 * gestiona los filtros de búsqueda y abre el navegador web para localizar un elemento
 */
public class BusquedaController {
    // se cambia cuando lo acabamos en la maquina virtual 
    private static final String URL = "file:///D:/Usuarios/DAW123/Desktop/Web_reto/Plano.html";
    
    private ObjetoDAO dao = new ObjetoDAO();
    
    /**
     * Busca objeto por 4 criterios dados
     * @param nombre
     * @param codigo
     * @param categoria
     * @param estdao
     * @return 
     */
    public List<Objeto> buscar(String nombre, String codigo, String categoria, String estdao){
        return dao.buscar(nombre, codigo, categoria, estdao);
    }
    /**
     * busca unicamente por codigo/id del objeto
     * @param codigo
     * @return 
     */
    public List<Objeto> buscarPorCodigo(String codigo){
        return dao.buscar("", codigo, "", "");
    }
    
    /**
     * Abre el navegador en el sitio web de localizacion en la mv
     * @return 
     */
    public boolean abrirLocalizacion(){
        
        try{
            // me lo ha hecho ia porque cuando queria abrir la web desde la aplicación
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(new URI(URL));
                return true;
            }        
        } catch (Exception e) {
            Teclado.error("Error al abrir el navegador");
            Teclado.error(e.getMessage());
        }
        return false;
    }
}
