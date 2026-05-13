/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.equipo2.inventario.dao;

import es.equipo2.inventario.db.AccesoBase;
import es.equipo2.inventario.model.Ubicacion;
import es.equipo2.inventario.util.Teclado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DAW104
 */
public class UbicacionDAO {
    
    private Connection conn = AccesoBase.getInstance().getConn();
    
    /**
     * devuelve todas las ubicaciones de una balda concreta
     * @param idBalda
     * @return 
     */
    public List<Ubicacion> listarPorBalda(int idBalda){
        List<Ubicacion> lista =  new ArrayList<>();
        
        String sql = "SELECT * FROM ubicacion WHERE idBalda = ? ORDER BY posicion";
        
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, idBalda);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                lista.add(new Ubicacion(
                        rs.getInt("idUbicacion"),
                        rs.getString("posicion"),
                        rs.getInt("idBalda")
                ));
            }
        } catch (SQLException e) {
            Teclado.error("Error al listar ubicacion por balda");
            Teclado.error(e.getMessage());
        }
        return lista;
    }
    
    /**
     * devuelve ubicaciones del sistema
     * @return una lista con todas ubicaciones
     */
    public List<Ubicacion> listarTodas(){
        List<Ubicacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM ubicacion ORDER BY idBalda, posicion";
        
        try(Statement st = conn.prepareStatement(sql)){
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                lista.add(new Ubicacion(
                        rs.getInt("idUbicacion"),
                        rs.getString("posicion"),
                        rs.getInt("idBalda")
                ));
            }
        } catch (SQLException e) {
            Teclado.error("Error al listar todas las Ubicaciones");
            Teclado.error(e.getMessage());
        }
        return lista;
    }
    
    
    /**
     * obtiene ubicacion por su 
     * @param idUbicacion de la ubicacion de la que queremos obtener la informacion
     * @return 
     */
    public Ubicacion obtenerPorId(int idUbicacion){
        String sql = "SELECT * FROM ubicacion WHERE idUbicacion = ?";
        
        try(PreparedStatement ps = conn.prepareStatement(sql)){
           ps.setInt(1, idUbicacion);
           ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Ubicacion(
                        rs.getInt("idUbicacion"),
                        rs.getString("posicion"),
                        rs.getInt("idBalda")
                );
            }
        } catch (SQLException e) {
            Teclado.error("Error al obtener ubicacion");
            Teclado.error(e.getMessage());
        }
        return null;
    }
}
