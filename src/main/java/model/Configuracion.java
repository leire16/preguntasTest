package model;

import java.util.List;

import util.Constantes;

public class Configuracion {

    private TipoTest tipoTest;
    private List<Integer> temas;
    private int numeroPreguntas;

    public Configuracion() {
    }

    public Configuracion(TipoTest tipoTest, List<Integer> temas, int numeroPreguntas) {
        this.tipoTest = tipoTest;
        this.temas = temas;
        this.numeroPreguntas = numeroPreguntas;
    }

    public TipoTest getTipoTest() {
        return tipoTest;
    }

    public void setTipoTest(TipoTest tipoTest) {
        this.tipoTest = tipoTest;
    }

    public List<Integer> getTemas() {
        return temas;
    }

    public void setTemas(List<Integer> temas) {
        this.temas = temas;
    }

    public int getNumeroPreguntas() {
        return numeroPreguntas;
    }

    public void setNumeroPreguntas(int numeroPreguntas) {
        this.numeroPreguntas = numeroPreguntas;
    }

    public int getNumeroPreguntasReales() {

        if (numeroPreguntas != Constantes.TODAS_LAS_PREGUNTAS) {
            return numeroPreguntas;
        }

        // Si son todas habrá que calcularlo
        return calcularTotalPreguntas();
    }

    private int calcularTotalPreguntas() {

        return Constantes.TOTAL_PREGUNTAS_BD;

    }

    @Override
    public String toString() {
        return "Configuracion{" +
                "tipoTest=" + tipoTest +
                ", temas=" + temas +
                ", numeroPreguntas=" + numeroPreguntas +
                '}';
    }

}