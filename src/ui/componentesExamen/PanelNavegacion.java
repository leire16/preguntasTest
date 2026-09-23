package ui.componentesExamen;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelNavegacion extends JPanel {

    private JButton btnAnterior;
    private JButton btnSiguiente;
    private JButton btnSalir;

    public PanelNavegacion() {

        setLayout(new BorderLayout(20, 0));

        crearBotones();

    }

    private void crearBotones() {

        btnAnterior = new JButton("← Anterior");
        btnSiguiente = new JButton("Siguiente →");
        btnSalir = new JButton("Salir al menú");

        btnAnterior.setPreferredSize(new Dimension(170, 45));
        btnSiguiente.setPreferredSize(new Dimension(170, 45));
        btnSalir.setPreferredSize(new Dimension(170, 45));

        btnAnterior.setEnabled(false);

        JPanel panelderecha = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panelderecha.add(btnAnterior);
        panelderecha.add(btnSiguiente);          // ← aquí se añade btnSalir

        add(btnSalir, BorderLayout.WEST); // ← y aquí se añade panelIzquierda al panel principal
        add(panelderecha, BorderLayout.EAST);

    }

    // ===========================================
    // GETTERS
    // ===========================================

    public JButton getBtnAnterior() {
        return btnAnterior;
    }

    public JButton getBtnSiguiente() {
        return btnSiguiente;
    }

    public JButton getBtnSalir() {
        return btnSalir;
    }


    // ===========================================
    // MÉTODOS PÚBLICOS
    // ===========================================

    public void actualizarEstado(int preguntaActual,
                             int totalPreguntas,
                             boolean puedeFinalizar) {

        btnAnterior.setEnabled(preguntaActual > 1);

        if (preguntaActual == totalPreguntas) {

            btnSiguiente.setText("Finalizar test");
            btnSiguiente.setEnabled(puedeFinalizar);

        } else {

            btnSiguiente.setText("Siguiente →");
            btnSiguiente.setEnabled(true);

        }

    }

}