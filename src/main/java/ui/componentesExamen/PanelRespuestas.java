package ui.componentesExamen;

import java.awt.Component;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;

import model.Respuesta;

public class PanelRespuestas extends JPanel {

    private final PanelRespuesta[] respuestas;
    private final ButtonGroup grupo;

    public PanelRespuestas() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        grupo = new ButtonGroup();
        respuestas = new PanelRespuesta[4];
        crearRespuestas();
    }

    private void crearRespuestas() {
        for (int i = 0; i < respuestas.length; i++) {
            respuestas[i] = new PanelRespuesta(grupo);
            respuestas[i].setAlignmentX(Component.LEFT_ALIGNMENT);
            add(respuestas[i]);
            if (i < respuestas.length - 1) {
                add(Box.createVerticalStrut(12));

            }
        }
    }

    // ===========================================
    // MÉTODOS PÚBLICOS
    // ===========================================

    public void mostrarRespuestas(List<Respuesta> opciones) {
        grupo.clearSelection();
        for (int i = 0; i < respuestas.length; i++) {
            if (i < opciones.size()) {
                char letra = (char) ('A' + i);
                respuestas[i].setVisible(true);
                respuestas[i].setRespuesta(
                    letra,
                    opciones.get(i));
            } else {
                respuestas[i].setVisible(false);
            }

        }

        revalidate();
        repaint();

    }

    public Respuesta getRespuestaSeleccionada() {
        for (PanelRespuesta respuesta : respuestas) {
            if (respuesta.isSelected()) {
                return respuesta.getRespuesta();
            }
        }
        return null;
    }

    public void limpiarSeleccion() {
        grupo.clearSelection();
    }

    public PanelRespuesta getRespuesta(int indice) {
        return respuestas[indice];
    }
  
    public void setOnRespuestaSeleccionada(Runnable listener) {
        for (PanelRespuesta respuesta : respuestas) {
            respuesta.setOnSeleccionada(listener);
        }
    }
}