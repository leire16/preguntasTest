package ui.componentesEstadisticas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class PanelRendimientoTema extends JPanel {

    private JLabel lblNombre;
    private JLabel lblPorcentaje;
    private JProgressBar barra;

    public PanelRendimientoTema(
            int numeroTema,
            String nombre) {

        setLayout(
                new BorderLayout(
                        10,
                        5));

        setBorder(
                BorderFactory.createEmptyBorder(
                        5,
                        10,
                        5,
                        10));

        lblNombre =
                new JLabel(
                        "Tema "
                        + numeroTema
                        + " - "
                        + nombre);

        lblNombre.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14));

        lblPorcentaje =
                new JLabel(
                        "0%");

        lblPorcentaje.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14));

        barra =
                new JProgressBar(
                        0,
                        100);

        JPanel panelSuperior =
                new JPanel(
                        new BorderLayout());

        panelSuperior.setOpaque(false);

        panelSuperior.add(
                lblNombre,
                BorderLayout.WEST);

        panelSuperior.add(
                lblPorcentaje,
                BorderLayout.EAST);

        add(
                panelSuperior,
                BorderLayout.NORTH);

        add(
                barra,
                BorderLayout.CENTER);

    }

    public void setPorcentaje(
            double porcentaje) {

        int valor =
                (int) porcentaje;

        barra.setValue(
                valor);

        lblPorcentaje.setText(
                String.format(
                        "%.1f%%",
                        porcentaje));

        if(valor >= 70) {

            barra.setForeground(
                    new Color(
                            46,
                            204,
                            113));

        } else if(valor >= 50) {

            barra.setForeground(
                    new Color(
                            241,
                            196,
                            15));

        } else {

            barra.setForeground(
                    new Color(
                            231,
                            76,
                            60));

        }

    }

}