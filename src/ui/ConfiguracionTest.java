package ui;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.Configuracion;
import model.TipoTest;
import ui.componentes.PanelBotones;
import ui.componentes.PanelNumeroPreguntas;
import ui.componentes.PanelTemas;

public class ConfiguracionTest extends JPanel {

    private VentanaPrincipal ventana;

    private JLabel lblTitulo;
    private JLabel lblModo;

    private PanelTemas panelTemas;
    private PanelNumeroPreguntas panelNumeroPreguntas;
    private PanelBotones panelBotones;

    private TipoTest tipoTest;

    public ConfiguracionTest(VentanaPrincipal ventana) {

        this.ventana = ventana;

        setLayout(new BorderLayout(15, 15));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        crearCentro();
        crearBotones();

    }

    private void crearCentro() {

        JPanel centro = new JPanel();
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));

        lblTitulo = new JLabel("CONFIGURACIÓN DEL TEST", SwingConstants.CENTER);
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(26f));

        lblModo = new JLabel();
        lblModo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblModo.setFont(lblModo.getFont().deriveFont(18f));

        panelTemas = new PanelTemas();
        panelNumeroPreguntas = new PanelNumeroPreguntas();

        centro.add(lblTitulo);
        centro.add(Box.createVerticalStrut(15));

        centro.add(lblModo);
        centro.add(Box.createVerticalStrut(25));

        centro.add(panelTemas);
        centro.add(Box.createVerticalStrut(20));

        centro.add(panelNumeroPreguntas);

        add(centro, BorderLayout.CENTER);

    }

    private void crearBotones() {

        panelBotones = new PanelBotones();

        panelBotones.getBtnVolver().addActionListener(e ->
                ventana.mostrarMenu());

        panelBotones.getBtnComenzar().addActionListener(e -> {

            Configuracion configuracion = obtenerConfiguracion();

            System.out.println(configuracion);

            // Próximamente:
            // ventana.mostrarExamen(configuracion);

        });

        add(panelBotones, BorderLayout.SOUTH);

    }

    private Configuracion obtenerConfiguracion() {

        Configuracion configuracion = new Configuracion();

        configuracion.setTipoTest(tipoTest);

        if (tipoTest == TipoTest.TEMAS) {

            if (panelTemas.hayTemasSeleccionados()) {

                configuracion.setTemas(panelTemas.getTemasSeleccionados());

            } else {

                configuracion.setTemas(panelTemas.getTodosLosTemas());

            }

        }

        configuracion.setNumeroPreguntas(panelNumeroPreguntas.getNumeroPreguntas());

        return configuracion;

    }

    public void setTipoTest(TipoTest tipo) {

        this.tipoTest = tipo;

        switch (tipo) {

            case TEMAS:

                lblModo.setText("Modo: Test por temas");
                panelTemas.setVisible(true);
                break;

            case COMPLETO:

                lblModo.setText("Modo: Test completo");
                panelTemas.setVisible(false);
                break;

            case FALLADAS:

                lblModo.setText("Modo: Preguntas falladas");
                panelTemas.setVisible(false);
                break;

        }

        revalidate();
        repaint();

    }

}