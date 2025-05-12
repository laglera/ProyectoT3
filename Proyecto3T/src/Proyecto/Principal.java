package Proyecto;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.sql.SQLException;

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
        setBounds(600, 300, 650, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("Iniciar Sesión");
        lblTitulo.setFont(new Font("Arial Black", Font.PLAIN, 25));
        lblTitulo.setBounds(220, 30, 200, 30);
        contentPane.add(lblTitulo);

        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setFont(new Font("Arial Black", Font.PLAIN, 20));
        lblNombre.setBounds(275, 100, 100, 20);
        contentPane.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(242, 130, 156, 30);
        contentPane.add(txtNombre);

        JLabel lblContrasena = new JLabel("Contraseña");
        lblContrasena.setFont(new Font("Arial Black", Font.PLAIN, 20));
        lblContrasena.setBounds(255, 171, 124, 20);
        contentPane.add(lblContrasena);

        txtContrasena = new JPasswordField();
        txtContrasena.setBounds(242, 200, 156, 30);
        contentPane.add(txtContrasena);

        JButton btnLogin = new JButton("Ingresar");
        btnLogin.setFont(new Font("Arial", Font.PLAIN, 18));
        btnLogin.setBounds(270, 250, 120, 40);
        contentPane.add(btnLogin);

        btnLogin.addActionListener(e -> validarCredenciales());
        
        ConexionMySQL conexion = new ConexionMySQL("root", "", "biblioteca");
        
        try {
        	conexion.conectar();

        	String sentencia = "INSERT INTO usuarios(Nombre, contraseña) VALUES ('"+ txtNombre.getText() + "'," +  txtContrasena.getPassword() + ")";
        	conexion.ejecutarInsertDeleteUpdate(sentencia);

        	conexion.desconectar();

        	} catch (SQLException e1) {
        	// TODO Auto-generated catch block
        	e1.printStackTrace();
        	}
    }

    private void validarCredenciales() {
        String usuario = txtNombre.getText();
        String contrasena = new String(txtContrasena.getPassword());

        if (usuario.equals("admin") && contrasena.equals("1234")) {
            JOptionPane.showMessageDialog(this, "Inicio de sesión correcto");
            
            Buscar_Pelicula buscarPelicula = new Buscar_Pelicula();
            buscarPelicula.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Contraseña o usuario incorrecto");
        }
    }
    
}
