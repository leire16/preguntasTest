package model;

import java.util.ArrayList;
import java.util.List;

public class Estadisticas {

    private int testsRealizados;
    private int preguntasRespondidas;
    private int preguntasDistintas;
    private double porcentajeAciertos;

    private List<RendimientoTema> rendimientoTemas;

    public Estadisticas() {

        rendimientoTemas =
                new ArrayList<>();

    }

    public int getTestsRealizados() {
        return testsRealizados;
    }

    public void setTestsRealizados(
            int testsRealizados) {

        this.testsRealizados = testsRealizados;
    }

    public int getPreguntasRespondidas() {
        return preguntasRespondidas;
    }

    public void setPreguntasRespondidas(
            int preguntasRespondidas) {

        this.preguntasRespondidas = preguntasRespondidas;
    }

    public int getPreguntasDistintas() {
        return preguntasDistintas;
    }

    public void setPreguntasDistintas(
            int preguntasDistintas) {

        this.preguntasDistintas = preguntasDistintas;
    }

    public double getPorcentajeAciertos() {
        return porcentajeAciertos;
    }

    public void setPorcentajeAciertos(
            double porcentajeAciertos) {

        this.porcentajeAciertos = porcentajeAciertos;
    }

    public List<RendimientoTema> getRendimientoTemas() {

        return rendimientoTemas;
    }

    public void setRendimientoTemas(
            List<RendimientoTema> rendimientoTemas) {

        this.rendimientoTemas = rendimientoTemas;
    }

    private List<PreguntaDificil> preguntasMasFalladas;

    public List<PreguntaDificil> getPreguntasMasFalladas() {
        return preguntasMasFalladas;
    }

    public void setPreguntasMasFalladas(
            List<PreguntaDificil> preguntasMasFalladas) {

        this.preguntasMasFalladas = preguntasMasFalladas;

    }

}