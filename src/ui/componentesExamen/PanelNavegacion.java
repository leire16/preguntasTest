package ui.componentesExamen;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelNavegacion extends JPanel {

    private JButton btnAnterior;
    private JButton btnSiguiente;

    public PanelNavegacion() {

        setLayout(new BorderLayout(20, 0));

        crearBotones();

    }

    private void crearBotones() {

        btnAnterior = new JButton("← Anterior");
        btnSiguiente = new JButton("Siguiente →");

        btnAnterior.setPreferredSize(new Dimension(170, 45));
        btnSiguiente.setPreferredSize(new Dimension(170, 45));

        btnAnterior.setEnabled(false);

        add(btnAnterior, BorderLayout.WEST);
        add(btnSiguiente, BorderLayout.EAST);

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