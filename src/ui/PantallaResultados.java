package ui;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import service.ResultadoPregunta;
import service.ResultadoTest;
import ui.componentesResultados.PanelBotonMenu;
import ui.componentesResultados.PanelResultadoPregunta;
import ui.componentesResultados.PanelResumenResultado;

public class PantallaResultados extends JPanel {

    private VentanaPrincipal ventana;

    private PanelResumenResultado panelResumen;

    private JPanel panelListadoPreguntas;

    private JScrollPane scroll;

    private PanelBotonMenu panelBotonMenu;

    public PantallaResultados(VentanaPrincipal ventana) {

        this.ventana = ventana;

        setLayout(new BorderLayout(15, 15));

        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        inicializarComponentes();

        colocarComponentes();

        configurarEventos();

    }

    private void inicializarComponentes() {

        panelResumen = new PanelResumenResultado();

        panelListadoPreguntas = new JPanel();

        panelListadoPreguntas.setLayout(new javax.swing.BoxLayout(
                panelListadoPreguntas,
                javax.swing.BoxLayout.Y_AXIS));

        scroll = new JScrollPane(panelListadoPreguntas);

        scroll.getVerticalScrollBar().setUnitIncrement(16);

        panelBotonMenu = new PanelBotonMenu();

    }

    private void colocarComponentes() {

        add(panelResumen, BorderLayout.NORTH);

        add(scroll, BorderLayout.CENTER);

        add(panelBotonMenu, BorderLayout.SOUTH);

    }

    private void configurarEventos() {

        panelBotonMenu.getBtnVolverMenu().addActionListener(e -> {

            ventana.mostrarMenu();

        });

    }

    // ===================================================
    // MÉTODOS PÚBLICOS
    // ===================================================

    public void limpiarListado() {

        panelListadoPreguntas.removeAll();

        panelListadoPreguntas.revalidate();

        panelListadoPreguntas.repaint();

    }

    public JPanel getPanelListadoPreguntas() {

        return panelListadoPreguntas;

    }

    public PanelResumenResultado getPanelResumen() {

        return panelResumen;

    }

    public void mostrarResultado(ResultadoTest resultado) {

        limpiarListado();

        panelResumen.mostrarResultado(resultado);

        for (ResultadoPregunta resultadoPregunta : resultado.getPreguntas()) {

            PanelResultadoPregunta panelResultadoPregunta = new PanelResultadoPregunta();

            panelResultadoPregunta.mostrarResultadoPregunta(resultadoPregunta);

            panelListadoPreguntas.add(panelResultadoPregunta);

        }

        revalidate();
        repaint();

    }

}