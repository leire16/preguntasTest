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
import javax.swing.SwingUtilities;

import dao.TemaDao;
import model.Tema;
import model.TipoTema;
import util.Constantes;

public class PanelTemas extends JPanel {

    private final TemaDao temaDao;

    private List<Tema> temasCargados;
    private JCheckBox[] checkTemas;

    private JButton btnSeleccionarTodos;
    private JButton btnDeseleccionarTodos;

    private JPanel panelChecks;
    private JScrollPane scroll;

    private TipoTema tipoActual;


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
     * Carga los temas según el tipo seleccionado.
     */
    public void cargarTemas(TipoTema tipo) {

        List<Tema> todos = temaDao.obtenerTodos();

        this.tipoActual = tipo;

        temasCargados = new ArrayList<>();

        List<Integer> idsComunes = Constantes.idsTemaComun();

        for (Tema tema : todos) {

            boolean esComun = idsComunes.contains(tema.getId());

            boolean incluir = switch (tipo) {
                case COMUN -> esComun;
                case ESPECIFICO -> !esComun;
                case TODOS -> true;
            };

            if (incluir) {
                temasCargados.add(tema);
            }
        }

        reconstruirCheckboxes();
    }


    private void reconstruirCheckboxes() {

        panelChecks.removeAll();

        if (temasCargados == null || temasCargados.isEmpty()) {

            panelChecks.setLayout(new GridLayout(1, 1));

            return;
        }


        panelChecks.setLayout(
                new GridLayout(temasCargados.size(), 1, 0, 8)
        );


        checkTemas = new JCheckBox[temasCargados.size()];


        for (int i = 0; i < temasCargados.size(); i++) {

            Tema tema = temasCargados.get(i);

            checkTemas[i] = new JCheckBox(
                    Constantes.etiquetaTema(tema.getId(), tipoActual == TipoTema.TODOS)
                    + " - " + tema.getNombre()
            );

            panelChecks.add(checkTemas[i]);
        }


        panelChecks.revalidate();
        panelChecks.repaint();

         // Volver siempre al inicio del scroll
        SwingUtilities.invokeLater(() -> {
            scroll.getVerticalScrollBar().setValue(0);
        });

    }


    public List<Integer> getTemasSeleccionados() {

        List<Integer> temas = new ArrayList<>();

        if (checkTemas == null) {
            return temas;
        }


        for (int i = 0; i < checkTemas.length; i++) {

            if (checkTemas[i].isSelected()) {

                temas.add(
                    temasCargados.get(i).getId()
                );
            }
        }

        return temas;
    }


    public List<Integer> getTodosLosTemas() {

        List<Integer> temas = new ArrayList<>();

        if (temasCargados == null) {
            return temas;
        }


        for (Tema tema : temasCargados) {

            temas.add(tema.getId());

        }

        return temas;
    }


    public boolean hayTemasSeleccionados() {

        if (checkTemas == null) {
            return false;
        }


        for (JCheckBox check : checkTemas) {

            if (check.isSelected()) {

                return true;
            }
        }

        return false;
    }


    public void seleccionarTodos() {

        if (checkTemas == null) {
            return;
        }


        for (JCheckBox check : checkTemas) {

            check.setSelected(true);

        }
    }


    public void deseleccionarTodos() {

        if (checkTemas == null) {
            return;
        }


        for (JCheckBox check : checkTemas) {

            check.setSelected(false);

        }
    }

}