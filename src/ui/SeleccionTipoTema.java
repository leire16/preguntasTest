package ui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class SeleccionTipoTema extends JPanel {

    public SeleccionTipoTema(VentanaPrincipal ventana) {

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titulo = new JLabel("¿QUÉ TIPO DE TEMA QUIERES?", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));

        JButton btnEspecifico = new JButton("📖 Temas Específicos");
        JButton btnComun = new JButton("📌 Tema Común");

        btnEspecifico.addActionListener(e ->
                ventana.confirmarSeleccionTipoTema(false));

        btnComun.addActionListener(e ->
                ventana.confirmarSeleccionTipoTema(true));

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(titulo, gbc);

        gbc.gridy++;
        add(btnEspecifico, gbc);

        gbc.gridy++;
        add(btnComun, gbc);

    }

}