package service;

import dao.PreguntaDao;
import model.Configuracion;
import model.Pregunta;

import java.util.Collections;
import java.util.List;

public class TestService {

    private final PreguntaDao preguntaDao;

    public TestService() {
        preguntaDao = new PreguntaDao();
    }

    /**
     * Genera un examen según la configuración elegida.
     */
    public List<Pregunta> generarTest(Configuracion configuracion) {

        List<Pregunta> preguntas;

        switch (configuracion.getTipoTest()) {

            case TEMA:
                preguntas = preguntaDao.obtenerPorTemas(
                        configuracion.getTemas());
                break;

            case COMPLETO:
                // COMPLETO = todas las preguntas de los temas seleccionados
                preguntas = preguntaDao.obtenerPorTemas(
                        configuracion.getTemas());
                break;

            case REPASO:
                // REPASO = preguntas falladas de los temas seleccionados
                preguntas = preguntaDao.obtenerFalladas(
                        configuracion.getTemas());
                break;

            default:
                throw new IllegalArgumentException(
                        "Tipo de test no soportado.");
        }

        mezclarPreguntas(preguntas);

        limitarNumeroPreguntas(
                preguntas,
                configuracion.getNumeroPreguntasReales());

        mezclarRespuestas(preguntas);

        return preguntas;
    }

    /**
     * Mezcla las preguntas.
     */
    private void mezclarPreguntas(List<Pregunta> preguntas) {
        Collections.shuffle(preguntas);
    }

    /**
     * Deja únicamente las preguntas necesarias.
     */
    private void limitarNumeroPreguntas(
            List<Pregunta> preguntas,
            int numeroPreguntas) {

        if (preguntas.size() > numeroPreguntas) {
            preguntas.subList(
                    numeroPreguntas,
                    preguntas.size()).clear();
        }
    }

    /**
     * Mezcla las respuestas de cada pregunta.
     */
    private void mezclarRespuestas(List<Pregunta> preguntas) {

        for (Pregunta pregunta : preguntas) {
            Collections.shuffle(pregunta.getRespuestas());
        }
    }
}