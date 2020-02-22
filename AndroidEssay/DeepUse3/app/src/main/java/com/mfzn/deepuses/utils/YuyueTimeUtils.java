package com.mfzn.deepuses.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class YuyueTimeUtils {

    public static List<String> fillData(){

        int[] ints = currentTime();
        int month = ints[1];

        List<String> strings = null;
        if(month == 1){
            strings = fillJanuary(month);
        }else if(month == 2){
            strings = fillFebruary(month);
        }else if(month == 3){
            strings = fillMarch(month);
        }else if(month == 4){
            strings = fillApril(month);
        }else if(month == 5){
            strings = fillMay(month);
        }else if(month == 6){
            strings = fillJune(month);
        }else if(month == 7){
            strings = fillJuly(month);
        }else if(month == 8){
            strings = fillAugust(month);
        }else if(month == 9){
            strings = fillSeptember(month);
        }else if(month == 10){
            strings = fillOctober(month);
        }else if(month == 11){
            strings = fillNovember(month);
        }else if(month == 12){
            strings = fillDecember(month);
        }
        return strings;
    }

    public static List<List<String>> timeData(){
        List<String> s = new ArrayList<>();
        List<String> strings = fillTime();//时间集合
        List<String> list1 = fillData();//日期集合
        List<List<String>> list = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        //小时
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        for (int i = hour ; i < strings.size() ; i++){
            String s1 = strings.get(i);
            s.add(s1);
        }
        list.add(s);//添加当天的时间
        //添加以后的时间
        for (int i = 1 ; i < list1.size() ; i++){
            list.add(strings);
        }

        return list;
    }

    private static List<String> fillTime(){
        List<String> strings = new ArrayList<>();

        strings.add("00:00~01:00");
        strings.add("01:00~02:00");
        strings.add("02:00~03:00");
        strings.add("03:00~04:00");
        strings.add("04:00~05:00");
        strings.add("05:00~06:00");
        strings.add("06:00~07:00");
        strings.add("07:00~08:00");
        strings.add("08:00~09:00");
        strings.add("09:00~10:00");
        strings.add("10:00~11:00");
        strings.add("11:00~12:00");
        strings.add("12:00~13:00");
        strings.add("13:00~14:00");
        strings.add("14:00~15:00");
        strings.add("15:00~16:00");
        strings.add("16:00~17:00");
        strings.add("17:00~18:00");
        strings.add("18:00~19:00");
        strings.add("19:00~20:00");
        strings.add("20:00~21:00");
        strings.add("21:00~22:00");
        strings.add("22:00~23:00");
        strings.add("23:00~24:00");

        return strings;
    }

    //当前是一月时填充的数据 1
    private static List<String> fillJanuary(int month){
        int[] ints = currentTime();
        int year = ints[0];
        int day = ints[2];

        List<String> dataList = new ArrayList<>();
        String months = "0" + month;
        String days = null;

        for (int i = day ; i <= 31 ; i++){
            if (i < 10) {
                days = "0" + i;
            } else {
                days = "" + i;
            }
            dataList.add(year + "/" + months + "/" + days);
        }
        int m = month + 1;
        String monthss = "0" + m;
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            for (int i = 1 ; i <= 29 ; i++){
                if (i < 10) {
                    days = "0" + i;
                } else {
                    days = "" + i;
                }
                dataList.add(year + "/" + monthss + "/" + days);
            }
        } else {
            for (int i = 1 ; i <= 28 ; i++){
                if (i < 10) {
                    days = "0" + i;
                } else {
                    days = "" + i;
                }
                dataList.add(year + "/" + monthss + "/" + days);
            }
        }
        return dataList;
    }

    //当前是一月时填充的数据 2
    private static List<String> fillFebruary(int month){
        int[] ints = currentTime();
        int year = ints[0];
        int day = ints[2];

        List<String> dataList = new ArrayList<>();
        String months = "0" + month;
        String days = null;

        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            for (int i = 1 ; i <= 29 ; i++){
                if (i < 10) {
                    days = "0" + i;
                } else {
                    days = "" + i;
                }
                dataList.add(year + "/" + months + "/" + days);
            }
        } else {
            for (int i = 1 ; i <= 28 ; i++){
                if (i < 10) {
                    days = "0" + i;
                } else {
                    days = "" + i;
                }
                dataList.add(year + "/" + months + "/" + days);
            }
        }
        int m = month + 1;
        String monthss = "0" + m;
        for (int i = 1 ; i <= 31 ; i++){
            if (i < 10) {
                days = "0" + i;
            } else {
                days = "" + i;
            }
            dataList.add(year + "/" + monthss + "/" + days);
        }
        return dataList;
    }

    //当前是一月时填充的数据 3
    private static List<String> fillMarch(int month){
        int[] ints = currentTime();
        int year = ints[0];
        int day = ints[2];

        List<String> dataList = new ArrayList<>();
        String months = "0" + month;
        String days = null;

        for (int i = day ; i <= 31 ; i++){
            if (i < 10) {
                days = "0" + i;
            } else {
                days = "" + i;
            }
            dataList.add(year + "/" + months + "/" + days);
        }
        int m = month + 1;
        String monthss = "0" + m;
        for (int i = 1 ; i < 31 ; i++){
            if (i < 10) {
                days = "0" + i;
            } else {
                days = "" + i;
            }
            dataList.add(year + "/" + monthss + "/" + days);
        }
        return dataList;
    }

    //当前是一月时填充的数据 4
    private static List<String> fillApril(int month){
        int[] ints = currentTime();
        int year = ints[0];
        int day = ints[2];

        List<String> dataList = new ArrayList<>();
        String months = "0" + month;
        String days = null;

        for (int i = day ; i <= 30 ; i++){
            if (i < 10) {
                days = "0" + i;
            } else {
                days = "" + i;
            }
            dataList.add(year + "/" + months + "/" + days);
        }
        int m = month + 1;
        String monthss = "0" + m;
        for (int i = 1 ; i <= 31 ; i++){
            if (i < 10) {
                days = "0" + i;
            } else {
                days = "" + i;
            }
            dataList.add(year + "/" + monthss + "/" + days);
        }
        return dataList;
    }

    //当前是一月时填充的数据 5
    private static List<String> fillMay(int month){
        int[] ints = currentTime();
        int year = ints[0];
        int day = ints[2];

        List<String> dataList = new ArrayList<>();
        String months = "0" + month;
        String days = null;

        for (int i = day ; i <= 31 ; i++){
            if (i < 10) {
                days = "0" + i;
            } else {
                days = "" + i;
            }
            dataList.add(year + "/" + months + "/" + days);
        }
        int m = month + 1;
        String monthss = "0" + m;
        for (int i = 1 ; i <= 30 ; i++){
            if (i < 10) {
                days = "0" + i;
            } else {
                days = "" + i;
            }
            dataList.add(year + "/" + monthss + "/" + days);
        }
        return dataList;
    }

    //当前是一月时填充的数据 6
    private static List<String> fillJune(int month){
        int[] ints = currentTime();
        int year = ints[0];
        int day = ints[2];

        List<String> dataList = new ArrayList<>();
        String months = "0" + month;
        String days = null;

        for (int i = day ; i <= 30 ; i++){
            if (i < 10) {
                days = "0" + i;
            } else {
                days = "" + i;
            }
            dataList.add(year + "/" + months + "/" + days);
        }
        int m = month + 1;
        String monthss = "0" + m;
        for (int i = 1 ; i <= 31 ; i++){
            if (i < 10) {
                days = "0" + i;
            } else {
                days = "" + i;
            }
            dataList.add(year + "/" + monthss + "/" + days);
        }
        return dataList;
    }

    //当前是一月时填充的数据 7
    private static List<String> fillJuly(int month){
        int[] ints = currentTime();
        int year = ints[0];
        int day = ints[2];

        List<String> dataList = new ArrayList<>();
        String months = "0" + month;
        String days = null;

        for (int i = day ; i <= 31 ; i++){
            if (i < 10) {
                days = "0" + i;
            } else {
                days = "" + i;
            }
            dataList.add(year + "/" + months + "/" + days);
        }
        int m = month + 1;
        String monthss = "0" + m;
        for (int i = 1 ; i <= 31 ; i++){
            if (i < 10) {
                days = "0" + i;
            } else {
                days = "" + i;
            }
            dataList.add(year + "/" + monthss + "/" + days);
        }
        return dataList;
    }

    //当前是一月时填充的数据 8
    private static List<String> fillAugust(int month){
        int[] ints = currentTime();
        int year = ints[0];
        int day = ints[2];

        List<String> dataList = new ArrayList<>();
        String months = "0" + month;
        String days = null;

        for (int i = day ; i <= 31 ; i++){
            if (i < 10) {
                days = "0" + i;
            } else {
                days = "" + i;
            }
            dataList.add(year + "/" + months + "/" + days);
        }
        int m = month + 1;
        String monthss = "0" + m;
        for (int i = 1 ; i <= 30 ; i++){
            if (i < 10) {
                days = "0" + i;
            } else {
                days = "" + i;
            }
            dataList.add(year + "/" + monthss + "/" + days);
        }
        return dataList;
    }

    //当前是一月时填充的数据 9
    private static List<String> fillSeptember(int month){
        int[] ints = currentTime();
        int year = ints[0];
        int day = ints[2];

        List<String> dataList = new ArrayList<>();
        String months = "0" + month;
        String days = null;

        for (int i = day ; i <= 30 ; i++){
            if (i < 10) {
                days = "0" + i;
            } else {
                days = "" + i;
            }
            dataList.add(year + "/" + months + "/" + days);
        }
        int m = month + 1;
        for (int i = 1 ; i <= 31 ; i++){
            if (i < 10) {
                days = "0" + i;
            } else {
                days = "" + i;
            }
            dataList.add(year + "/" + m + "/" + days);
        }
        return dataList;
    }

    //当前是一月时填充的数据 10
    private static List<String> fillOctober(int month){
        int[] ints = currentTime();
        int year = ints[0];
        int day = ints[2];

        List<String> dataList = new ArrayList<>();
        String days = null;

        for (int i = day ; i <= 31 ; i++){
            if (i < 10) {
                days = "0" + i;
            } else {
                days = "" + i;
            }
            dataList.add(year + "/" + month + "/" + days);
        }
        int m = month + 1;
        for (int i = 1 ; i <= 30 ; i++){
            if (i < 10) {
                days = "0" + i;
            } else {
                days = "" + i;
            }
            dataList.add(year + "/" + m + "/" + days);
        }
        return dataList;
    }

    //当前是一月时填充的数据 11
    private static List<String> fillNovember(int month){
        int[] ints = currentTime();
        int year = ints[0];
        int day = ints[2];

        List<String> dataList = new ArrayList<>();
        String days = null;

        for (int i = day ; i <= 30 ; i++){
            if (i < 10) {
                days = "0" + i;
            } else {
                days = "" + i;
            }
            dataList.add(year + "/" + month + "/" + days);
        }
        int m = month + 1;
        for (int i = 1 ; i <= 31 ; i++){
            if (i < 10) {
                days = "0" + i;
            } else {
                days = "" + i;
            }
            dataList.add(year + "/" + m + "/" + days);
        }
        return dataList;
    }

    //当前是一月时填充的数据 12
    private static List<String> fillDecember(int month){
        int[] ints = currentTime();
        int year = ints[0];
        int day = ints[2];

        List<String> dataList = new ArrayList<>();
        String days = null;

        for (int i = day ; i <= 31 ; i++){
            if (i < 10) {
                days = "0" + i;
            } else {
                days = "" + i;
            }
            dataList.add(year + "/" + month + "/" + days);
        }
        int y = year + 1;
        for (int i = 1 ; i <= 31 ; i++){
            if (i < 10) {
                days = "0" + i;
            } else {
                days = "" + i;
            }
            dataList.add(y + "/" + "01" + "/" + days);
        }
        return dataList;
    }

    //获取当天的年月日
    private static int[] currentTime() {

        String months = null;
        String days = null;
        int[] ints = new int[3];

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

        ints[0] = year;
        ints[1] = month;
        ints[2] = day;

        String riqi = year + "/" + months + "/" + days;
        return ints;
    }
}
