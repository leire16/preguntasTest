package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import database.ConexionSQLite;
import model.Tema;

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