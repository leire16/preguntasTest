package dao;

import database.ConexionSQLite;
import model.Tema;
import util.Constantes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TemaDao {

    public List<Tema> obtenerTodos() {

        List<Tema> temas = new ArrayList<>();

        String sql = """
                SELECT
                    id,
                    nombre,
                    pregunta_inicio,
                    pregunta_fin
                FROM temas
                ORDER BY id
                """;

        try (
                Connection conn = ConexionSQLite.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                Tema tema = new Tema(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("pregunta_inicio"),
                        rs.getInt("pregunta_fin")
                );

                temas.add(tema);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return temas;

    }

    public Tema obtenerPorId(int idTema) {

        String sql = """
                SELECT
                    id,
                    nombre,
                    pregunta_inicio,
                    pregunta_fin
                FROM temas
                WHERE id = ?
                """;

        try (
                Connection conn = ConexionSQLite.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, idTema);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    return new Tema(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getInt("pregunta_inicio"),
                            rs.getInt("pregunta_fin")
                    );

                }

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;

    }

    /**
     * Devuelve los ids de todos los temas específicos.
     */
    public List<Integer> obtenerIdsTemasEspecificos() {

        String sql = """
                SELECT id
                FROM temas
                WHERE id NOT BETWEEN ? AND ?
                ORDER BY id
                """;

        List<Integer> temas = new ArrayList<>();

        try (
                Connection conn = ConexionSQLite.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, Constantes.ID_INICIO_TEMA_COMUN);
            ps.setInt(2, Constantes.ID_FIN_TEMA_COMUN);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    temas.add(rs.getInt("id"));

                }

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return temas;

    }

    public int contarTemas() {

        String sql = "SELECT COUNT(*) FROM temas";

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

}