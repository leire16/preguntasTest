package ui.componentesEstadisticas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelPreguntaDificil extends JPanel {

    private JLabel lblPregunta;
    private JLabel lblNivel;

    private JLabel lblCorrectas;
    private JLabel lblFalladas;
    private JLabel lblPorcentaje;

    public PanelPreguntaDificil(
            int numeroPregunta,
            int temaId) {

        setLayout(new BorderLayout(10, 5));

        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)));

        // ==========================
        // Cabecera
        // ==========================

        JPanel superior = new JPanel(new BorderLayout());

        superior.setOpaque(false);

        lblPregunta = new JLabel(
                "Pregunta "
                + numeroPregunta
                + " - Tema "
                + temaId);

        lblPregunta.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14));

        lblNivel = new JLabel();

        lblNivel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14));

        superior.add(
                lblPregunta,
                BorderLayout.WEST);

        superior.add(
                lblNivel,
                BorderLayout.EAST);

        add(
                superior,
                BorderLayout.NORTH);

        // ==========================
        // Datos
        // ==========================

        JPanel inferior = new JPanel(
                new GridLayout(1, 3));

        inferior.setOpaque(false);

        lblCorrectas = new JLabel(
                "",
                SwingConstants.CENTER);

        lblFalladas = new JLabel(
                "",
                SwingConstants.CENTER);

        lblPorcentaje = new JLabel(
                "",
                SwingConstants.CENTER);

        lblCorrectas.setForeground(
                new Color(46, 204, 113));

        lblFalladas.setForeground(
                new Color(231, 76, 60));

        lblPorcentaje.setForeground(
                new Color(52, 73, 94));

        inferior.add(lblCorrectas);
        inferior.add(lblFalladas);
        inferior.add(lblPorcentaje);

        add(
                inferior,
                BorderLayout.CENTER);

    }

    public void actualizar(
            int vecesPreguntada,
            int vecesFallada,
            double porcentaje) {

        int correctas =
                vecesPreguntada - vecesFallada;

        lblCorrectas.setText(
                "Correctas: " + correctas);

        lblFalladas.setText(
                "Falladas: " + vecesFallada);

        lblPorcentaje.setText(
                String.format(
                        "Acierto: %.1f%%",
                        porcentaje));

        Color color;
        String texto;

        int valor =
                (int) Math.round(porcentaje);

        if (valor >= 85) {

            color = new Color(46, 204, 113);
            texto = "Dominada";

        } else if (valor >= 70) {

            color = new Color(39, 174, 96);
            texto = "Aceptable";

        } else if (valor >= 50) {

            color = new Color(241, 196, 15);
            texto = "Difícil";

        } else {

            color = new Color(231, 76, 60);
            texto = "Muy difícil";

        }

        lblNivel.setForeground(color);
        lblNivel.setText(texto);

    }

}