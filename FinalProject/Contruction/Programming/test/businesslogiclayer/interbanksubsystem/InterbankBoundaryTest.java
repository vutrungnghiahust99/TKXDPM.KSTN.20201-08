package businesslogiclayer.interbanksubsystem;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entities.Card;
import entities.InterbankTransaction;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class InterbankBoundaryTest {
    InterbankBoundary interbankBoundary = new InterbankBoundary();
    JsonObject body1 = new JsonObject();
    JsonObject body2 = new JsonObject();
    Card card = new Card();
    @BeforeEach
    void setUp() {
    }

    @ParameterizedTest
    @CsvSource({
            "1, 00",
            "2, 05",
            "3, 04",
            "4, 04",
            "5, 06",
            "6, 02",
            "7, 01",
            "8, 07"
    })
    void processTransaction(String th, String expected) throws JsonProcessingException {
        card.setCardCode("118131_group8_2020");
        card.setOwner("Group 8");
        card.setCVV("427");
        card.setExpiredDate("1125");
        int cost;
        switch (th){
            case "6": cost = 5000000;break;
            case "8": cost = -1; break;
            default:
                cost = 10;
        }
        JsonObject sentJson = new JsonObject();
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createdAt = df.format(date);
        InterbankTransaction transaction = new InterbankTransaction();
        transaction.setCardCode(card.getCardCode());
        if (th.equals("7")) transaction.setOwner("Lê Thế Nam");
        else transaction.setOwner(card.getOwner());
        transaction.setCvvCode(card.getCVV());
        transaction.setDateExpired(card.getExpiredDate());
        transaction.setCommand("pay");
        transaction.setTransactionContent("Trừ tiền cọc");
        transaction.setAmount(cost);
        transaction.setCreatedAt(createdAt);
        String transactString = new ObjectMapper().writeValueAsString(transaction);
        JsonObject transactionBody = new JsonParser().parse(transactString).getAsJsonObject();
        JsonObject transToHash = new JsonObject();
        transToHash.addProperty("secretKey", "Bk5+TDRsBPY=");
        transToHash.add("transaction", transactionBody);
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(transToHash.toString().getBytes());
        byte[] digest = md.digest();
        String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
        switch (th){
            case "1":
            case "6":
            case "7":
            case "8":
            case "9":
                sentJson.addProperty("version", "1.0.1");
                sentJson.add("transaction", transactionBody);
                sentJson.addProperty("appCode", "ApBD97uYEU8=");
                sentJson.addProperty("hashCode", myHash);
                break;
            case "2":
                sentJson.addProperty("version", "1.0.1");
//                sentJson.add("transaction", transactionBody);
                sentJson.addProperty("appCode", "ApBD97uYEU8=");
                sentJson.addProperty("hashCode", myHash);
                break;
            case "3":
                sentJson.addProperty("version", "1.0.1");
                sentJson.add("transaction", transactionBody);
//                sentJson.addProperty("appCode", "ApBD97uYEU8=");
                sentJson.addProperty("hashCode", myHash);
                break;
            case "4":
                sentJson.addProperty("version", "1.0.1");
                sentJson.add("transaction", transactionBody);
                sentJson.addProperty("appCode", "ApBD97uYEU8=");
//                sentJson.addProperty("hashCode", myHash);
                break;
            case "5":
                sentJson.add("transaction", transactionBody);
                sentJson.addProperty("appCode", "ApBD97uYEU8=");
                sentJson.addProperty("hashCode", myHash);
                break;
        }
        String code = interbankBoundary.processTransaction(sentJson);
        assertEquals(code, expected);
    }
}