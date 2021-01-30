package sample;

import java.sql.Connection;
import java.sql.DriverManager;


public class DatabaseConnection {

    public Connection databaseLink;

    public Connection getConnection(){
        String databaseName = "demo_db";
        String databaseUser = "root";
        String databasePassword = "Heslo123";
        String url = "jdbc:mysql://localhost:3306/demo_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            databaseLink = DriverManager.getConnection(url, databaseUser,databasePassword);
        } catch (Exception ex){
            ex.printStackTrace();
            ex.getCause();
        }

        return databaseLink;

    }
}
