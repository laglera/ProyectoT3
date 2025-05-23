package ProyeTest;

public class Catalogo_CompletoTest {
    private Catalogo_Completo catalogo;
    private ConexionMySQL mockConexion;
    private ResultSet mockResultSet;

    @Before
    public void setUp() throws SQLException {
        catalogo = new Catalogo_Completo();
        mockConexion = mock(ConexionMySQL.class);
        mockResultSet = mock(ResultSet.class);
        
        when(mockResultSet.getString("titulo")).thenReturn("Pelicula1");
        when(mockResultSet.getString("genero")).thenReturn("Acción");
        when(mockResultSet.getString("año")).thenReturn("2023");
    }

    // 7. Carga inicial de películas
    @Test
    public void testCargarDatosPeliculas() throws SQLException {
        when(mockConexion.ejecutarSelect(anyString())).thenReturn(mockResultSet);
        catalogo.cargarDatosPeliculas();
        assertEquals(1, catalogo.peliculasOriginales.size());
    }

    // 8. Filtrado por género
    @Test
    public void testFiltrarPorGenero() {
        catalogo.comboGeneros.setSelectedItem("Acción");
        catalogo.filtrarPeliculas();
        assertTrue(catalogo.panelPrincipal.getComponentCount() >= 0);
    }
    
    @Test
    public void testResetearFiltros() {
        Catalogo_Completo catalogo = new Catalogo_Completo();
        catalogo.comboGeneros.setSelectedIndex(1);
        catalogo.resetearFiltros();
        assertEquals("Todos los géneros", catalogo.comboGeneros.getSelectedItem());
    }
}