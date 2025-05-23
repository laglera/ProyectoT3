package ProyeTest;

// Pruebas para Registrarse.java
public class RegistrarseTest {
    private Registrarse registrarse;
    private ConexionMySQL mockConexion;

    @Before
    public void setUp() {
        registrarse = new Registrarse();
        registrarse.txtNombre = new JTextField("nuevoUsuario");
        registrarse.txtCorreo = new JTextField("test@example.com");
        registrarse.txtTelefono = new JTextField("123456789");
        registrarse.txtContrasena = new JPasswordField("password123");
        
        mockConexion = mock(ConexionMySQL.class);
    }

    // 3. Registro exitoso
    @Test
    public void testRegistroExitoso() throws SQLException {
        when(mockConexion.ejecutarInsertDeleteUpdate(anyString())).thenReturn(1);
        registrarse.registrarUsuario();
        assertFalse(registrarse.isVisible()); // Verifica si se cierra la ventana
    }

    // 4. Registro con error en BD
    @Test
    public void testRegistroErrorBD() throws SQLException {
        when(mockConexion.ejecutarInsertDeleteUpdate(anyString())).thenThrow(SQLException.class);
        registrarse.registrarUsuario();
        // Debería mostrar JOptionPane de error
    }
}