package ui;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.Timer;

import model.Configuracion;
import model.Pregunta;
import model.Respuesta;
import service.CorreccionService;
import service.GuardadoResultadoService;
import service.ResultadoTest;
import service.TestService;
import ui.componentesExamen.PanelCabeceraExamen;
import ui.componentesExamen.PanelNavegacion;
import ui.componentesExamen.PanelPregunta;
import ui.componentesExamen.PanelRespuestas;

public class PantallaExamen extends JPanel {

    private VentanaPrincipal ventana;

    private PanelCabeceraExamen panelCabecera;
    private PanelPregunta panelPregunta;
    private PanelRespuestas panelRespuestas;
    private JScrollPane scrollRespuestas;
    private PanelNavegacion panelNavegacion;

    private Timer cronometro;
    private int segundosTranscurridos;

    // ==========================================
    // LÓGICA DEL TEST
    // ==========================================

    private TestService testService;

    private Configuracion configuracionActual;

    private List<Pregunta> preguntas;

    private Integer[] respuestasSeleccionadas;

    private int indicePreguntaActual;

    // ==========================================

    public PantallaExamen(VentanaPrincipal ventana) {

        this.ventana = ventana;

        setLayout(new BorderLayout(15, 15));

        setBorder(BorderFactory.createEmptyBorder(
                20, 20, 20, 20));

        inicializarComponentes();

        colocarComponentes();

        configurarEventos();

    }

    private void inicializarComponentes() {

        panelCabecera = new PanelCabeceraExamen();

        panelPregunta = new PanelPregunta();

        panelRespuestas = new PanelRespuestas();

        scrollRespuestas = new JScrollPane(panelRespuestas);

        scrollRespuestas.setBorder(null);

        scrollRespuestas.setHorizontalScrollBarPolicy(
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        scrollRespuestas.getVerticalScrollBar().setUnitIncrement(18);

        panelNavegacion = new PanelNavegacion();

        testService = new TestService();

    }

    private void colocarComponentes() {

        add(panelCabecera, BorderLayout.NORTH);

        JPanel centro = new JPanel(new BorderLayout(20, 20));

        centro.add(panelPregunta, BorderLayout.NORTH);

        centro.add(scrollRespuestas, BorderLayout.CENTER);

        add(centro, BorderLayout.CENTER);

        add(panelNavegacion, BorderLayout.SOUTH);

    }

       private void configurarEventos() {

        panelNavegacion.getBtnAnterior().addActionListener(e ->
                mostrarPreguntaAnterior());

        panelNavegacion.getBtnSiguiente().addActionListener(e ->
                pulsarBotonSiguiente());
        panelNavegacion.getBtnSalir().addActionListener(e ->
                confirmarSalirAlMenu());

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

        this.configuracionActual = configuracion;

        panelCabecera.inicializar(configuracion);

        preguntas = testService.generarTest(configuracion);

        respuestasSeleccionadas = new Integer[preguntas.size()];

        indicePreguntaActual = 0;

        // El listener se registra SOLO cuando ya existe el examen
        panelRespuestas.setOnRespuestaSeleccionada(() -> {

            guardarRespuestaActual();

            panelCabecera.actualizarProgreso(
                    contarRespondidas(),
                    preguntas.size());

            panelNavegacion.actualizarEstado(
                    indicePreguntaActual + 1,
                    preguntas.size(),
                    todasRespondidas());

        });

        iniciarCronometro();

        mostrarPreguntaActual();

    }

    private void mostrarPreguntaActual() {

        Pregunta pregunta = preguntas.get(indicePreguntaActual);

        panelPregunta.mostrarPregunta(
        pregunta,
        indicePreguntaActual + 1);

        panelRespuestas.mostrarRespuestas(
                pregunta.getRespuestas());

        restaurarRespuestaActual();

        scrollRespuestas.getVerticalScrollBar().setValue(0);

        panelCabecera.actualizarPregunta(
                indicePreguntaActual + 1,
                preguntas.size());

        panelCabecera.actualizarProgreso(
                contarRespondidas(),
                preguntas.size());

        panelNavegacion.actualizarEstado(
                indicePreguntaActual + 1,
                preguntas.size(),
                todasRespondidas());

    }

    private void guardarRespuestaActual() {

        if (respuestasSeleccionadas == null) {
            return;
        }

        Respuesta respuesta =
                panelRespuestas.getRespuestaSeleccionada();

        if (respuesta == null) {
            return;
        }

        respuestasSeleccionadas[indicePreguntaActual] =
                respuesta.getId();
    }

    private void restaurarRespuestaActual() {

        panelRespuestas.limpiarSeleccion();

        if (respuestasSeleccionadas == null) {
            return;
        }

        Integer respuestaGuardada =
                respuestasSeleccionadas[indicePreguntaActual];

        if (respuestaGuardada == null) {
            return;
        }

        for (int i = 0; i < 4; i++) {

            if (!panelRespuestas.getRespuesta(i).isVisible()) {
                continue;
            }

            Respuesta respuesta =
                    panelRespuestas.getRespuesta(i).getRespuesta();

            if (respuesta.getId().equals(respuestaGuardada)) {

                panelRespuestas.getRespuesta(i).setSelected(true);

                break;

            }

        }

    }

    private void mostrarPreguntaAnterior() {

        guardarRespuestaActual();

        if (indicePreguntaActual > 0) {

            indicePreguntaActual--;

            mostrarPreguntaActual();

        }

    }

    private void mostrarPreguntaSiguiente() {

        guardarRespuestaActual();

        if (indicePreguntaActual < preguntas.size() - 1) {

            indicePreguntaActual++;

            mostrarPreguntaActual();

        }

    }

    private void pulsarBotonSiguiente() {

        guardarRespuestaActual();

        if (respuestasSeleccionadas[indicePreguntaActual] == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debes responder la pregunta antes de continuar.",
                    "Pregunta sin responder",
                    JOptionPane.WARNING_MESSAGE);

            return;

        }

        if (indicePreguntaActual == preguntas.size() - 1) {

            finalizarExamen();

        } else {

            mostrarPreguntaSiguiente();

        }

    }

    private int contarRespondidas() {

        if (respuestasSeleccionadas == null) {
            return 0;
        }

        int total = 0;

        for (Integer respuesta : respuestasSeleccionadas) {

            if (respuesta != null) {
                total++;
            }

        }

        return total;

    }

    private boolean todasRespondidas() {

        if (respuestasSeleccionadas == null) {
            return false;
        }

        return contarRespondidas() == preguntas.size();

    }

    private void finalizarExamen() {

        detenerCronometro();

        guardarRespuestaActual();

        CorreccionService correccionService = new CorreccionService();

        ResultadoTest resultado =
        correccionService.finalizarExamen(
                preguntas,
                respuestasSeleccionadas,
                segundosTranscurridos);

        GuardadoResultadoService guardadoResultadoService = new GuardadoResultadoService();

        int sesionId = guardadoResultadoService.guardarResultado(
                configuracionActual,
                preguntas.size(),
                resultado);

        if (sesionId == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "No se ha podido guardar la sesión del test en la base de datos.",
                    "Error al guardar",
                    JOptionPane.ERROR_MESSAGE);

        }

        ventana.mostrarResultados(resultado);
    }

    private void confirmarSalirAlMenu() {

        int opcion = JOptionPane.showConfirmDialog(
                this,
                "Si sales ahora perderás el progreso del test actual.\n¿Seguro que quieres salir?",
                "Salir del examen",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (opcion == JOptionPane.YES_OPTION) {

            detenerCronometro();

            ventana.mostrarMenu();

        }

    }

}