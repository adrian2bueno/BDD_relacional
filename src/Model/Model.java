package Model;

import java.sql.*;

public class Model {
    public static void connection(){
        //LAS FUNCIONES sequencias SQL
        //donde se guardan los datos???????
        try {
            Connection connection= DriverManager.getConnection("jdbc:mysql://192.168.56.103:3306/nba","perepi","pastanaga");
            Statement statement= connection.createStatement();

//            ResultSet resultSet= statement.executeQuery("select * from players");

            try (ResultSet resultSet = statement.executeQuery("select * from players")) {
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();

                // titulos
                for (int i = 1; i <= columnCount; i++) {
                    System.out.printf("%-15s", metaData.getColumnName(i));
                }
                System.out.println();

                // separador
                for (int i = 0; i < columnCount; i++) {
                    System.out.print("---------------");
                }
                System.out.println();
                while (resultSet.next()) {
                    for (int i = 1; i <= columnCount ; i++) {
                        System.out.printf("%-15s", resultSet.getString(i));

                    }
                    System.out.println();
                }
            }

//            while(resultSet.next()) {
//                for(int i = 1; i <=6; i++){
//                    System.out.print(resultSet.getString(i) + " ") ;
//                }
//                System.out.println();
//            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }


}
