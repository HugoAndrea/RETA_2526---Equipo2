/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.equipo2.inventario.dao;

import es.equipo2.inventario.db.AccesoBase;
import es.equipo2.inventario.model.Armario;
import es.equipo2.inventario.util.Teclado;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author DAW104
 * 
 * 
 * listar todo();
 */
public class ArmarioDAO {
    private Connection getConn() { return AccesoBase.getInstance().getConn(); }
    
    /**
     * 
     * @return retorna lista de armarios disponibles, se ordena en orden alfabético por el nombre
     */
    public List<Armario> listarTodos(){
        List<Armario> lista = new ArrayList<>();
        String sql = "SELECT * FROM armario ORDER BY nombre";
        
        try(Statement st = getConn().createStatement()){
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                lista.add(new Armario(
                        rs.getInt("idArmario"),
                        rs.getString("nombre")
                ));
            }
        } catch (SQLException e) {
            Teclado.error("Error al listar armarios");
            Teclado.error(e.getMessage());
        }
        return lista;
    }
}
