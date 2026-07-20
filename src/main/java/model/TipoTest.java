package model;

import util.Constantes;

public enum TipoTest {

    TEMA(Constantes.TEXTO_TEST_TEMAS),
    COMPLETO(Constantes.TEXTO_TEST_COMPLETO),
    REPASO(Constantes.TEXTO_TEST_FALLADAS);

    private final String descripcion;

    TipoTest(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

}