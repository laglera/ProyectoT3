package Proye;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Buscar_Pelicula extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Buscar_Pelicula frame = new Buscar_Pelicula();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Buscar_Pelicula() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(83, 114, 261, 54);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblbuscapeliculas = new JLabel("lblbuscapeliculas");
		lblbuscapeliculas.setBounds(0, 0, 434, 261);
		
		ImageIcon icono3= new ImageIcon(Buscar_Pelicula.class.getResource("/imagenes/Buscador.jpg"));
	    Image imagen2 = icono3.getImage().getScaledInstance( lblbuscapeliculas.getWidth(),lblbuscapeliculas.getHeight(), Image.SCALE_SMOOTH);
	    ImageIcon iconoAjustado2 = new ImageIcon(imagen2);
	    lblbuscapeliculas.setIcon(iconoAjustado2); 
		contentPane.add(lblbuscapeliculas);
	}
}
