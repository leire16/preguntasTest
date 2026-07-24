package model;

public class RendimientoTema {

    private int temaId;
    private String nombre;
    private double porcentaje;

    public RendimientoTema(
            int temaId,
            String nombre,
            double porcentaje) {

        this.temaId = temaId;
        this.nombre = nombre;
        this.porcentaje = porcentaje;
    }

    public int getTemaId() {
        return temaId;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

}