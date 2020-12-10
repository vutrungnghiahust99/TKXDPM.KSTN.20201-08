package businesslogiclayer.interbanksubsystem;

import com.google.gson.JsonObject;
import entities.*;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;

public class ConvertToTransaction {
    private         Card card = Card.getInstance();

    public InterbankTransaction convertToPaymentTransaction(int cost, String command, String content){
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();

        InterbankTransaction transaction= new InterbankTransaction();
        transaction.setCardCode(card.getCardCode());
        transaction.setOwner(card.getOwner());
        transaction.setCvvCode(card.getCVV());
        transaction.setDateExpired(card.getExpiredDate());
        transaction.setCommand(command);
        transaction.setTransactionContent(content);
        transaction.setAmount(cost);
        transaction.setCreatedAt(""+ (date.getYear() + 1900) + "-" + (date.getMonth() + 1) + "-"
                + date.getDate() + " " + date.getHours() + ":" + date.getMinutes() + ":"
                + date.getSeconds());
        return transaction;
    }

    public JsonObject resetTransaction(JsonObject body){
        body.addProperty("cardCode", card.getCardCode());
        body.addProperty("owner", card.getOwner());
        body.addProperty("cvvCode", card.getCVV());
        body.addProperty("dateExpired", card.getExpiredDate());
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
