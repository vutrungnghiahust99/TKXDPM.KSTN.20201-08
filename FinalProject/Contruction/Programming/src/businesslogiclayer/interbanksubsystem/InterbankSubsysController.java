package businesslogiclayer.interbanksubsystem;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entities.Card;
import entities.InterbankTransaction;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class InterbankSubsysController implements IInterbank {

    /**
     * @param card : instance chứa thông tin thẻ người dùng
     * @param cost : giá tiền cần giao dịch
     * @param command : yêu cầu giao dịch {"pay", "refund"}
     * @param content : nội dung giao dịch
     * @return : mã error code mà api gửi lại cho hệ thống
     */

    @Override
    public String processTransaction(Card card, int cost, String command, String content){
        reset();
        try {
            // convert to payment transaction
            Calendar calendar = Calendar.getInstance();
            Date date = calendar.getTime();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String createdAt = df.format(date);

            InterbankTransaction transaction= new InterbankTransaction();
            transaction.setCardCode(card.getCardCode());
            transaction.setOwner(card.getOwner());
            transaction.setCvvCode(card.getCVV());
            transaction.setDateExpired(card.getExpiredDate());
            transaction.setCommand(command);
            transaction.setTransactionContent(content);
            transaction.setAmount(cost);
            transaction.setCreatedAt(createdAt);


            String transactString = new ObjectMapper().writeValueAsString(transaction);
            JsonObject transactionBody = new JsonParser().parse(transactString).getAsJsonObject();

            //convert to request transaction
            JsonObject transToHash = new JsonObject();
            // transToHash là chuỗi cần băm
            transToHash.addProperty("secretKey", "Bk5+TDRsBPY=");
            transToHash.add("transaction", transactionBody);
            MessageDigest md = null;
            try {
                md = MessageDigest.getInstance("MD5");
            } catch (Exception e) {
                e.printStackTrace();
            }
            md.update(transToHash.toString().getBytes());
            byte[] digest = md.digest();
            String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
            JsonObject sentJson = new JsonObject();
            sentJson.addProperty("version","1.0.1");
            sentJson.add("transaction",transactionBody);
            sentJson.addProperty("appCode", "ApBD97uYEU8=");
            sentJson.addProperty("hashCode", myHash);
            System.out.println("Chuỗi gửi lên: " + sentJson.toString());

            InterbankBoundary interbank = new InterbankBoundary();
            String errorCode = interbank.processTransaction(sentJson);
            return errorCode;

        }catch (Exception e) {
            System.out.println("Xử lý giao dịch lỗi!, intercontroller");
            return "08";
        }
    }


    /**
     * Reset tiền trong tài khoản người dùng
     */
    @Override
    public void reset(){
        JsonObject body = new JsonObject();
        InterbankBoundary interbank = new InterbankBoundary();
        String errorCode = interbank.reset();
        switch (errorCode) {
            case "00": System.out.println("Reset balance thành công!"); break;
            case "01": System.out.println("Thẻ không hợp lệ!"); break;
            case "02": System.out.println("Thẻ không đủ số dư!"); break;
            case "03": System.out.println("Internal Server Error!"); break;
            case "04": System.out.println("Giao dịch bị nghi ngờ gian lận!"); break;
            case "05": System.out.println("Không đủ thông tin giao dịch!"); break;
            case "06": System.out.println("Thiếu thông tin version!"); break;
            case "07": System.out.println("Amount không hợp lệ!"); break;
            default: System.out.println("404 Not Found, bạn vui lòng liên hệ nhà phát hành!");
        }
    }
}
