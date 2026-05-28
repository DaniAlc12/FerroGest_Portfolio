package ferrogest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "jdbc:postgresql://localhost:5432/ferrogest";
    private static final String user = "postgres";
    private static final String password = "Maagd12005";

    public static Connection conectar() throws SQLException{
        Connection conexion = null;
            conexion = DriverManager.getConnection(URL, user, password);
            System.out.println("Conectado con exito");
        return conexion;
    }
}
