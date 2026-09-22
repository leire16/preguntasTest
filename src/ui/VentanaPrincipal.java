package ui;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import dao.TemaDao;
import model.Configuracion;
import model.TipoTest;
import service.ResultadoTest;
import util.Constantes;

public class VentanaPrincipal extends JFrame {

    public static final String TEMA = "TEMA";
    public static final String MENU = "MENU";
    public static final String CONFIGURACION = "CONFIGURACION";
    public static final String EXAMEN = "EXAMEN";
    public static final String RESULTADOS = "RESULTADOS";
    public static final String ESTADISTICAS = "ESTADISTICAS";

    private CardLayout cardLayout;
    private JPanel contenedor;

    private SeleccionTipoTema temaPrincipal;
    private MenuPrincipal menuPrincipal;
    private ConfiguracionTest configuracionTest;

    // Más adelante
    private PantallaExamen pantallaExamen;
    private PantallaResultados pantallaResultados;
    private PantallaEstadisticas pantallaEstadisticas;
    
    private boolean temaComunSeleccionado;

    public VentanaPrincipal() {

        setTitle(Constantes.TITULO_APP);
        setSize(
            Constantes.ANCHO_VENTANA,
            Constantes.ALTO_VENTANA);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        inicializarComponentes();
        registrarPantallas();

        add(contenedor);

        mostrarTema(); // en vez de mostrarMenu();

        setVisible(true);
    }

    private void inicializarComponentes() {

        cardLayout = new CardLayout();
        contenedor = new JPanel(cardLayout);

        temaPrincipal = new SeleccionTipoTema(this);
        menuPrincipal = new MenuPrincipal(this);
        configuracionTest = new ConfiguracionTest(this);
        pantallaExamen = new PantallaExamen(this);
        pantallaResultados = new PantallaResultados(this);
        pantallaEstadisticas = new PantallaEstadisticas(this);

    }

    private void registrarPantallas() {
        contenedor.add(temaPrincipal, TEMA);
        contenedor.add(menuPrincipal, MENU);
        contenedor.add(configuracionTest, CONFIGURACION);
        contenedor.add(pantallaExamen, EXAMEN);
        contenedor.add(pantallaResultados, RESULTADOS);
        contenedor.add(pantallaEstadisticas, ESTADISTICAS);
    }

    public void mostrarTema() {

        cardLayout.show(contenedor, TEMA);

    }

    public void mostrarMenu() {

        cardLayout.show(contenedor, MENU);

    }

    public void mostrarConfiguracion(TipoTest tipo) {

        configuracionTest.setTipoTest(tipo);
        configuracionTest.setTemaComun(temaComunSeleccionado);

        cardLayout.show(contenedor, CONFIGURACION);

    }

    public void mostrarExamen(Configuracion configuracion) {

        pantallaExamen.iniciarExamen(configuracion);

        cardLayout.show(contenedor, "EXAMEN");

    }

    public void mostrarResultados(ResultadoTest resultado) {

        pantallaResultados.mostrarResultado(resultado);

        cardLayout.show(contenedor, "RESULTADOS");

    }

    public void mostrarEstadisticas() {

        Configuracion configuracion = new Configuracion();

        if (temaComunSeleccionado) {
            configuracion.setTemas(Constantes.idsTemaComun());

        } else {
            configuracion.setTemas(
                    new TemaDao().obtenerIdsTemasEspecificos());

        }

        pantallaEstadisticas.cargarEstadisticas(configuracion);

        cardLayout.show(contenedor, ESTADISTICAS);

    }

    public void confirmarSeleccionTipoTema(boolean esComun) {

        this.temaComunSeleccionado = esComun;

        mostrarMenu();

    }
}