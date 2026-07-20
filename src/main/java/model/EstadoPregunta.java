package model;

import java.time.LocalDate;

public class EstadoPregunta {

    private int preguntaId;
    private int vecesPreguntada;
    private int vecesFallada;
    private LocalDate ultimaFecha;

    public EstadoPregunta() {
    }

    public EstadoPregunta(int preguntaId,
                          int vecesPreguntada,
                          int vecesFallada,
                          LocalDate ultimaFecha) {

        this.preguntaId = preguntaId;
        this.vecesPreguntada = vecesPreguntada;
        this.vecesFallada = vecesFallada;
        this.ultimaFecha = ultimaFecha;

    }

    public int getPreguntaId() {
        return preguntaId;
    }

    public void setPreguntaId(int preguntaId) {
        this.preguntaId = preguntaId;
    }

    public int getVecesPreguntada() {
        return vecesPreguntada;
    }

    public void setVecesPreguntada(int vecesPreguntada) {
        this.vecesPreguntada = vecesPreguntada;
    }

    public int getVecesFallada() {
        return vecesFallada;
    }

    public void setVecesFallada(int vecesFallada) {
        this.vecesFallada = vecesFallada;
    }

    public LocalDate getUltimaFecha() {
        return ultimaFecha;
    }

    public void setUltimaFecha(LocalDate ultimaFecha) {
        this.ultimaFecha = ultimaFecha;
    }

}