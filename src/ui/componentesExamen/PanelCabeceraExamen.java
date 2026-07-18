package ui.componentesExamen;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import model.Configuracion;
import model.TipoTest;

public class PanelCabeceraExamen extends JPanel {

    private JLabel lblTipoTest;
    private JLabel lblPregunta;
    private JLabel lblTiempo;

    private JProgressBar barraProgreso;

    public PanelCabeceraExamen() {

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));

        crearCabecera();
        crearBarraProgreso();

    }

    private void crearCabecera() {

        JPanel panelCabecera = new JPanel();
        panelCabecera.setLayout(new BoxLayout(panelCabecera, BoxLayout.Y_AXIS));

        // Primera fila
        JPanel panelSuperior = new JPanel(new BorderLayout());

        lblTipoTest = new JLabel("Test por temas");
        lblTipoTest.setFont(new Font("Arial", Font.BOLD, 20));

        lblTiempo = new JLabel("Tiempo: 00:00:00");
        lblTiempo.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTiempo.setFont(new Font("Arial", Font.PLAIN, 18));

        panelSuperior.add(lblTipoTest, BorderLayout.WEST);
        panelSuperior.add(lblTiempo, BorderLayout.EAST);

        // Segunda fila
        lblPregunta = new JLabel("Pregunta 1 de 50");
        lblPregunta.setFont(new Font("Arial", Font.BOLD, 18));
        lblPregunta.setBorder(BorderFactory.createEmptyBorder(8, 0, 0, 0));

        panelCabecera.add(panelSuperior);
        panelCabecera.add(lblPregunta);

        add(panelCabecera, BorderLayout.NORTH);

    }

    private void crearBarraProgreso() {

        barraProgreso = new JProgressBar(0, 100);

        barraProgreso.setValue(2);
        barraProgreso.setString("2%");
        barraProgreso.setStringPainted(true);

        add(barraProgreso, BorderLayout.SOUTH);

    }

    // =====================================================
    // MÉTODOS PÚBLICOS
    // =====================================================

    public void actualizarTipoTest(TipoTest tipo) {

        lblTipoTest.setText(tipo.getDescripcion());

    }

    public void actualizarPregunta(int actual, int total) {

        lblPregunta.setText("Pregunta " + actual + " de " + total);

        int porcentaje = (int) ((actual * 100.0) / total);

        barraProgreso.setValue(porcentaje);
        barraProgreso.setString(porcentaje + "%");

    }

    public void actualizarTiempo(String tiempo) {

        lblTiempo.setText("Tiempo: " + tiempo);

    }
 
    public void inicializar(Configuracion configuracion) {

        actualizarTipoTest(configuracion.getTipoTest());
        System.out.println(configuracion.getNumeroPreguntasReales());
        actualizarPregunta(1, configuracion.getNumeroPreguntasReales());

        actualizarTiempo("00:00:00");

    }
}