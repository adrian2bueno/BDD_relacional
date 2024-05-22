package Model;

import java.sql.*;
//todo Hacer que se conecte a la API NBA https://github.com/albaamatamoros/PracticaUF6-NBA/blob/master/src/Model/Model.java
//todo: Canviar nombres
//todo:
//todo:
//todo:
public class DatabaseConnection {
    //https://stats.nba.com/stats/synergyplaytypes?LeagueID=00&PerMode=PerGame&PlayType=Isolation&PlayerOrTeam=P&SeasonType=Playoffs&SeasonYear=2023-24&TypeGrouping=offensive
    private static final String URL = "jdbc:mysql://192.168.56.103:3306/nba"; // Ajusta el puerto si es necesario y el nombre de la base de datos
    private static final String USER = "perepi";  // Reemplaza con tu usuario
    private static final String PASSWORD = "pastanaga";  // Reemplaza con tu contraseña
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver"; // Esto es el driver de JDBC para MySQL

    public static Connection getConnection() {
        try {
            Class.forName(DRIVER); // Asegura que el driver JDBC está cargado.
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Error conectando a la base de datos", e);
        }
    }

    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT nom, cognom FROM jugadors"); // Consulta de ejemplo
            while (resultSet.next()) {
                String nom = resultSet.getString("nom");
                String congnoms = resultSet.getString("cognom");
                System.out.println("Jugador: " + nom + ", cognom: " + congnoms);
            }
            resultSet.close(); // Cerrar el ResultSet
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}