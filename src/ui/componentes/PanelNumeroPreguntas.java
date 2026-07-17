package ui.componentes;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class PanelNumeroPreguntas extends JPanel {

    private JRadioButton r5;
    private JRadioButton r10;
    private JRadioButton r25;
    private JRadioButton r50;
    private JRadioButton r100;
    private JRadioButton rTodas;

    public PanelNumeroPreguntas() {

        setLayout(new FlowLayout(FlowLayout.CENTER));

        setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Número de preguntas"));

        r5 = new JRadioButton("5");
        r10 = new JRadioButton("10");
        r25 = new JRadioButton("25");
        r50 = new JRadioButton("50", true);
        r100 = new JRadioButton("100");
        rTodas = new JRadioButton("Todas");

        ButtonGroup grupo = new ButtonGroup();

        grupo.add(r5);
        grupo.add(r10);
        grupo.add(r25);
        grupo.add(r50);
        grupo.add(r100);
        grupo.add(rTodas);

        add(r5);
        add(r10);
        add(r25);
        add(r50);
        add(r100);
        add(rTodas);

    }

    public int getNumeroPreguntas() {

        if (r5.isSelected()) return 5;
        if (r10.isSelected()) return 10;
        if (r25.isSelected()) return 25;
        if (r50.isSelected()) return 50;
        if (r100.isSelected()) return 100;

        return -1; // Todas

    }

}