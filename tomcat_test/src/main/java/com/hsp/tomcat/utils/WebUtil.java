package com.hsp.tomcat.utils;

/**
 * @author FAUST
 * @version 1.0
 * @date 2023/8/10 10:28
 */
public class WebUtil {
    public static int parseString(String str) {
        int num = 0;
        try {
            num=Integer.parseInt(str);
        } catch (NumberFormatException e) {
            System.out.println("error");
        }
        return num;
    }
}
