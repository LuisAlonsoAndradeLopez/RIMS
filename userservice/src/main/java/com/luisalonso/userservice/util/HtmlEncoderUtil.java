package com.luisalonso.userservice.util;

import org.owasp.encoder.Encode;

public class HtmlEncoderUtil {

    private HtmlEncoderUtil() {
        // Prevent instantiation
    }

    public static String encode(String input) {

        if (input == null) {
            return null;
        }

        return Encode.forHtml(input);

    }

}
