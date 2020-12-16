package connection;

import java.sql.*;
import java.util.ArrayList;

/**
 * Tạo connection với cơ sở dũ liệu và thực hiện các command và query
 */
public class DBConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ecodatabase";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "21011999";
    private static Connection conn = getConnection();

    /**
     * Tạo connection với cơ sở dữ liệu
     * @return: Connection
     */
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

    /**
     * Thực thi một lệnh bất ký và không trả về
     * @param command: lệnh thực thi
     */
    public static void execute(String command){
        System.out.println("Executing command: \n" + command);
        try{
            Statement stmt = conn.createStatement();
            stmt.execute(command);
            System.out.println("Successfully execute command: " + command);
        } catch (Exception e){
            System.out.println("Fail to execute command: \n" + command);
            e.printStackTrace();
        }
    }

    /**
     * Thực thi một lệnh bất kỳ và trả về một mảng hai chiều ứng với bảng trong cơ sở dữ liệu,
     * mảng hai chiều được biểu diễn theo dạng ArrayList<ArrayList<String>>
     *
     * @param command: lệnh thực thi
     * @return: mảng hai chiều chứa kết quả query
     */
    public static ArrayList<ArrayList<String>> query(String command){
        System.out.println("Executing query: \n" + command);
        try{
            ArrayList<ArrayList<String>> queryResults = new ArrayList<>();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(command);
            int numberOfColumn = rs.getMetaData().getColumnCount();
            while (rs.next()){
                ArrayList<String> s = new ArrayList<>();
                for(int i = 1; i <= numberOfColumn; ++i)
                    s.add(rs.getString(i));
                queryResults.add(s);
            }
            System.out.println("Successfully execute command: " + command);
            return queryResults;
        }catch (Exception e){
            System.out.println("Successfully execute command: " + command);
            e.printStackTrace();
            return null;
        }
    }
}