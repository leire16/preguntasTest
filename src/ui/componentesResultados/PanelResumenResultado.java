package ui.componentesResultados;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelResumenResultado extends JPanel {

    private JLabel lblTitulo;

    private JLabel lblCorrectas;
    private JLabel lblIncorrectas;
    private JLabel lblNota;
    private JLabel lblTiempo;

    public PanelResumenResultado() {

        setLayout(new GridLayout(3, 2, 15, 10));

        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Resumen"),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        inicializarComponentes();

    }

    private void inicializarComponentes() {

        lblTitulo = new JLabel("RESULTADOS DEL TEST");

        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));

        lblCorrectas = crearEtiqueta("✅ Correctas: 0");

        lblIncorrectas = crearEtiqueta("❌ Incorrectas: 0");

        lblNota = crearEtiqueta("📊 Nota: 0%");

        lblTiempo = crearEtiqueta("⏱ Tiempo: 00:00:00");

        add(lblTitulo);

        add(new JLabel());

        add(lblCorrectas);

        add(lblIncorrectas);

        add(lblNota);

        add(lblTiempo);

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
                                  String tiempo) {

        lblCorrectas.setText("✅ Correctas: " + correctas);

        lblIncorrectas.setText("❌ Incorrectas: " + incorrectas);

        lblNota.setText(String.format("📊 Nota: %.2f%%", nota));

        lblTiempo.setText("⏱ Tiempo: " + tiempo);

    }

}