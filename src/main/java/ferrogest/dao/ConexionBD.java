package ferrogest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "jdbc:postgresql://localhost:5432/ferrogest";
    private static final String user = "postgres";
    private static final String password = "PASSWORD";

    public static Connection conectar(){
        Connection conexion = null;
        try{
            conexion = DriverManager.getConnection(URL, user, password);
            System.out.println("Conectado con exito");
        }catch(SQLException e){
            System.out.println("[!] Error crítico conectando a la base de datos: " + e.getMessage());
        }
        return conexion;
    }
}
