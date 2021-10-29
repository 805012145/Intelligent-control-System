package com.example.intelligentcontrolsystem.dao.impl;

import com.example.intelligentcontrolsystem.dao.BusinessDao;
import com.example.intelligentcontrolsystem.entity.Business;
import com.example.intelligentcontrolsystem.util.StringUtil;
import com.example.intelligentcontrolsystem.util.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BusinessDaoImpl implements BusinessDao {

    @Override
    public List<Business> getBusInfoBySrcId(String id) {
        Util util = new Util();
        if (util.keys("business").size() == 0) {
            return null;
        }
        List<Business> businesses = new ArrayList<>();
        List<String> keys = new ArrayList<>(util.hmget("business").keySet());
        for (String key : keys) {
            Business business = new Gson().fromJson(util.hget("business", key), new TypeToken<Business>() {}.getType());
            business.setId(key);
            if (business.getSrc().equals(id)) {
                businesses.add(business);
            }
        }
        util.UtilClose();
        return businesses;
    }

    @Override
    public List<Business> getBusInfoByParam(String src, String src_port, String dst, String dst_port, String link_type) {
        Util util = new Util();
        if (util.keys("business").size() == 0) {
            return null;
        }
        List<Business> businesses = new ArrayList<>();
        List<String> keys = new ArrayList<>(util.hmget("business").keySet());
        for (String key : keys) {
            Business business = new Gson().fromJson(util.hget("business", key), new TypeToken<Business>() {}.getType());
            business.setId(key);
            if (StringUtil.hasStr(src, dst, business.getRoute())) {
                String[] routes = StringUtil.StringToArray(business.getRoute());
                String[] link_types = StringUtil.StringToArray(business.getLink_type());
                for (int i = 0; i < routes.length - 1; i++) {
                    if (routes[i].equals(src) && routes[i+1].equals(dst) && link_types[i].equals(link_type)
                            && System.currentTimeMillis() - Long.parseLong(business.getTime()) < 5) {//todo 最近时间的界定
                        businesses.add(business);
                        break;
                    }
                }
            }
        }
        util.UtilClose();
        return businesses;
    }

    @Override
    public List<Business> getBusInfo() {
        Util util = new Util();
        if (util.keys("business").size() == 0) {
            return null;
        }
        List<Business> businesses = new ArrayList<>();
        List<String> keys = new ArrayList<>(util.hmget("business").keySet());
        for (String key : keys) {
            Business business = new Gson().fromJson(util.hget("business", key), new TypeToken<Business>() {}.getType());
            business.setId(key);
            businesses.add(business);
        }
        util.UtilClose();
        return businesses;
    }

    @Override
    public List<Business> getBusInfo(String algorithm) {
        Util util = new Util();
        String table_key = "business_" + algorithm;
        if (util.keys(table_key).size() == 0) {
            return null;
        }
        List<Business> businesses = new ArrayList<>();
        List<String> keys = new ArrayList<>(util.hmget(table_key).keySet());
        for (String key : keys) {
            Business business = new Gson().fromJson(util.hget(table_key, key), new TypeToken<Business>() {}.getType());
            business.setId(key);
            businesses.add(business);
        }
        util.UtilClose();
        return businesses;
    }

    //todo 获取各业务数目
    @Override
    public String getBusNumByEachType() {
        Util util = new Util();
        if (util.keys("business").size() == 0) {
            return null;
        }
        Map<String, Integer> busNumByEachType = new HashMap<>();
        List<String> item_keys = new ArrayList<>(util.hmget("business").keySet());
        for (String item : item_keys) {
            Business business = new Gson().fromJson(util.hget("business", item), new TypeToken<Business>() {}.getType());
            business.setId(item);
            busNumByEachType.merge(business.getType(), 1, Integer::sum);
        }
        util.UtilClose();
        return new Gson().toJson(busNumByEachType);
    }

    @Override
    public String getBusNumByEachType(String algorithm) {
        Util util = new Util();
        String table_key = "business_" + algorithm;
        if (util.keys(table_key).size() == 0) {
            return null;
        }
        Map<String, Integer> busNumByEachType = new HashMap<>();
        List<String> item_keys = new ArrayList<>(util.hmget(table_key).keySet());
        for (String item : item_keys) {
            Business business = new Gson().fromJson(util.hget(table_key, item), new TypeToken<Business>() {}.getType());
            business.setId(item);
            busNumByEachType.merge(business.getType(), 1, Integer::sum);
        }
        util.UtilClose();
        return new Gson().toJson(busNumByEachType);
    }

    @Override
    public String getBusNum() {
        Util util = new Util();
        if (util.keys("business").size() == 0) {
            return null;
        }
        util.UtilClose();
       return String.valueOf(util.hmget("business").keySet().size());
    }
}
