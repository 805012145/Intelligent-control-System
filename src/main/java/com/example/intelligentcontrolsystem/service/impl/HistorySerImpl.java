package com.example.intelligentcontrolsystem.service.impl;

import com.example.intelligentcontrolsystem.dao.HistoryParaDao;
import com.example.intelligentcontrolsystem.service.HistoryParaSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistorySerImpl implements HistoryParaSer {

    private HistoryParaDao historyParaDao;

    @Autowired
    public void setHistory_para_dao(HistoryParaDao historyParaDao) {
        this.historyParaDao = historyParaDao;
    }

    @Override
    public String getAllHistoryParam() {
        return historyParaDao.getAllHistoryParam();
    }
}
