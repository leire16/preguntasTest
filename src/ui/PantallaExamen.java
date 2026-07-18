package ui;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.Configuracion;
import ui.componentesExamen.PanelCabeceraExamen;
import ui.componentesExamen.PanelNavegacion;
import ui.componentesExamen.PanelPregunta;
import ui.componentesExamen.PanelRespuestas;

public class PantallaExamen extends JPanel {

    private VentanaPrincipal ventana;

    private PanelCabeceraExamen panelCabecera;
    private PanelPregunta panelPregunta;
    private PanelRespuestas panelRespuestas;
    private PanelNavegacion panelNavegacion;

    private Timer cronometro;
    private int segundosTranscurridos;

    public PantallaExamen(VentanaPrincipal ventana) {

        this.ventana = ventana;

        setLayout(new BorderLayout(15, 15));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        inicializarComponentes();
        colocarComponentes();
        configurarEventos();

    }

    private void inicializarComponentes() {

        panelCabecera = new PanelCabeceraExamen();
        panelPregunta = new PanelPregunta();
        panelRespuestas = new PanelRespuestas();
        panelNavegacion = new PanelNavegacion();

    }

    private void colocarComponentes() {

        add(panelCabecera, BorderLayout.NORTH);

        JPanel centro = new JPanel(new BorderLayout(20, 20));

        centro.add(panelPregunta, BorderLayout.NORTH);
        centro.add(panelRespuestas, BorderLayout.CENTER);

        add(centro, BorderLayout.CENTER);

        add(panelNavegacion, BorderLayout.SOUTH);

    }

    private void configurarEventos() {

        panelNavegacion.getBtnAnterior().addActionListener(e -> {

            mostrarPreguntaAnterior();

        });

        panelNavegacion.getBtnSiguiente().addActionListener(e -> {

            pulsarBotonSiguiente();

        });

    }

    // ==================================================
    // CRONÓMETRO
    // ==================================================

    private void iniciarCronometro() {

        detenerCronometro();

        segundosTranscurridos = 0;

        panelCabecera.actualizarTiempo(
                formatearTiempo(segundosTranscurridos));

        cronometro = new Timer(1000, e -> {

            segundosTranscurridos++;

            panelCabecera.actualizarTiempo(
                    formatearTiempo(segundosTranscurridos));

        });

        cronometro.start();

    }

    private void detenerCronometro() {

        if (cronometro != null) {
            cronometro.stop();
        }

    }

    private String formatearTiempo(int segundos) {

        int horas = segundos / 3600;
        int minutos = (segundos % 3600) / 60;
        int segundosRestantes = segundos % 60;

        return String.format("%02d:%02d:%02d",
                horas,
                minutos,
                segundosRestantes);

    }

    // ==================================================
    // EXAMEN
    // ==================================================

    public void iniciarExamen(Configuracion configuracion) {

        panelCabecera.inicializar(configuracion);

        iniciarCronometro();

        // TODO
        // cargar preguntas
        // mostrar primera pregunta

    }

    private void mostrarPreguntaAnterior() {

        // TODO

    }

    private void mostrarPreguntaSiguiente() {

        // TODO

    }

    private void pulsarBotonSiguiente() {

        // TODO
        // Si es la última -> finalizarExamen()
        // Si no -> mostrarPreguntaSiguiente()

    }

    private void finalizarExamen() {

        detenerCronometro();

        // TODO
        // Calcular nota
        // Mostrar resultados

    }

}