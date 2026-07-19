package ui.componentesResultados;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelBotonMenu extends JPanel {

    private JButton btnVolverMenu;

    public PanelBotonMenu() {

        setLayout(new FlowLayout(FlowLayout.CENTER));

        crearBoton();

    }

    private void crearBoton() {

        btnVolverMenu = new JButton("Menu Principal");

        btnVolverMenu.setFont(new Font("Arial", Font.BOLD, 15));

        add(btnVolverMenu);

    }

    // ===========================================
    // GETTERS
    // ===========================================

    public JButton getBtnVolverMenu() {

        return btnVolverMenu;

    }

}