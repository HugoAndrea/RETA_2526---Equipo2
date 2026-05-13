/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.equipo2.inventario.dao;

import es.equipo2.inventario.db.AccesoBase;
import es.equipo2.inventario.util.Teclado;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author DAW104
 * 
 * Registra accesos de usuarios al sistema
 */
public class VistaDAO {
    private Connection conn = AccesoBase.getInstance().getConn();
    
    /**
     * Registra el acceso de usuario al sistema
     * @param id del usuario 
     * @return true si se registra 
     */
    public boolean registrarAcceso(int id){
        String sql = "INSERT INTO visitas (fecha, idUsuario) VALUES (?,?)";
        
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setDate(1, Date.valueOf(LocalDate.now()));
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            Teclado.error("Error al registrar la vista");
            Teclado.error(e.getMessage());
            return false;
        }
    }
}
