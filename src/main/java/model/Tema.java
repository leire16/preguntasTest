package model;

public class Tema {
    private final int id;
    private final String nombre;
    private final int preguntaInicio;
    private final int preguntaFin;

    public Tema(int id, String nombre, int preguntaInicio, int preguntaFin) {
        this.id = id;
        this.nombre = nombre;
        this.preguntaInicio = preguntaInicio;
        this.preguntaFin = preguntaFin;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPreguntaInicio() {
        return preguntaInicio;
    }

    public int getPreguntaFin() {
        return preguntaFin;
    }

    public boolean contienePregunta(int numeroPregunta) {
        return numeroPregunta >= preguntaInicio &&
                numeroPregunta <= preguntaFin;
    }
}
