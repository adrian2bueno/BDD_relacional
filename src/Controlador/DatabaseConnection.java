package Controlador;

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
    //Funcio per tancar la connexió
    public static void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    //Funcio per tancar els statement
    public static void close(PreparedStatement smt) {
        if (smt != null) {
            try {
                smt.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}