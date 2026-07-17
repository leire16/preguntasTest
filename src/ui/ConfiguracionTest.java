package ui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.TipoTest;

public class ConfiguracionTest extends JPanel {

    private JLabel lblModo;

    public ConfiguracionTest(VentanaPrincipal ventana) {

        setLayout(new BorderLayout(10, 10));

        JLabel titulo = new JLabel("CONFIGURACIÓN DEL TEST", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));

        add(titulo, BorderLayout.NORTH);

        lblModo = new JLabel("", SwingConstants.CENTER);
        lblModo.setFont(new Font("Arial", Font.PLAIN, 20));

        add(lblModo, BorderLayout.CENTER);

    }

    public void setTipoTest(TipoTest tipo) {

        switch (tipo) {

            case TEMAS:
                lblModo.setText("Modo: Test por temas");
                break;

            case COMPLETO:
                lblModo.setText("Modo: Todas las preguntas");
                break;

            case FALLADAS:
                lblModo.setText("Modo: Preguntas falladas");
                break;

        }

    }

}