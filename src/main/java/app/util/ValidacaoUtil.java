package app.util;

public class ValidacaoUtil {
    // Checa se um conjunto de strings são NOT NULL e NOT BLANK
    public static boolean blank(String... strings) {
        for (String s : strings) {
            if (s.isBlank())
                return true;
        }
        return false;
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
 }
