package Proye;

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

    	//Hacemos visible la ventana
    
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

    	//Configuramos los atributos y el contenido de la ventana 
    
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
        
        //campo para el email
        
        JLabel lblCorreo = new JLabel("Correo electrónico");
        lblCorreo.setFont(new Font("Arial Black", Font.PLAIN, 15));
        lblCorreo.setBounds(261, 207, 160, 26);
        contentPane.add(lblCorreo);

        txtCorreo = new JTextField();
        txtCorreo.setBounds(432, 209, 113, 24);
        contentPane.add(txtCorreo);

        //Campo para el Telefono
        
        JLabel lblTelefono = new JLabel("Teléfono");
        lblTelefono.setFont(new Font("Arial Black", Font.PLAIN, 15));
        lblTelefono.setBounds(310, 247, 98, 26);
        contentPane.add(lblTelefono);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(432, 249, 113, 24);
        contentPane.add(txtTelefono);

        //Campo para la contraseña, 
        
        JLabel lblContrasena = new JLabel("Contraseña");
        lblContrasena.setFont(new Font("Arial Black", Font.PLAIN, 15));
        lblContrasena.setBounds(298, 293, 106, 26);
        contentPane.add(lblContrasena);

        txtContrasena = new JPasswordField();
        txtContrasena.setBounds(432, 296, 113, 24);
        contentPane.add(txtContrasena);

        // Campo para el registro
        JButton btnRegistrarse = new JButton("Registrarse");
        btnRegistrarse.setBounds(345, 349, 150, 30);
        contentPane.add(btnRegistrarse);
        
        //Añadimos el fondo de la ventana con un Label
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(Registrarse.class.getResource("/imagenes/fondo3.png")));
        lblNewLabel.setBounds(0, 0, 834, 561);
        contentPane.add(lblNewLabel);
        
        btnRegistrarse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrarUsuario();
            }
        });
    }
    
    // Método para conectar y registrar el usuario en la base de datos
    private void registrarUsuario() {
        String nombre = txtNombre.getText();
        String correo = txtCorreo.getText();
        String telefono = txtTelefono.getText();
        String contrasena = new String(txtContrasena.getPassword());

        ConexionMySQL conexion = new ConexionMySQL("root", "", "biblioteca");

        try {
            conexion.conectar();

            // Inserta el usuario en la base de datos
            String sentencia = "INSERT INTO usuarios (Nombre, Correo, Telefono, Contraseña) VALUES ('" 
                    + nombre + "', '" + correo + "', '" + telefono + "', '" + contrasena + "')";

            int filasAfectadas = conexion.ejecutarInsertDeleteUpdate(sentencia);

            conexion.desconectar();

            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(this, "Registro exitoso");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar usuario");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al conectar con la base de datos.");
        }
    }
}
