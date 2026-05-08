/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventario.dao;

import com.mycompany.inventario.db.AccesoBase;
import com.mycompany.inventario.model.Categoria;
import com.mycompany.inventario.util.Teclado;
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
 * listarTodo(); insertar();
 */
public class CategoriaDAO {
    
    // otra forma de hacer la conexion en java
    private Connection conn = AccesoBase.getInstance().getConn();
    
    /**
     * 
     * @return retorna la lista de categorias disponibles
     */
    public List<Categoria> listar(){
        List<Categoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM categoria ORDER BY nombre";
        
        try(Statement st = conn.createStatement()){
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                lista.add(new Categoria(
                        rs.getInt("idCategoria"),
                        rs.getString("nombre")
                ));
            }
        } catch (SQLException e) {
            Teclado.error("Error al listar las categorias disponibles");
        }
        return lista;
    }
}
