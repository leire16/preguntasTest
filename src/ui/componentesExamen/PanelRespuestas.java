package ui.componentesExamen;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class PanelRespuestas extends JPanel {

    private ButtonGroup grupo;
    private JRadioButton[] respuestas;

    public PanelRespuestas() {

        setLayout(new BorderLayout());

        setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Respuestas"));

        crearRespuestas();

    }

    private void crearRespuestas() {

        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));

        grupo = new ButtonGroup();
        respuestas = new JRadioButton[4];

        char letra = 'A';

        for (int i = 0; i < respuestas.length; i++) {

            JPanel fila = new JPanel(new BorderLayout());

            respuestas[i] = new JRadioButton();

            respuestas[i].setFont(new Font("Arial", Font.PLAIN, 17));
            respuestas[i].setOpaque(false);
            respuestas[i].setFocusPainted(false);

            respuestas[i].setText(letra + ". Respuesta " + (i + 1));

            grupo.add(respuestas[i]);

            fila.add(respuestas[i], BorderLayout.CENTER);

            panelCentral.add(fila);
            panelCentral.add(Box.createVerticalStrut(12));

            letra++;

        }

        add(panelCentral, BorderLayout.NORTH);

    }

    // =====================================================
    // MÉTODOS PÚBLICOS
    // =====================================================

    public void mostrarRespuestas(String[] opciones) {

        char letra = 'A';

        for (int i = 0; i < respuestas.length; i++) {

            respuestas[i].setText(letra + ". " + opciones[i]);

            letra++;

        }

    }

    public int getRespuestaSeleccionada() {

        for (int i = 0; i < respuestas.length; i++) {

            if (respuestas[i].isSelected()) {
                return i;
            }

        }

        return -1;

    }

    public void seleccionarRespuesta(int indice) {

        if (indice >= 0 && indice < respuestas.length) {

            respuestas[indice].setSelected(true);

        }

    }

    public void limpiarSeleccion() {

        grupo.clearSelection();

    }

}