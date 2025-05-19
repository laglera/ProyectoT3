package Proyecto;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Registrarse extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtNombre;
    private JTextField txtCorreo;
    private JTextField txtTelefono;
    private JPasswordField txtContrasena;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Registrarse frame = new Registrarse();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Registrarse() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(600, 300, 850, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblTituloRegistro = new JLabel("Registro");
        lblTituloRegistro.setFont(new Font("Arial Black", Font.PLAIN, 25));
        lblTituloRegistro.setBounds(347, 122, 137, 25);
        contentPane.add(lblTituloRegistro);
        
        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setFont(new Font("Arial Black", Font.PLAIN, 15));
        lblNombre.setBounds(325, 170, 71, 26);
        contentPane.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(432, 170, 113, 24);
        contentPane.add(txtNombre);
        
        JLabel lblCorreo = new JLabel("Correo electrónico");
        lblCorreo.setFont(new Font("Arial Black", Font.PLAIN, 15));
        lblCorreo.setBounds(261, 207, 160, 26);
        contentPane.add(lblCorreo);

        txtCorreo = new JTextField();
        txtCorreo.setBounds(432, 209, 113, 24);
        contentPane.add(txtCorreo);

        JLabel lblTelefono = new JLabel("Teléfono");
        lblTelefono.setFont(new Font("Arial Black", Font.PLAIN, 15));
        lblTelefono.setBounds(310, 247, 98, 26);
        contentPane.add(lblTelefono);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(432, 249, 113, 24);
        contentPane.add(txtTelefono);

        JLabel lblContrasena = new JLabel("Contraseña");
        lblContrasena.setFont(new Font("Arial Black", Font.PLAIN, 15));
        lblContrasena.setBounds(298, 293, 106, 26);
        contentPane.add(lblContrasena);

        txtContrasena = new JPasswordField();
        txtContrasena.setBounds(432, 296, 113, 24);
        contentPane.add(txtContrasena);

        // Botón de registro
        JButton btnRegistrarse = new JButton("Registrarse");
        btnRegistrarse.setBounds(345, 349, 150, 30);
        contentPane.add(btnRegistrarse);
        
        btnRegistrarse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrarUsuario();
            }
        });
    }
    
    // Método para registrar el usuario en la base de datos
    private void registrarUsuario() {
        String nombre = txtNombre.getText();
        String correo = txtCorreo.getText();
        String telefono = txtTelefono.getText();
        String contrasena = new String(txtContrasena.getPassword());

        ConexionMySQL conexion = new ConexionMySQL("root", "", "biblioteca");

        try {
            conexion.conectar();

            // Insertar el usuario en la base de datos
            String sentencia = "INSERT INTO usuarios (Nombre, Correo, Telefono, Contraseña) VALUES ('" 
                    + nombre + "', '" + correo + "', '" + telefono + "', '" + contrasena + "')";

            int filasAfectadas = conexion.ejecutarInsertDeleteUpdate(sentencia);

            conexion.desconectar();

            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(this, "Registro exitoso");
                dispose(); // Cierra la ventana de registro después de la inserción
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar usuario");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al conectar con la base de datos.");
        }
    }
}

