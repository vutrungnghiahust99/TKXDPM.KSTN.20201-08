package dataaccesslayer;

import connection.DBConnection;

import java.util.ArrayList;
import java.util.Date;

public class RentBikeTransactionDAO {
    public static void save(int barcode, String rentalCode, String type, String rentTime, String returnTime,
                            int rentBikeCost, String owner, int priceForFirst30Minutes,
                            int priceFor15MinutesAfter30Minutes){
        String command = "INSERT INTO rentbiketransaction " +
                "(rentalCode, barcode, type, rentBikeCost, owner, priceFor30FirstMinutes, priceFor15MinutesAfter30Minutes, rentTime, returnTime) VALUES " +
                "(" + '\'' + rentalCode + '\'' + ", " +
                barcode + ", " +
                '\'' + type + '\'' + ", " +
                rentBikeCost + ", " +
                '\'' + owner + '\'' + ", " +
                priceForFirst30Minutes + ", " +
                priceFor15MinutesAfter30Minutes + ", " +
                '\'' + rentTime + '\'' + ", " +
                '\'' + returnTime + '\'' + ")";

        DBConnection.execute(command);
    }

    public static ArrayList<ArrayList<String>> queryByRentalCode(String rentalCode){
        String command = "SELECT * FROM rentbiketransaction WHERE rentalCode=" + '\'' + rentalCode + '\'';
        System.out.println(command);
        return DBConnection.query(command);
    }

    public static void updateReturnTimeAndCost(String rentalCode, int rentBikeCost, String returnTime){
        String command = "UPDATE rentbiketransaction SET rentBikeCost=" + rentBikeCost + ", returnTime=" + '\'' + returnTime + '\'' + " WHERE rentalCode=" + '\'' + rentalCode + '\'';
        DBConnection.execute(command);
    }
}
