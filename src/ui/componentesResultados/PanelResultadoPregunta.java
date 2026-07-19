package ui.componentesResultados;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Respuesta;
import service.ResultadoPregunta;

public class PanelResultadoPregunta extends JPanel {

    private JLabel lblCabecera;
    private JLabel lblEnunciado;

    private JPanel panelRespuestas;

    public PanelResultadoPregunta() {

        setLayout(new BorderLayout(10, 10));

        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 210, 210)),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)));

        inicializarComponentes();

        colocarComponentes();

    }

    private void inicializarComponentes() {

        lblCabecera = new JLabel();

        lblCabecera.setFont(
                new Font("Arial", Font.BOLD, 16)
        );

        lblEnunciado = new JLabel();

        lblEnunciado.setFont(
                new Font("Arial", Font.PLAIN, 15)
        );

        panelRespuestas = new JPanel();

        panelRespuestas.setLayout(
                new BoxLayout(panelRespuestas, BoxLayout.Y_AXIS)
        );

    }

    private void colocarComponentes() {

        add(lblCabecera, BorderLayout.NORTH);

        JPanel centro = new JPanel();

        centro.setLayout(
                new BoxLayout(centro, BoxLayout.Y_AXIS)
        );

        centro.add(lblEnunciado);

        centro.add(Box.createVerticalStrut(12));

        centro.add(panelRespuestas);

        add(centro, BorderLayout.CENTER);

    }

    // =====================================================
    // CARGAR RESULTADO DE UNA PREGUNTA
    // =====================================================

    public void mostrarResultadoPregunta(ResultadoPregunta resultado) {

        limpiarRespuestas();

        boolean esCorrecta = resultado.isCorrecta();

        // -------------------------------
        // Cabecera
        // -------------------------------

        lblCabecera.setText(
                "Pregunta "
                + resultado.getPregunta().getNumeroOriginal()
                + (esCorrecta
                    ? "  - CORRECTA"
                    : "  - INCORRECTA")
        );

        if (esCorrecta) {

            lblCabecera.setForeground(
                    new Color(0, 150, 0)
            );

        } else {

            lblCabecera.setForeground(
                    Color.RED
            );

        }

        // -------------------------------
        // Enunciado
        // -------------------------------

        lblEnunciado.setText(
                "<html><b>"
                + resultado.getPregunta().getEnunciado()
                + "</b></html>"
        );

        // -------------------------------
        // Respuestas
        // -------------------------------

        for (Respuesta respuesta : resultado.getPregunta().getRespuestas()) {

            JLabel lblRespuesta = new JLabel(
                    respuesta.getLetra()
                    + ") "
                    + respuesta.getTexto()
            );

            lblRespuesta.setAlignmentX(
                    Component.LEFT_ALIGNMENT
            );

            // Respuesta correcta
            if (respuesta.equals(resultado.getRespuestaCorrecta())) {

                lblRespuesta.setText(
                        "✔ "
                        + lblRespuesta.getText()
                );

                lblRespuesta.setForeground(
                        new Color(0, 150, 0)
                );

                lblRespuesta.setFont(
                        lblRespuesta.getFont()
                                .deriveFont(Font.BOLD)
                );

            }

            // Respuesta elegida por usuario pero incorrecta
            if (!esCorrecta
                    && respuesta.equals(resultado.getRespuestaUsuario())) {

                lblRespuesta.setText(
                        "✘ "
                        + lblRespuesta.getText()
                );

                lblRespuesta.setForeground(
                        Color.RED
                );


                lblRespuesta.setFont(
                        lblRespuesta.getFont()
                                .deriveFont(Font.BOLD)
                );

            }

            addRespuesta(lblRespuesta);

        }

        revalidate();

        repaint();

    }

    private void limpiarRespuestas() {

        panelRespuestas.removeAll();

    }

    private void addRespuesta(JLabel respuesta) {

        respuesta.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        panelRespuestas.add(respuesta);

        panelRespuestas.add(
                Box.createVerticalStrut(5)
        );
    }
}