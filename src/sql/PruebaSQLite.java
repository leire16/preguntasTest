package sql;

import database.ConexionSQLite;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class PruebaSQLite {

    public static void main(String[] args) {

        try {
            Connection conn = ConexionSQLite.getConnection();

            System.out.println("Conexión realizada correctamente.");

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(
                    "SELECT name FROM sqlite_master WHERE type='table';");

            System.out.println();
            System.out.println("Tablas encontradas:");

            while (rs.next()) {

                System.out.println("- " + rs.getString("name"));

            }

            rs.close();
            st.close();
            conn.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}