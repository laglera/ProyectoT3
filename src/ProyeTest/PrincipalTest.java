package ProyeTest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.*;

import Proye.ConexionMySQL;
import Proye.Principal;

import javax.swing.*;
import java.sql.*;

public class PrincipalTest {
    private Principal principal;
    private ConexionMySQL mockConexion;
    private ResultSet mockResultSet;

    @Before
    public void setUp() throws SQLException {
        principal = new Principal();
        principal.txtNombre = new JTextField("testUser");
        principal.txtContrasena = new JPasswordField("testPass");
        
        mockConexion = mock(ConexionMySQL.class);
        mockResultSet = mock(ResultSet.class);
        when(mockResultSet.next()).thenReturn(true); // Para login exitoso
    }

    // 1. Validar credenciales correctas
    @Test
    public void testValidarCredencialesExitoso() throws SQLException {
        when(mockConexion.ejecutarSelect(anyString())).thenReturn(mockResultSet);
        principal.validarCredenciales();
        // Verificar que se abre Buscar_Pelicula (necesita verificación visual o flag)
        assertTrue(principal.isVisible()); // Ejemplo básico
    }

    // 2. Validar credenciales incorrectas
    @Test
    public void testValidarCredencialesFallido() throws SQLException {
        when(mockResultSet.next()).thenReturn(false);
        when(mockConexion.ejecutarSelect(anyString())).thenReturn(mockResultSet);
        principal.validarCredenciales();
        // Debería mostrar JOptionPane (necesita captura de diálogo)
    }
}
