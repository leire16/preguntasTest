package model;

public class PreguntaDificil {

    private int preguntaId;
    private int numeroPregunta;

    private int temaId;
    private String nombreTema;

    private int vecesPreguntada;
    private int vecesFallada;

    private double porcentajeAciertos;

    public PreguntaDificil(
            int preguntaId,
            int numeroPregunta,
            int temaId,
            String nombreTema,
            int vecesPreguntada,
            int vecesFallada,
            double porcentajeAciertos) {

        this.preguntaId = preguntaId;
        this.numeroPregunta = numeroPregunta;
        this.temaId = temaId;
        this.nombreTema = nombreTema;
        this.vecesPreguntada = vecesPreguntada;
        this.vecesFallada = vecesFallada;
        this.porcentajeAciertos = porcentajeAciertos;

    }

    public int getPreguntaId() {
        return preguntaId;
    }

    public int getNumeroPregunta() {
        return numeroPregunta;
    }

    public int getTemaId() {
        return temaId;
    }

    public String getNombreTema() {
        return nombreTema;
    }

    public int getVecesPreguntada() {
        return vecesPreguntada;
    }

    public int getVecesFallada() {
        return vecesFallada;
    }

    public int getVecesAcertada() {
        return vecesPreguntada - vecesFallada;
    }

    public double getPorcentajeAciertos() {
        return porcentajeAciertos;
    }

}