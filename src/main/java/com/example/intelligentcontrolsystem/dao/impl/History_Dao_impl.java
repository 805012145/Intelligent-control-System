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
        List<String> tables = new ArrayList<>(util.keys("history_para*"));
        List<History_para> history_paras = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            History_para history_para = new History_para(0,0,0,0,0, 0L);
            List<History_para> tempList = new ArrayList<>();
            for (String table : tables) {
                if (util.zcard(table) - i > 0) {
                    History_para item = new Gson().fromJson(util.zget(table, util.zcard(table) - i - 1, util.zcard(table) - i).get(0),
                            new TypeToken<History_para>() {
                            }.getType());
                    tempList.add(item);
                }
            }
            if (tempList.size() == 0) {
                break;
            }
            for (History_para item : tempList) {
                history_para.controller_sum += item.controller_sum;
                history_para.business_sum += item.business_sum;
                history_para.host_sum += item.host_sum;
                history_para.link_sum += item.link_sum;
                history_para.switch_sum += item.switch_sum;
                history_para.throughout += item.throughout;
            }
            history_para.controller_sum++;
            Date date = new Date(tempList.get(0).date);
            String id = sdf.format(date);
            history_para.setDate(id);
            history_paras.add(history_para);
        }
        util.UtilClose();
        historyParaMap.put("historyPara", history_paras);
        return new Gson().toJson(historyParaMap);
    }
}
