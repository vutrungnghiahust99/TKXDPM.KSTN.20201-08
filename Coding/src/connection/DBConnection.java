package connection;

import java.sql.*;
import java.util.ArrayList;

public class DBConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ecodatabase";
    private static final String USER_NAME = "vutrungnghia";
    private static final String PASSWORD = "29452269";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            System.out.println("connect successfully!");
        } catch (Exception ex) {
            System.out.println("connect failure!");
            ex.printStackTrace();
        }
        return conn;
    }

    public static void execute(String command){
        try{
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            stmt.execute(command);
            System.out.println("Execute command: " + command + " successfully");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ArrayList<ArrayList<String>> query(String command){
        try{
            ArrayList<ArrayList<String>> queryResults = new ArrayList<>();
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(command);
            int numberOfColumn = rs.getMetaData().getColumnCount();
            while (rs.next()){
                ArrayList<String> s = new ArrayList<>();
                for(int i = 1; i <= numberOfColumn; ++i)
                    s.add(rs.getString(i));
                queryResults.add(s);
            }
            return queryResults;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}