package com.mfzn.deepuses.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by sun on 2017/12/21.
 * 获取当前时间
 */

public class ObtainTime {

    //获取一个月前的时间
    public static String showTime(int year,int month,int day) {
        switch (month) {
            case 2:
                if(day > 28) {
                    if(year % 4 == 0 && year % 100 != 0 || year % 400 == 0){
                        return year + "/" + month + "/" + 29;
                    }else{
                        return year + "/" + month + "/" + 28;
                    }
                }else {
                    return year + "/" + month + "/" + day;
                }
            case 4:
                if(day > 30) {
                    return year + "/" + month + "/" + 30;
                }else {
                    return year + "/" + month + "/" + day;
                }
            case 6:
                if(day > 30) {
                    return year + "/" + month + "/" + 30;
                }else {
                    return year + "/" + month + "/" + day;
                }
            case 9:
                if(day > 30) {
                    return year + "/" + month + "/" + 30;
                }else {
                    return year + "/" + month + "/" + day;
                }
            case 11:
                if(day > 30) {
                    return year + "/" + month + "/" + 30;
                }else {
                    return year + "/" + month + "/" + day;
                }
            default:
                return year + "/" + month + "/" + day;
        }
    }

    //获取一个月前的时间
    public static String startTime() {

        String months2 = null;
        String days2 = null;

        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);

        //当前日期减去30获取一个月的日期
        c.set(Calendar.DAY_OF_MONTH, day - 30);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        int month2 = c.get(Calendar.MONTH) + 1;
        int year2 = c.get(Calendar.YEAR);

        if (month2 < 10) {
            months2 = "0" + month2;
        } else {
            months2 = "" + month2;
        }
        if (mDay < 10) {
            days2 = "0" + mDay;
        } else {
            days2 = "" + mDay;
        }

        String riqi2 = year2 + "-" + months2 + "-" + days2;

        return riqi2;
    }

    //获取当天的年月日
    public static String endTime() {

        String months = null;
        String days = null;

        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);

        if (month < 10) {
            months = "0" + month;
        } else {
            months = "" + month;
        }
        if (day < 10) {
            days = "0" + day;
        } else {
            days = "" + day;
        }

        String riqi = year + "." + months + "." + days;
        return riqi;
    }

    //获取当天的月日
    public static String monthDayTime() {

        String months = null;
        String days = null;

        Calendar c = Calendar.getInstance();
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);

        if (month < 10) {
            months = "0" + month;
        } else {
            months = "" + month;
        }
        if (day < 10) {
            days = "0" + day;
        } else {
            days = "" + day;
        }

        String riqi = months + "月" + days + "日";
        return riqi;
    }

    //获取当天的月
    public static String monthTime() {

        String months = null;

        Calendar c = Calendar.getInstance();
        int month = c.get(Calendar.MONTH) + 1;

        if (month < 10) {
            months = "0" + month;
        } else {
            months = "" + month;
        }

        return months;
    }

    //判断月是否为各位数
    public static String judgeMonth(int month) {

        if (month < 10) {
            return "0" + month + "月";
        } else {
            return "" + month + "月";
        }
    }

    //获取当天的日
    public static String dayTime() {

        String days = null;

        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);

        if (day < 10) {
            days = "0" + day;
        } else {
            days = "" + day;
        }

        return days;
    }

    //获取当天前一天的月日
    public static List<String> monthDay() {

        List<String> titles = new ArrayList<String>();

        String days = null;
        String months = null;

        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);

        //当前日期减去30获取一个月的日期
        c.set(Calendar.DAY_OF_MONTH, day - 1);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH) + 1;

        if (month < 10) {
            months = "0" + month;
        } else {
            months = "" + month;
        }
        if (mDay < 10) {
            days = "0" + mDay;
        } else {
            days = "" + mDay;
        }

        titles.add(months);
        titles.add(days);

        return titles;
    }

    //获取当前的日期时间
    public static String endDataTime() {

        String months = null;
        String days = null;

        Calendar calendar = Calendar.getInstance();
        //获取系统的日期
        //年
        int year = calendar.get(Calendar.YEAR);
        //月
        int month = calendar.get(Calendar.MONTH) + 1;
        //日
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        //获取系统时间
        //小时
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        //分钟
        int minute = calendar.get(Calendar.MINUTE);
        //秒
        int second = calendar.get(Calendar.SECOND);

        if (month < 10) {
            months = "0" + month;
        } else {
            months = "" + month;
        }
        if (day < 10) {
            days = "0" + day;
        } else {
            days = "" + day;
        }

        String data = year + "-" + months + "-" + days + "-" + hour + ":" + minute + ":" + second;

        return data;
    }
}
