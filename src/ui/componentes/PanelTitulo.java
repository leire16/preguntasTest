package ui.componentes;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelTitulo extends JPanel {

    public PanelTitulo(String tituloTexto) {

        JLabel titulo = new JLabel(tituloTexto, SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 28));

        add(titulo);

    }

}