package com.mfzn.deepuses.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 时间日期工具类
 *
 * @author zhangbostay
 * @date 2019/4/12
 */
public class DateUtils {

    public static String dateFormat(String dateFormat, Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.getDefault());
        return sdf.format(date);
    }

    /**
     * 字符串日期格式的计算
     *
     * @param smdate
     * @param bdate
     * @return
     * @throws ParseException
     */
    public static int daysBetween(String smdate, String bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(between_days));
    }

    public static String longToString(long millSec) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
        Date date = new Date(millSec * 1000);
        return sdf.format(date);
    }

    /**
     * 秒转换成时间
     *
     * @param dateFormat
     * @param millSec
     * @return
     */
    public static String longToString(String dateFormat, Long millSec) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.getDefault());
        Date date = new Date(millSec * 1000);
        return sdf.format(date);
    }

    public static String longToDateStr(String millSec) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日", Locale.getDefault());
        Date date = new Date(Long.valueOf(millSec) * 1000);
        return sdf.format(date);
    }

    // strTime要转换的String类型的时间
    // formatType时间格式
    // strTime的时间格式和formatType的时间格式必须相同
    public static long stringToLong(String strTime, String formatType) {
        // String类型转成date类型
        Date date = stringToDate(strTime, formatType);
        if (date == null) {
            return 0;
        } else {
            // date类型转成long类型
            return dateToLong(date);
        }
    }

    // strTime要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日
    // HH时mm分ss秒，
    // strTime的时间格式必须要与formatType的时间格式相同
    public static Date stringToDate(String strTime, String formatType) {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType, Locale.getDefault());
        Date date = null;
        try {
            date = formatter.parse(strTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    // date要转换的date类型的时间
    public static long dateToLong(Date date) {
        return date.getTime();
    }

    /**
     * 计算两个时间之间的相差的天数
     *
     * @param date1 开始时间
     * @param date2 结束时间
     * @return
     */
    public static int compareDays(Date date1, Date date2) {
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.setTime(date1);
        calendar2.setTime(date2);
        int day1 = calendar1.get(Calendar.DAY_OF_YEAR);
        int day2 = calendar2.get(Calendar.DAY_OF_YEAR);
        int year1 = calendar1.get(Calendar.YEAR);
        int year2 = calendar2.get(Calendar.YEAR);
        if (year1 > year2) {
            int tempyear = year1;
            int tempday = day1;
            day1 = day2;
            day2 = tempday;
            year1 = year2;
            year2 = tempyear;
        }
        if (year1 == year2) {
            return day2 - day1;
        } else {
            int DayCount = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {
                    DayCount += 366;
                } else {
                    DayCount += 365;
                }
            }
            return DayCount + (day2 - day1);
        }
    }

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s, String format) {
        if (s == null || s.isEmpty() || s.equals("null")) {
            return "";
        }
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
//        return sdf.format(new Date(Long.valueOf(s + "000")));
        long lt = new Long(s);
        Date date = new Date(lt * 1000);
        String res = sdf.format(date);
        return res;
    }

    /*
     * 将时间戳转换为 日期 时间
     */
    public static String stampToDateTime(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        long lt = new Long(s);
        Date date = new Date(lt * 1000);
        res = simpleDateFormat.format(date);
        return res;
    }

    public static String stampToDateTime4(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM月dd日 HH:mm");
        long lt = new Long(s);
        Date date = new Date(lt * 1000);
        res = simpleDateFormat.format(date);
        return res;
    }

    public static String stampToDateTime2(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        long lt = new Long(s);
        Date date = new Date(lt * 1000);
        res = simpleDateFormat.format(date);
        return res;
    }

    public static String stampToDateTime3(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt * 1000);
        res = simpleDateFormat.format(date);
        return res;
    }

    /*
     * 将时间戳转换为 日期 时间
     */
    public static String stampToDate(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long lt = new Long(s);
        Date date = new Date(lt * 1000);
        res = simpleDateFormat.format(date);
        return res;
    }

    /*
     * 将时间戳转换为 日期 时间
     */
    public static String stampDate(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        long lt = new Long(s);
        Date date = new Date(lt * 1000);
        res = simpleDateFormat.format(date);
        return res;
    }

    //将字符串转为时间戳   pattern yyyy/MM/dd
    public static String getStringToDate(String user_time) {
        String re_time = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date d;
        try {
            d = sdf.parse(user_time);
            long l = d.getTime();
            String str = String.valueOf(l);
            re_time = str.substring(0, 10);
        }catch (ParseException e) {
        }
        return re_time;
    }

    //将字符串转为时间戳   pattern yyyy/MM/dd
    public static String getStringToDate2(String user_time) {
        String re_time = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date d;
        try {
            d = sdf.parse(user_time);
            long l = d.getTime();
            String str = String.valueOf(l);
            re_time = str.substring(0, 10);
        }catch (ParseException e) {
        }
        return re_time;
    }

    /*
     * 将时间戳转换为 日期 时间
     */
    public static String stampDateTime(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM月dd日 HH:mm");
        long lt = new Long(s);
        Date date = new Date(lt * 1000);
        res = simpleDateFormat.format(date);
        return res;
    }

    public static Calendar getCalendar(String date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(stringToDate(date, "yyyy-MM-dd"));
        return calendar;
    }
}
