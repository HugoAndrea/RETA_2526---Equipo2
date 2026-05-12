/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.equipo3.inventario.controller;

import es.equipo3.inventario.dao.MovimientoDAO;
import es.equipo3.inventario.dao.ObjetoDAO;
import es.equipo3.inventario.model.Movimiento;
import es.equipo3.inventario.model.Objeto;
import java.util.List;

/**
 *
 * @author DAW104
 * 
 * 
 * generar el informe y llama a los exportadores
 */
public class InformesController {
    private ObjetoDAO objdao = new ObjetoDAO();
    private MovimientoDAO movdao = new MovimientoDAO();
     
    
    /**
     * 
     * @return salida del metodo de la calse objetoDAO
     */
    public List<Objeto> informeCompleto(){
        return objdao.listarTodos();
    }
    
    /**
     * 
     * @return salida del metodo de la calse objetoDAO por categoria
     */
    public List<Objeto> informePorCat(String categoria){
        return objdao.buscar("", "", categoria, "");
    }
    /**
     * 
     * @param estado
     * @return 
     */
    public List<Objeto> informePorEstado(String estado){
        return objdao.buscar("", "", "", estado);
    }
    /**
     * 
     * @param idArmario
     * @param numBalda
     * @return 
     */
    public List<Objeto> informeArmarioBalda(int idArmario, int numBalda){
        return objdao.listarPorArmarioBalda(idArmario, numBalda);
    }
    
    /**
     * 
     * @return 
     */
    public List<Movimiento> informeMovmiento(){
        return movdao.listarTodos();
    }

}
