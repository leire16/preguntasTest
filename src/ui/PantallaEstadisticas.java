package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import model.Configuracion;
import model.Estadisticas;
import model.RendimientoTema;
import service.EstadisticasService;
import ui.componentes.PanelBotonVolver;
import ui.componentesEstadisticas.PanelRendimientoTema;
import ui.componentesEstadisticas.TarjetaEstadistica;
import ui.componentesResultados.PanelCirculoProgreso;

public class PantallaEstadisticas extends JPanel {

    private final EstadisticasService estadisticasService;

    private TarjetaEstadistica tarjetaTests;
    private TarjetaEstadistica tarjetaPreguntas;
    private TarjetaEstadistica tarjetaDistintas;

    private PanelCirculoProgreso circuloPorcentaje;

    private JPanel panelTemas;
    private JScrollPane scrollTemas;
    private JPanel panelListaTemas;

    public PantallaEstadisticas(VentanaPrincipal ventana) {

        estadisticasService =
                new EstadisticasService();

        setLayout(
                new BorderLayout(
                        20,
                        20));

        setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        30,
                        20,
                        30));

        crearCentro();
        crearBotonVolver(ventana);
    }

    private void crearCentro() {

        JPanel centro =
                new JPanel();

        centro.setLayout(
                new BoxLayout(
                        centro,
                        BoxLayout.Y_AXIS));

        centro.setOpaque(false);

        JLabel titulo =
                new JLabel(
                        "ESTADÍSTICAS",
                        SwingConstants.CENTER);

        titulo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28));

        titulo.setForeground(
                new Color(
                        60,
                        60,
                        60));

        titulo.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        centro.add(titulo);

        centro.add(
                Box.createVerticalStrut(
                        25));

        JPanel panelResumen =
                new JPanel(
                        new GridLayout(
                                1,
                                4,
                                15,
                                0));

        panelResumen.setMaximumSize(
                new Dimension(
                        850,
                        150));

        tarjetaTests =
                new TarjetaEstadistica(
                        "Tests realizados");

        tarjetaPreguntas =
                new TarjetaEstadistica(
                        "Preguntas respondidas");

        tarjetaDistintas =
                new TarjetaEstadistica(
                        "Preguntas distintas");

        panelResumen.add(
                tarjetaTests);

        panelResumen.add(
                tarjetaPreguntas);

        panelResumen.add(
                tarjetaDistintas);

        panelResumen.add(
                crearTarjetaPorcentaje());

        centro.add(panelResumen);

        centro.add(
                Box.createVerticalStrut(
                        30));

        crearPanelTemas();

        scrollTemas =
                new JScrollPane(
                        panelTemas);

        scrollTemas.setPreferredSize(
                new Dimension(
                        700,
                        250));

        centro.add(
                scrollTemas);

        add(
                centro,
                BorderLayout.CENTER);
    }

    private JPanel crearTarjetaPorcentaje() {

        JPanel panel =
                new JPanel(
                        new BorderLayout());

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                Color.LIGHT_GRAY),
                        BorderFactory.createEmptyBorder(
                                8,
                                10,
                                8,
                                10)));

        JLabel titulo =
                new JLabel(
                        "Porcentaje aciertos",
                        SwingConstants.CENTER);

        titulo.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12));

        titulo.setForeground(
                new Color(
                        100,
                        100,
                        100));

        circuloPorcentaje =
                new PanelCirculoProgreso();

        panel.add(
                titulo,
                BorderLayout.NORTH);

        panel.add(
                circuloPorcentaje,
                BorderLayout.CENTER);

        return panel;
    }

    private void crearPanelTemas(){

        panelTemas =
                new JPanel(
                        new BorderLayout());

        panelTemas.setBorder(
                BorderFactory.createTitledBorder(
                        "Rendimiento por temas"));

        panelListaTemas =
                new JPanel();

        panelListaTemas.setLayout(
                new BoxLayout(
                        panelListaTemas,
                        BoxLayout.Y_AXIS));

        panelTemas.add(
                panelListaTemas,
                BorderLayout.CENTER);
    }

    private void crearBotonVolver(
            VentanaPrincipal ventana) {

        PanelBotonVolver panelVolver =
                new PanelBotonVolver();

        panelVolver.getBtnVolver()
                .addActionListener(e ->
                        ventana.mostrarMenu());

        add(
                panelVolver,
                BorderLayout.SOUTH);
    }

    public void cargarEstadisticas(
            Configuracion configuracion) {

        Estadisticas estadisticas =
                estadisticasService
                        .obtenerEstadisticas(
                                configuracion);

        tarjetaTests.setValor(
                estadisticas.getTestsRealizados());

        tarjetaPreguntas.setValor(
                estadisticas.getPreguntasRespondidas());

        tarjetaDistintas.setValor(
                estadisticas.getPreguntasDistintas());

        circuloPorcentaje.setPorcentaje(
                estadisticas.getPorcentajeAciertos());

        boolean esComun =
                configuracion.getTemas().size() == 1
                &&
                configuracion.getTemas().get(0) == 21;

        panelTemas.setVisible(
                !esComun);

        scrollTemas.setVisible(
                !esComun);

        if(!esComun){
            cargarRendimientoTemas(
                estadisticas.getRendimientoTemas());

        }
    }

    private void cargarRendimientoTemas(
        List<RendimientoTema> rendimiento){

        panelListaTemas.removeAll();

        for(RendimientoTema tema : rendimiento){

            PanelRendimientoTema panel =
                new PanelRendimientoTema(
                        tema.getTemaId(),
                        tema.getNombre());

            panel.setPorcentaje(
                    tema.getPorcentaje());

            panelListaTemas.add(
                    panel);

        }

        panelListaTemas.revalidate();

        panelListaTemas.repaint();

        // Volver siempre al inicio del scroll
        SwingUtilities.invokeLater(() -> {
                scrollTemas.getVerticalScrollBar().setValue(0);
        });

    }
}