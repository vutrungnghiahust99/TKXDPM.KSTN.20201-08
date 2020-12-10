package businesslogiclayer.interbanksubsystem;

import com.google.gson.JsonObject;
import entities.*;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;

public class ConvertToTransaction {
    public InterbankTransaction convertToPaymentTransaction(int cost, String command, String content){
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();

        InterbankTransaction transaction= new InterbankTransaction();
        transaction.setCardCode("118131_group8_2020");
        transaction.setOwner("Group 8");
        transaction.setCvvCode("427");
        transaction.setDateExpired("1125");
        transaction.setCommand(command);
        transaction.setTransactionContent(content);
        transaction.setAmount(cost);
        transaction.setCreatedAt(""+Integer.toString(date.getYear() + 1900) + "-" + date.getMonth() + "-"
                + date.getDay() + " " + date.getHours() + ":" + date.getMinutes() + ":"
                + date.getSeconds());
        return transaction;
    }

    public JsonObject resetTransaction(JsonObject body){
        body.addProperty("cardCode", "118131_group8_2020");
        body.addProperty("owner", "Group 8");
        body.addProperty("cvvCode", "427");
        body.addProperty("dateExpired", "1125");
        InterbankBoundary interbank = new InterbankBoundary();
        return body;
    }

    public JsonObject requestTransaction(JsonObject transToHash, JsonObject transactionBody) throws NoSuchAlgorithmException {
        // transToHash là chuỗi cần băm
        transToHash.addProperty("secretKey", "Bk5+TDRsBPY=");
        transToHash.add("transaction", transactionBody);
        MessageDigest md = MessageDigest.getInstance("MD5") ;
        md.update(transToHash.toString().getBytes());
        byte[] digest = md.digest();

        // myHash: chuỗi sau khi băm
        String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();

        // Tạo đối tượng sentJson để gửi lên api
        JsonObject sentJson = new JsonObject();
        sentJson.addProperty("version","1.0.1");
        sentJson.add("transaction",transactionBody);
        sentJson.addProperty("appCode", "ApBD97uYEU8=");
        sentJson.addProperty("hashCode", myHash);
        System.out.println("Chuỗi gửi lên: " + sentJson.toString());
        return sentJson;
    }
}
