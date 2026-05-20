/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.equipo2.inventario.dao;

import es.equipo2.inventario.db.AccesoBase;
import es.equipo2.inventario.model.Estado;
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
 */
public class EstadoDAO {
    private Connection getConn() { return AccesoBase.getInstance().getConn(); }
    
    /**
     * 
     * @return lista con estados ordenados por nombre
     */
    public List<Estado> listarTodos(){
        String sql = "SELECT * FROM estado ORDER BY nombre";
        List<Estado> lista = new ArrayList<>();
        
        try(Statement st = getConn().createStatement()){
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                lista.add(new Estado(
                        rs.getInt("idEstado"),
                        rs.getString("nombre")
                ));
            }
        } catch (SQLException e) {
            Teclado.error("Error al lista estados");
            Teclado.error(e.getMessage());
        }
        return lista;
    }
}
