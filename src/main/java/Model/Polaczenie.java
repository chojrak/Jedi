package Model;

import java.sql.*;

public abstract class Polaczenie {

    private static String url = "jdbc:postgresql:baza";
    private static String user = "postgres";
    private static String password = "1qqrqxxx";

    public static ResultSet Execute(String query) {
        try {

            Connection con = DriverManager.getConnection(url, user, password);
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(query);
            return rs;
        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static void Update (String query) {
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            Statement stm = con.createStatement();
            stm.executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
