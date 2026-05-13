/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.equipo2.inventario.controller;

import es.equipo2.inventario.dao.HistorialEstadoDAO;
import es.equipo2.inventario.dao.ObjetoDAO;
import es.equipo2.inventario.model.HistorialEstado;
import es.equipo2.inventario.model.Objeto;
import es.equipo2.inventario.model.Usuario;
import es.equipo2.inventario.util.Teclado;
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
    private ObjetoDAO          dao  = new ObjetoDAO();
    private HistorialEstadoDAO hdao = new HistorialEstadoDAO();
 
    /**
     * da de alta un objeto nuevo validando el nombre
     * @param o objeto a insertar
     * @return true si se inserto correctamente
     */
    public boolean altaObjeto(Objeto o) {
        if (!Teclado.textoValido(o.getNombre(), 30)) return false;
        if (o.getFechaAlta() == null) o.setFechaAlta(LocalDate.now());
        if (o.getCantidad() <= 0)     o.setCantidad(1);
        return dao.insertar(o);
    }
 
    /**
     * modifica un objeto existente
     * @param o objeto con los datos actualizados
     * @return true si se modifico correctamente
     */
    public boolean modificarObjeto(Objeto o) {
        if (!Teclado.textoValido(o.getNombre(), 30)) return false;
        return dao.modificar(o);  // CORREGIDO: antes era dao.insertar(o)
    }
 
    /**
     * elimina un objeto por su ID
     * @param id del objeto
     * @return true si se elimino
     */
    public boolean bajaObjeto(int id) {
        return dao.eliminar(id);
    }
 
    /**
     * Lista todos los objetos del inventario
     * @return lista de objetos
     */
    public List<Objeto> listarTodo() {
        return dao.listarTodos();
    }
 
    /**
     * Busca objetos por los 4 criterios del enunciado
     * @param nombre texto en el nombre
     * @param codigo ID del objeto como texto
     * @param categoria texto en la categoria
     * @param estado texto en el estado
     * @return lista de objetos que cumplen los criterios
     */
    public List<Objeto> buscar(String nombre, String codigo, String categoria, String estado) {
        return dao.buscar(nombre, codigo, categoria, estado);
    }
 
    /**
     * Cambia el estado de un objeto y lo registra en historialEstado.
     * @param idObj ID del objeto
     * @param idEstado ID del nuevo estado
     * @param us usuario activo que realiza el cambio
     * @return true si se registro correctamente
     */
    public boolean cambiarEstado(int idObj, int idEstado, Usuario us) {
        if (us == null) return false;
        HistorialEstado h = new HistorialEstado();
        h.setIdObjeto(idObj);
        h.setIdEstado(idEstado);
        h.setIdUsuario(us.getIdUsuario());
        h.setFecha(LocalDate.now());
        return hdao.insertar(h);
    }
}
