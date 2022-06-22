package makingInfo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Cipher {
    private static String deencode(String string) {
        char xorKey = 'P';

        String outputString = "";

        int len = string.length();

        for (int i = 0; i < len; i++)
            outputString = outputString + (char) (string.charAt(i) ^ xorKey);

        return outputString;
    }

    public static String encode(String string) {
        return deencode(string);
    }

    public static String decode(String string) {
        return deencode(string);
    }
}