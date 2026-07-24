package ui.componentesEstadisticas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TarjetaEstadistica extends JPanel {

    private final JLabel lblValor;

    public TarjetaEstadistica(String titulo) {

        setLayout(
                new BorderLayout());

        setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                Color.LIGHT_GRAY),
                        BorderFactory.createEmptyBorder(
                                15,
                                10,
                                15,
                                10)));

        JLabel lblTitulo =
                new JLabel(
                        titulo,
                        SwingConstants.CENTER);

        lblTitulo.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12));

        lblTitulo.setForeground(
                new Color(
                        100,
                        100,
                        100));

        lblValor =
                new JLabel(
                        "0",
                        SwingConstants.CENTER);

        lblValor.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24));

        add(
                lblTitulo,
                BorderLayout.NORTH);

        add(
                lblValor,
                BorderLayout.CENTER);
    }

    public void setValor(int valor) {
        lblValor.setText(
                String.valueOf(valor));

    }
}