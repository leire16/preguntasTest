package sql;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.Pregunta;
import model.Respuesta;
import model.Tema;

public class GeneradorJSON {

    private static final String ARCHIVO = "docs/texto_extraido.txt";
    private static final String ARCHIVO_RESPUESTAS = "docs/respuestas_correctas.txt";
    private static final String SALIDA_JSON = "docs/preguntas.json";

    public static void main(String[] args) {

        try {

            List<String> lineas = Files.readAllLines(Path.of(ARCHIVO));

            List<Pregunta> preguntas = parsearPreguntas(lineas);

            System.out.println();
            System.out.println("======================================");
            System.out.println("Preguntas encontradas: " + preguntas.size());
            System.out.println("======================================");

            // -------------------------------------------------------
            // Aplicar las respuestas correctas reales
            // -------------------------------------------------------

            Map<Integer, String> correctas = leerRespuestasCorrectas(ARCHIVO_RESPUESTAS);

            System.out.println("Respuestas correctas leídas: " + correctas.size());

            aplicarRespuestasCorrectas(preguntas, correctas);

            for (int i = 0; i < Math.min(5, preguntas.size()); i++) {

                Pregunta p = preguntas.get(i);

                System.out.println("--------------------------------------");
                System.out.println("Pregunta: " + p.getNumeroOriginal());
                System.out.println("Tema: " + p.getTemaId());
                System.out.println();
                System.out.println(p.getEnunciado());
                System.out.println();

                for (Respuesta r : p.getRespuestas()) {
                    System.out.println("  " + r.getLetra() + ") " + r.getTexto()
                            + (r.isCorrecta() ? "   <-- CORRECTA" : ""));
                }

                System.out.println("--------------------------------------");
            }

            // -------------------------------------------------------
            // Generar el JSON con Jackson
            // -------------------------------------------------------

            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(SALIDA_JSON), preguntas);

            System.out.println();
            System.out.println("JSON generado en " + SALIDA_JSON);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Lee un fichero de texto con el formato "1 c; 2 b; 3 b; ..." (admite
     * también "193c" sin espacio) y devuelve un mapa numeroPregunta -> letra.
     */
    private static Map<Integer, String> leerRespuestasCorrectas(String ruta) throws Exception {

        String contenido = Files.readString(Path.of(ruta));

        Map<Integer, String> mapa = new HashMap<>();

        // Cada entrada: uno o más dígitos, espacio opcional, una letra a-d
        Pattern patron = Pattern.compile("(\\d+)\\s*([a-dA-D])");
        Matcher matcher = patron.matcher(contenido);

        while (matcher.find()) {
            int numero = Integer.parseInt(matcher.group(1));
            String letra = matcher.group(2).toLowerCase();
            mapa.put(numero, letra);
        }

        return mapa;
    }

    /**
     * Recorre las preguntas y marca como correcta la respuesta indicada en el
     * mapa, desmarcando el resto.
     */
    private static void aplicarRespuestasCorrectas(List<Pregunta> preguntas,
            Map<Integer, String> correctas) {

        for (Pregunta p : preguntas) {

            String letraCorrecta = correctas.get(p.getNumeroOriginal());

            if (letraCorrecta == null) {
                System.out.println("Aviso: no hay respuesta correcta registrada para la pregunta "
                        + p.getNumeroOriginal());
                continue;
            }

            boolean encontrada = false;

            for (Respuesta r : p.getRespuestas()) {
                boolean esEsta = r.getLetra().equalsIgnoreCase(letraCorrecta);
                r.setCorrecta(esEsta);
                if (esEsta) {
                    encontrada = true;
                }
            }

            if (!encontrada) {
                System.out.println("Aviso: la letra '" + letraCorrecta
                        + "' no coincide con ninguna respuesta de la pregunta "
                        + p.getNumeroOriginal());
            }
        }
    }

    /**
     * Lee todas las preguntas del documento, incluyendo sus respuestas.
     */
    private static List<Pregunta> parsearPreguntas(List<String> lineas) {

        List<Pregunta> preguntas = new ArrayList<>();

        int i = 0;

        while (i < lineas.size()) {

            String linea = lineas.get(i).trim();

            if (!esInicioPregunta(linea)) {
                i++;
                continue;
            }

            int punto = linea.indexOf('.');
            int numeroPregunta = Integer.parseInt(linea.substring(0, punto));

            StringBuilder enunciado = new StringBuilder();
            enunciado.append(linea.substring(linea.indexOf("-") + 1).trim());

            i++;

            while (i < lineas.size()) {

                linea = lineas.get(i).trim();

                if (esInicioOpcion(linea, "a")) {
                    break;
                }

                if (!linea.isBlank()) {
                    enunciado.append(" ");
                    enunciado.append(linea);
                }

                i++;
            }

            List<Respuesta> respuestas = new ArrayList<>();
            String[] letras = { "a", "b", "c", "d" };

            for (int letraIdx = 0; letraIdx < letras.length; letraIdx++) {

                String letra = letras[letraIdx];

                if (i >= lineas.size() || !esInicioOpcion(lineas.get(i).trim(), letra)) {
                    System.out.println("Aviso: falta la opción '" + letra
                            + "' en la pregunta " + numeroPregunta);
                    break;
                }

                linea = lineas.get(i).trim();

                StringBuilder textoRespuesta = new StringBuilder();
                textoRespuesta.append(linea.substring(2).trim());

                i++;

                String siguienteLetra = (letraIdx + 1 < letras.length)
                        ? letras[letraIdx + 1]
                        : null;

                while (i < lineas.size()) {

                    linea = lineas.get(i).trim();

                    if (linea.isBlank()) {
                        break;
                    }

                    if (siguienteLetra != null && esInicioOpcion(linea, siguienteLetra)) {
                        break;
                    }

                    textoRespuesta.append(" ");
                    textoRespuesta.append(linea);

                    i++;
                }

                // Provisional: se sobreescribirá con aplicarRespuestasCorrectas()
                boolean esCorrecta = false;

                respuestas.add(new Respuesta(
                        letra,
                        limpiarTexto(textoRespuesta.toString()),
                        esCorrecta));
            }

            Tema tema = Temas.obtenerTema(numeroPregunta);

            if (tema == null) {
                System.out.println("Tema no encontrado para la pregunta " + numeroPregunta);
                continue;
            }

            Pregunta pregunta = new Pregunta(
                    numeroPregunta,
                    tema.getId(),
                    limpiarTexto(enunciado.toString()));

            pregunta.setRespuestas(respuestas);

            preguntas.add(pregunta);
        }

        return preguntas;
    }

    private static boolean esInicioPregunta(String linea) {
        return linea.matches("^\\d+\\.\\-.*");
    }

    private static boolean esInicioOpcion(String linea, String letra) {
        return linea.startsWith(letra + ")");
    }

    private static String limpiarTexto(String texto) {
        return texto.replaceAll("\\s+", " ").trim();
    }

}