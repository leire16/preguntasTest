package model;

public class Respuesta {

    private String letra;
    private String texto;
    private boolean correcta;

    public Respuesta() {
    }

    public Respuesta(String letra, String texto, boolean correcta) {
        this.letra = letra;
        this.texto = texto;
        this.correcta = correcta;
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