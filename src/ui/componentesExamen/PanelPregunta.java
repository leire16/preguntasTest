package ui.componentesExamen;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class PanelPregunta extends JPanel {

    private JTextArea txtPregunta;

    public PanelPregunta() {

        setLayout(new BorderLayout());

        actualizarTitulo(1);

        crearPregunta();

    }

    private void crearPregunta() {

        txtPregunta = new JTextArea();

        txtPregunta.setEditable(false);
        txtPregunta.setLineWrap(true);
        txtPregunta.setWrapStyleWord(true);
        txtPregunta.setOpaque(false);
        txtPregunta.setFocusable(false);

        txtPregunta.setFont(new Font("Arial", Font.PLAIN, 18));

        txtPregunta.setText("Aquí aparecerá el enunciado de la pregunta.");

        JScrollPane scroll = new JScrollPane(txtPregunta);

        scroll.setBorder(null);
        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        add(scroll, BorderLayout.CENTER);

    }

    // =====================================================
    // MÉTODOS PÚBLICOS
    // =====================================================

    public void mostrarPregunta(String pregunta) {

        txtPregunta.setText(pregunta);
        txtPregunta.setCaretPosition(0);

    }

    public void actualizarTitulo(int numeroPregunta) {

        setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Pregunta " + numeroPregunta));

    }

}