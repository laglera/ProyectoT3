package Proyecto;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Catalogo_Completo extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel panelPrincipal;
    private ConexionMySQL conexion;
    private List<JPanel> carteles = new ArrayList<>();

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Catalogo_Completo frame = new Catalogo_Completo();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Catalogo_Completo() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Catálogo Completo");
        setBounds(600, 300, 850, 600);
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        
        JLabel lblTitulo = new JLabel("Catálogo Completo");
        lblTitulo.setFont(new Font("Arial Black", Font.PLAIN, 22));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblTitulo, BorderLayout.NORTH);
        
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridLayout(0, 3, 15, 15)); // 3 columnas, espacio horizontal y vertical de 15
        
        JScrollPane scrollPane = new JScrollPane(panelPrincipal);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        conexion = new ConexionMySQL("root", "", "biblioteca");
        cargarCartelesPeliculas();
    }

    private void cargarCartelesPeliculas() {
        String sql = "SELECT titulo FROM peliculas";
        
        try {
            conexion.conectar();
            ResultSet rs = conexion.ejecutarSelect(sql);
            
            while (rs.next()) {
                String titulo = rs.getString("titulo");
                JPanel panelCartel = crearPanelCartel(titulo);
                panelPrincipal.add(panelCartel);
                carteles.add(panelCartel);
            }
            
            conexion.desconectar();
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar películas: " + e.getMessage());
        }
    }

    private JPanel crearPanelCartel(String titulo) {
        JPanel panelCartel = new JPanel();
        panelCartel.setLayout(new BorderLayout());
        panelCartel.setPreferredSize(new Dimension(250, 350));
        panelCartel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        // Label para la imagen
        JLabel lblImagen = new JLabel();
        lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
        lblImagen.setPreferredSize(new Dimension(240, 300));
        
        // Intentar cargar la imagen
        try {
            URL imgUrl = getClass().getResource("/imagenes/" + titulo + ".jpg");
            if (imgUrl != null) {
                ImageIcon icono = new ImageIcon(imgUrl);
                Image imagen = icono.getImage().getScaledInstance(240, 300, Image.SCALE_SMOOTH);
                lblImagen.setIcon(new ImageIcon(imagen));
            } else {
                // Imagen por defecto si no se encuentra
                ImageIcon icono = new ImageIcon(getClass().getResource("/imagenes/default.jpg"));
                if (icono.getImage() != null) {
                    Image imagen = icono.getImage().getScaledInstance(240, 300, Image.SCALE_SMOOTH);
                    lblImagen.setIcon(new ImageIcon(imagen));
                } else {
                    lblImagen.setText("Imagen no disponible");
                    lblImagen.setFont(new Font("Arial", Font.PLAIN, 14));
                }
            }
        } catch (Exception e) {
            lblImagen.setText("Error cargando imagen");
            lblImagen.setFont(new Font("Arial", Font.PLAIN, 14));
        }
        
        // Label para el título
        JLabel lblTitulo = new JLabel(titulo, SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        
        panelCartel.add(lblImagen, BorderLayout.CENTER);
        panelCartel.add(lblTitulo, BorderLayout.SOUTH);
        
        // Eventos del ratón
        panelCartel.addMouseListener(new MouseAdapter() {
           
            public void mouseClicked(MouseEvent e) {
                abrirBusquedaPelicula(titulo);
            }
    
        });
        
        return panelCartel;
    }

    private void abrirBusquedaPelicula(String titulo) {
        Buscar_Pelicula buscarPelicula = new Buscar_Pelicula();
        buscarPelicula.setVisible(true);
        buscarPelicula.setTituloBusqueda(titulo);
        this.dispose();
    }
}