package dao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import database.ConexionSQLite;
import model.SesionTest;
import model.TipoTest;

public class SesionTestDao {

    /**
     * Inserta una sesión de test junto con todos sus temas asociados.
     * Usa una transacción: si falla la inserción de algún tema, no se
     * guarda nada a medias.
     */
    public int insertar(SesionTest sesion) {

        String sqlSesion = """
                INSERT INTO sesiones_test
                (
                    fecha,
                    tipo,
                    num_preguntas,
                    num_aciertos,
                    num_fallos,
                    duracion_segundos
                )
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        String sqlTema = """
                INSERT INTO sesion_tema (sesion_id, tema_id)
                VALUES (?, ?)
                """;

        try (Connection conn = ConexionSQLite.getConnection()) {

            conn.setAutoCommit(false);

            int idSesion = -1;

            try (PreparedStatement ps = conn.prepareStatement(
                    sqlSesion, Statement.RETURN_GENERATED_KEYS)) {

                ps.setString(1, sesion.getFecha().toString());
                ps.setString(2, sesion.getTipoTest().name());
                ps.setInt(3, sesion.getNumeroPreguntas());
                ps.setInt(4, sesion.getNumeroAciertos());
                ps.setInt(5, sesion.getNumeroFallos());
                ps.setInt(6, sesion.getDuracionSegundos());

                ps.executeUpdate();

                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        idSesion = rs.getInt(1);
                    }
                }
            }

            if (idSesion != -1 &&
                    sesion.getTemas() != null &&
                    !sesion.getTemas().isEmpty()) {

                try (PreparedStatement ps = conn.prepareStatement(sqlTema)) {

                    for (int temaId : sesion.getTemas()) {
                        ps.setInt(1, idSesion);
                        ps.setInt(2, temaId);
                        ps.addBatch();
                    }

                    ps.executeBatch();
                }
            }

            conn.commit();
            return idSesion;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    /**
     * Devuelve los ids de tema asociados a una sesión.
     */
    private List<Integer> obtenerTemasDeSesion(Connection conn, int sesionId) throws SQLException {

        List<Integer> temas = new ArrayList<>();

        String sql = "SELECT tema_id FROM sesion_tema WHERE sesion_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, sesionId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    temas.add(rs.getInt("tema_id"));
                }
            }
        }

        return temas;
    }

    /**
     * Obtiene una sesión por su id, incluyendo sus temas.
     */
    public SesionTest obtenerPorId(int id) {

        String sql = """
                SELECT *
                FROM sesiones_test
                WHERE id = ?
                """;

        try (
                Connection conn = ConexionSQLite.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    SesionTest sesion = new SesionTest();

                    sesion.setId(rs.getInt("id"));
                    sesion.setFecha(LocalDateTime.parse(rs.getString("fecha")));
                    sesion.setTipoTest(TipoTest.valueOf(rs.getString("tipo")));
                    sesion.setNumeroPreguntas(rs.getInt("num_preguntas"));
                    sesion.setNumeroAciertos(rs.getInt("num_aciertos"));
                    sesion.setNumeroFallos(rs.getInt("num_fallos"));
                    sesion.setDuracionSegundos(rs.getInt("duracion_segundos"));
                    sesion.setTemas(obtenerTemasDeSesion(conn, id));

                    return sesion;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Devuelve todas las sesiones realizadas, cada una con sus temas.
     */
    public List<SesionTest> obtenerTodas() {

        List<SesionTest> sesiones = new ArrayList<>();

        String sql = """
                SELECT *
                FROM sesiones_test
                ORDER BY fecha DESC
                """;

        try (
                Connection conn = ConexionSQLite.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                SesionTest sesion = new SesionTest();

                sesion.setId(rs.getInt("id"));
                sesion.setFecha(LocalDateTime.parse(rs.getString("fecha")));
                sesion.setTipoTest(TipoTest.valueOf(rs.getString("tipo")));
                sesion.setNumeroPreguntas(rs.getInt("num_preguntas"));
                sesion.setNumeroAciertos(rs.getInt("num_aciertos"));
                sesion.setNumeroFallos(rs.getInt("num_fallos"));
                sesion.setDuracionSegundos(rs.getInt("duracion_segundos"));
                sesion.setTemas(obtenerTemasDeSesion(conn, sesion.getId()));

                sesiones.add(sesion);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return sesiones;
    }

    /**
    * Elimina una sesión (y sus filas en sesion_tema, gracias al
    * ON DELETE CASCADE de la foreign key).
    */
    public void eliminar(int id) {

        String sql = """
                DELETE
                FROM sesiones_test
                WHERE id = ?
                """;

        try (
                Connection conn = ConexionSQLite.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}