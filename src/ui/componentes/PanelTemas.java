package ui.componentes;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class PanelTemas extends JPanel {

    private JCheckBox[] checkTemas;

    public PanelTemas() {

        setLayout(new GridLayout(5, 4, 15, 10));

        setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Selecciona los temas"));

        checkTemas = new JCheckBox[20];

        for (int i = 1; i <= 20; i++) {

            checkTemas[i - 1] = new JCheckBox("Tema " + i);
            add(checkTemas[i - 1]);

        }

    }

    public List<Integer> getTemasSeleccionados() {

        List<Integer> temas = new ArrayList<>();

        for (int i = 0; i < checkTemas.length; i++) {

            if (checkTemas[i].isSelected()) {

                temas.add(i + 1);

            }

        }

        return temas;

    }

    public void seleccionarTodos() {

        for (JCheckBox c : checkTemas) {

            c.setSelected(true);

        }

    }

    public void deseleccionarTodos() {

        for (JCheckBox c : checkTemas) {

            c.setSelected(false);

        }

    }

}