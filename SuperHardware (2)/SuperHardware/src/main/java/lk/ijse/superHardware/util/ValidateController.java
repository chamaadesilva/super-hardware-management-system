package lk.ijse.superHardware.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateController {
    public static boolean intValueCheck(String value) {
        String intRegex = "^-?[0-9]+$";
        Pattern pattern = Pattern.compile(intRegex);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
}
