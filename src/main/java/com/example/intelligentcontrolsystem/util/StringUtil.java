package com.example.intelligentcontrolsystem.util;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
//        String h = "h1";
//        String s= "s1";
//        Map<String, String> s2h = new HashMap<>();
//        s2h.put("name", h);
//        s2h.put("switch", s);
//        System.out.println(new Gson().toJson(s2h));
        List<String>[] lists = new List[3];
        lists[1] = Collections.singletonList("sss");
        System.out.println(lists);
    }
}
