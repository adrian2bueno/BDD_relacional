package Model;

import java.sql.*;
//todo Hacer que se conecte a la API NBA https://github.com/albaamatamoros/PracticaUF6-NBA/blob/master/src/Model/Model.java
//todo: Canviar nombres
//todo://https://stats.nba.com/stats/synergyplaytypes?LeagueID=00&PerMode=PerGame&PlayType=Isolation&PlayerOrTeam=P&SeasonType=Playoffs&SeasonYear=2023-24&TypeGrouping=offensive
//todo:          //PARA COMPORVAR LA CONN USAR EL MAIN SIN LOS SELECTS

public class DatabaseConnection {
    //MAQUINA BD/ 10.0.2.15
    //nba
    //root patata????????????
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