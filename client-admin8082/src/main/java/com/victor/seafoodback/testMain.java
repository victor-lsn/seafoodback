package com.victor.seafoodback;

import com.victor.seafoodback.entity.Seafood;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import sun.plugin.javascript.navig.Array;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.*;

public class testMain {

    public static void main(String[] args) {
        getCount("23co  untA$$  ");
    }

    public static void getCount(String str) {
        int spaceCount = 0;
        int abcCount = 0;
        int count = 0;
        int elseCount = 0;

        String[] intList = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String[] strList = {"q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "a", "s", "d", "f", "g", "h", "j", "k", "l",
                "z", "x", "c", "v", "b", "n", "m"};
        for (int i = 0; i < str.length(); i++) {
            int flag = 0;
            char c = str.charAt(i);
            String s = String.valueOf(c);
            if (s.equals(" ")) {
                spaceCount++;
                flag = 1;
                continue;
            } else {
                for (int j = 0; j < intList.length; j++) {
                    if (s.equals(intList[j])) {
                        count++;
                        flag = 1;
                        break;
                    }
                }

                if (flag == 0) {
                    for (int j = 0; j < strList.length; j++) {
                        if (s.equalsIgnoreCase(strList[j])) {
                            abcCount++;
                            flag = 1;
                            break;
                        }
                    }
                }
                if (flag == 0) {
                    elseCount++;
                }
            }
        }
        System.out.println("空格数量：" + spaceCount);
        System.out.println("字母数量：" + abcCount);
        System.out.println("数字数量：" + count);
        System.out.println("其他数量" + elseCount);
    }

}

