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

    @Override
    public String getAllHistoryParam() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Util util = new Util();
        Map<String, List<History_para>> historyParaMap = new HashMap<>();
        int size = util.keys("history_para*").size();
        if (size == 0) {
            return null;
        }
        List<String> keys = new ArrayList<>(util.keys("history_para*")) ;
        for (String key : keys) {
            List<History_para> history_paras = new ArrayList<>();
            List<String> items = new ArrayList<>(util.hmget(key).keySet());
            for (String item : items) {
                Date date = new Date(item);
                String id = sdf.format(date);
                History_para history_para = new Gson().fromJson(util.hget(key, items.get(items.size() - 1)), new TypeToken<History_para>() {}.getType());
                history_para.setId(id);
                history_paras.add(history_para);
            }
            historyParaMap.put(key, history_paras);
        }
        util.UtilClose();
        return new Gson().toJson(historyParaMap);
    }
}
