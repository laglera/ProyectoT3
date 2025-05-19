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
    private JTextField textField_Busqueda;
    private JTextArea resultadoTextArea;
    private ConexionMySQL conexion;
    private JComboBox<String> listaPeliculas; 
    
    public Buscar_Pelicula() { 
        setTitle("Buscar Película");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(600, 300, 850, 600);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);
        JLabel lblTitulo = new JLabel("Introduce la película que quieres buscar:");
        lblTitulo.setFont(new Font("Arial Black", Font.PLAIN, 16));
        lblTitulo.setBounds(223, 21, 368, 30);
        contentPane.add(lblTitulo);
        textField_Busqueda = new JTextField();
        textField_Busqueda.setBounds(272, 62, 273, 30);
        contentPane.add(textField_Busqueda);
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(272, 109, 100, 30);
        contentPane.add(btnBuscar);
        resultadoTextArea = new JTextArea();
        resultadoTextArea.setEditable(false);
        resultadoTextArea.setLineWrap(true);
        resultadoTextArea.setWrapStyleWord(true);
        resultadoTextArea.setFont(new Font("Monospace", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(resultadoTextArea);

        scrollPane.setBounds(50, 150, 724, 357);

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
        listaPeliculas.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//textField_Busqueda.setText(listaPeliculas.getItemAt(listaPeliculas.getItemCount()));
        		String imprimirtexto = (String) listaPeliculas.getSelectedItem();
        		if (listaPeliculas.getSelectedIndex()!=0) {
        			textField_Busqueda.setText(imprimirtexto);
                } 
        	}
        });
        listaPeliculas.setBounds(618, 28, 156, 22);
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

            listaPeliculas.addItem("Selecciona pelicula");
            while (rs.next()) {
                listaPeliculas.addItem(rs.getString("titulo"));
            }
            conexion.desconectar();
        } catch (SQLException e) {
        	System.out.println("Error al cargar películas en el ComboBox: " + e.getMessage());
        }
    }

    
    
    private void buscarPelicula() {
        String tituloBuscado = textField_Busqueda.getText().trim();

        if (tituloBuscado.isEmpty()) {
            resultadoTextArea.setText("⚠️ Por favor, introduce un título.");
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
                int contador = 1;
                do {
                    resultado.append("🎬 Película ").append(contador++).append("\n");
                    resultado.append("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
                    resultado.append("📌 TÍTULO: ").append(rs.getString("titulo")).append("\n");
                    resultado.append("🎥 DIRECTOR: ").append(rs.getString("director")).append("\n");
                    resultado.append("📅 AÑO: ").append(rs.getInt("año")).append("\n");
                    resultado.append("🎭 GÉNERO: ").append(rs.getString("genero")).append("\n");
                    resultado.append("📝 SINOPSIS:\n").append(rs.getString("sinopsis")).append("\n");
                    resultado.append("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n\n");
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