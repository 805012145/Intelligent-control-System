package com.example.intelligentcontrolsystem.dao.impl;

import com.example.intelligentcontrolsystem.dao.Business_Dao;
import com.example.intelligentcontrolsystem.entity.Business;
import com.example.intelligentcontrolsystem.util.StringUtil;
import com.example.intelligentcontrolsystem.util.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class Business_Dao_impl implements Business_Dao {
    Util util = Util.getInstance();
    @Override
    public List<Business> getBusInfoBySrcId(String id) {
        List<Business> businesses = new ArrayList<>();
        List<String> keys = new ArrayList<>(util.hmget("business").keySet());
        for (String key : keys) {
            Business business = new Gson().fromJson(util.hget("business", key), new TypeToken<Business>() {}.getType());
            business.setId(key);
            if (business.getSrc().equals(id)) {
                businesses.add(business);
            }
        }
        return businesses;
    }

    @Override
    public List<Business> getBusInfoByParam(String src, String src_port, String dst, String dst_port, String link_type) {
        List<Business> businesses = new ArrayList<>();
        List<String> keys = new ArrayList<>(util.hmget("business").keySet());
        for (String key : keys) {
            Business business = new Gson().fromJson(util.hget("business", key), new TypeToken<Business>() {}.getType());
            business.setId(key);
            if (StringUtil.hasStr(src, dst, business.getRoute())) {
                String[] routes = StringUtil.StringToArray(business.getRoute());
                String[] link_types = StringUtil.StringToArray(business.getLink_type());
                for (int i = 0; i < routes.length - 1; i++) {
                    System.out.println(i + "  " + routes[i]);
                    if (routes[i].equals(src) && routes[i+1].equals(dst) && link_types[i].equals(link_type)
                            && System.currentTimeMillis() - Long.parseLong(business.getTime()) < 5) {
                        businesses.add(business);
                        break;
                    }
                }
            }
        }
        return businesses;
    }

    @Override
    public List<Business> getBusInfo() {
        List<Business> businesses = new ArrayList<>();
        List<String> keys = new ArrayList<>(util.hmget("business").keySet());
        for (String key : keys) {
            Business business = new Gson().fromJson(util.hget("business", key), new TypeToken<Business>() {}.getType());
            business.setId(key);
            businesses.add(business);
        }
        return businesses;
    }

    //todo 获取各业务数目
    @Override
    public String getBusNumByEachType() {
        Map<String, Integer> busNumByEachType = new HashMap<>();
        List<String> keys = new ArrayList<>(util.hmget("business").keySet());
        for (String key : keys) {
            Business business = new Gson().fromJson(util.hget("business", key), new TypeToken<Business>() {}.getType());
            business.setId(key);
            busNumByEachType.merge(business.getType(), 1, Integer::sum);
        }
        return new Gson().toJson(busNumByEachType);
    }

    @Override
    public String getBusNum() {
       return String.valueOf(util.hmget("business").keySet().size());
    }
}
