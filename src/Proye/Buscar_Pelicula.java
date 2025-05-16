package Proye;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JComboBox;

public class Buscar_Pelicula extends JFrame {

	private static final long serialVersionUID = 1L;

    private JPanel contentPane;

    private JTextField textField;

    private JTextArea resultadoTextArea;

    private ConexionMySQL conexion;

    private JComboBox<String> listaPeliculas; 



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



        JButton btnBuscar = new JButton("Buscar");

        btnBuscar.setBounds(272, 109, 100, 30);

        contentPane.add(btnBuscar);



        resultadoTextArea = new JTextArea();

        resultadoTextArea.setEditable(false);

        resultadoTextArea.setLineWrap(true);

        resultadoTextArea.setWrapStyleWord(true);



        JScrollPane scrollPane = new JScrollPane(resultadoTextArea);

        scrollPane.setBounds(50, 150, 529, 180);

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        contentPane.add(scrollPane);



        conexion = new ConexionMySQL("root", "", "biblioteca");



        btnBuscar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                buscarPelicula();

            }

        });



        listaPeliculas = new JComboBox<>();

        listaPeliculas.setBounds(514, 28, 110, 22);

        contentPane.add(listaPeliculas);



        cargarPeliculasEnComboBox();



        setVisible(true);

    }



    private void cargarPeliculasEnComboBox() {

        String sql = "SELECT titulo FROM peliculas";

        try {

            conexion.conectar();

            ResultSet rs = conexion.ejecutarSelect(sql);



            listaPeliculas.removeAllItems();



            while (rs.next()) {

                listaPeliculas.addItem(rs.getString("titulo"));

            }

            conexion.desconectar();

        } catch (SQLException e) {

            System.out.println("Error al cargar películas en el ComboBox: " + e.getMessage());

        }

    }



    private void buscarPelicula() {

        String tituloBuscado = textField.getText().trim();

        if (tituloBuscado.isEmpty()) {

            resultadoTextArea.setText("Por favor, introduce un título.");

            return;

        }



        String sql = "SELECT * FROM peliculas WHERE titulo LIKE '%" + tituloBuscado + "%'";



        try {

            conexion.conectar();

            ResultSet rs = conexion.ejecutarSelect(sql);



            if (!rs.next()) {

                resultadoTextArea.setText("No se encontraron resultados.");

            } else {

                StringBuilder resultado = new StringBuilder();

                do {

                    resultado.append("Título: ").append(rs.getString("titulo")).append("\n");

                    resultado.append("Director: ").append(rs.getString("director")).append("\n");

                    resultado.append("Año: ").append(rs.getInt("anio")).append("\n");

                    resultado.append("Género: ").append(rs.getString("genero")).append("\n");

                    resultado.append("Sinopsis: ").append(rs.getString("sinopsis")).append("\n\n");

                } while (rs.next());



                resultadoTextArea.setText(resultado.toString());

            }

            conexion.desconectar();

        } catch (SQLException ex) {

            resultadoTextArea.setText("Error al buscar película: " + ex.getMessage());

        }

    }



    public static void main(String[] args) {

        new Buscar_Pelicula();

    }

}
