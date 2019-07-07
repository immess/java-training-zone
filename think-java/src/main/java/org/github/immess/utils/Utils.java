package org.github.immess.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

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
}
