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

import dao.TemaDao;
import model.Tema;
import util.Constantes;

public class PanelTemas extends JPanel {

    private final TemaDao temaDao;

    private List<Tema> temasCargados;
    private JCheckBox[] checkTemas;

    private JButton btnSeleccionarTodos;
    private JButton btnDeseleccionarTodos;

    private JPanel panelChecks;
    private JScrollPane scroll;

    public PanelTemas() {

        temaDao = new TemaDao();

        setLayout(new BorderLayout(10, 10));

        setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Temas"));

        crearBotones();

        panelChecks = new JPanel();
        scroll = new JScrollPane(panelChecks);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        add(scroll, BorderLayout.CENTER);

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

    /**
     * Carga los temas desde la base de datos, filtrando según el tipo
     * elegido en la pantalla anterior:
     * - temaComunSeleccionado == true  -> solo el tema con id == ID_TEMA_COMUN
     * - temaComunSeleccionado == false -> todos los temas EXCEPTO ID_TEMA_COMUN
     */
    public void cargarTemas(boolean temaComunSeleccionado) {

        List<Tema> todos = temaDao.obtenerTodos();

        temasCargados = new ArrayList<>();

        for (Tema tema : todos) {

            boolean esComun = tema.getId() == Constantes.ID_TEMA_COMUN;

            if (temaComunSeleccionado == esComun) {
                temasCargados.add(tema);
            }

        }

        reconstruirCheckboxes();

    }

    private void reconstruirCheckboxes() {

        panelChecks.removeAll();
        panelChecks.setLayout(new GridLayout(temasCargados.size(), 1, 0, 8));

        checkTemas = new JCheckBox[temasCargados.size()];

        for (int i = 0; i < temasCargados.size(); i++) {

            Tema tema = temasCargados.get(i);

            checkTemas[i] = new JCheckBox(
                    tema.getId() + ". " + tema.getNombre());

            panelChecks.add(checkTemas[i]);

        }

        panelChecks.revalidate();
        panelChecks.repaint();

    }

    public List<Integer> getTemasSeleccionados() {

        List<Integer> temas = new ArrayList<>();

        for (int i = 0; i < checkTemas.length; i++) {

            if (checkTemas[i].isSelected()) {

                temas.add(temasCargados.get(i).getId());

            }

        }

        return temas;

    }

    public List<Integer> getTodosLosTemas() {

        List<Integer> temas = new ArrayList<>();

        for (Tema tema : temasCargados) {

            temas.add(tema.getId());

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