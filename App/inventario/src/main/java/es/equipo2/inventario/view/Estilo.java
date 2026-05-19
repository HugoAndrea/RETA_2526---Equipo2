package es.equipo2.inventario.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 * Clase de utilidad con los colores, fuentes y estilos compartidos
 * por todos los paneles y frames de la aplicacion.
 * @author DAW104
 */
public class Estilo {

    // Paleta de colores
    public static final Color AZUL_OSCURO = new Color(30,  55,  90);
    public static final Color AZUL_MEDIO  = new Color(52,  96, 152);
    public static final Color AZUL_CLARO  = new Color(210, 228, 252);
    public static final Color GRIS_FONDO  = new Color(245, 246, 248);
    public static final Color GRIS_LINEA  = new Color(210, 215, 222);
    public static final Color ROJO        = new Color(192,  40,  40);
    public static final Color VERDE       = new Color( 40, 140,  60);
    public static final Color NARANJA     = new Color(200, 100,  20);
    public static final Color BLANCO      = Color.WHITE;

    // Fuentes
    public static final Font FUENTE_TITULO = new Font("Segoe UI", Font.BOLD,  18);
    public static final Font FUENTE_SUBTIT = new Font("Segoe UI", Font.BOLD,  13);
    public static final Font FUENTE_NORMAL = new Font("Segoe UI", Font.PLAIN, 12);
    public static final Font FUENTE_TABLA  = new Font("Segoe UI", Font.PLAIN, 12);
    public static final Font FUENTE_BTN    = new Font("Segoe UI", Font.BOLD,  12);

    // Bordes
    public static final Border BORDE_PANEL =
        BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(GRIS_LINEA, 1),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        );

    //  BOTONES

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

    public static JButton botonPrimario(String texto)    { return boton(texto, AZUL_MEDIO); }
    public static JButton botonPeligro(String texto)     { return boton(texto, ROJO);       }
    public static JButton botonExito(String texto)       { return boton(texto, VERDE);      }
    public static JButton botonAdvertencia(String texto) { return boton(texto, NARANJA);    }

    //  LABELS

    public static JLabel labelTitulo(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(FUENTE_TITULO);
        lbl.setForeground(AZUL_OSCURO);
        return lbl;
    }

    public static JLabel label(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(FUENTE_NORMAL);
        return lbl;
    }

    //  TABLA — renderer forzado para que funcione en Windows/sistema

    /**
     * Aplica estilo visual a una JTable.
     * Usa un DefaultTableCellRenderer personalizado en la cabecera para forzar
     * los colores aunque el Look and Feel del sistema los sobreescriba.
     * CORRECCIÓN: antes setBackground/setForeground en JTableHeader no
     * funcionaban en Windows con el Look and Feel del sistema.
     */
    public static void estilizarTabla(JTable tabla) {
        tabla.setFont(FUENTE_TABLA);
        tabla.setRowHeight(26);
        tabla.setGridColor(GRIS_LINEA);
        tabla.setBackground(BLANCO);
        tabla.setForeground(Color.BLACK);              // texto de datos visible
        tabla.setSelectionBackground(AZUL_CLARO);
        tabla.setSelectionForeground(AZUL_OSCURO);
        tabla.setShowVerticalLines(false);
        tabla.setAutoCreateRowSorter(true);            // permite ordenar por columna

        // Renderer de cabecera — fuerza colores independientemente del L&F
        JTableHeader header = tabla.getTableHeader();
        header.setFont(FUENTE_SUBTIT);
        header.setReorderingAllowed(false);
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable t, Object value, boolean isSelected,
                    boolean hasFocus, int row, int col) {

                JLabel lbl = (JLabel) super.getTableCellRendererComponent(
                    t, value, isSelected, hasFocus, row, col);

                lbl.setBackground(AZUL_OSCURO);        // fondo azul oscuro forzado
                lbl.setForeground(BLANCO);             // texto blanco forzado
                lbl.setFont(FUENTE_SUBTIT);
                lbl.setOpaque(true);
                lbl.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(0, 0, 0, 1, AZUL_MEDIO),
                    BorderFactory.createEmptyBorder(0, 8, 0, 8)
                ));
                lbl.setHorizontalAlignment(SwingConstants.LEFT);
                return lbl;
            }
        });

        // Renderer de celdas de datos — fondo blanco con texto negro visible
        tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable t, Object value, boolean isSelected,
                    boolean hasFocus, int row, int col) {

                JLabel lbl = (JLabel) super.getTableCellRendererComponent(
                    t, value, isSelected, hasFocus, row, col);

                if (isSelected) {
                    lbl.setBackground(AZUL_CLARO);
                    lbl.setForeground(AZUL_OSCURO);
                } else {
                    // Filas alternas ligeramente diferenciadas
                    lbl.setBackground(row % 2 == 0 ? BLANCO : new Color(250, 251, 253));
                    lbl.setForeground(Color.BLACK);
                }
                lbl.setOpaque(true);
                lbl.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 8));
                return lbl;
            }
        });

        // Renderer especial para columnas numericas (Integer)
        tabla.setDefaultRenderer(Integer.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable t, Object value, boolean isSelected,
                    boolean hasFocus, int row, int col) {

                JLabel lbl = (JLabel) super.getTableCellRendererComponent(
                    t, value, isSelected, hasFocus, row, col);

                if (isSelected) {
                    lbl.setBackground(AZUL_CLARO);
                    lbl.setForeground(AZUL_OSCURO);
                } else {
                    lbl.setBackground(row % 2 == 0 ? BLANCO : new Color(250, 251, 253));
                    lbl.setForeground(Color.BLACK);
                }
                lbl.setOpaque(true);
                lbl.setHorizontalAlignment(SwingConstants.CENTER);
                lbl.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 8));
                return lbl;
            }
        });
    }

    //  OTROS COMPONENTES

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
        txt.setForeground(Color.BLACK);
        txt.setBackground(BLANCO);
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
        pf.setForeground(Color.BLACK);
        pf.setBackground(BLANCO);
        pf.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(GRIS_LINEA),
            BorderFactory.createEmptyBorder(4, 6, 4, 6)
        ));
        return pf;
    }
}