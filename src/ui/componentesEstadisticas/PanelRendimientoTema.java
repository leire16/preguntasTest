package ui.componentesEstadisticas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

public class PanelRendimientoTema extends JPanel {

    private JLabel lblNombre;
    private JLabel lblPorcentaje;
    private JLabel lblNivel;

    private JLabel lblRespondidas;
    private JLabel lblAciertos;
    private JLabel lblFallos;

    private JProgressBar barra;

    public PanelRendimientoTema(
            int numeroTema,
            String nombre) {

        setLayout(new BorderLayout(10, 8));

        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        // ==========================
        // Cabecera
        // ==========================

        lblNombre = new JLabel(
                "Tema " + numeroTema + " - " + nombre);

        lblNombre.setFont(new Font("Arial", Font.BOLD, 15));

        add(lblNombre, BorderLayout.NORTH);

        // ==========================
        // Centro
        // ==========================

        JPanel centro = new JPanel();
        centro.setOpaque(false);
        centro.setLayout(new GridLayout(3, 1, 0, 5));

        barra = new JProgressBar(0, 100);

        lblPorcentaje = new JLabel(
                "0 %",
                SwingConstants.CENTER);

        lblPorcentaje.setFont(
                new Font("Arial", Font.BOLD, 15));

        lblNivel = new JLabel(
                "",
                SwingConstants.CENTER);

        lblNivel.setFont(
                new Font("Arial", Font.BOLD, 14));

        centro.add(barra);
        centro.add(lblPorcentaje);
        centro.add(lblNivel);

        add(centro, BorderLayout.CENTER);

        // ==========================
        // Parte inferior
        // ==========================

        JPanel datos = new JPanel();

        datos.setOpaque(false);

        datos.setLayout(new GridLayout(3, 1));

        lblRespondidas = new JLabel("Respondidas: 0");
        lblAciertos = new JLabel("Aciertos: 0");
        lblFallos = new JLabel("Fallos: 0");

        datos.add(lblRespondidas);
        datos.add(lblAciertos);
        datos.add(lblFallos);

        add(datos, BorderLayout.SOUTH);

    }

    public void actualizar(
        int respondidas,
        int aciertos,
        int fallos,
        double porcentaje) {

                lblRespondidas.setText(
                        "Respondidas: " + respondidas);

                lblAciertos.setText(
                        "Aciertos: " + aciertos);

                lblFallos.setText(
                        "Fallos: " + fallos);

                int valor = (int) Math.round(porcentaje);

                barra.setValue(valor);

                lblPorcentaje.setText(
                        String.format("%.1f %%", porcentaje));

                Color color;
                String texto;

                if (valor >= 85) {

                        color = new Color(46, 204, 113);
                        texto = "Excelente";

                } else if (valor >= 70) {

                        color = new Color(39, 174, 96);
                        texto = "Bueno";

                } else if (valor >= 50) {

                        color = new Color(241, 196, 15);
                        texto = "Mejorable";

                } else {

                        color = new Color(231, 76, 60);
                        texto = "Prioridad";

                }

                barra.setForeground(color);

                lblNivel.setForeground(color);
                lblNivel.setText(texto);

        }

}