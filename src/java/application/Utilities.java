package application;

import application.enums.Register;
// i took it on stack overflow
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import java.util.regex.Pattern;

public class Utilities {
    // --- Patterns for validation
    public static Pattern CPFpattern = Pattern.compile("([0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[-]?[0-9]{2})",Pattern.CASE_INSENSITIVE);
    public static Pattern EmailPattern = Pattern.compile("([a-z0-9.]+@[a-z0-9]+\\.[a-z]+\\.[a-z]+\\.?\\w([a-z]+)?)|([a-z0-9.]+@[a-z0-9]+\\.[a-z]+\\.?\\w([a-z]+)?)",Pattern.CASE_INSENSITIVE);
    private static String processId = "";
    private static Register status;
    private static final String URL_GET = "http://httpbin.org/ip";


    public static String RandomId(int length) {
        // ID cannot contain a character value greater than the limit 5
        int limit = 5;
        
        String result = RandomString(limit);
        for (int i = 0; i < (length - 1); i++) {
            result += "-" + RandomString(limit);
        }
        
        processId = result;
        return result;
    }

    public static void Process(){
        System.out.println("Developer vision: ");
        System.out.println(" * Process ID: '"+processId+"';");
        status = Register.GETTING_DATA;
        System.out.println(" * "+status);
        getGlobalId();
        status = Register.VALIDATING_DATA;
        System.out.println(" * "+status);
    }

    // i took it on stack overflow
    private static String getGlobalId(){
        String result = "";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
          .GET()
          .timeout(Duration.ofSeconds(10))
          .uri(URI.create(URL_GET))
          .build();
        try{
            HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
            System.out.println(" * " + response.statusCode());
            System.out.println(response.body());
            result = response.body();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

        return result;
    }


    public static String RandomString(int length) {
        // ~6.471.002 possibilities with no especial characters
        // ~10.424.128 possibilities with the especial characters
        // 29.797.127.274.205.127.375.894.525.715.105.457.488.654.045.430.962.069.871.877.958.268.968.768

        String AlphaNumericString =
        "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + //Uppercase
        "0123456789" + // Numerals
        "abcdefghijklmnopqrstuvxyz" //Lowercase
        +"@#%&*+"; // Especial characters
        
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = (int) (AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }
}
