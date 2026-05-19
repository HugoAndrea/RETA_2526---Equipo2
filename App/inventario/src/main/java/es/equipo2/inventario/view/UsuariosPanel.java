/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package es.equipo2.inventario.view;

import es.equipo2.inventario.controller.UsuarioController;
import es.equipo2.inventario.model.Usuario;
import es.equipo2.inventario.util.Teclado;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 * Panel de gestion de usuarios (solo administrador) Al crear un usuario nuevo
 * pide la contrasenia del admin activo para confirmar initComponents() generado
 * por NetBeans no tocar myInitComponents() contiene todo el diseno y la logica.
 *
 * @author DAW104
 */
public class UsuariosPanel extends javax.swing.JPanel {

    private UsuarioController controller = new UsuarioController();
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private JButton btnAlta, btnModificar, btnEliminar;
    private JLabel lblTotal;

    /**
     * Creates new form UsuariosPanel
     */
    public UsuariosPanel() {
        initComponents();
        myInitComponents();
        cargarDatos();
    }

    private void myInitComponents() {
        setLayout(new BorderLayout(0, 0));
        setBackground(Estilo.GRIS_FONDO);

        // --- Cabecera + toolbar en NORTH ---
        JPanel norte = new JPanel(new BorderLayout());
        norte.add(Estilo.barraHeader("👥  Gestion de Usuarios"), BorderLayout.NORTH);

        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        toolbar.setBackground(Estilo.BLANCO);
        toolbar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Estilo.GRIS_LINEA));

        btnAlta = Estilo.botonExito("＋  Nuevo usuario");
        btnModificar = Estilo.botonAdvertencia("✎  Modificar");
        btnEliminar = Estilo.botonPeligro("✕  Eliminar");

        JLabel aviso = new JLabel(
                "⚠  Crear usuarios requiere confirmar tu contrasena de administrador");
        aviso.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        aviso.setForeground(new Color(150, 100, 20));

        toolbar.add(btnAlta);
        toolbar.add(btnModificar);
        toolbar.add(btnEliminar);
        toolbar.add(Box.createHorizontalStrut(20));
        toolbar.add(aviso);

        norte.add(toolbar, BorderLayout.SOUTH);

        // --- Tabla en CENTER ---
        modeloTabla = new DefaultTableModel(
                new String[]{"ID", "Nombre de usuario", "Rol"}, 0) {
            @Override
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };

        tabla = new JTable(modeloTabla);
        Estilo.estilizarTabla(tabla);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        tabla.getColumnModel().getColumn(0).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(250);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(150);

        // Renderer: colorea filas por rol Y fuerza texto negro
        // CORRECCION: el anterior no ponia setForeground(Color.BLACK)
        // y Windows pintaba el texto en gris invisible
        tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object value,
                    boolean sel, boolean foc, int row, int col) {

                JLabel lbl = (JLabel) super.getTableCellRendererComponent(
                        t, value, sel, foc, row, col);

                if (sel) {
                    lbl.setBackground(Estilo.AZUL_CLARO);
                    lbl.setForeground(Estilo.AZUL_OSCURO);
                } else {
                    String rol = (String) t.getModel().getValueAt(row, 2);
                    lbl.setBackground("administrador".equals(rol)
                            ? new Color(230, 240, 255)
                            : (row % 2 == 0 ? Estilo.BLANCO : new Color(250, 251, 253)));
                    lbl.setForeground(Color.BLACK); // texto negro siempre
                }

                lbl.setOpaque(true);
                lbl.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 8));
                return lbl;
            }
        });

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createEmptyBorder(10, 12, 10, 12));

        // --- Pie en SOUTH ---
        JPanel pie = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 6));
        pie.setBackground(Estilo.BLANCO);
        pie.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Estilo.GRIS_LINEA));

        JLabel leyendaAdmin = new JLabel("■  Administrador");
        leyendaAdmin.setForeground(new Color(60, 90, 160));
        leyendaAdmin.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        JLabel leyendaProf = new JLabel("■  Profesor");
        leyendaProf.setForeground(Color.GRAY);
        leyendaProf.setFont(new Font("Segoe UI", Font.PLAIN, 11));

        lblTotal = new JLabel("Total: 0 usuarios");
        lblTotal.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblTotal.setForeground(Color.GRAY);

        pie.add(leyendaAdmin);
        pie.add(new JLabel("   "));
        pie.add(leyendaProf);
        pie.add(new JLabel("   |   "));
        pie.add(lblTotal);

        add(norte, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(pie, BorderLayout.SOUTH);

        btnAlta.addActionListener(e -> mostrarDialogoAlta());
        btnModificar.addActionListener(e -> mostrarDialogoModificar());
        btnEliminar.addActionListener(e -> eliminarUsuario());
    }
    /**
     * Construye un formulario con etiquetas y componentes alineados.
     */

    private void cargarDatos() {
        modeloTabla.setRowCount(0);
        for (Usuario u : controller.listarUsuarios()) {
            modeloTabla.addRow(new Object[]{
                u.getIdUsuario(), u.getUsuario(), u.getRol()
            });
        }
        lblTotal.setText("Total: " + modeloTabla.getRowCount() + " usuarios");
    }

    private void mostrarDialogoAlta() {
        // Paso 1: datos del nuevo usuario
        JTextField txtNombre = Estilo.campo(22);
        JPasswordField txtPass = Estilo.campoPassword(22);
        JComboBox<String> cmbRol = new JComboBox<>(
                new String[]{"administrador", "profesor"});
        cmbRol.setFont(Estilo.FUENTE_NORMAL);

        JPanel formNuevo = construirForm(
                new String[]{"Nombre de usuario *", "Contrasena *", "Rol *"},
                new Component[]{txtNombre, txtPass, cmbRol});

        int res = JOptionPane.showConfirmDialog(this, formNuevo,
                "Nuevo usuario", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (res != JOptionPane.OK_OPTION) {
            return;
        }

        String nombre = txtNombre.getText().trim();
        String pass = new String(txtPass.getPassword());
        String rol = (String) cmbRol.getSelectedItem();

        if (!Teclado.nombreValido(nombre)) {
            JOptionPane.showMessageDialog(this, "Nombre no valido (3-25 letras)");
            return;
        }
        if (!Teclado.passwordValido(pass)) {
            JOptionPane.showMessageDialog(this, "Contrasena no valida (min. 4 caracteres)");
            return;
        }

        // Paso 2: confirmar con contrasena del admin activo
        JPasswordField txtAdmin = Estilo.campoPassword(22);
        JPanel formAdmin = construirForm(
                new String[]{"Tu contrasena de administrador:"},
                new Component[]{txtAdmin});

        int resAdmin = JOptionPane.showConfirmDialog(this, formAdmin,
                "Confirmar identidad",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
        if (resAdmin != JOptionPane.OK_OPTION) {
            return;
        }

        String passAdmin = new String(txtAdmin.getPassword());
        Usuario nuevo = new Usuario(0, nombre, pass, rol);

        if (controller.registrarUsuario(nuevo, passAdmin)) {
            JOptionPane.showMessageDialog(this,
                    "Usuario '" + nombre + "' creado correctamente.");
            cargarDatos();
        } else {
            JOptionPane.showMessageDialog(this,
                    "No se pudo crear el usuario.\n"
                    + "Comprueba que tu contrasena de administrador es correcta.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarUsuario() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un usuario");
            return;
        }

        int id = (int) modeloTabla.getValueAt(fila, 0);
        String nom = (String) modeloTabla.getValueAt(fila, 1);

        int ok = JOptionPane.showConfirmDialog(this,
                "Eliminar el usuario '" + nom + "'?\nEsta accion no se puede deshacer.",
                "Confirmar eliminacion",
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (ok == JOptionPane.YES_OPTION) {
            if (controller.eliminarUsuario(id)) {
                JOptionPane.showMessageDialog(this, "Usuario eliminado correctamente.");
                cargarDatos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void mostrarDialogoModificar() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un usuario");
            return;
        }

        int id = (int) modeloTabla.getValueAt(fila, 0);
        String nom = (String) modeloTabla.getValueAt(fila, 1);
        String rol = (String) modeloTabla.getValueAt(fila, 2);

        JTextField txtNombre = Estilo.campo(22);
        txtNombre.setText(nom);
        JPasswordField txtPass = Estilo.campoPassword(22);
        JComboBox<String> cmbRol = new JComboBox<>(
                new String[]{"administrador", "profesor"});
        cmbRol.setSelectedItem(rol);
        cmbRol.setFont(Estilo.FUENTE_NORMAL);

        JPanel form = construirForm(
                new String[]{"Nombre *",
                    "Nueva contrasena (vacio = no cambiar)", "Rol *"},
                new Component[]{txtNombre, txtPass, cmbRol});

        int res = JOptionPane.showConfirmDialog(this, form,
                "Modificar usuario", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (res != JOptionPane.OK_OPTION) {
            return;
        }

        String nuevoNombre = txtNombre.getText().trim();
        String nuevaPass = new String(txtPass.getPassword());
        String nuevoRol = (String) cmbRol.getSelectedItem();

        if (!Teclado.nombreValido(nuevoNombre)) {
            JOptionPane.showMessageDialog(this, "Nombre no valido");
            return;
        }
        if (!nuevaPass.isEmpty() && !Teclado.passwordValido(nuevaPass)) {
            JOptionPane.showMessageDialog(this, "Contrasena no valida (min. 4 caracteres)");
            return;
        }

        if (controller.modificarUsuario(new Usuario(id, nuevoNombre, nuevaPass, nuevoRol))) {
            JOptionPane.showMessageDialog(this, "Usuario modificado correctamente.");
            cargarDatos();
        } else {
            JOptionPane.showMessageDialog(this, "Error al modificar",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private JPanel construirForm(String[] etiquetas, Component[] componentes) {
        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(Estilo.GRIS_FONDO);
        form.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5, 5, 5, 5);
        g.fill = GridBagConstraints.HORIZONTAL;
        for (int i = 0; i < etiquetas.length; i++) {
            g.gridx = 0;
            g.gridy = i;
            g.weightx = 0;
            form.add(Estilo.label(etiquetas[i]), g);
            g.gridx = 1;
            g.weightx = 1;
            form.add(componentes[i], g);
        }
        return form;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
