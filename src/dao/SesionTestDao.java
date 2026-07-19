package dao;

import database.ConexionSQLite;
import model.SesionTest;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SesionTestDao {

    /**
     * Inserta una sesión de test y devuelve el id generado.
     */
    public int insertar(SesionTest sesion) {

        String sql = """
                INSERT INTO sesion_test
                (
                    fecha,
                    tipo,
                    tema_id,
                    num_preguntas,
                    num_aciertos,
                    num_fallos,
                    duracion_segundos
                )
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        try (

                Connection conn = ConexionSQLite.getConnection();

                PreparedStatement ps = conn.prepareStatement(
                        sql,
                        Statement.RETURN_GENERATED_KEYS)

        ) {

            ps.setString(1, sesion.getFecha().toString());
            ps.setString(2, sesion.getTipoTest().name());

            // TODO:
            // Cuando haya varios temas decidir cómo almacenarlo.
            // Por ahora:
            if (sesion.getTemas() != null &&
                    !sesion.getTemas().isEmpty()) {

                ps.setInt(3, sesion.getTemas().get(0));

            } else {

                ps.setNull(3, Types.INTEGER);

            }

            ps.setInt(4, sesion.getNumeroPreguntas());
            ps.setInt(5, sesion.getNumeroAciertos());
            ps.setInt(6, sesion.getNumeroFallos());
            ps.setInt(7, sesion.getDuracionSegundos());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {

                if (rs.next()) {

                    return rs.getInt(1);

                }

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return -1;

    }

    /**
     * Obtiene una sesión por su id.
     */
    public SesionTest obtenerPorId(int id) {

        String sql = """
                SELECT *
                FROM sesion_test
                WHERE id = ?
                """;

        try (

                Connection conn = ConexionSQLite.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql)

        ) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    SesionTest sesion = new SesionTest();

                    sesion.setId(rs.getInt("id"));

                    sesion.setFecha(
                            LocalDateTime.parse(
                                    rs.getString("fecha")));

                    sesion.setNumeroPreguntas(
                            rs.getInt("num_preguntas"));

                    sesion.setNumeroAciertos(
                            rs.getInt("num_aciertos"));

                    sesion.setNumeroFallos(
                            rs.getInt("num_fallos"));

                    sesion.setDuracionSegundos(
                            rs.getInt("duracion_segundos"));

                    return sesion;

                }

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;

    }

    /**
     * Devuelve todas las sesiones realizadas.
     */
    public List<SesionTest> obtenerTodas() {

        List<SesionTest> sesiones = new ArrayList<>();

        String sql = """
                SELECT *
                FROM sesion_test
                ORDER BY fecha DESC
                """;

        try (

                Connection conn = ConexionSQLite.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql);

                ResultSet rs = ps.executeQuery()

        ) {

            while (rs.next()) {

                SesionTest sesion = new SesionTest();

                sesion.setId(rs.getInt("id"));

                sesion.setFecha(
                        LocalDateTime.parse(
                                rs.getString("fecha")));

                sesion.setNumeroPreguntas(
                        rs.getInt("num_preguntas"));

                sesion.setNumeroAciertos(
                        rs.getInt("num_aciertos"));

                sesion.setNumeroFallos(
                        rs.getInt("num_fallos"));

                sesion.setDuracionSegundos(
                        rs.getInt("duracion_segundos"));

                sesiones.add(sesion);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return sesiones;

    }

    /**
     * Elimina una sesión.
     */
    public void eliminar(int id) {

        String sql = """
                DELETE
                FROM sesion_test
                WHERE id = ?
                """;

        try (

                Connection conn = ConexionSQLite.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(sql)

        ) {

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}