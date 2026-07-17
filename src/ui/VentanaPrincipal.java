package ui;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.TipoTest;
import util.Constantes;

public class VentanaPrincipal extends JFrame {

    public static final String MENU = "MENU";
    public static final String CONFIGURACION = "CONFIGURACION";
    public static final String EXAMEN = "EXAMEN";
    public static final String RESULTADOS = "RESULTADOS";

    private CardLayout cardLayout;
    private JPanel contenedor;

    private MenuPrincipal menuPrincipal;
    private ConfiguracionTest configuracionTest;

    // Más adelante
    // private PantallaExamen pantallaExamen;
    // private PantallaResultado pantallaResultado;

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

        mostrarMenu();

        setVisible(true);

    }

    private void inicializarComponentes() {

        cardLayout = new CardLayout();
        contenedor = new JPanel(cardLayout);

        menuPrincipal = new MenuPrincipal(this);
        configuracionTest = new ConfiguracionTest(this);

    }

    private void registrarPantallas() {

        contenedor.add(menuPrincipal, MENU);
        contenedor.add(configuracionTest, CONFIGURACION);

    }

    public void mostrarMenu() {

        cardLayout.show(contenedor, MENU);

    }

    public void mostrarConfiguracion(TipoTest tipo) {

        configuracionTest.setTipoTest(tipo);

        cardLayout.show(contenedor, CONFIGURACION);

    }

    public void mostrarExamen() {

        cardLayout.show(contenedor, EXAMEN);

    }

    public void mostrarResultados() {

        cardLayout.show(contenedor, RESULTADOS);

    }

}