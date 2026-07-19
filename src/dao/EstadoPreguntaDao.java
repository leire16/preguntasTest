package dao;

import database.ConexionSQLite;
import model.EstadoPregunta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class EstadoPreguntaDao {

    /**
     * Devuelve el estado de una pregunta.
     */
    public EstadoPregunta obtenerPorPregunta(int preguntaId) {

        String sql = """
                SELECT
                    pregunta_id,
                    veces_preguntada,
                    veces_fallada,
                    ultima_fecha
                FROM estado_pregunta
                WHERE pregunta_id = ?
                """;

        try (

                Connection conn = ConexionSQLite.getConnection();

                PreparedStatement ps = conn.prepareStatement(sql)

        ) {

            ps.setInt(1, preguntaId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    EstadoPregunta estado = new EstadoPregunta();

                    estado.setPreguntaId(rs.getInt("pregunta_id"));
                    estado.setVecesPreguntada(rs.getInt("veces_preguntada"));
                    estado.setVecesFallada(rs.getInt("veces_fallada"));

                    String fecha = rs.getString("ultima_fecha");

                    if (fecha != null) {
                        estado.setUltimaFecha(LocalDate.parse(fecha));
                    }

                    return estado;

                }

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;

    }

    /**
     * Actualiza el estado de una pregunta después de responderla.
     */
    public void actualizarEstado(int preguntaId, boolean correcta) {

        String sql;

        if (correcta) {

            sql = """
                    UPDATE estado_pregunta
                    SET veces_preguntada = veces_preguntada + 1,
                        ultima_fecha = ?
                    WHERE pregunta_id = ?
                    """;

        } else {

            sql = """
                    UPDATE estado_pregunta
                    SET veces_preguntada = veces_preguntada + 1,
                        veces_fallada = veces_fallada + 1,
                        ultima_fecha = ?
                    WHERE pregunta_id = ?
                    """;

        }

        try (

                Connection conn = ConexionSQLite.getConnection();

                PreparedStatement ps = conn.prepareStatement(sql)

        ) {

            ps.setString(1, LocalDate.now().toString());
            ps.setInt(2, preguntaId);

            ps.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    /**
     * Reinicia las estadísticas de una pregunta.
     */
    public void reiniciarEstado(int preguntaId) {

        String sql = """
                UPDATE estado_pregunta
                SET veces_preguntada = 0,
                    veces_fallada = 0,
                    ultima_fecha = NULL
                WHERE pregunta_id = ?
                """;

        try (

                Connection conn = ConexionSQLite.getConnection();

                PreparedStatement ps = conn.prepareStatement(sql)

        ) {

            ps.setInt(1, preguntaId);

            ps.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}