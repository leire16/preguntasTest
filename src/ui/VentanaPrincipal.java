package ui;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.TipoTest;

public class VentanaPrincipal extends JFrame {

    private CardLayout cardLayout;
    private JPanel contenedor;

    private MenuPrincipal menuPrincipal;
    private ConfiguracionTest configuracionTest;

    public VentanaPrincipal() {

        setTitle("OPE Trainer");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        contenedor = new JPanel(cardLayout);

        configuracionTest = new ConfiguracionTest(this);
        menuPrincipal = new MenuPrincipal(this);

        contenedor.add(menuPrincipal, "MENU");
        contenedor.add(configuracionTest, "CONFIGURACION");

        add(contenedor);

        mostrarMenu();

        setVisible(true);

    }

    public void mostrarMenu() {

        cardLayout.show(contenedor, "MENU");

    }

    public void mostrarConfiguracion(TipoTest tipo) {

        configuracionTest.setTipoTest(tipo);

        cardLayout.show(contenedor, "CONFIGURACION");

    }

}