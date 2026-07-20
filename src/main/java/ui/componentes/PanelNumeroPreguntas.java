package ui.componentes;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import util.Constantes;

public class PanelNumeroPreguntas extends JPanel {

    private JRadioButton[] opciones;

    private JRadioButton todas;

    public PanelNumeroPreguntas() {

        setLayout(new FlowLayout(FlowLayout.CENTER));

        setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Número de preguntas"));

        crearOpciones();

    }

    private void crearOpciones() {

        opciones = new JRadioButton[
                Constantes.OPCIONES_NUMERO_PREGUNTAS.length];

        ButtonGroup grupo = new ButtonGroup();

        for(int i = 0; i < Constantes.OPCIONES_NUMERO_PREGUNTAS.length; i++) {

            int numero = Constantes.OPCIONES_NUMERO_PREGUNTAS[i];

            opciones[i] = new JRadioButton(
                    String.valueOf(numero));

            if(numero == 50) {
                opciones[i].setSelected(true);
            }

            grupo.add(opciones[i]);

            add(opciones[i]);

        }

        todas = new JRadioButton("Todas");

        grupo.add(todas);

        add(todas);
    }

    public int getNumeroPreguntas() {

        for(JRadioButton opcion : opciones) {

            if(opcion.isSelected()) {

                return Integer.parseInt(opcion.getText());

            }

        }

        return -1;

    }

    public boolean isTodasSeleccionada() {

        return todas.isSelected();

    }

}