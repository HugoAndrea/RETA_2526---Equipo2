/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.equipo3.inventario.controller;

import es.equipo3.inventario.dao.ObjetoDAO;
import es.equipo3.inventario.model.Objeto;
import es.equipo3.inventario.util.Teclado;
import java.util.List;

/**
 *
 * @author DAW104
 * 
 * 
 * 
 *  gestiona alta, baja, modificación de elementos. También lanza los hilos de importar/exportar
 */
public class InventarioController {
    private ObjetoDAO dao = new ObjetoDAO();
    
    /**
     * dar de alta a un objeto nuevo y validar el nombre
     * @param o objeto que queremos dar de alta
     * @return 
     */
    public boolean altaObjeto(Objeto o){
        if (!Teclado.textoValido(o.getNombre(), 30)) {
            return false;
        }
        return dao.insertar(o);
    }
    
    /**
     * 
     * @param o objeto a modificar
     * @return true o false
     */
    public boolean modificarObjeto(Objeto o){
        if (!Teclado.textoValido(o.getNombre(), 30)) {
            return false;
        }
        return dao.insertar(o);
    }
    
    /**
     * 
     * @param id del objeto
     * @return uso el metodo que he creado anteriormente en clase objetoDAO
     */
    public boolean bajaObjeto(int id){
        return dao.eliminar(id);
    }
    
    /**
     * 
     * @return uso el metodo que he creado anteriormente en clase objetoDAO
     */
    public List<Objeto> listarTodo(){
        return dao.listarTodos();
    }
    
    
    /**
     * parametros necesiarios para buscar el objeto
     * @param nombre
     * @param categoria
     * @param estado
     * @return uso el metodo que he creado anteriormente en clase objetoDAO
     */
    public List<Objeto> buscar(String nombre, String categoria, String estado){
        return dao.buscar(nombre, categoria, estado);
    }
}
