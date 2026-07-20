package ui.componentesResultados;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import service.ResultadoTest;

public class PanelResumenResultado extends JPanel {

    private JLabel lblTitulo;

    private JLabel lblCorrectas;
    private JLabel lblIncorrectas;
    private PanelCirculoProgreso panelCirculo;
    private JLabel lblTiempo;

    public PanelResumenResultado() {

        setLayout(new BorderLayout(15, 10));

        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Resumen"),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        inicializarComponentes();

    }

    private void inicializarComponentes() {

        lblTitulo = new JLabel("RESULTADOS DEL TEST");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));

        panelCirculo = new PanelCirculoProgreso();

        lblCorrectas = crearEtiqueta("✅ Correctas: 0");
        lblIncorrectas = crearEtiqueta("❌ Incorrectas: 0");
        lblTiempo = crearEtiqueta("⏱ Tiempo: 00:00:00");

        JPanel panelTextos = new JPanel(new GridLayout(3, 1, 5, 8));
        panelTextos.setOpaque(false);
        panelTextos.add(lblCorrectas);
        panelTextos.add(lblIncorrectas);
        panelTextos.add(lblTiempo);

        JPanel panelCentro = new JPanel(new BorderLayout(20, 0));
        panelCentro.setOpaque(false);
        panelCentro.add(panelCirculo, BorderLayout.WEST);
        panelCentro.add(panelTextos, BorderLayout.CENTER);

        add(lblTitulo, BorderLayout.NORTH);
        add(panelCentro, BorderLayout.CENTER);

    }

    private JLabel crearEtiqueta(String texto) {

        JLabel lbl = new JLabel(texto);

        lbl.setFont(new Font("Arial", Font.PLAIN, 16));

        return lbl;

    }

    // ==========================================
    // MÉTODOS PÚBLICOS
    // ==========================================

    public void actualizarResumen(int correctas,
                                  int incorrectas,
                                  double nota,
                                  int tiempoSegundos) {

        lblCorrectas.setText("Correctas: " + correctas);

        lblIncorrectas.setText("Incorrectas: " + incorrectas);

        panelCirculo.setPorcentaje(nota);

        lblTiempo.setText("Tiempo: " + formatearTiempo(tiempoSegundos));

    }
  
    /**
     * Convierte una duración en segundos al formato HH:MM:SS.
     */
    private String formatearTiempo(int totalSegundos) {

        int horas = totalSegundos / 3600;
        int minutos = (totalSegundos % 3600) / 60;
        int segundos = totalSegundos % 60;

        return String.format("%02d:%02d:%02d", horas, minutos, segundos);

    }
 
    public void mostrarResultado(ResultadoTest resultado) {

        actualizarResumen(
                resultado.getAciertos(),
                resultado.getFallos(),
                resultado.getNota(),
                resultado.getDuracionSegundos()
        );

    }

}