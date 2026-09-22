package ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.TipoTest;
import ui.componentes.PanelBotonVolver;
import util.Constantes;

public class MenuPrincipal extends JPanel {

    public MenuPrincipal(VentanaPrincipal ventana) {

        setLayout(new BorderLayout(15, 15));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        crearCentro(ventana);
        crearBotonVolver(ventana);

    }

    private void crearCentro(VentanaPrincipal ventana) {

        JPanel centro = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titulo = new JLabel(
                Constantes.TITULO_APP,
                SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 28));

        JButton btnTemas = new JButton("📚 " + TipoTest.TEMA.getDescripcion());
        JButton btnCompleto = new JButton("🌍 " + TipoTest.COMPLETO.getDescripcion());
        JButton btnRepaso = new JButton("❌ " + TipoTest.REPASO.getDescripcion());
        JButton btnEstadisticas = new JButton("📊 Estadísticas");

        btnTemas.addActionListener(e ->
                ventana.mostrarConfiguracion(TipoTest.TEMA));

        // btnCompleto.addActionListener(e ->
                // ventana.mostrarConfiguracion(TipoTest.COMPLETO));

        btnCompleto.addActionListener(e ->
            ventana.iniciarTestCompleto());

        btnRepaso.addActionListener(e ->
                ventana.mostrarConfiguracion(TipoTest.REPASO));

        btnEstadisticas.addActionListener(e -> {
            ventana.mostrarEstadisticas();
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        centro.add(titulo, gbc);

        gbc.gridy++;
        centro.add(btnTemas, gbc);

        gbc.gridy++;
        centro.add(btnCompleto, gbc);

        gbc.gridy++;
        centro.add(btnRepaso, gbc);

        gbc.gridy++;
        centro.add(btnEstadisticas, gbc);

        add(centro, BorderLayout.CENTER);

    }

    private void crearBotonVolver(VentanaPrincipal ventana) {

        PanelBotonVolver panelVolver = new PanelBotonVolver();

        panelVolver.getBtnVolver().addActionListener(e ->
                ventana.mostrarTema());

        add(panelVolver, BorderLayout.SOUTH);

    }

}