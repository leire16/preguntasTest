package model;

public class Respuesta {
    private Integer id;
    // FK de la tabla preguntas
    private Integer preguntaId;
    private String letra;
    private String texto;
    private boolean correcta;

    public Respuesta() {
    }

    public Respuesta(Integer id,
                     Integer preguntaId,
                     String letra,
                     String texto,
                     boolean correcta) {
        this.id = id;
        this.preguntaId = preguntaId;
        this.letra = letra;
        this.texto = texto;
        this.correcta = correcta;
    }
 
    public Respuesta(
                     String letra,
                     String texto,
                     boolean correcta) {
        this.letra = letra;
        this.texto = texto;
        this.correcta = correcta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPreguntaId() {
        return preguntaId;
    }

    public void setPreguntaId(Integer preguntaId) {
        this.preguntaId = preguntaId;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public boolean isCorrecta() {
        return correcta;
    }

    public void setCorrecta(boolean correcta) {
        this.correcta = correcta;
    }

}