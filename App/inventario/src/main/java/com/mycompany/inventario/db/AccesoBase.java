/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventario.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author DAW104
 * 
 * conexion a la base de datos 
 * getInstance();
 */
public class AccesoBase {
    
    //todavia no tengo el archivo, pero en cuanto lo tenga lo cargo
    Properties prop = new Properties();
    private String url;
    private String clave;
    private String usuario;
    
    public void cargarArchivo(){
        try(FileInputStream file = new FileInputStream("nombre del archivo")){
            prop.load(file);
            
            this.url = prop.getProperty("db.url");
            this.usuario = prop.getProperty("db.usuario");
            this.clave = prop.getProperty("db.clave");
            
            System.out.println("Configuracion Cargada");
        } catch (IOException e) {
            System.out.println("Error al cargar el archivo con configuraciones");
        }
    }
    
    private Connection conn;
    
    private void abrirConexion(){
        try{
            prop.setProperty("url", this.url);
            prop.setProperty("usuario", this.usuario);
            prop.setProperty("clave ", this.clave);
            prop.setProperty("useSSL", "false");
            prop.setProperty("serverTimezone", "Europe/Madrid");
            
            conn = DriverManager.getConnection(this.url, prop);
            
        } catch (SQLException e) {
            System.out.println("Error al abrir la conexion");
            conn = null;
        }
    }
    
    private AccesoBase(){
        abrirConexion();
    }
    
    private static class AccesoBaseDatosHolder{
        private static final AccesoBase INSTANCE = new AccesoBase();
    }
    
    public Connection getConn(){
        try{
            if (conn == null && !conn.isClosed()) {
                abrirConexion();
            }
        }catch(SQLException e){
            System.out.println("Error añ comprobar el estado de la conexión");
        }
        return conn;
    }
    
    public boolean cerrarConexion(){
        if (conn == null) {
            return true;
        }
        try{
            conn.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexion");
            System.out.println("Mensaje: " + e.getMessage());
            return false;
        }
    }
}
