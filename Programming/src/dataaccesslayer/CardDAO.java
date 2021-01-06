package dataaccesslayer;

import connection.DBConnection;

import java.util.ArrayList;

public class CardDAO {
    public static ArrayList<ArrayList<String>> getAllCardInUse(){
        String command = "Select * from card";
        ArrayList<ArrayList<String>> s = new ArrayList<>();
        s = DBConnection.query(command);
        return s;
    }
}
