package ui.componentesResultados;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Arc2D;

import javax.swing.JPanel;

/**
 * Círculo de progreso tipo "donut" que muestra un porcentaje,
 * con el arco coloreado según el resultado (verde/amarillo/rojo)
 * y la cifra en el centro.
 */
public class PanelCirculoProgreso extends JPanel {

    private double porcentaje; // 0-100
    private static final int GROSOR_ANILLO = 9;

    // Umbrales de nota, ajustables aquí
    private static final double UMBRAL_APROBADO = 70; // >= verde
    private static final double UMBRAL_REGULAR = 50;  // >= amarillo, si no rojo

    private static final Color COLOR_APROBADO = new Color(46, 204, 113);  // verde
    private static final Color COLOR_REGULAR = new Color(241, 196, 15);   // amarillo
    private static final Color COLOR_SUSPENSO = new Color(231, 76, 60);   // rojo

    private static final Color COLOR_FONDO_ANILLO = new Color(230, 230, 230);

    public PanelCirculoProgreso() {
        setPreferredSize(new Dimension(70, 70));
        setOpaque(false);
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
        repaint();
    }

    private Color obtenerColorSegunNota() {

        if (porcentaje >= UMBRAL_APROBADO) {
            return COLOR_APROBADO;
        } else if (porcentaje >= UMBRAL_REGULAR) {
            return COLOR_REGULAR;
        } else {
            return COLOR_SUSPENSO;
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        int diametro = Math.min(getWidth(), getHeight()) - GROSOR_ANILLO;
        int x = (getWidth() - diametro) / 2;
        int y = (getHeight() - diametro) / 2;

        // Anillo de fondo (gris), completo
        g2.setStroke(new java.awt.BasicStroke(
                GROSOR_ANILLO, java.awt.BasicStroke.CAP_ROUND, java.awt.BasicStroke.JOIN_ROUND));
        g2.setColor(COLOR_FONDO_ANILLO);
        g2.draw(new Arc2D.Double(x, y, diametro, diametro, 0, 360, Arc2D.OPEN));

        // Arco de progreso, coloreado según la nota
        g2.setColor(obtenerColorSegunNota());
        double angulo = 360 * (porcentaje / 100.0);
        g2.draw(new Arc2D.Double(x, y, diametro, diametro, 90, -angulo, Arc2D.OPEN));

        // Texto del porcentaje, centrado
        String texto = String.format("%.0f%%", porcentaje);
        g2.setFont(new Font("Arial", Font.BOLD, 14));
        g2.setColor(Color.DARK_GRAY);

        java.awt.FontMetrics fm = g2.getFontMetrics();
        int anchoTexto = fm.stringWidth(texto);
        int altoTexto = fm.getAscent();

        g2.drawString(texto,
                (getWidth() - anchoTexto) / 2,
                (getHeight() + altoTexto) / 2 - 4);

        g2.dispose();
    }

}