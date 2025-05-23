package Proye;

import javax.swing.*;

import java.awt.Desktop;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.FlowLayout;

public class Buscar_Pelicula extends JFrame {
	private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField_Busqueda;
    private JTextArea resultadoTextArea;
    private ConexionMySQL conexion;
    private JComboBox<String> listaPeliculas; 
    
    //Metodo para la barra de busqueda de peliculas, donde usamos un tsxtField
    
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
        btnBuscar.setBounds(359, 108, 100, 30);
        contentPane.add(btnBuscar);
        resultadoTextArea = new JTextArea();
        resultadoTextArea.setEditable(false);
        resultadoTextArea.setLineWrap(true);
        resultadoTextArea.setWrapStyleWord(true);
        resultadoTextArea.setFont(new Font("Monospace", Font.PLAIN, 14));

        JLabel lblNewLabel_cartel = new JLabel("");
        lblNewLabel_cartel.addMouseListener(new MouseAdapter() {
        	
        	
        });
        lblNewLabel_cartel.setBounds(10, 108, 214, 306);
        contentPane.add(lblNewLabel_cartel);
        
        //creamos la barra para movernos arriba y abajo, el scroll determina sus atributos tanto horizontales como verticales, y conectamos a la bbdd
        
        JScrollPane scrollPane = new JScrollPane(resultadoTextArea);

        scrollPane.setBounds(231, 160, 580, 356);

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        contentPane.add(scrollPane);

        conexion = new ConexionMySQL("root", "", "biblioteca");

        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarPelicula(lblNewLabel_cartel);
            }
        });

        //creamos el desplegable de peliculas
        
        listaPeliculas = new JComboBox<>();
        listaPeliculas.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String imprimirtexto = (String) listaPeliculas.getSelectedItem();
        		if (listaPeliculas.getSelectedIndex()!=0) {
        			textField_Busqueda.setText(imprimirtexto);
                } 
        	}
        });
        listaPeliculas.setBounds(618, 28, 156, 22);
        contentPane.add(listaPeliculas);
        
        //creamos el boton que nos lleva a la descarga del programa y le insertamos el enlace
        
        JButton btnNewButton_botonYT = new JButton("");
        btnNewButton_botonYT.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
					Desktop.getDesktop().browse(new URI("https://www.youtube.com/watch?v=Xa5mFoe_5xk"));
				} catch (IOException e1) {
			
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					
					e1.printStackTrace();
				}
        	}
        });
        
        //le damos la imagen personalizada al boton
        
        btnNewButton_botonYT.setIcon(new ImageIcon(Buscar_Pelicula.class.getResource("/Imagenes/Captura de pantalla 2025-05-19 142716.png")));
        btnNewButton_botonYT.setBounds(10, 432, 214, 84);
        contentPane.add(btnNewButton_botonYT);
        
        
        //creamos el boton de catalogo completo
        
        JButton btnNewButton_Catalogo = new JButton("Catálogo completo");
        btnNewButton_Catalogo.addActionListener(e -> {
            Catalogo_Completo catalogo = new Catalogo_Completo();
            catalogo.setVisible(true);
        });
        btnNewButton_Catalogo.setBounds(618, 66, 156, 23);
        contentPane.add(btnNewButton_Catalogo);
        
        //añadimos el fondo a la ventana
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(Buscar_Pelicula.class.getResource("/imagenes/fondo3.png")));
        lblNewLabel.setBounds(0, 0, 834, 561);
        contentPane.add(lblNewLabel);
        
       
        //hacemos visible el ComboBox
        
        cargarPeliculasEnComboBox();
        setVisible(true);
    }
    
    
    //Le damos las propiedades al comboBox (Deslizable) para que coja todas las peliculas de la BBDD hasta que no queden mas
    
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

    
    	//Le damos propiedades a la barra de busqueda con sus sentencia SQL, y conectamos a la bbdd
    
    private void buscarPelicula(JLabel lblNewLabel_cartel) {
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
                	String cogerCartel = rs.getString("titulo");
                	ImageIcon icono2=new ImageIcon(Buscar_Pelicula.class.getResource("/imagenes/" + cogerCartel + ".jpg"));
                	Image imagen2 = icono2.getImage().getScaledInstance(lblNewLabel_cartel.getWidth(),lblNewLabel_cartel.getHeight(), Image.SCALE_SMOOTH);
                	ImageIcon iconoAjustado2 = new ImageIcon(imagen2);
                	lblNewLabel_cartel.setIcon(iconoAjustado2);
                	
                	//Hacemos mas visual el cuadro de busqueda con emoticonos, utilizamos append, que sirve para concatenar igual que el +, pero es mas eficiente, 
                	//se nota mucho mas en cadenas grandes o bucles
                	
                    resultado.append("🎬 Película ");
                    resultado.append("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
                    resultado.append("📌 TÍTULO: ").append(rs.getString("titulo")).append("\n");
                    resultado.append("🎥 DIRECTOR: ").append(rs.getString("director")).append("\n");
                    resultado.append("📅 AÑO: ").append(rs.getInt("año")).append("\n");
                    resultado.append("🎭 GÉNERO: ").append(rs.getString("genero")).append("\n");
                    resultado.append("📝 SINOPSIS:\n").append(rs.getString("sinopsis"));
                    resultado.append("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n\n");
                } while (rs.next());
                resultadoTextArea.setText(resultado.toString());
            }

            conexion.desconectar();
        } catch (SQLException ex) {
            resultadoTextArea.setText("Error al buscar película: " + ex.getMessage());
        }
    }
    
 // En Buscar_Pelicula.java
    public void setTituloBusqueda(String titulo) {
        textField_Busqueda.setText(titulo);
        JLabel lblNewLabel_cartel = null;
		buscarPelicula(lblNewLabel_cartel);
    }

    public static void main(String[] args) {
        new Buscar_Pelicula();
    }
}