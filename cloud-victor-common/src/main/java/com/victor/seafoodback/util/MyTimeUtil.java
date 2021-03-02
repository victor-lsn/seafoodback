package com.victor.seafoodback.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MyTimeUtil {

    public static Date dealDateFormat(String oldDate) {
        if (oldDate == null){
            return null;
        }
        Date date1 = null;
        DateFormat df2 = null;
        try {
            oldDate= oldDate.replace("Z", " UTC");
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
            date1 = df.parse(oldDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date1;
    }
}
