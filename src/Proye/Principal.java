package Proye;



import java.awt.*;

import javax.swing.*;

import javax.swing.border.EmptyBorder;



import java.awt.event.*;



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

        setBounds(100, 100, 650, 400);

        contentPane = new JPanel();

        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);

        contentPane.setLayout(null);



        JLabel lblTitulo = new JLabel("Iniciar Sesión");
        lblTitulo.setBackground(new Color(255, 255, 255));

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

        lblContrasena.setBounds(252, 169, 141, 20);

        contentPane.add(lblContrasena);



        txtContrasena = new JPasswordField();

        txtContrasena.setBounds(242, 200, 156, 30);

        contentPane.add(txtContrasena);



        JButton btnLogin = new JButton("Ingresar");

        btnLogin.setFont(new Font("Arial", Font.PLAIN, 18));

        btnLogin.setBounds(262, 251, 120, 40);

        contentPane.add(btnLogin);
        
        JButton btnRegistrarse = new JButton("Registrarse");
        btnRegistrarse.setBounds(500, 327, 124, 23);
        contentPane.add(btnRegistrarse);

        btnRegistrarse.addActionListener(e -> {
            Registrarse registro = new Registrarse();
            registro.setVisible(true);
        });
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setBounds(0, 0, 634, 361);
        ImageIcon icono2= new ImageIcon(Principal.class.getResource("/imagenes/ChatGPTImagen2.jpg"));
        Image imagen2 = icono2.getImage().getScaledInstance( lblNewLabel.getWidth(),lblNewLabel.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon iconoAjustado2 = new ImageIcon(imagen2);
        lblNewLabel.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/ChatGPTImagen2.jpg"))); 

        contentPane.add(lblNewLabel);



        btnLogin.addActionListener(e -> validarCredenciales());

    }



    private void validarCredenciales() {

        String usuario = txtNombre.getText();

        String contrasena = new String(txtContrasena.getPassword());



        if (usuario.equals("admin") && contrasena.equals("1234")) {

            JOptionPane.showMessageDialog(this, "Inicio de sesión correcto");
            Buscar_Pelicula buscarpelicula = new Buscar_Pelicula();
            buscarpelicula.setVisible(true);

        } else {

            JOptionPane.showMessageDialog(this, "Contraseña o usuario incorrecto");

        }

    }
}