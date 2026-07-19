package service;

import dao.EstadoPreguntaDao;
import dao.RespuestaUsuarioDao;
import dao.SesionTestDao;
import model.Configuracion;
import model.RespuestaUsuario;
import model.SesionTest;

import java.time.LocalDateTime;

/**
 * Se encarga de persistir en BD el resultado de un test ya corregido:
 * 1) inserta la cabecera en sesion_test,
 * 2) inserta el detalle de cada pregunta en respuestas_usuario,
 * 3) actualiza las estadísticas acumuladas en estado_pregunta.
 */
public class GuardadoResultadoService {

    private final SesionTestDao sesionTestDao = new SesionTestDao();
    private final RespuestaUsuarioDao respuestaUsuarioDao = new RespuestaUsuarioDao();
    private final EstadoPreguntaDao estadoPreguntaDao = new EstadoPreguntaDao();

    /**
     * Guarda la sesión de test completa.
     *
     * @return el id de la sesión guardada, o -1 si no se ha podido guardar la cabecera
     *         (en ese caso no se guarda ningún detalle).
     */
    public int guardarResultado(Configuracion configuracion,
                                 int numeroPreguntas,
                                 ResultadoTest resultado) {

        SesionTest sesion = construirSesion(configuracion, numeroPreguntas, resultado);

        int sesionId = sesionTestDao.insertar(sesion);

        if (sesionId == -1) {
            return -1;
        }

        for (ResultadoPregunta rp : resultado.getPreguntas()) {
            guardarDetallePregunta(sesionId, rp);
        }

        return sesionId;

    }

    private SesionTest construirSesion(Configuracion configuracion,
                                        int numeroPreguntas,
                                        ResultadoTest resultado) {

        SesionTest sesion = new SesionTest();

        sesion.setFecha(LocalDateTime.now());
        sesion.setTipoTest(configuracion.getTipoTest());
        sesion.setTemas(configuracion.getTemas());
        sesion.setNumeroPreguntas(numeroPreguntas);
        sesion.setNumeroAciertos(resultado.getAciertos());
        sesion.setNumeroFallos(resultado.getFallos());
        sesion.setDuracionSegundos(resultado.getDuracionSegundos());

        return sesion;

    }

    private void guardarDetallePregunta(int sesionId, ResultadoPregunta rp) {

        int preguntaId = rp.getPregunta().getId();
        boolean correcta = rp.isCorrecta();

        RespuestaUsuario respuestaUsuario = new RespuestaUsuario();

        respuestaUsuario.setSesionId(sesionId);
        respuestaUsuario.setPreguntaId(preguntaId);
        respuestaUsuario.setRespuestaId(rp.getRespuestaUsuario().getId());
        respuestaUsuario.setRespuestaCorrectaId(rp.getRespuestaCorrecta().getId());
        respuestaUsuario.setCorrecta(correcta);

        respuestaUsuarioDao.insertar(respuestaUsuario);

        estadoPreguntaDao.actualizarEstado(preguntaId, correcta);

    }

}
