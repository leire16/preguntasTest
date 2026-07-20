package ui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.TipoTest;
import util.Constantes;

public class MenuPrincipal extends JPanel {

    public MenuPrincipal(VentanaPrincipal ventana) {

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

       JLabel titulo = new JLabel(
        Constantes.TITULO_APP,
        SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 28));

        JButton btnTemas = new JButton("📚 " + TipoTest.TEMA.getDescripcion());
        JButton btnCompleto = new JButton("🌍 " + TipoTest.COMPLETO.getDescripcion());
        JButton btnFalladas = new JButton("❌ " + TipoTest.REPASO.getDescripcion());

        btnTemas.addActionListener(e ->
                ventana.mostrarConfiguracion(TipoTest.TEMA));

        btnCompleto.addActionListener(e ->
                ventana.mostrarConfiguracion(TipoTest.COMPLETO));

        btnFalladas.addActionListener(e ->
                ventana.mostrarConfiguracion(TipoTest.REPASO));

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