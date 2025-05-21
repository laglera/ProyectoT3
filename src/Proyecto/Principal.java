package Proyecto;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.sql.*;

public class Principal extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtNombre;
    private JPasswordField txtContrasena;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Principal frame = new Principal();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Principal() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(600, 300, 850, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("Iniciar Sesión");
        lblTitulo.setFont(new Font("Arial Black", Font.PLAIN, 25));
        lblTitulo.setBounds(317, 83, 200, 30);
        contentPane.add(lblTitulo);

        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setFont(new Font("Arial Black", Font.PLAIN, 20));
        lblNombre.setBounds(361, 179, 100, 20);
        contentPane.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(325, 210, 166, 30);
        contentPane.add(txtNombre);

        JLabel lblContrasena = new JLabel("Contraseña");
        lblContrasena.setFont(new Font("Arial Black", Font.PLAIN, 20));
        lblContrasena.setBounds(350, 251, 124, 20);
        contentPane.add(lblContrasena);

        txtContrasena = new JPasswordField();
        txtContrasena.setBounds(325, 283, 166, 30);
        contentPane.add(txtContrasena);

        JButton btnLogin = new JButton("Ingresar");
        btnLogin.setFont(new Font("Arial", Font.PLAIN, 18));
        btnLogin.setBounds(350, 356, 120, 40);
        contentPane.add(btnLogin);
        
        btnLogin.addActionListener(e -> validarCredenciales());

        
        JButton btnRegistrarse = new JButton("Registrarse");
        btnRegistrarse.setBounds(325, 407, 166, 23);
        contentPane.add(btnRegistrarse);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/fondo3.png")));
        lblNewLabel.setBounds(0, 0, 834, 561);
        contentPane.add(lblNewLabel);

        btnRegistrarse.addActionListener(e -> {
            Registrarse registro = new Registrarse();
            registro.setVisible(true);
        });

    }

    private void validarCredenciales() {
        String usuario = txtNombre.getText();
        String contrasena = new String(txtContrasena.getPassword());

        ConexionMySQL conexion = new ConexionMySQL("root", "", "biblioteca");


        try {
            conexion.conectar();

            String consulta = "SELECT * FROM usuarios WHERE Nombre = '" + usuario + "' AND Contraseña = '" + contrasena + "'";
            ResultSet resultado = conexion.ejecutarSelect(consulta);

            if (resultado.next()) { 
                JOptionPane.showMessageDialog(this, "Inicio de sesión correcto");

                Buscar_Pelicula buscarPelicula = new Buscar_Pelicula();
                buscarPelicula.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Usuario no existe");
            }

            conexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al conectar con la base de datos.");
        }
    }
}
