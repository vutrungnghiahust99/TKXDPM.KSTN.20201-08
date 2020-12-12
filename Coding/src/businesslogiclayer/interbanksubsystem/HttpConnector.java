package businesslogiclayer.interbanksubsystem;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Slf4j
public class HttpConnector {
    private final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(10000, TimeUnit.MILLISECONDS)
            .readTimeout(10000,TimeUnit.MILLISECONDS)
            .retryOnConnectionFailure(true)
            .build() ;

    public String patch(String bodyRequest) throws IOException {
        HttpPatch httpPatch = new HttpPatch("https://ecopark-system-api.herokuapp.com" + "/api/card/processTransaction");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        httpPatch.setEntity(new StringEntity(bodyRequest, ContentType.APPLICATION_JSON));

        HttpResponse httpResponse = httpClient.execute(httpPatch);
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode != 200)
        {
            throw new RuntimeException("Failed with HTTP error code : " + statusCode);
        }
        //Now pull back the response object
        HttpEntity httpEntity = httpResponse.getEntity();
        String apiOutput = EntityUtils.toString(httpEntity);

        System.out.println("Result : "+  apiOutput);
        return null;
    }

    public String sendPatch(String url , String body) {
        try {
            RequestBody requestBody = RequestBody.create(MediaType.parse(org.springframework.http.MediaType.APPLICATION_JSON_VALUE), body);
            Request request = new Request.Builder().url(url)
                    .patch(requestBody).build();
            Response response = client.newCall(request).execute();
            assert response.body() != null;
            return response.body().string();
        } catch (Exception e) {
            System.out.println("Lỗi rồi sếp ơi! (method: sendPatch)");
        }
        return null;
    }

    public  String sendGet(String url){
        try{
            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();
            assert response.body() != null;
            return  response.body().string();
        } catch (Exception e){
            System.out.println("Lỗi rồi sếp ơi! (method: sendGet)");
        }
        return null;
    }
}
