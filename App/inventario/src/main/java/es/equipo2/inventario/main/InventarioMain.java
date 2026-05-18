/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package es.equipo2.inventario.main;

import es.equipo2.inventario.view.LoginFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author DAW104
 */
public class InventarioMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
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
