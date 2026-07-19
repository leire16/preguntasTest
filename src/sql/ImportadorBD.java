package sql;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Pregunta;
import model.Respuesta;
import util.Constantes;

import java.io.File;
import java.sql.*;
import java.util.List;

public class ImportadorBD {

    private static final String JSON = "docs/preguntas.json";

    public static void main(String[] args) {

        Connection conn = null;

        try {

            DriverManager.getConnection(Constantes.RUTA_BD);

            ObjectMapper mapper = new ObjectMapper();

            List<Pregunta> preguntas = mapper.readValue(
                    new File(JSON),
                    new TypeReference<List<Pregunta>>() {
                    });

            System.out.println("=================================");
            System.out.println("Preguntas leídas: " + preguntas.size());
            System.out.println("=================================");
            System.out.println();

            conn.setAutoCommit(false);

            insertarTemas(conn);

            for (Pregunta pregunta : preguntas) {

                insertarPregunta(conn, pregunta);

            }

            conn.commit();

            System.out.println();
            System.out.println();
            System.out.println("=================================");
            System.out.println("Importación finalizada correctamente.");
            System.out.println("Preguntas importadas: " + preguntas.size());
            System.out.println("=================================");

        } catch (Exception e) {

            System.out.println();
            System.out.println("=================================");
            System.out.println("ERROR durante la importación");
            System.out.println("=================================");

            try {

                if (conn != null) {

                    conn.rollback();

                    System.out.println("Rollback realizado correctamente.");

                }

            } catch (SQLException ex) {

                ex.printStackTrace();

            }

            e.printStackTrace();

        } finally {

            try {

                if (conn != null) {

                    conn.close();

                }

            } catch (SQLException e) {

                e.printStackTrace();

            }

        }

    }

    private static void insertarPregunta(Connection conn, Pregunta pregunta) throws Exception {

        // ------------------------------------------
        // Insertar pregunta
        // ------------------------------------------

        String sqlPregunta = """
                INSERT INTO preguntas
                (tema_id, numero_original, enunciado)
                VALUES (?, ?, ?)
                """;

        PreparedStatement psPregunta = conn.prepareStatement(
                sqlPregunta,
                Statement.RETURN_GENERATED_KEYS);

        psPregunta.setInt(1, pregunta.getTemaId());
        psPregunta.setInt(2, pregunta.getNumeroOriginal());
        psPregunta.setString(3, pregunta.getEnunciado());

        psPregunta.executeUpdate();

        ResultSet rs = psPregunta.getGeneratedKeys();

        rs.next();

        int idPregunta = rs.getInt(1);

        rs.close();
        psPregunta.close();

        // ------------------------------------------
        // Insertar respuestas
        // ------------------------------------------

        String sqlRespuesta = """
                INSERT INTO respuestas
                (pregunta_id, letra, texto, es_correcta)
                VALUES (?, ?, ?, ?)
                """;

        PreparedStatement psRespuesta = conn.prepareStatement(sqlRespuesta);

        for (Respuesta respuesta : pregunta.getRespuestas()) {

            psRespuesta.setInt(1, idPregunta);
            psRespuesta.setString(2, respuesta.getLetra());
            psRespuesta.setString(3, respuesta.getTexto());
            psRespuesta.setInt(4, respuesta.isCorrecta() ? 1 : 0);

            psRespuesta.executeUpdate();

        }

        psRespuesta.close();

        // ------------------------------------------
        // Crear estado inicial
        // ------------------------------------------

        String sqlEstado = """
                INSERT INTO estado_pregunta
                (pregunta_id,
                 veces_preguntada,
                 veces_fallada,
                 ultima_fecha)
                VALUES (?,0,0,NULL)
                """;

        PreparedStatement psEstado = conn.prepareStatement(sqlEstado);

        psEstado.setInt(1, idPregunta);

        psEstado.executeUpdate();

        psEstado.close();

        System.out.printf(
                "\rImportando pregunta %3d / 500",
                pregunta.getNumeroOriginal());
    }

    private static void insertarTemas(Connection conn) throws Exception {

        String sql = """
                                INSERT OR IGNORE INTO temas
                (id, nombre, pregunta_inicio, pregunta_fin)
                VALUES (?, ?, ?, ?)
                                """;

        PreparedStatement ps = conn.prepareStatement(sql);

        for (model.Tema tema : Temas.LISTA) {

            ps.setInt(1, tema.getId());
            ps.setString(2, tema.getNombre());
            ps.setInt(3, tema.getPreguntaInicio());
            ps.setInt(4, tema.getPreguntaFin());

            ps.executeUpdate();

        }

        ps.close();
    }

}