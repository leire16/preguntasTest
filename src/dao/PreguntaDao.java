package dao;

import database.ConexionSQLite;
import model.Pregunta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PreguntaDao {

    private final RespuestaDao respuestaDAO;

    public PreguntaDao() {

        respuestaDAO = new RespuestaDao();

    }

    /**
     * Devuelve todas las preguntas de la base de datos.
     */
    public List<Pregunta> obtenerTodas() {

        String sql = """
                SELECT
                    id,
                    numero_original,
                    tema_id,
                    enunciado
                FROM preguntas
                ORDER BY numero_original
                """;

        return ejecutarConsulta(sql);

    }

    /**
     * Devuelve todas las preguntas pertenecientes a los temas indicados.
     */
    public List<Pregunta> obtenerPorTemas(List<Integer> temas) {

        if (temas == null || temas.isEmpty()) {
            return obtenerTodas();
        }

        StringBuilder sql = new StringBuilder("""
                SELECT
                    id,
                    numero_original,
                    tema_id,
                    enunciado
                FROM preguntas
                WHERE tema_id IN (
                """);

        for (int i = 0; i < temas.size(); i++) {

            sql.append("?");

            if (i < temas.size() - 1) {
                sql.append(",");
            }

        }

        sql.append(") ORDER BY numero_original");

        List<Pregunta> preguntas = new ArrayList<>();

        try (

                Connection conn = ConexionSQLite.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql.toString())

        ) {

            for (int i = 0; i < temas.size(); i++) {

                ps.setInt(i + 1, temas.get(i));

            }

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    preguntas.add(crearPregunta(rs));

                }

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return preguntas;

    }

    /**
     * Devuelve una pregunta por su id.
     */
    public Pregunta obtenerPorId(int idPregunta) {

        String sql = """
                SELECT
                    id,
                    numero_original,
                    tema_id,
                    enunciado
                FROM preguntas
                WHERE id = ?
                """;

        try (

                Connection conn = ConexionSQLite.getConnection();

                PreparedStatement ps = conn.prepareStatement(sql)

        ) {

            ps.setInt(1, idPregunta);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    return crearPregunta(rs);

                }

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;

    }

    /**
     * Devuelve las preguntas falladas de los temas indicados.
     */
    public List<Pregunta> obtenerFalladas(List<Integer> temas) {

        StringBuilder sql = new StringBuilder("""
                SELECT
                    p.id,
                    p.numero_original,
                    p.tema_id,
                    p.enunciado
                FROM preguntas p
                INNER JOIN estado_pregunta ep
                    ON ep.pregunta_id = p.id
                WHERE ep.veces_fallada > 0
                """);

        if (temas != null && !temas.isEmpty()) {

            sql.append(" AND p.tema_id IN (");

            for (int i = 0; i < temas.size(); i++) {

                sql.append("?");

                if (i < temas.size() - 1) {
                    sql.append(",");
                }

            }

            sql.append(")");

        }

        sql.append(" ORDER BY ep.veces_fallada DESC");

        System.out.println(sql + " falladas");

        List<Pregunta> preguntas = new ArrayList<>();

        try (

                Connection conn = ConexionSQLite.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql.toString())

        ) {

            if (temas != null && !temas.isEmpty()) {

                for (int i = 0; i < temas.size(); i++) {

                    ps.setInt(i + 1, temas.get(i));

                }

            }

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    preguntas.add(crearPregunta(rs));

                }

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return preguntas;

    }

    /**
     * Cuenta todas las preguntas.
     */
    public int contarPreguntas() {

        String sql = "SELECT COUNT(*) FROM preguntas";

        try (

                Connection conn = ConexionSQLite.getConnection();

                PreparedStatement ps = conn.prepareStatement(sql);

                ResultSet rs = ps.executeQuery()

        ) {

            if (rs.next()) {

                return rs.getInt(1);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return 0;

    }

    /**
     * Cuenta las preguntas de unos temas.
     */
    public int contarPreguntas(List<Integer> temas) {

        if (temas == null || temas.isEmpty()) {
            return contarPreguntas();
        }

        StringBuilder sql = new StringBuilder(
                "SELECT COUNT(*) FROM preguntas WHERE tema_id IN (");

        for (int i = 0; i < temas.size(); i++) {

            sql.append("?");

            if (i < temas.size() - 1) {
                sql.append(",");

            }

        }

        sql.append(")");

        try (

                Connection conn = ConexionSQLite.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql.toString())

        ) {

            for (int i = 0; i < temas.size(); i++) {

                ps.setInt(i + 1, temas.get(i));

            }

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    return rs.getInt(1);

                }

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return 0;

    }

    // ============================================
    // MÉTODOS PRIVADOS
    // ============================================

    private List<Pregunta> ejecutarConsulta(String sql) {

        List<Pregunta> preguntas = new ArrayList<>();

        try (

                Connection conn = ConexionSQLite.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql);

                ResultSet rs = ps.executeQuery()

        ) {

            while (rs.next()) {

                preguntas.add(crearPregunta(rs));

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return preguntas;

    }

    private Pregunta crearPregunta(ResultSet rs) throws Exception {

        Pregunta pregunta = new Pregunta();

        int idPregunta = rs.getInt("id");

        pregunta.setId(idPregunta);

        pregunta.setNumeroOriginal(
                rs.getInt("numero_original"));

        pregunta.setTemaId(
                rs.getInt("tema_id"));

        pregunta.setEnunciado(
                rs.getString("enunciado"));

        pregunta.setRespuestas(
                respuestaDAO.obtenerPorPregunta(idPregunta));

        return pregunta;

    }
    

}