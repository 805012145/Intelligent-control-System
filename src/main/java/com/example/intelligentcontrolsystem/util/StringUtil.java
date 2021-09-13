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

//        List<Date> dates = new ArrayList<>();
//        String beginTime = "2018-07-29 12:26:32";
//        String endTime = "2018-07-30 12:26:32";
//        String otherTime = "2018-07-30 12:26:32";
//        DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        try {
//            Date date1 = dFormat.parse(beginTime);
//            dates.add(date1);
//            Date date3 = dFormat.parse(otherTime);
//            dates.add(date3);
//            Date date2 = dFormat.parse(endTime);
//            dates.add(date2);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        dates.sort(Comparator.comparing(Date::getTime).reversed());
//        for (int i = 1; i < dates.size(); i++) {
//            if (dates.get(i).equals(dates.get(0))) {
//                System.out.println(dates.get(i));
//            }
//        }
        Util util = Util.getInstance();
        Map<String, List<Link_Dao_impl.LinkEntity>> pairlinksMap = new HashMap<>();
        List<String> keys = new ArrayList<>(util.hmget("link").keySet());
        for (String key : keys) {
            Link link = new Gson().fromJson(util.hget("link", key), new TypeToken<Link>() {}.getType());
            link.setId(key);
            SrcDstPair srcDstPair = new SrcDstPair(link.getSrc(), link.getDst());
            String kk = new Gson().toJson(srcDstPair.getPair());
            pairlinksMap.computeIfAbsent(kk, k -> new ArrayList<>());
            Link_Dao_impl.LinkEntity linkEntity = new Link_Dao_impl.LinkEntity(link.getScore(),link.getRemain_bandwidth(), link.getLink_type());
            String product = srcDstPair.getSrc()+":"+srcDstPair.getDst();
            linkEntity.setProduct(product);
            pairlinksMap.get(kk).add(linkEntity);
        }
        String str = new Gson().toJson(pairlinksMap);
        JSONObject jsonObject = JSON.parseObject(str);
        System.out.println(jsonObject);
        for (String k : jsonObject.keySet()) {
            System.out.println(jsonObject.get(k));
        }
    }
}
