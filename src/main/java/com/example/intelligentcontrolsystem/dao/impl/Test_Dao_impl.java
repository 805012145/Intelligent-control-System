package com.example.intelligentcontrolsystem.dao.impl;

import com.example.intelligentcontrolsystem.dao.Test_Dao;
import com.example.intelligentcontrolsystem.util.JSONUtil;
import com.example.intelligentcontrolsystem.util.Util;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Repository
public class Test_Dao_impl implements Test_Dao {
    Util util = Util.getInstance();
    @Override
    public String test() throws Exception {
        util.hset("user:002", "age" , "35");
        util.hset("user:002", "name" , "yyy");
        util.hset("user:003", "age" , "36");
        util.hset("user:003", "name" , "zzz");
        Set<String> keys = util.keys("user:*");
        List<String> ages = new ArrayList<>();
        List<String> names = new ArrayList<>();
        for (String key : keys) {
            ages.add(util.hget(key, "age"));
            names.add(util.hget(key, "name"));
        }
        List<Object> keyList = Arrays.asList(keys.toArray());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key", keyList);
        jsonObject.put("age", ages);
        jsonObject.put("name", names);
        return jsonObject.toString();
    }
}
