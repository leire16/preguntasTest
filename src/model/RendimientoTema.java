package model;

public class RendimientoTema {

    private int temaId;
    private String nombre;

    private int respondidas;
    private int acertadas;
    private int falladas;

    private double porcentaje;

    public RendimientoTema(
            int temaId,
            String nombre,
            int respondidas,
            int acertadas,
            int falladas,
            double porcentaje) {

        this.temaId = temaId;
        this.nombre = nombre;
        this.respondidas = respondidas;
        this.acertadas = acertadas;
        this.falladas = falladas;
        this.porcentaje = porcentaje;
    }

    public int getTemaId() {
        return temaId;
    }

    public String getNombre() {
        return nombre;
    }

    public int getRespondidas() {
        return respondidas;
    }

    public int getAcertadas() {
        return acertadas;
    }

    public int getFalladas() {
        return falladas;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

}