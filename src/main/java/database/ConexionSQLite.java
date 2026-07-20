package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import util.Constantes;

public final class ConexionSQLite {


    private ConexionSQLite() {
        // Evita instanciar la clase
    }

    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(Constantes.RUTA_BD);

    }

}