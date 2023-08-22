package services;

public class StringRandomizer {
    public static String newRString(int length) {
        // ~6.471.002 possibilities with no especial characters
        // ~10.424.128 possibilities with the especial characters  // i dont need this

        String AlphaNumericString =
        "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + //Uppercase
        "0123456789" + // Numerals
        "abcdefghijklmnopqrstuvxyz"; //Lowercase
        /* +"@#%&*+"; */// Especial characters
        
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = (int) (AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }
}
