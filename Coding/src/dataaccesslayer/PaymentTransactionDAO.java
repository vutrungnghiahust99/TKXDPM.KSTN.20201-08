package dataaccesslayer;

import connection.DBConnection;

public class PaymentTransactionDAO {
    public static void save(String rentalCode, String cardCode, String owner, String transactionContent, int amount, String time, String day){
        String command = "INSERT INTO paymenttransaction " +
                "(rentalCode, cardCode, owner, transactionContent, amount, time, day) VALUES " +
                '(' + rentalCode + ", " +
                '\'' + cardCode + '\'' + ", " +
                '\'' + owner + '\'' + ", " +
                '\'' + transactionContent + '\'' + ", " +
                amount + ", " +
                '\'' + time + '\'' + ", " +
                '\'' + day + '\'' + ')';
        DBConnection.execute(command);
    }
}
