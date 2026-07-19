package model;

public class RespuestaUsuario {

    private int id;

    private int sesionId;

    private int preguntaId;

    private int respuestaId;

    private int respuestaCorrectaId;

    private boolean correcta;

    public RespuestaUsuario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSesionId() {
        return sesionId;
    }

    public void setSesionId(int sesionId) {
        this.sesionId = sesionId;
    }

    public int getPreguntaId() {
        return preguntaId;
    }

    public void setPreguntaId(int preguntaId) {
        this.preguntaId = preguntaId;
    }

    public int getRespuestaId() {
        return respuestaId;
    }

    public void setRespuestaId(int respuestaId) {
        this.respuestaId = respuestaId;
    }

    public int getRespuestaCorrectaId() {
        return respuestaCorrectaId;
    }

    public void setRespuestaCorrectaId(int respuestaCorrectaId) {
        this.respuestaCorrectaId = respuestaCorrectaId;
    }

    public boolean isCorrecta() {
        return correcta;
    }

    public void setCorrecta(boolean correcta) {
        this.correcta = correcta;
    }

}