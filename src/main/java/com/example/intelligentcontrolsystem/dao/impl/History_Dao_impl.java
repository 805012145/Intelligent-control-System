package com.example.intelligentcontrolsystem.dao.impl;

import com.example.intelligentcontrolsystem.dao.History_para_Dao;
import com.example.intelligentcontrolsystem.entity.History_para;
import com.example.intelligentcontrolsystem.util.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class History_Dao_impl implements History_para_Dao {
    Util util = Util.getInstance();

    @Override
    public String getAllHistoryParam() {
        Map<String, History_para> historyParaMap = new HashMap<>();
        int size = util.keys("history_para*").size();
        if (size == 0) {
            return null;
        }
        List<String> keys = new ArrayList<>(util.keys("history_para*")) ;
        for (String key : keys) {
            List<String> items = new ArrayList<>(util.hmget(key).keySet());
            History_para history_para = new Gson().fromJson(util.hget(key, items.get(items.size() - 1)), new TypeToken<History_para>() {}.getType());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date(items.get(items.size() - 1));
            String id = sdf.format(date);
            history_para.setId(id);
            historyParaMap.put(key, history_para);
        }
        return new Gson().toJson(historyParaMap);
    }
}
