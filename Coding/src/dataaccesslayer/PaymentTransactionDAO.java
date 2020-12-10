package dataaccesslayer;

import connection.DBConnection;

public class PaymentTransactionDAO {
    public static void save(String rentalCode, String cardCode, String owner, String transactionContent, int amount,
                            int balance, String time, String day){
        String command = "INSERT INTO paymenttransaction " +
                "(rentalCode, cardCode, owner, transactionContent, amount, balance, time, day) VALUES " +
                '(' + rentalCode + ", " +
                '\'' + cardCode + '\'' + ", " +
                '\'' + owner + '\'' + ", " +
                '\'' + transactionContent + '\'' + ", " +
                amount + ", " +
                balance + ", " +
                '\'' + time + '\'' + ", " +
                '\'' + day + '\'' + ')';
        DBConnection.execute(command);
    }
}
