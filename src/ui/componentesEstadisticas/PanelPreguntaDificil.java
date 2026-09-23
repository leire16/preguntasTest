package ui.componentesEstadisticas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelPreguntaDificil extends JPanel {

    private JLabel lblPregunta;
    private JLabel lblTema;
    private JLabel lblCorrectas;
    private JLabel lblFalladas;
    private JLabel lblPorcentaje;
    private JLabel lblNivel;

    public PanelPreguntaDificil() {

        setLayout(new GridLayout(1, 6));

        setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        35));

        setAlignmentX(Component.LEFT_ALIGNMENT);

        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(
                        0, 0, 1, 0,
                        Color.GRAY),
                BorderFactory.createEmptyBorder(
                        6, 8, 6, 8)));

        add(crearCabecera("Pregunta"));
        add(crearCabecera("Tema"));
        add(crearCabecera("Correctas"));
        add(crearCabecera("Falladas"));
        add(crearCabecera("%"));
        add(crearCabecera("Nivel"));
    }

    public PanelPreguntaDificil(
            int numeroPregunta,
            String etiquetaTema) {

        setLayout(new GridLayout(
                1,
                6));

        setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        35));

        setAlignmentX(
                Component.LEFT_ALIGNMENT);

        setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(
                                0,
                                0,
                                1,
                                0,
                                new Color(
                                        225,
                                        225,
                                        225)),
                        BorderFactory.createEmptyBorder(
                                6,
                                8,
                                6,
                                8)));

        lblPregunta = crearLabel(
                "Pregunta " + numeroPregunta);

        lblTema = crearLabel(etiquetaTema);

        if (etiquetaTema.startsWith("Tema C")) {
        lblTema.setForeground(new Color(41, 128, 255)); // azul
        }

        lblCorrectas = crearLabel("");

        lblCorrectas.setForeground(
                new Color(
                        46,
                        204,
                        113));

        lblFalladas = crearLabel("");

        lblFalladas.setForeground(
                new Color(
                        231,
                        76,
                        60));

        lblPorcentaje = crearLabel("");

        lblNivel = crearLabel("");

        lblNivel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13));

        add(lblPregunta);
        add(lblTema);
        add(lblCorrectas);
        add(lblFalladas);
        add(lblPorcentaje);
        add(lblNivel);

    }

    private JLabel crearLabel(
            String texto) {

        JLabel lbl =
                new JLabel(
                        texto,
                        SwingConstants.CENTER);

        lbl.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13));

        return lbl;

    }

    public void actualizar(
            int vecesPreguntada,
            int vecesFallada,
            double porcentaje) {

        int correctas =
                vecesPreguntada
                - vecesFallada;

        lblCorrectas.setText(
                String.valueOf(
                        correctas));

        lblFalladas.setText(
                String.valueOf(
                        vecesFallada));

        lblPorcentaje.setText(
                String.format(
                        "%.1f%%",
                        porcentaje));

        int valor =
                (int) Math.round(
                        porcentaje);

        Color color;
        String texto;

        if (valor >= 95) {

                color = new Color(39, 174, 96);
                texto = "Perfecta";

        } else if (valor >= 85) {

                color = new Color(46, 204, 113);
                texto = "Dominada";

        } else if (valor >= 70) {

                color = new Color(102, 187, 106);
                texto = "Aceptable";

        } else if (valor >= 50) {

                color = new Color(241, 196, 15);
                texto = "Justa";

        } else if (valor >= 30) {

                color = new Color(230, 126, 34);
                texto = "Difícil";

        } else if (valor >= 10) {

                color = new Color(231, 76, 60);
                texto = "Muy difícil";

        } else {

                color = new Color(192, 57, 43);
                texto = "Desastre";

        }

        lblNivel.setForeground(
                color);

        lblNivel.setText(
                texto);

    }

    private JLabel crearCabecera(String texto) {

        JLabel lbl = new JLabel(
                texto,
                SwingConstants.CENTER);

        lbl.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13));

        return lbl;
    }

}