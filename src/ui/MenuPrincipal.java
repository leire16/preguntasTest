package ui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.TipoTest;

public class MenuPrincipal extends JPanel {

    public MenuPrincipal(VentanaPrincipal ventana) {

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titulo = new JLabel("OPE TRAINER");
        titulo.setFont(new Font("Arial", Font.BOLD, 28));

        JButton btnTemas = new JButton("📚 Test por temas");
        JButton btnCompleto = new JButton("🌍 Test completo");
        JButton btnFalladas = new JButton("❌ Preguntas falladas");

        btnTemas.addActionListener(e ->
                ventana.mostrarConfiguracion(TipoTest.TEMAS));

        btnCompleto.addActionListener(e ->
                ventana.mostrarConfiguracion(TipoTest.COMPLETO));

        btnFalladas.addActionListener(e ->
                ventana.mostrarConfiguracion(TipoTest.FALLADAS));

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(titulo, gbc);

        gbc.gridy++;
        add(btnTemas, gbc);

        gbc.gridy++;
        add(btnCompleto, gbc);

        gbc.gridy++;
        add(btnFalladas, gbc);

    }

}