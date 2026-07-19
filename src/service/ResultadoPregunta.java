package service;

import model.Pregunta;
import model.Respuesta;

public class ResultadoPregunta {

    private Pregunta pregunta;

    private Respuesta respuestaUsuario;

    private Respuesta respuestaCorrecta;

    private boolean correcta;

    public ResultadoPregunta() {
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public Respuesta getRespuestaUsuario() {
        return respuestaUsuario;
    }

    public void setRespuestaUsuario(Respuesta respuestaUsuario) {
        this.respuestaUsuario = respuestaUsuario;
    }

    public Respuesta getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public void setRespuestaCorrecta(Respuesta respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public boolean isCorrecta() {
        return correcta;
    }

    public void setCorrecta(boolean correcta) {
        this.correcta = correcta;
    }

}