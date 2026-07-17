package ui.componentes;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import util.Constantes;

public class PanelTemas extends JPanel {

    private JCheckBox[] checkTemas;

    private JButton btnSeleccionarTodos;
    private JButton btnDeseleccionarTodos;

    public PanelTemas() {

        setLayout(new BorderLayout(10, 10));

        setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Temas"));

        crearBotones();
        crearTemas();

    }

    private void crearBotones() {

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));

        btnSeleccionarTodos = new JButton("Seleccionar todos");
        btnDeseleccionarTodos = new JButton("Deseleccionar todos");

        panelBotones.add(btnSeleccionarTodos);
        panelBotones.add(btnDeseleccionarTodos);

        btnSeleccionarTodos.addActionListener(e -> seleccionarTodos());
        btnDeseleccionarTodos.addActionListener(e -> deseleccionarTodos());

        add(panelBotones, BorderLayout.NORTH);

    }

    private void crearTemas() {

        JPanel panelChecks = new JPanel();
        panelChecks.setLayout(new GridLayout(Constantes.TOTAL_TEMAS, 1, 0, 8));

        checkTemas = new JCheckBox[Constantes.TOTAL_TEMAS];

        for (int i = 0; i < Constantes.TOTAL_TEMAS; i++) {

            checkTemas[i] = new JCheckBox(
                    (i + 1) + ". " + Constantes.TEMAS[i]);

            panelChecks.add(checkTemas[i]);

        }

        JScrollPane scroll = new JScrollPane(panelChecks);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        add(scroll, BorderLayout.CENTER);

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

    public List<Integer> getTodosLosTemas() {

        List<Integer> temas = new ArrayList<>();

        for (int i = 1; i <= Constantes.TOTAL_TEMAS; i++) {

            temas.add(i);

        }

        return temas;

    }

    public boolean hayTemasSeleccionados() {

        for (JCheckBox check : checkTemas) {

            if (check.isSelected()) {

                return true;

            }

        }

        return false;

    }

    public void seleccionarTodos() {

        for (JCheckBox check : checkTemas) {

            check.setSelected(true);

        }

    }

    public void deseleccionarTodos() {

        for (JCheckBox check : checkTemas) {

            check.setSelected(false);

        }

    }

}