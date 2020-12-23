package businesslogiclayer.barcodconvertersubsystem;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import java.util.concurrent.TimeUnit;

@Slf4j
/**
 * Tạo kết nối tới api barcode converter
 */
public class HttpConnector {
    private final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(10000, TimeUnit.MILLISECONDS)
            .readTimeout(10000,TimeUnit.MILLISECONDS)
            .retryOnConnectionFailure(true)
            .build() ;

    /**
     * tao request de gui len api
     * @param url : duong dan toi api
     * @param body : noi dung request (chua barcode)
     * @return response
     */
    public String post(String url , String body) {
        try {
            RequestBody requestBody = RequestBody.create(MediaType.parse(org.springframework.http.MediaType.APPLICATION_JSON_VALUE), body);
            Request request = new Request.Builder().url(url)
                    .post(requestBody).build();
            Response response = client.newCall(request).execute();
            assert response.body() != null;
            return response.body().string();
        } catch (Exception e) {
            System.out.println("Lỗi rồi sếp ơi! (method: sendPatch)");
        }
        return null;
    }
}