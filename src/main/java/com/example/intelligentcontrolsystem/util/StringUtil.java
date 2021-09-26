package com.example.intelligentcontrolsystem.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.intelligentcontrolsystem.dao.impl.Link_Dao_impl;
import com.example.intelligentcontrolsystem.entity.Link;
import com.example.intelligentcontrolsystem.entity.SrcDstPair;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.DateFormat;
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
        System.out.println(str);
        String str1 = route.replaceAll(" ", "");
        System.out.println(str1);
        if (str1.contains(str)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String str = "100.01";
        System.out.println(Double.parseDouble(str));
    }
}
