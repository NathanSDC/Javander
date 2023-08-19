package application;

public class Utilities {

    public static String RandomId(int length) {
        // ID cannot contain a character value greater than the limit 5
        int limit = 5;

        String result = RandomString(limit);
        for (int i = 0; i < (length - 1); i++) {
            result += "-" + RandomString(limit);
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
