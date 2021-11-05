package com.example.intelligentcontrolsystem.util;
import java.text.ParseException;

public class StringUtil {
    public static String[] StringToArray(String str) {
        String str1 = str.replace('{',' ');
        String str2 = str1.replace('}', ' ');
        String str3 = str2.replace('[',' ');
        String str4 = str3.replace(']', ' ');
        String str5 = str4.replace('(', ' ');
        String str6 = str5.replace(')', ' ');
        String str7 = str6.replaceAll(" ", "");
        return str7.split(",");
    }

    public static boolean hasStr(String src, String dst, String route) {
        String str = src+","+dst;
        String str1 = route.replaceAll(" ", "");
        if (str1.contains(str)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws ParseException {
//        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSSS");
//        Date date = new Date(System.currentTimeMillis());
//        System.out.println(formatter.format(date));
//        Long time = formatter.parse(formatter.format(date)).getTime();
//        Long time2 = 1635931348369L;
//        System.out.println(time.compareTo(time2));
//        String table = "src:10.0.0.1 srcport:1 dst:10.0.0.2 dstport:1";
//        String[] quaternion = table.split(" ");
//
//        System.out.println(quaternion[0].split(":")[1]);
        String str = "[1,2,3]";
        if (str.contains("1")) {
            System.out.println("1");
        }
    }
}
