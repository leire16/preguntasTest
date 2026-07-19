package service;

import java.util.ArrayList;
import java.util.List;

public class ResultadoTest {

    private int aciertos;

    private int fallos;

    private int preguntasSinResponder;

    private int duracionSegundos;

    private List<ResultadoPregunta> preguntas =
            new ArrayList<>();

    public ResultadoTest() {
    }

    public int getAciertos() {
        return aciertos;
    }

    public void setAciertos(int aciertos) {
        this.aciertos = aciertos;
    }

    public int getFallos() {
        return fallos;
    }

    public void setFallos(int fallos) {
        this.fallos = fallos;
    }

    public int getPreguntasSinResponder() {
        return preguntasSinResponder;
    }

    public void setPreguntasSinResponder(int preguntasSinResponder) {
        this.preguntasSinResponder = preguntasSinResponder;
    }

    public int getDuracionSegundos() {
        return duracionSegundos;
    }

    public void setDuracionSegundos(int duracionSegundos) {
        this.duracionSegundos = duracionSegundos;
    }

    public List<ResultadoPregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<ResultadoPregunta> preguntas) {
        this.preguntas = preguntas;
    }
  
    public double getNota() {

        if (aciertos + fallos == 0) {
            return 0;
        }

        return Math.round((aciertos * 100.0 / (aciertos + fallos)) * 100.0) / 100.0;

    }

}