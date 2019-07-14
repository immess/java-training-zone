package org.github.immess.utils;

import java.io.*;
import java.util.ArrayList;

public class Utils {

    public static String str(InputStream stream) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        copy(stream,out);
        return out.toString("UTF-8");
    }

    public static void copy(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[2048];
        int length;
        while ((length = in.read(buffer)) > 0) {
            out.write(buffer, 0, length);
        }
    }

    public static void tryCopy(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[2048];
        int length;
        while ((length = in.available()) > 0) {
            int readCount = in.read(buffer, 0, length);
            out.write(buffer, 0, readCount);
        }
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

    public static String str(Exception cause) {
        StringWriter sw = new StringWriter();
        PrintWriter writer = new PrintWriter(sw);
        cause.printStackTrace(writer);
        return sw.toString();
    }
}
