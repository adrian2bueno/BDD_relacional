package Model;

import java.sql.*;


public class DatabaseConnection {
    //MAQUINA: localhost
    //BD: nba
    //Usuari i contrasenya: root patata
     private static final String URL = "jdbc:mysql://localhost:3306/nba";
    private static final String USER = "root";
    private static final String PASSWORD = "patata";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Error connectant a la base de dades", e);
        }
    }
}