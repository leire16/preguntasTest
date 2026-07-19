package model;

import java.util.ArrayList;
import java.util.List;

public class Pregunta {
    private int id;
    private int numeroOriginal;
    private int temaId;
    private String enunciado;

    private List<Respuesta> respuestas = new ArrayList<>();

    public Pregunta() {
    }

    public Pregunta(int id, int numeroOriginal, int temaId, String enunciado) {
        this.id = id;
        this.numeroOriginal = numeroOriginal;
        this.temaId = temaId;
        this.enunciado = enunciado;
    }
 
    public Pregunta(int numeroOriginal, int temaId, String enunciado) {
        this.numeroOriginal = numeroOriginal;
        this.temaId = temaId;
        this.enunciado = enunciado;
    }

    public int getNumeroOriginal() {
        return numeroOriginal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNumeroOriginal(int numeroOriginal) {
        this.numeroOriginal = numeroOriginal;
    }

    public int getTemaId() {
        return temaId;
    }

    public void setTemaId(int temaId) {
        this.temaId = temaId;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public List<Respuesta> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<Respuesta> respuestas) {
        this.respuestas = respuestas;
    }

    public void addRespuesta(Respuesta respuesta) {
        respuestas.add(respuesta);
    }

}