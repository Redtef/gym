package gym.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBSingleton {


    private static DBSingleton instance = null;

    private static String orcString = "jdbc:oracle:thin:@//localhost:1521/pdborcl";
    private static String orcUser = "hr";
    private static String orcPassword = "hr";
    private static Connection connection;

    private DBSingleton() {
        try {
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {

        if (connection == null) {
            synchronized (DBSingleton.class) {
                if (connection == null) {
                    try {
                        connection = DriverManager.getConnection(orcString, orcUser, orcPassword);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return connection;
    }

    public static DBSingleton getInstance() {
        if (instance == null)
            synchronized (DBSingleton.class) {
                if (instance == null)
                    instance = new DBSingleton();
            }

        return instance;
    }
}
