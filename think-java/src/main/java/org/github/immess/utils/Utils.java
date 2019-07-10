package org.github.immess.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Utils {

    public static String str(InputStream stream) throws IOException {
        byte[] buffer = new byte[2048];
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int length;
        while ((length = stream.read(buffer)) > 0) {
            out.write(buffer, 0, length);
        }
        return out.toString("UTF-8");
    }

    public static String[] splitWithQuote(String source, char delimiter, char quote) {
        ArrayList<String> result = new ArrayList<>();

        int prev = 0;
        int length = source.length();
        for (int current = 0; current < length; ++current) {
            if (source.charAt(current) == delimiter) {
                continue;
            }
            prev = current;
            if (source.charAt(current) == quote) {
                while (current < length-1 && source.charAt(current + 1) != quote) {
                    ++current;
                }
                if (prev < length - 1) {
                    result.add(source.substring(prev + 1, current + 1));
                    ++current;
                } else {
                    result.add("");
                }
            } else {
                while (current < length-1 && source.charAt(current + 1) != delimiter) {
                    ++current;
                }
                result.add(source.substring(prev, current + 1));
            }
        }

        return result.toArray(new String[0]);
    }
}
