package dataaccesslayer;

import connection.DBConnection;

import java.util.ArrayList;

public class BikeDAO {
    public static void updateIsInUse(boolean isInUse, int barcode, String type, int value, int priceForFirst30Minutes,
                     int priceFor15MinutesAfter30Minutes, int remainBattery, float maxTime, String licensePlate){
        int flag;
        if (isInUse)
            flag = 1;
        else
            flag = 0;
        String command = "UPDATE bike SET isInUse=" + flag + " WHERE barcode=" + barcode;
        DBConnection.execute(command);
    }

    public static ArrayList<ArrayList<String>> queryWithDockID(String dockID){
        ArrayList<ArrayList<String>> s = new ArrayList<>();
        String command = "SELECT * from bike WHERE dockID=" + '\'' + dockID + '\'';
        s = DBConnection.query(command);
        return s;
    }

    public static ArrayList<ArrayList<String>> getBikes(){
        ArrayList<ArrayList<String>> s = new ArrayList<>();
        String command = "SELECT * from bike";
        s = DBConnection.query(command);
        return s;
    }
}
