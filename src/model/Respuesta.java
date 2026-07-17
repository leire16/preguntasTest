package model;

public class Respuesta {

    private String letra;
    private String texto;
    private boolean esCorrecta;

    public Respuesta() {
    }

    public Respuesta(String letra, String texto, boolean esCorrecta) {
        this.letra = letra;
        this.texto = texto;
        this.esCorrecta = esCorrecta;
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

    public boolean isEsCorrecta() {
        return esCorrecta;
    }

    public void setEsCorrecta(boolean esCorrecta) {
        this.esCorrecta = esCorrecta;
    }

}