package Proyecto;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
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
    private JComboBox<String> comboGeneros;
    private JComboBox<String> comboAños;
    private List<String> peliculasOriginales = new ArrayList<>();

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
        
        // Panel superior con título y filtros
        JPanel panelSuperior = new JPanel(new BorderLayout());
        
        JLabel lblTitulo = new JLabel("Catálogo Completo");
        lblTitulo.setFont(new Font("Arial Black", Font.PLAIN, 22));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panelSuperior.add(lblTitulo, BorderLayout.NORTH);
        
        // Panel de filtros
        JPanel panelFiltros = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        panelFiltros.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        JLabel lblGenero = new JLabel("Filtrar por género:");
        comboGeneros = new JComboBox<>();
        comboGeneros.addItem("Todos los géneros");
        
        JLabel lblAño = new JLabel("Filtrar por año:");
        comboAños = new JComboBox<>();
        comboAños.addItem("Todos los años");
        
        JButton btnFiltrar = new JButton("Filtrar");
        btnFiltrar.addActionListener(e -> filtrarPeliculas());
        
        JButton btnReset = new JButton("Resetear");
        btnReset.addActionListener(e -> resetearFiltros());
        
        panelFiltros.add(lblGenero);
        panelFiltros.add(comboGeneros);
        panelFiltros.add(lblAño);
        panelFiltros.add(comboAños);
        panelFiltros.add(btnFiltrar);
        panelFiltros.add(btnReset);
        
        
        
        panelSuperior.add(panelFiltros, BorderLayout.SOUTH);
        contentPane.add(panelSuperior, BorderLayout.NORTH);
        
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridLayout(0, 3, 15, 15));
        
        JScrollPane scrollPane = new JScrollPane(panelPrincipal);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        conexion = new ConexionMySQL("root", "", "biblioteca");
        cargarDatosPeliculas();
    }

    private void cargarDatosPeliculas() {
        String sql = "SELECT titulo, genero, año FROM peliculas ORDER BY titulo";
        
        try {
            conexion.conectar();
            ResultSet rs = conexion.ejecutarSelect(sql);
            
            Set<String> generos = new HashSet<>();
            Set<String> años = new HashSet<>();
            
            while (rs.next()) {
                String titulo = rs.getString("titulo");
                String genero = rs.getString("genero");
                String año = rs.getString("año");
                
                peliculasOriginales.add(titulo);
                generos.add(genero);
                años.add(año);
                
                JPanel panelCartel = crearPanelCartel(titulo);
                panelPrincipal.add(panelCartel);
                carteles.add(panelCartel);
            }
            
            // Llenar combobox de géneros
            for (String genero : generos) {
                if (genero != null && !genero.trim().isEmpty()) {
                    comboGeneros.addItem(genero);
                }
            }
            
            // Llenar combobox de años (ordenados)
            List<String> listaAños = new ArrayList<>(años);
            listaAños.sort((a1, a2) -> {
                try {
                    return Integer.compare(Integer.parseInt(a1), Integer.parseInt(a2));
                } catch (NumberFormatException e) {
                    return a1.compareTo(a2);
                }
            });
            for (String año : listaAños) {
                if (año != null && !año.trim().isEmpty()) {
                    comboAños.addItem(año);
                }
            }
            
            conexion.desconectar();
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar películas: " + e.getMessage());
        }
    }

    private void filtrarPeliculas() {
        String generoSeleccionado = (String) comboGeneros.getSelectedItem();
        String añoSeleccionado = (String) comboAños.getSelectedItem();
        
        // Limpiar el panel principal
        panelPrincipal.removeAll();
        carteles.clear();
        
        try {
            conexion.conectar();
            
            StringBuilder sql = new StringBuilder("SELECT titulo FROM peliculas WHERE 1=1");
            
            if (!generoSeleccionado.equals("Todos los géneros")) {
                sql.append(" AND genero = '").append(generoSeleccionado).append("'");
            }
            
            if (!añoSeleccionado.equals("Todos los años")) {
                sql.append(" AND año = '").append(añoSeleccionado).append("'");
            }
            
            sql.append(" ORDER BY titulo");
            
            ResultSet rs = conexion.ejecutarSelect(sql.toString());
            
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
            JOptionPane.showMessageDialog(this, "Error al filtrar películas: " + e.getMessage());
        }
    }

    private void resetearFiltros() {
        comboGeneros.setSelectedIndex(0);
        comboAños.setSelectedIndex(0);
        filtrarPeliculas();
    }

    private JPanel crearPanelCartel(String titulo) {
        JPanel panelCartel = new JPanel();
        panelCartel.setLayout(new BorderLayout());
        panelCartel.setPreferredSize(new Dimension(250, 350));
        panelCartel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        JLabel lblImagen = new JLabel();
        lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
        lblImagen.setPreferredSize(new Dimension(240, 300));
        
        try {
            URL imgUrl = getClass().getResource("/imagenes/" + titulo + ".jpg");
            if (imgUrl != null) {
                ImageIcon icono = new ImageIcon(imgUrl);
                Image imagen = icono.getImage().getScaledInstance(240, 300, Image.SCALE_SMOOTH);
                lblImagen.setIcon(new ImageIcon(imagen));
            } else {
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
        
        JLabel lblTitulo = new JLabel(titulo, SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        
        panelCartel.add(lblImagen, BorderLayout.CENTER);
        panelCartel.add(lblTitulo, BorderLayout.SOUTH);
        
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