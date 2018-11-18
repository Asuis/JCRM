package com.jc.crm.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Utils {
    public static String encode(String str) {
        byte[] b = null;
        String s = null;
        b = str.getBytes(StandardCharsets.UTF_8);
        s = Base64.getEncoder().encodeToString(b);
        return s;
    }
    public static String decode(String s) {
        byte[] b = null;
        String result = null;
        if (s!=null) {
            BASE64Decoder decoder = new BASE64Decoder();
            b = Base64.getDecoder().decode(s);
            result = new String(b, StandardCharsets.UTF_8);
        }
        return result;
    }
}
