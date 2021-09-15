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
        String str5 = str4.replaceAll(" ", "");
        return str5.split(",");
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
        Util util = Util.getInstance();

        List<List<String>> bwInfo = new ArrayList<>();
        List<String> head = new ArrayList<>();
        head.add("remain");
        head.add("max");
        bwInfo.add(head);
        List<String> channel1 = new ArrayList<>();
        String re1 = "10";
        String max1 = "10";
        channel1.add(re1);
        channel1.add(max1);
        bwInfo.add(channel1);
        List<String> channel2 = new ArrayList<>();
        String re2 = "10";
        String max2 = "10";
        channel2.add(re2);
        channel2.add(max2);
        bwInfo.add(channel2);
        List<String> channel3 = new ArrayList<>();
        String re3 = "10";
        String max3 = "10";
        channel3.add(re3);
        channel3.add(max3);
        bwInfo.add(channel3);
        List<String> channel4 = new ArrayList<>();
        String re4 = "10";
        String max4 = "10";
        channel4.add(re4);
        channel4.add(max4);
        bwInfo.add(channel4);
        Gson bwinfo = new Gson();
        System.out.println(bwinfo.toJson(bwInfo));
    }
}
