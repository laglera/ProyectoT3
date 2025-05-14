package Proyecto;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Buscar_Pelicula extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JTextArea resultadoTextArea;
    private ConexionMySQL conexion;
    private JButton btnBuscar_1;

    public Buscar_Pelicula() {
        setTitle("Buscar Película");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(600, 300, 650, 400);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("Introduce la película que quieres buscar:");
        lblTitulo.setFont(new Font("Arial Black", Font.PLAIN, 16));
        lblTitulo.setBounds(136, 21, 368, 30);
        contentPane.add(lblTitulo);

        textField = new JTextField();
        textField.setBounds(193, 62, 250, 30);
        contentPane.add(textField);

     // Crear el JTextArea con configuración mejorada
        resultadoTextArea = new JTextArea();
        resultadoTextArea.setEditable(false);
        resultadoTextArea.setLineWrap(true); // Permite ajustar el texto dentro del área
        resultadoTextArea.setWrapStyleWord(true); // Evita cortar palabras a la mitad

        // Crear el JScrollPane y agregar el JTextArea dentro
        JScrollPane scrollPane = new JScrollPane(resultadoTextArea);
        scrollPane.setBounds(50, 150, 529, 152);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Barra de desplazamiento siempre visible
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // No permitir desplazamiento horizontal

        // Agregar el JScrollPane en lugar del JTextArea directamente
        contentPane.add(scrollPane);
        
        btnBuscar_1 = new JButton("Buscar");
        btnBuscar_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnBuscar_1.setBounds(265, 103, 100, 30);
        contentPane.add(btnBuscar_1);


        // Inicializar conexión con la base de datos
        conexion = new ConexionMySQL("root", "", "biblioteca");

        setVisible(true);
    }

    public static void main(String[] args) {
        new Buscar_Pelicula();
    }
}

