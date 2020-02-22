package com.mfzn.deepuses.utils;

public class DoubleUtils {

    public static String doubleTrans1(double num){
        if(num % 1.0 == 0){
            return String.valueOf((long)num);
        }else {
            return String.valueOf(num);
        }
    }
}
