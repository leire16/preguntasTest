package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import database.ConexionSQLite;
import model.Respuesta;

public class RespuestaDao {

    /**
     * Devuelve todas las respuestas de una pregunta.
     */
    public List<Respuesta> obtenerPorPregunta(int preguntaId) {
        List<Respuesta> respuestas = new ArrayList<>();

        String sql = """
                SELECT
                    id,
                    pregunta_id,
                    letra,
                    texto,
                    es_correcta
                FROM respuestas
                WHERE pregunta_id = ?
                """;
        try (
                Connection conn = ConexionSQLite.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, preguntaId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Respuesta respuesta = new Respuesta();
                    respuesta.setId(rs.getInt("id"));
                    respuesta.setPreguntaId(rs.getInt("pregunta_id"));
                    respuesta.setLetra(rs.getString("letra"));
                    respuesta.setTexto(rs.getString("texto"));
                    respuesta.setCorrecta(rs.getInt("es_correcta") == 1);
                    respuestas.add(respuesta);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuestas;
    }
}