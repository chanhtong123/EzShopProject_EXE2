package com.example.EzShopProject_EXE2.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static Date stringToDate(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Date date = dateFormat.parse(dateString);
        // Chuyển đổi sang định dạng chỉ chứa ngày-tháng-năm
        SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return newDateFormat.parse(newDateFormat.format(date));
    }

    public static String dateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }
}
