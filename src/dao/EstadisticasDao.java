package dao;

import database.ConexionSQLite;
import model.Estadisticas;
import model.RendimientoTema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EstadisticasDao {

    public Estadisticas obtenerEstadisticas(List<Integer> temas) {

        Estadisticas estadisticas = new Estadisticas();

        estadisticas.setTestsRealizados(
                obtenerNumeroTests(temas));

        estadisticas.setPreguntasRespondidas(
                obtenerPreguntasRespondidas(temas));

        estadisticas.setPreguntasDistintas(
                obtenerPreguntasDistintas(temas));

        estadisticas.setPorcentajeAciertos(
                obtenerPorcentajeAciertos(temas));

        estadisticas.setRendimientoTemas(
                obtenerRendimientoPorTema(temas));

        return estadisticas;
    }

    /**
     * Número de tests realizados.
     */
    private int obtenerNumeroTests(List<Integer> temas) {

        String sql = construirSqlNumeroTests(temas);

        try (
                Connection conn = ConexionSQLite.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql)

        ) {

            rellenarParametros(ps, temas);

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

    /**
     * Número total de preguntas respondidas.
     */
    private int obtenerPreguntasRespondidas(List<Integer> temas) {

        String sql = construirSqlRespuestas(temas, false);

        try (
                Connection conn = ConexionSQLite.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql)

        ) {

            rellenarParametros(ps, temas);

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

    /**
     * Número de preguntas distintas respondidas.
     */
    private int obtenerPreguntasDistintas(List<Integer> temas) {

        String sql = construirSqlRespuestas(temas, true);

        try (
                Connection conn = ConexionSQLite.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql)

        ) {

            rellenarParametros(ps, temas);

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

    /**
     * Porcentaje global de aciertos.
     */
    private double obtenerPorcentajeAciertos(List<Integer> temas) {

        StringBuilder sql = new StringBuilder("""
                SELECT
                    SUM(CASE WHEN ru.es_correcta = 1 THEN 1 ELSE 0 END),
                    COUNT(*)
                FROM respuestas_usuario ru
                INNER JOIN preguntas p
                    ON ru.pregunta_id = p.id
                WHERE p.tema_id IN (
                """);

        añadirInterrogaciones(
                sql,
                temas.size());

        sql.append(")");

        try (
                Connection conn = ConexionSQLite.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(
                                sql.toString())

        ) {

            rellenarParametros(ps, temas);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    int aciertos =
                            rs.getInt(1);

                    int total =
                            rs.getInt(2);

                    if(total == 0) {

                        return 0;

                    }

                    return aciertos * 100.0 / total;

                }

            }

        } catch(Exception e) {

            e.printStackTrace();

        }

        return 0;
    }

    /**
     * Porcentaje de aciertos por cada tema.
     */
    private List<RendimientoTema> obtenerRendimientoPorTema(
        List<Integer> temas) {

        List<RendimientoTema> rendimiento =
                new ArrayList<>();

        StringBuilder sql = new StringBuilder("""
                SELECT
                    t.id AS tema_id,
                    t.nombre AS nombre,
                    SUM(CASE WHEN ru.es_correcta = 1 THEN 1 ELSE 0 END) AS aciertos,
                    COUNT(ru.id) AS total
                FROM temas t
                LEFT JOIN preguntas p
                    ON p.tema_id = t.id
                LEFT JOIN respuestas_usuario ru
                    ON ru.pregunta_id = p.id
                WHERE t.id IN (
                """);

        añadirInterrogaciones(
                sql,
                temas.size());

        sql.append("""
                )
                GROUP BY t.id, t.nombre
                ORDER BY t.id
                """);

        try(
                Connection conn =
                        ConexionSQLite.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(
                                sql.toString())
        ){

            rellenarParametros(
                    ps,
                    temas);

            try(ResultSet rs = ps.executeQuery()) {

                while(rs.next()) {

                    int temaId =
                            rs.getInt("tema_id");

                    String nombre =
                            rs.getString("nombre");

                    int aciertos =
                            rs.getInt("aciertos");

                    int total =
                            rs.getInt("total");

                    double porcentaje = 0;

                    if(total > 0){

                        porcentaje =
                                aciertos * 100.0 / total;

                    }

                    rendimiento.add(
                            new RendimientoTema(
                                    temaId,
                                    nombre,
                                    porcentaje));

                }
            }

        }catch(Exception e){

            e.printStackTrace();

        }

        return rendimiento;
    }

    // =======================================================
    // SQL
    // =======================================================

    private String construirSqlNumeroTests(List<Integer> temas) {

        StringBuilder sql = new StringBuilder("""
                SELECT COUNT(DISTINCT sesion_id)
                FROM sesion_tema
                WHERE tema_id IN (
                """);

        añadirInterrogaciones(
                sql,
                temas.size());

        sql.append(")");

        return sql.toString();
    }

    private String construirSqlRespuestas(
            List<Integer> temas,
            boolean distintas) {

        StringBuilder sql = new StringBuilder();

        if(distintas) {

            sql.append("""
                    SELECT COUNT(DISTINCT ru.pregunta_id)
                    """);

        } else {

            sql.append("""
                    SELECT COUNT(*)
                    """);

        }

        sql.append("""
                FROM respuestas_usuario ru
                INNER JOIN preguntas p
                    ON ru.pregunta_id = p.id
                WHERE p.tema_id IN (
                """);

        añadirInterrogaciones(
                sql,
                temas.size());

        sql.append(")");

        return sql.toString();
    }

    // =======================================================
    // AUXILIARES
    // =======================================================

    private void rellenarParametros(
            PreparedStatement ps,
            List<Integer> temas) throws Exception {

        for(int i = 0; i < temas.size(); i++) {

            ps.setInt(
                    i + 1,
                    temas.get(i));

        }

    }

    private void añadirInterrogaciones(
            StringBuilder sql,
            int cantidad) {


        for(int i = 0; i < cantidad; i++) {

            sql.append("?");


            if(i < cantidad - 1) {

                sql.append(",");

            }

        }

    }

}