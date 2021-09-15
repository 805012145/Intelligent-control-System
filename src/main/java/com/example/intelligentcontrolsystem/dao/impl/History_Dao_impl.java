package com.example.intelligentcontrolsystem.dao.impl;

import com.example.intelligentcontrolsystem.dao.History_para_Dao;
import com.example.intelligentcontrolsystem.entity.History_para;
import com.example.intelligentcontrolsystem.service.Business_Ser;
import com.example.intelligentcontrolsystem.util.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class History_Dao_impl implements History_para_Dao {
    Util util = Util.getInstance();
    @Autowired
    Business_Ser business_ser;
    @Override
    public List<History_para> getAllHistoryParam() {
        if (!util.hasKey("history_para")) {
            return null;
        }
        List<String> keys = new ArrayList<>(util.hmget("history_para").keySet()); //controller表里的item集合作为键集合
        List<History_para> history_paras = new ArrayList<>();
        for (String key : keys) {
            History_para history_para = new Gson().fromJson(util.hget("history_para", key), new TypeToken<History_para>() {}.getType());
            String businessnum = business_ser.getBusNum();
            history_para.setBusiness_sum(businessnum);
            history_para.setId(key);
            history_paras.add(history_para);
        }
        return history_paras;
    }
}
