package ui.componentes;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelBotones extends JPanel {

    private JButton btnVolver;
    private JButton btnComenzar;

    public PanelBotones() {

        setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));

        btnVolver = new JButton("Volver");
        btnComenzar = new JButton("Comenzar");

        btnVolver.setPreferredSize(new Dimension(140, 35));
        btnComenzar.setPreferredSize(new Dimension(140, 35));

        add(btnVolver);
        add(btnComenzar);

    }

    public JButton getBtnVolver() {
        return btnVolver;
    }

    public JButton getBtnComenzar() {
        return btnComenzar;
    }

}