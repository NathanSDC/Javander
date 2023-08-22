package application;

import java.util.regex.Pattern;

public class Utilities {
    // --- Patterns for validation
    public static Pattern CPFpattern = Pattern.compile("([0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[-]?[0-9]{2})",Pattern.CASE_INSENSITIVE);
    public static Pattern EmailPattern = Pattern.compile("([a-z0-9.]+@[a-z0-9]+\\.[a-z]+\\.[a-z]+\\.?\\w([a-z]+)?)|([a-z0-9.]+@[a-z0-9]+\\.[a-z]+\\.?\\w([a-z]+)?)",Pattern.CASE_INSENSITIVE);
}
