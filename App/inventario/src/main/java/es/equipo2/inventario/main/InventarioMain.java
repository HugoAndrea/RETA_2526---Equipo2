/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package es.equipo2.inventario.main;

import es.equipo2.inventario.db.AccesoBase;
import es.equipo2.inventario.ssh.DatabaseTunel;
import es.equipo2.inventario.util.Encriptador;
import es.equipo2.inventario.view.LoginFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Clase main que ejecuta todo el programas
 * @author DAW104
 */
public class InventarioMain {
    
    private static DatabaseTunel tunnel;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
         // 1. PRIMERO: Abrir el túnel SSH
        tunnel = new DatabaseTunel();
        try {
            tunnel.abrirTunel();
        } catch (Exception e) {
            System.err.println("Error al establecer el túnel SSH: " + e.getMessage());
            System.exit(1); // Sin túnel no continuamos
        }
        
        AccesoBase.getInstance().inicializar();
 
        // TEMPORAL: genera el hash de 'admin1234' y actualiza la BD automaticamente
        try {
            String hash = Encriptador.hashear("admin1234");
            System.out.println(">>> HASH GENERADO: " + hash);
            java.sql.Connection conn = AccesoBase.getInstance().getConn();
            java.sql.PreparedStatement ps = conn.prepareStatement(
                "UPDATE usuario SET contrasenia = ? WHERE nombre = 'admin'");
            ps.setString(1, hash);
            int rows = ps.executeUpdate();
            System.out.println(">>> USUARIOS ACTUALIZADOS: " + rows);
            ps.close();
        } catch (Exception e) {
            System.out.println(">>> ERROR actualizando hash: " + e.getMessage());
        }

        // 2. Cerrar el túnel cuando se cierre la aplicación
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            tunnel.cerrarTunel();
        }));
        
        // Aplica el look and feel del sistema operativo
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Si falla usa el look and feel por defecto
        }

 
        // CORREGIDO: antes solo imprimia "Hello World"
        // Ahora abre la ventana de login en el hilo de Swing (obligatorio)
        SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }
    
}
