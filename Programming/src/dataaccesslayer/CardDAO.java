package dataaccesslayer;

import connection.DBConnection;
import entities.Card;

import java.util.ArrayList;

public class CardDAO {
    public static ArrayList<ArrayList<String>> getAllCardInUse(){
        String command = "Select * from card";
        ArrayList<ArrayList<String>> s = new ArrayList<>();
        s = DBConnection.query(command);
        return s;
    }

    public static void saveCardInfo(String cardCode, String owner, String CVV, String expiredDate){
        String command = "INSERT INTO card values" +
                "(" + '\'' + cardCode + '\'' + ", " +
                '\'' + owner + '\'' + ", " +
                '\'' + CVV + '\'' + ", " +
                '\'' + expiredDate + '\'' + ");";
        DBConnection.execute(command);
    }

    public static void deleteCardInfo(String cardCode){
        String command = "DELETE FROM card WHERE" +
                "(card.cardCode" + "==" + '\'' + cardCode + '\'' + ");";
        DBConnection.execute(command);
    }
}
