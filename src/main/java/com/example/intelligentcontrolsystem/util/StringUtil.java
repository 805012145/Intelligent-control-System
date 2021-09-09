package com.example.intelligentcontrolsystem.util;

public class StringUtil {
    public static String[] StringToArray(String str) {
        String str1 = str.replace('{',' ');
        String str2 = str1.replace('}', ' ');
        String str3 = str2.replaceAll(" ", "");
        return str3.split(",");
    }

    public static boolean hasStr(String src, String dst, String route) {
        String str = src+","+dst;
        System.out.println(str);
        String str1 = route.replaceAll(" ", "");
        System.out.println(str1);
        if (str1.contains(str)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        long i = 10000000000l;
        System.out.println(i+1);
    }
}
