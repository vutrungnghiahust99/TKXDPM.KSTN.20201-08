package businesslogiclayer.interbanksubsystem;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class InterbankBoundary {
    private String reset_path = "api/card/reset-balance" ;
    private String transaction_path = "api/card/processTransaction";
    private String base_url = "https://ecopark-system-api.herokuapp.com/";

    // reset thẻ
    public String reset(JsonObject body){
        try {
            String response = new HttpConnector().sendPatch(base_url + reset_path, body.toString());
            if (response != null && new JsonParser().parse(response).isJsonObject()) {
                JsonObject responseJson = new JsonParser().parse(response).getAsJsonObject();
                System.out.println(responseJson);
                String code = responseJson.get("errorCode").getAsString();
                return code;
            }
        } catch (Exception e){
            System.out.println("Không kết nối được API anh ơi!");
        }
        return "08";
    }

    // xử lý giao dịch: "pay" and "refund"
    public String processTransaction(JsonObject sentJson) {
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
        return "08";
    }
}
