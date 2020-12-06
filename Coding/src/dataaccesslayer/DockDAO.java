package dataaccesslayer;

import connection.DBConnection;

import java.util.ArrayList;

public class DockDAO {
    public static ArrayList<ArrayList<String>> getAllDocks(){
        String command = "SELECT * FROM dock";
        return DBConnection.query(command);
    }
}
