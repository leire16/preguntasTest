package sql;

import model.Pregunta;
import model.Tema;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GeneradorJSON {

    private static final String ARCHIVO = "docs/texto_extraido.txt";

    // Detecta:
    // 145.- Pregunta...
    private static final Pattern PATRON_PREGUNTA = Pattern.compile("^(\\d+)\\.\\-\\s*(.*)");

    public static void main(String[] args) {

        List<Pregunta> preguntas = leerPreguntas();

        System.out.println();
        System.out.println("==========================================");
        System.out.println("Preguntas obtenidas: " + preguntas.size());
        System.out.println("==========================================");

        // Cambia estos números por los que quieras comprobar
        int[] preguntasMostrar = { 1, 145, 289, 457, 500 };

        for (int numero : preguntasMostrar) {

            Pregunta p = preguntas.get(numero - 1);

            System.out.println("------------------------------------------");
            System.out.println("Pregunta: " + p.getNumeroOriginal());
            System.out.println("Tema: " + p.getTemaId());
            System.out.println();
            System.out.println(p.getEnunciado());
            System.out.println("------------------------------------------");
            System.out.println();
        }

    }

    private static List<Pregunta> leerPreguntas() {

        List<Pregunta> preguntas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {

            String linea;

            while ((linea = br.readLine()) != null) {

                linea = linea.trim();

                if (linea.isEmpty())
                    continue;

                Matcher matcher = PATRON_PREGUNTA.matcher(linea);

                if (!matcher.matches())
                    continue;

                // Número de pregunta
                int numeroPregunta = Integer.parseInt(matcher.group(1));

                // Primera línea del enunciado
                StringBuilder enunciado = new StringBuilder();
                enunciado.append(matcher.group(2).trim());

                // Seguir leyendo hasta llegar a "a)"
                while ((linea = br.readLine()) != null) {

                    linea = linea.trim();

                    if (linea.startsWith("a)"))
                        break;

                    if (!linea.isEmpty()) {
                        enunciado.append(" ");
                        enunciado.append(linea);
                    }
                }

                Tema tema = Temas.obtenerTema(numeroPregunta);

                if (tema == null) {
                    System.out.println("No se encontró tema para la pregunta "
                            + numeroPregunta);
                    continue;
                }

                Pregunta pregunta = new Pregunta(
                        numeroPregunta,
                        tema.getId(),
                        enunciado.toString());

                preguntas.add(pregunta);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return preguntas;

    }

}