/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.equipo3.inventario.controller;

import es.equipo3.inventario.dao.HistorialEstadoDAO;
import es.equipo3.inventario.dao.ObjetoDAO;
import es.equipo3.inventario.model.HistorialEstado;
import es.equipo3.inventario.model.Objeto;
import es.equipo3.inventario.model.Usuario;
import es.equipo3.inventario.util.Teclado;
import java.time.LocalDate;
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
    private HistorialEstadoDAO hdao = new HistorialEstadoDAO();
    
    /**
     * dar de alta a un objeto nuevo y validar el nombre
     * @param o objeto que queremos dar de alta
     * @return 
     */
    public boolean altaObjeto(Objeto o){
        if (!Teclado.textoValido(o.getNombre(), 30)) {
            return false;
        }
        if (o.getFechaAlta() == null) {
            o.setFechaAlta(LocalDate.now());
        }
        if (o.getCantidad() <= 0) {
            o.setCantidad(1);
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
     * busca objetos por los criterios dados
     * @param nombr
     * @param codigo
     * @param categoria
     * @param estado
     * @return objeto
     */
    public List<Objeto> buscar(String nombr, String codigo, String categoria, String estado){
        return dao.buscar(nombr, codigo, categoria, estado);
    }
    
    public boolean cambiarEstado(int idObj, int idEstado, Usuario us){
        if (us == null) {
            return false;
        }
        HistorialEstado h = new HistorialEstado();
        h.setIdObjeto(idObj);
        h.setIdEstado(idEstado);
        h.setIdUsuario(us.getIdUsuario());
        h.setFecha(LocalDate.now());
        return hdao.insertar(h);
        
    }
}
