package com.example.intelligentcontrolsystem.service.impl;

import com.example.intelligentcontrolsystem.dao.History_para_Dao;
import com.example.intelligentcontrolsystem.entity.History_para;
import com.example.intelligentcontrolsystem.service.History_para_Ser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class History_para_impl implements History_para_Ser {
    @Autowired
    History_para_Dao history_para_dao;
    @Override
    public List<History_para> getAllHistoryParam() {
        return history_para_dao.getAllHistoryParam();
    }
}
