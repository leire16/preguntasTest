package service;

import java.util.List;

import model.Pregunta;
import model.Respuesta;

public class CorreccionService {

    public ResultadoTest finalizarExamen(
            List<Pregunta> preguntas,
            Integer[] respuestasSeleccionadas,
            int duracionSegundos) {

        ResultadoTest resultado = new ResultadoTest();

        resultado.setDuracionSegundos(duracionSegundos);

        int aciertos = 0;
        int fallos = 0;
        int sinResponder = 0;

        for (int i = 0; i < preguntas.size(); i++) {

            Pregunta pregunta = preguntas.get(i);

            ResultadoPregunta resultadoPregunta =
                    corregirPregunta(
                            pregunta,
                            respuestasSeleccionadas[i]);

            resultado.getPreguntas().add(resultadoPregunta);

            if (resultadoPregunta.getRespuestaUsuario() == null) {

                sinResponder++;

            } else if (resultadoPregunta.isCorrecta()) {

                aciertos++;
            } else {

                fallos++;
            }
           
        }

        resultado.setAciertos(aciertos);
        resultado.setFallos(fallos);
        resultado.setPreguntasSinResponder(sinResponder);
        
        return resultado;

    }

    // =========================================================

    private ResultadoPregunta corregirPregunta(
            Pregunta pregunta,
            Integer idRespuestaUsuario) {

        ResultadoPregunta resultado = new ResultadoPregunta();

        resultado.setPregunta(pregunta);

        Respuesta respuestaCorrecta = null;
        Respuesta respuestaUsuario = null;

        for (Respuesta respuesta : pregunta.getRespuestas()) {

            if (respuesta.isCorrecta()) {
                respuestaCorrecta = respuesta;
            }

            if (idRespuestaUsuario != null
                    && respuesta.getId().equals(idRespuestaUsuario)) {

                respuestaUsuario = respuesta;

            }

        }

        resultado.setRespuestaCorrecta(respuestaCorrecta);

        resultado.setRespuestaUsuario(respuestaUsuario);

        resultado.setCorrecta(
                respuestaUsuario != null
                        && respuestaCorrecta != null
                        && respuestaUsuario.getId().equals(respuestaCorrecta.getId()));

        return resultado;

    }

}