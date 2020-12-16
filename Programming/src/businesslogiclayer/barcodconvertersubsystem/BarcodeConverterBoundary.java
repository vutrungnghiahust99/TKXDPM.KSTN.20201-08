package businesslogiclayer.barcodconvertersubsystem;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class BarcodeConverterBoundary {
    /**
     * chuyen barcode thanh bike code
     * @param body : noi dung request gui len api
     */
    public static String convertBarcodeToBikeCode(JsonObject body){
        try {
            String path = "https://barcodeservicebykv2.herokuapp.com/barcode";
            String response = new HttpConnector().post(path, body.toString());
            if (response != null && new JsonParser().parse(response).isJsonObject()) {
                JsonObject responseJson = new JsonParser().parse(response).getAsJsonObject();
                System.out.println(responseJson);
                return responseJson.get("id").getAsString();
            }
        } catch (Exception e){
            System.out.println("Không kết nối được API anh ơi!");
        }
        return null;
    }
}
