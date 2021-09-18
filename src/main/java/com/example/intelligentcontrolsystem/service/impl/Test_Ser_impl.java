package com.example.intelligentcontrolsystem.service.impl;

import com.example.intelligentcontrolsystem.dao.Test_Dao;
import com.example.intelligentcontrolsystem.service.Test_Ser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Test_Ser_impl implements Test_Ser {

    private Test_Dao test_dao;

    @Autowired
    public void setTest_dao(Test_Dao test_dao) {
        this.test_dao = test_dao;
    }

    @Override
    public String test() throws Exception {
        return test_dao.test();
    }
}
