package dao;

import database.ConexionSQLite;
import model.RespuestaUsuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RespuestaUsuarioDao {

    /**
     * Inserta una respuesta del usuario.
     */
    public void insertar(RespuestaUsuario respuesta) {

        String sql = """
                INSERT INTO respuestas_usuario
                (
                    sesion_id,
                    pregunta_id,
                    respuesta_id,
                    respuesta_correcta_id,
                    es_correcta 
                )
                VALUES (?, ?, ?, ?, ?)
                """;

        try (

                Connection conn = ConexionSQLite.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql)

        ) {

            ps.setInt(1, respuesta.getSesionId());
            ps.setInt(2, respuesta.getPreguntaId());
            ps.setInt(3, respuesta.getRespuestaId());
            ps.setInt(4, respuesta.getRespuestaCorrectaId());
            ps.setInt(5, respuesta.isCorrecta() ? 1 : 0);

            ps.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    /**
     * Devuelve todas las respuestas de una sesión.
     */
    public List<RespuestaUsuario> obtenerPorSesion(int sesionId) {

        List<RespuestaUsuario> respuestas = new ArrayList<>();

        String sql = """
                SELECT *
                FROM respuestas_usuario
                WHERE sesion_id = ?
                ORDER BY id
                """;

        try (

                Connection conn = ConexionSQLite.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql)

        ) {

            ps.setInt(1, sesionId);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    RespuestaUsuario respuesta =
                            new RespuestaUsuario();

                    respuesta.setId(rs.getInt("id"));

                    respuesta.setSesionId(
                            rs.getInt("sesion_id"));

                    respuesta.setPreguntaId(
                            rs.getInt("pregunta_id"));

                    respuesta.setRespuestaId(
                            rs.getInt("respuesta_id"));

                    respuesta.setRespuestaCorrectaId(
                            rs.getInt("respuesta_correcta_id"));

                    respuesta.setCorrecta(
                            rs.getInt("es_correcta ") == 1);

                    respuestas.add(respuesta);

                }

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return respuestas;

    }

    /**
     * Elimina todas las respuestas de una sesión.
     */
    public void eliminarPorSesion(int sesionId) {

        String sql = """
                DELETE
                FROM respuestas_usuario
                WHERE sesion_id = ?
                """;

        try (

                Connection conn = ConexionSQLite.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql)

        ) {

            ps.setInt(1, sesionId);

            ps.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}