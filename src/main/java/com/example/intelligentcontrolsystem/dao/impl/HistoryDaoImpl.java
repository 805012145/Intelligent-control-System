package com.example.intelligentcontrolsystem.dao.impl;

import com.example.intelligentcontrolsystem.dao.HistoryParaDao;
import com.example.intelligentcontrolsystem.entity.History_para;
import com.example.intelligentcontrolsystem.entity.TableEntity;
import com.example.intelligentcontrolsystem.util.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class HistoryDaoImpl implements HistoryParaDao {

    @Override
    public String getAllHistoryParam() {
        TableEntity tableEntity = new TableEntity();
        TableEntity.Data data = new TableEntity.Data();
        List<Object[]> historyInfoList = new ArrayList<>();
        Object[] header = {"controller_sum", "link_sum", "switch_sum", "host_sum", "business_sum", "throughout", "date"};
        historyInfoList.add(header);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Util util = new Util();
        int size = util.keys("history_para*").size();
        if (size == 0) {
            return null;
        }
        List<String> tables = new ArrayList<>(util.keys("history_para*"));
        if(tables.size() > 0) {
            for (int i = 0; i < 30; i++) {
                Object[] historyInfo = new Object[7];
                History_para history_para = new History_para(0, 0, 0, 0, 0, 0L);
                List<History_para> tempList = new ArrayList<>();
                for (String table : tables) {
                    if (util.hasKey(table)) {
                        if (util.zcard(table) - i > 0) {
                            History_para item = new Gson().fromJson(util.zget(table, util.zcard(table) - i - 1, util.zcard(table) - i).get(0),
                                    new TypeToken<History_para>() {
                                    }.getType());
                            tempList.add(item);
                        }
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
                Date date = new Date(tempList.get(0).date);
                String id = sdf.format(date);
                history_para.setDate(id);
                history_para.controller_sum++;
                historyInfo[0] = history_para.controller_sum;
                historyInfo[1] = history_para.link_sum;
                historyInfo[2] = history_para.switch_sum;
                historyInfo[3] = history_para.host_sum;
                historyInfo[4] = history_para.business_sum;
                historyInfo[5] = history_para.throughout;
                historyInfo[6] = history_para.date;
                historyInfoList.add(historyInfo);
            }
        }
        data.setSource(historyInfoList);
        tableEntity.setData(data);
        util.UtilClose();
        return new Gson().toJson(tableEntity);
    }
}
