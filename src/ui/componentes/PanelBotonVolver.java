package ui.componentes;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelBotonVolver extends JPanel {

    private JButton btnVolver;

    public PanelBotonVolver() {

        setLayout(new FlowLayout(FlowLayout.CENTER, 30, 15));

        btnVolver = new JButton("Volver");
        btnVolver.setPreferredSize(new Dimension(160, 40));

        add(btnVolver);

    }

    public JButton getBtnVolver() {
        return btnVolver;
    }

}