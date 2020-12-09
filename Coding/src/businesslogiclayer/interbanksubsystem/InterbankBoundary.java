package businesslogiclayer.interbanksubsystem;

import businesslogiclayer.interbanksubsystem.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;


@Slf4j
public class InterbankBoundary {
    private String reset_path = "api/card/reset-balance" ;
    private String transaction_path = "api/card/processTransaction";
    private String base_url = "https://ecopark-system-api.herokuapp.com/";

    // reset thẻ
    public String reset(){
        JsonObject body = new JsonObject();
        body.addProperty("cardCode", "118131_group8_2020");
        body.addProperty("owner", "Group 8");
        body.addProperty("cvvCode", "427");
        body.addProperty("dateExpired", "1125");

        try {
            String response = new HttpConnector().sendPatch(base_url + reset_path, body.toString());
            if (response != null && new JsonParser().parse(response).isJsonObject()) {
                JsonObject responseJson = new JsonParser().parse(response).getAsJsonObject();
                System.out.println(responseJson);
                String code = responseJson.get("errorCode").getAsString();
                if (code.equals("00")) {
                    System.out.println("Reset balance thành công rồi sếp ơi!");
                }
                return code;
            }
        } catch (Exception e){
            System.out.println("Không kết nối được API anh ơi!");
        }
        return "Không kết nối được API";
    }

    // xử lý giao dịch: "pay" and "refund"
    public String processTransaction(InterbankTransaction transaction) {
        try {
            // Chuyển transaction sang Json object
            String transactString = new ObjectMapper().writeValueAsString(transaction);
            JsonObject transactionBody = new JsonParser().parse(transactString).getAsJsonObject();
            JsonObject transToHash = new JsonObject();

            // transToHash  là chuỗi cần băm
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

            // response from api
            try {
                String response =  new HttpConnector().sendPatch(base_url + transaction_path,sentJson.toString());
                if (response != null && new JsonParser().parse(response).isJsonObject()) {
                    JsonObject responseJson = new JsonParser().parse(response).getAsJsonObject();
                    System.out.println("Giao dịch:" + responseJson);
                    String code = responseJson.get("errorCode").getAsString();
                    return code ;
                }

            } catch (Exception e) {
                System.out.println("Thằng api không response anh ơi!");
            }
        } catch (Exception e){
            System.out.println("Xử lý giao dịch lỗi anh ơi!");
        }
        return "08" ;
       }

        // Mã lỗi
       public String codeToDetail(String code){
            if (code.equals("00")){
                return "Giao dịch thành công" ;
            }
            else if (code.equals("01")){
                return "Thẻ không hợp lệ" ;
            }
            else if (code.equals("02")){
                return "Thẻ không đủ số dư" ;
            }
            else if (code.equals("03")){
                return "Internal Server Error";
            }
            else if (code.equals("04")){
                return "Giao dịch bị nghi ngờ gian lận";
            }
            else if (code.equals("05")){
                return "Không đủ thông tin giao dịch";
            }
            else if (code.equals("06")){
                return "Thiếu thông tin version";
            }
            else if (code.equals("07")){
               return "Amount không hợp lệ" ;
           } else {
                return "Something wrong !!!" ;
            }
       }
}
