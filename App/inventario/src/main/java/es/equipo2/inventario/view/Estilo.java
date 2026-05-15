/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.equipo2.inventario.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

/**
 *
 * clase con colores, fuentes, y estilos compartidos
 * @author DAW104
 * Esta clase esta hecha entera por ia 
 */
public class Estilo {
 
    // Paleta de colores
    public static final Color AZUL_OSCURO  = new Color(30,  55,  90);
    public static final Color AZUL_MEDIO   = new Color(52,  96, 152);
    public static final Color AZUL_CLARO   = new Color(210, 228, 252);
    public static final Color GRIS_FONDO   = new Color(245, 246, 248);
    public static final Color GRIS_LINEA   = new Color(210, 215, 222);
    public static final Color ROJO         = new Color(192,  40,  40);
    public static final Color VERDE        = new Color( 40, 140,  60);
    public static final Color NARANJA      = new Color(200, 100,  20);
    public static final Color BLANCO       = Color.WHITE;
 
    // Fuentes
    public static final Font FUENTE_TITULO   = new Font("Segoe UI", Font.BOLD,  18);
    public static final Font FUENTE_SUBTIT   = new Font("Segoe UI", Font.BOLD,  13);
    public static final Font FUENTE_NORMAL   = new Font("Segoe UI", Font.PLAIN, 12);
    public static final Font FUENTE_TABLA    = new Font("Segoe UI", Font.PLAIN, 12);
    public static final Font FUENTE_BTN      = new Font("Segoe UI", Font.BOLD,  12);
 
    // Bordes
    public static final Border BORDE_PANEL =
        BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(GRIS_LINEA, 1),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        );
 
    /** Crea un boton con el color de fondo dado. */
    public static JButton boton(String texto, Color fondo) {
        JButton btn = new JButton(texto);
        btn.setFont(FUENTE_BTN);
        btn.setBackground(fondo);
        btn.setForeground(BLANCO);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setOpaque(true);
        return btn;
    }
 
    /** Boton azul estandar. */
    public static JButton botonPrimario(String texto) {
        return boton(texto, AZUL_MEDIO);
    }
 
    /** Boton rojo para acciones de borrado. */
    public static JButton botonPeligro(String texto) {
        return boton(texto, ROJO);
    }
 
    /** Boton verde para acciones de creacion. */
    public static JButton botonExito(String texto) {
        return boton(texto, VERDE);
    }
 
    /** Boton naranja para acciones de modificacion. */
    public static JButton botonAdvertencia(String texto) {
        return boton(texto, NARANJA);
    }
 
    /** Crea un JLabel con estilo de titulo de seccion. */
    public static JLabel labelTitulo(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(FUENTE_TITULO);
        lbl.setForeground(AZUL_OSCURO);
        return lbl;
    }
 
    /** Crea un JLabel normal. */
    public static JLabel label(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(FUENTE_NORMAL);
        return lbl;
    }
 
    /** Aplica estilo visual a una JTable. */
    public static void estilizarTabla(JTable tabla) {
        tabla.setFont(FUENTE_TABLA);
        tabla.setRowHeight(26);
        tabla.setGridColor(GRIS_LINEA);
        tabla.setSelectionBackground(AZUL_CLARO);
        tabla.setSelectionForeground(AZUL_OSCURO);
        tabla.setShowVerticalLines(false);
        tabla.getTableHeader().setFont(FUENTE_SUBTIT);
        tabla.getTableHeader().setBackground(AZUL_OSCURO);
        tabla.getTableHeader().setForeground(BLANCO);
        tabla.getTableHeader().setReorderingAllowed(false);
    }
 
    /** Crea un panel de barra superior con fondo azul oscuro y titulo blanco. */
    public static JPanel barraHeader(String titulo) {
        JPanel barra = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 14));
        barra.setBackground(AZUL_OSCURO);
        JLabel lbl = new JLabel(titulo);
        lbl.setFont(FUENTE_TITULO);
        lbl.setForeground(BLANCO);
        barra.add(lbl);
        return barra;
    }
 
    /** Crea un campo de texto con estilo uniforme. */
    public static JTextField campo(int columnas) {
        JTextField txt = new JTextField(columnas);
        txt.setFont(FUENTE_NORMAL);
        txt.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(GRIS_LINEA),
            BorderFactory.createEmptyBorder(4, 6, 4, 6)
        ));
        return txt;
    }
 
    /** Crea un campo de contrasenia con estilo uniforme. */
    public static JPasswordField campoPassword(int columnas) {
        JPasswordField pf = new JPasswordField(columnas);
        pf.setFont(FUENTE_NORMAL);
        pf.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(GRIS_LINEA),
            BorderFactory.createEmptyBorder(4, 6, 4, 6)
        ));
        return pf;
    }
}
