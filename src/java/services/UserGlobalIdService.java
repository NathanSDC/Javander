package services;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class UserGlobalIdService {
    private static final String URL_GET = "http://httpbin.org/ip";

    // i took it on stack overflow
     public static String getGlobalId(){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
          .GET()
          .timeout(Duration.ofSeconds(10))
          .uri(URI.create(URL_GET))
          .build();
        try{
            HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
            //System.out.println(response.body());
            return String.valueOf(response.statusCode());
        }
        catch(Exception e){
            return e.getMessage();
        }
    }
}
