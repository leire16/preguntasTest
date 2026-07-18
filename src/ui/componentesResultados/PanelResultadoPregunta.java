package ui.componentesResultados;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelResultadoPregunta extends JPanel {

    private JLabel lblCabecera;
    private JLabel lblEnunciado;

    private JPanel panelRespuestas;

    public PanelResultadoPregunta() {

        setLayout(new BorderLayout(10, 10));

        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 210, 210)),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)));

        inicializarComponentes();

        colocarComponentes();

    }

    private void inicializarComponentes() {

        lblCabecera = new JLabel("Pregunta 1");

        lblCabecera.setFont(new Font("Arial", Font.BOLD, 16));

        lblEnunciado = new JLabel();

        lblEnunciado.setFont(new Font("Arial", Font.PLAIN, 15));

        panelRespuestas = new JPanel();

        panelRespuestas.setLayout(new BoxLayout(panelRespuestas,
                BoxLayout.Y_AXIS));

    }

    private void colocarComponentes() {

        add(lblCabecera, BorderLayout.NORTH);

        JPanel centro = new JPanel();

        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));

        centro.add(lblEnunciado);

        centro.add(Box.createVerticalStrut(12));

        centro.add(panelRespuestas);

        add(centro, BorderLayout.CENTER);

    }

    // =====================================================
    // MÉTODOS PÚBLICOS
    // =====================================================

    public void setCabecera(int numeroPregunta,
                            boolean correcta) {

        if (correcta) {

            lblCabecera.setText(
                    "Pregunta " + numeroPregunta + "   ✅ Correcta");

        } else {

            lblCabecera.setText(
                    "Pregunta " + numeroPregunta + "   ❌ Incorrecta");

        }

    }

    public void setEnunciado(String enunciado) {

        lblEnunciado.setText("<html><b>" + enunciado + "</b></html>");

    }

    public void limpiarRespuestas() {

        panelRespuestas.removeAll();

    }

    public void addRespuesta(JLabel respuesta) {

        respuesta.setAlignmentX(Component.LEFT_ALIGNMENT);

        panelRespuestas.add(respuesta);

        panelRespuestas.add(Box.createVerticalStrut(5));

    }

}