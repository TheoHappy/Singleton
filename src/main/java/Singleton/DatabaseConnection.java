package Singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//syncronized mai ieftin
public class DatabaseConnection {

    private static volatile DatabaseConnection instance;

    private Connection connection;
    private String url = "jdbc:postgresql://localhost:5432/postgres";
    private String username = "postgres";
    private String password = "somePassword";
    public static int counter = 0;

    private DatabaseConnection() throws SQLException {
        counter++;
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null){
            synchronized (DatabaseConnection.class){
                if (instance == null) {
                    instance = new DatabaseConnection();
                } else if (instance.getConnection().isClosed()) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }
}

////Adaugam syncronized la signatura metodei
//public class DatabaseConnection {
//
//    private static DatabaseConnection instance;
//
//    private Connection connection;
//    private String url = "jdbc:postgresql://localhost:5432/postgres";
//    private String username = "postgres";
//    private String password = "somePassword";
//    public static int counter = 0;
//
//    private DatabaseConnection() throws SQLException {
//        counter++;
//        try {
//            Class.forName("org.postgresql.Driver");
//            this.connection = DriverManager.getConnection(url, username, password);
//        } catch (ClassNotFoundException ex) {
//            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
//        }
//    }
//
//    public Connection getConnection() {
//        return connection;
//    }
//
//    public static synchronized DatabaseConnection getInstance() throws SQLException {
//        if (instance == null) {
//            instance = new DatabaseConnection();
//        } else if (instance.getConnection().isClosed()) {
//            instance = new DatabaseConnection();
//        }
//        return instance;
//    }
//}

//-------------------------------------------------------------------------------------------------

//Singletonul dat nu va lucra corect cu mai multe fire de executie.
//public class DatabaseConnection {
//
//    private static DatabaseConnection instance;
//    public static int counter = 0;
//    private Connection connection;
//    private String url = "jdbc:postgresql://localhost:5432/postgres";
//    private String username = "postgres";
//    private String password = "somePassword";
//
//    private DatabaseConnection() throws SQLException {
//        counter++;
//        try {
//            Class.forName("org.postgresql.Driver");
//            this.connection = DriverManager.getConnection(url, username, password);
//        } catch (ClassNotFoundException ex) {
//            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
//        }
//    }
//
//    public Connection getConnection() {
//        return connection;
//    }
//
//    public static DatabaseConnection getInstance() throws SQLException {
//        if (instance == null) {
//            instance = new DatabaseConnection();
//        } else if (instance.getConnection().isClosed()) {
//            instance = new DatabaseConnection();
//        }
//
//        return instance;
//    }
//}
