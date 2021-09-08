package com.example.intelligentcontrolsystem.service.impl;

import com.example.intelligentcontrolsystem.dao.Test_Dao;
import com.example.intelligentcontrolsystem.entity.Node;
import com.example.intelligentcontrolsystem.service.Test_Ser;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Test_Ser_impl implements Test_Ser {
    @Autowired
    Test_Dao test_dao;
    @Override
    public String test() throws Exception {
        return test_dao.test();
    }
}
