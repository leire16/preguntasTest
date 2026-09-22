package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

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
import model.PreguntaDificil;
import model.RendimientoTema;
import service.EstadisticasService;
import ui.componentes.PanelBotonVolver;
import ui.componentes.PanelPlegable;
import ui.componentesEstadisticas.PanelPreguntaDificil;
import ui.componentesEstadisticas.PanelRendimientoTema;
import ui.componentesEstadisticas.TarjetaEstadistica;
import ui.componentesResultados.PanelCirculoProgreso;
import util.Constantes;

public class PantallaEstadisticas extends JPanel {

    private final EstadisticasService estadisticasService;

    private TarjetaEstadistica tarjetaTests;
    private TarjetaEstadistica tarjetaPreguntas;
    private TarjetaEstadistica tarjetaDistintas;

    private PanelCirculoProgreso circuloPorcentaje;

    private PanelPlegable panelTemas;
    private JPanel panelListaTemas;

    private PanelPlegable panelPreguntas;
    private JPanel panelListaPreguntas;
    private JScrollPane scrollPreguntas;

    private JPanel panelCentral;

    private GridBagLayout layoutCentral;

    private GridBagConstraints gbcTemas;
    private GridBagConstraints gbcPreguntas;

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
                new JPanel(
                        new BorderLayout(
                                0,
                                20));

        centro.setOpaque(false);

        // =====================================================
        // PANEL SUPERIOR
        // =====================================================

        JPanel panelSuperior =
                new JPanel();

        panelSuperior.setOpaque(false);

        panelSuperior.setLayout(
                new BoxLayout(
                        panelSuperior,
                        BoxLayout.Y_AXIS));

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

        panelSuperior.add(titulo);

        panelSuperior.add(
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
                        Integer.MAX_VALUE,
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

        panelSuperior.add(
                panelResumen);

        centro.add(
                panelSuperior,
                BorderLayout.NORTH);

        // =====================================================
        // PANEL CENTRAL
        // =====================================================

        crearPanelTemas();
        crearPanelPreguntas();

        panelTemas.setListenerCambioEstado(
                this::actualizarDistribucionPaneles);

        panelPreguntas.setListenerCambioEstado(
                this::actualizarDistribucionPaneles);

        layoutCentral =
                new GridBagLayout();

        panelCentral =
                new JPanel(
                        layoutCentral);

        panelCentral.setOpaque(false);

        gbcTemas =
                new GridBagConstraints();

        gbcTemas.gridx = 0;
        gbcTemas.gridy = 0;
        gbcTemas.weightx = 1;
        gbcTemas.weighty = 0.5;
        gbcTemas.fill = GridBagConstraints.BOTH;
        gbcTemas.insets =
                new Insets(
                        0,
                        0,
                        10,
                        0);

        gbcPreguntas =
                new GridBagConstraints();

        gbcPreguntas.gridx = 0;
        gbcPreguntas.gridy = 1;
        gbcPreguntas.weightx = 1;
        gbcPreguntas.weighty = 0.5;
        gbcPreguntas.fill = GridBagConstraints.BOTH;

        panelCentral.add(
                panelTemas,
                gbcTemas);

        panelCentral.add(
                panelPreguntas,
                gbcPreguntas);

        centro.add(
                panelCentral,
                BorderLayout.CENTER);

        actualizarDistribucionPaneles();

        add(
                centro,
                BorderLayout.CENTER);

    }

    private void actualizarDistribucionPaneles() {

        boolean temas =
                panelTemas.estaExpandido();

        boolean preguntas =
                panelPreguntas.estaExpandido();

        if (temas && preguntas) {

                gbcTemas.weighty = 0.5;
                gbcPreguntas.weighty = 0.5;

        } else if (temas) {

                gbcTemas.weighty = 1;
                gbcPreguntas.weighty = 0;

        } else if (preguntas) {

                gbcTemas.weighty = 0;
                gbcPreguntas.weighty = 1;

        } else {

                gbcTemas.weighty = 0;
                gbcPreguntas.weighty = 0;

        }

        layoutCentral.setConstraints(
                panelTemas,
                gbcTemas);

        layoutCentral.setConstraints(
                panelPreguntas,
                gbcPreguntas);

        panelCentral.revalidate();
        panelCentral.repaint();

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

    private void crearPanelTemas() {

        panelTemas = new PanelPlegable(
                "Rendimiento por temas");

        panelListaTemas = new JPanel();

        panelListaTemas.setLayout(
                new BoxLayout(
                        panelListaTemas,
                        BoxLayout.Y_AXIS));

        panelTemas.getContenido().add(
                panelListaTemas);

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

        if(!esComun){
            cargarRendimientoTemas(
                estadisticas.getRendimientoTemas());
             cargarPreguntasMasFalladas(
                estadisticas.getPreguntasMasFalladas());

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

            
                panel.actualizar(
                        tema.getRespondidas(),
                        tema.getAcertadas(),
                        tema.getFalladas(),
                        tema.getPorcentaje());

            panelListaTemas.add(
                    panel);

        }

        panelListaTemas.revalidate();

        panelListaTemas.repaint();

    }

    private void crearPanelPreguntas() {

        panelPreguntas =
                new PanelPlegable(
                        "Preguntas más falladas");
        
        panelPreguntas.setSubtitulo(
                "Mostrando las " + Constantes.LIMITE_PREGUNTAS_MAS_FALLADAS
                + " preguntas con peor porcentaje de aciertos");

        panelListaPreguntas =
                new JPanel();

        panelListaPreguntas.setLayout(
                new BoxLayout(
                        panelListaPreguntas,
                        BoxLayout.Y_AXIS));

        scrollPreguntas =
                new JScrollPane(
                        panelListaPreguntas);

        scrollPreguntas.setBorder(null);

        panelPreguntas.getContenido().add(
                scrollPreguntas);

    }

    private void cargarPreguntasMasFalladas(
        List<PreguntaDificil> preguntas) {

        panelListaPreguntas.removeAll();

        // ==========================
        // Cabecera
        // ==========================

        panelListaPreguntas.add(
                new PanelPreguntaDificil());

        // ==========================
        // Filas
        // ==========================

        for (PreguntaDificil pregunta : preguntas) {

                PanelPreguntaDificil panel =
                        new PanelPreguntaDificil(
                                pregunta.getNumeroPregunta(),
                                pregunta.getTemaId());

                panel.actualizar(
                        pregunta.getVecesPreguntada(),
                        pregunta.getVecesFallada(),
                        pregunta.getPorcentajeAciertos());

                panelListaPreguntas.add(
                        panel);

        }

        panelListaPreguntas.revalidate();
        panelListaPreguntas.repaint();

        SwingUtilities.invokeLater(() ->
                scrollPreguntas.getVerticalScrollBar().setValue(0));

    }

}