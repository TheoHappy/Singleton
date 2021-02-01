package Singleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
// Testarea pentru multitreading
public class Test {
    public static void main(String[] args) throws SQLException, InterruptedException {

        final int SIZE = 100 ;
        Thread t[] = new Thread[SIZE];
        for (int i = 0; i < SIZE; i++){
            t[i] = new Thread(new TestRunnable());
            t[i].start();
        }
        for (int i =0; i<SIZE; i++){
            t[i].join();
        }
        System.out.println(DatabaseConnection.counter);

        Connection databaseConnection = DatabaseConnection.getInstance().getConnection();
        try {
            PreparedStatement statement = databaseConnection.prepareStatement("SELECT *FROM t_test");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                System.out.println(resultSet.getInt(1));
                System.out.println(resultSet.getString(2));
            }
        }catch (Exception e){

        }

    }
}

class TestRunnable implements Runnable{
    @Override
    public void run() {
        try {
            DatabaseConnection.getInstance().getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

//-------------------------------------------------------------------------------------------------

// Testare fara multitreading
//public class Test {
//    public static void main(String[] args) throws SQLException, InterruptedException {
//        Connection databaseConnection = DatabaseConnection.getInstance().getConnection();
//        System.out.println(DatabaseConnection.counter);
//        try {
//            PreparedStatement statement = databaseConnection.prepareStatement("SELECT *FROM t_test");
//            ResultSet resultSet = statement.executeQuery();
//            while (resultSet.next()){
//                System.out.println(resultSet.getInt(1));
//                System.out.println(resultSet.getString(2));
//            }
//        }catch (Exception e){
//
//        }
//    }
//}


