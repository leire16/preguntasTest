package ui.componentes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class PanelPlegable extends JPanel {

    private boolean expandido = true;

    private final JLabel lblTitulo;

    private final JPanel contenido;
    private final JScrollPane scroll;

    private final String titulo;

    public PanelPlegable(String titulo) {

        this.titulo = titulo;

        setLayout(new BorderLayout(0, 5));

        lblTitulo = new JLabel();

        lblTitulo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16));

        lblTitulo.setOpaque(true);

        lblTitulo.setBackground(
                new Color(
                        245,
                        245,
                        245));

        lblTitulo.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(220, 220, 220)),
                        BorderFactory.createEmptyBorder(
                                8,
                                10,
                                8,
                                10)));

        lblTitulo.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR));

        actualizarTitulo();

        lblTitulo.addMouseListener(
                new java.awt.event.MouseAdapter() {

                    @Override
                    public void mouseClicked(
                            java.awt.event.MouseEvent e) {

                        alternar();

                    }

                });

        contenido = new JPanel();

        scroll = new JScrollPane(contenido);

        scroll.setBorder(null);

        add(
                lblTitulo,
                BorderLayout.NORTH);

        add(
                scroll,
                BorderLayout.CENTER);

    }

    public JPanel getContenido() {

        return contenido;

    }

    public void alternar() {

        expandido = !expandido;

        scroll.setVisible(expandido);

        actualizarTitulo();

        revalidate();

        if(getParent() != null) {

            getParent().revalidate();
            getParent().repaint();

        }

    }

    public void expandir() {

        if(!expandido) {

            alternar();

        }

    }

    public void contraer() {

        if(expandido) {

            alternar();

        }

    }

    public boolean estaExpandido() {

        return expandido;

    }

    private void actualizarTitulo() {

        lblTitulo.setText(
                (expandido ? "▼ " : "► ")
                + titulo);

    }

}