package ui.componentesExamen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

import model.Respuesta;

public class PanelRespuesta extends JPanel {

    private final JRadioButton radio;

    private Respuesta respuesta;

    private final JTextArea texto;

    private Runnable onSeleccionada;

    public PanelRespuesta(ButtonGroup grupo) {

        setLayout(new BorderLayout(15, 10));

        setBackground(Color.WHITE);

        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 210, 210)),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)));

        radio = new JRadioButton();

        radio.setOpaque(false);

        radio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        grupo.add(radio);

        // Toda la lógica pasa por aquí
        radio.addActionListener(e -> {

            if (onSeleccionada != null) {

                onSeleccionada.run();

            }

        });

        texto = new JTextArea();

        texto.setEditable(false);

        texto.setFocusable(false);

        texto.setOpaque(false);

        texto.setLineWrap(true);

        texto.setWrapStyleWord(true);

        texto.setFont(new Font("SansSerif", Font.PLAIN, 16));

        texto.setBorder(null);

        texto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        add(radio, BorderLayout.WEST);

        add(texto, BorderLayout.CENTER);

        // Pulsar sobre el texto
        texto.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {

                radio.doClick();

            }

        });

        // Pulsar sobre cualquier parte de la tarjeta
        addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {

                radio.doClick();

            }

        });

    }

    // ==================================================

    public void setRespuesta(char letra, Respuesta respuesta) {

        this.respuesta = respuesta;

        texto.setText(letra + ". " + respuesta.getTexto());

        texto.setCaretPosition(0);

    }

    public void setOnSeleccionada(Runnable onSeleccionada) {

        this.onSeleccionada = onSeleccionada;

    }

    public Respuesta getRespuesta() {

        return respuesta;

    }

    public boolean isSelected() {

        return radio.isSelected();

    }

    public void setSelected(boolean seleccionado) {

        radio.setSelected(seleccionado);

    }

    public JRadioButton getRadio() {

        return radio;

    }

}