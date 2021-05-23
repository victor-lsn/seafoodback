package com.victor.seafoodback.util;

import java.util.Random;

public class CodeUtil {

    public static String codes = "0123456789qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";

    public static String getCode(){
        String code = "";
        for (int i = 0; i < 4; i++) {
            char c = codes.charAt(new Random().nextInt(codes.length()));
            String s = String.valueOf(c);
            code += s;
        }
        return code;
    }
}
