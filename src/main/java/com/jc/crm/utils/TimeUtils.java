package com.jc.crm.utils;

import java.util.Calendar;
import java.util.Date;

public class TimeUtils {
    public static Date getNowTime() {
        return new Date(System.currentTimeMillis());
    }
    public static long getTimeStamp() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTimeInMillis();
    }
}
