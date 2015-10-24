package com.saike.grape.dao.utils;

import java.math.BigDecimal;
import java.text.NumberFormat;

import org.apache.commons.lang.StringUtils;

public class DAOStringUtils {

    /**
     * 判断对象是否为空，不为空则返回字符串，否则返回空，用于从Map中取值转化成字符串
     */
    public static String getStringValue(Object obj) {
        if (obj != null) {
            return obj.toString();
        } else {
            return "";
        }
    }

    /**
     * 判断对象是否为空，不为空则返回字符串，否则返回空，用于从Map中取值转化成字符串
     */
    public static float getFloatValue(Object obj) {
        if (obj != null) {
            BigDecimal   bd   =   new   BigDecimal(obj.toString());  
            bd   =   bd.setScale(2,BigDecimal.ROUND_HALF_UP);
            return bd.floatValue();
        } else {
            return 0;
        }
    }

    /**
     * 判断对象是否为空，不为空则返回字符串，否则返回空，用于从Map中取值转化成字符串
     */
    public static int getIntValue(Object obj) {
        if (obj != null) {
            return Integer.valueOf(obj.toString());
        } else {
            return 0;
        }
    }

    
    /**
     * 判断对象是否为空，不为空则返回字符串，否则返回空，用于从Map中取值转化成字符串
     */
    public static Long getLongValue(Object obj) {
        if (obj != null) {
            return Long.valueOf(obj.toString());
        } else {
            return 0L;
        }
    }

    /**
     * 用于从map中获取折扣值
     */
    public static float getDiscount(Object obj) {
        return getFloatValue(obj) == 0 ? 1 : getFloatValue(obj);
    }
    
    /**
     * 将任意数字对象转成两位小数的字符串
     */
    public static String formatNumber(Number number, int digits){
        NumberFormat numberFormat = NumberFormat.getInstance(); 
        numberFormat.setMaximumFractionDigits(digits);
        numberFormat.setMinimumFractionDigits(digits);
        return StringUtils.replace(numberFormat.format(number), ",", "");
    }
    
    
    /**
     * 将任意数字乴组成的字符串转成两位小数的字符串
     */
    public static String formatNumberFromStr(String numberStr, int digits){
        NumberFormat numberFormat = NumberFormat.getInstance(); 
        numberFormat.setMaximumFractionDigits(digits);
        numberFormat.setMinimumFractionDigits(digits);
        return StringUtils.replace(numberFormat.format(numberStr), ",", "");
    }
    
    
    /**
     * 将任意数字或者数字字符串转成两位小数的字符串
     */
//    public static String formatNumberByObject(Object Object, int digits){
//        NumberFormat numberFormat = NumberFormat.getInstance(); 
//        numberFormat.setMaximumFractionDigits(digits);
//        numberFormat.setMinimumFractionDigits(digits);
//        return StringUtils.replace(numberFormat.format(Object), ",", "");
//    }
    
}
