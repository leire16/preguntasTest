package sql;

import java.util.ArrayList;
import java.util.List;

import model.Tema;
import util.Constantes;

public class Temas {

    // Solo los rangos [inicio, fin] de cada tema, en el mismo orden que Constantes.TEMAS
    private static final int[][] RANGOS = {
            { 1, 24 },
            { 25, 72 },
            { 73, 96 },
            { 97, 120 },
            { 121, 144 },
            { 145, 168 },
            { 169, 192 },
            { 193, 216 },
            { 217, 240 },
            { 241, 264 },
            { 265, 288 },
            { 289, 312 },
            { 313, 336 },
            { 337, 360 },
            { 361, 384 },
            { 385, 408 },
            { 409, 432 },
            { 433, 456 },
            { 457, 480 },
            { 481, 500 },
            { 501, 700 },
    };

    public static final List<Tema> LISTA = construirLista();

    private static List<Tema> construirLista() {
        List<Tema> lista = new ArrayList<>();
        for (int i = 0; i < Constantes.TEMAS.length; i++) {
            lista.add(new Tema(i + 1, Constantes.TEMAS[i], RANGOS[i][0], RANGOS[i][1]));
        }
        return List.copyOf(lista);
    }

    public static Tema obtenerTema(int numeroPregunta) {
        for (Tema tema : LISTA) {
            if (tema.contienePregunta(numeroPregunta)) {
                return tema;
            }
        }
        return null;
    }

}