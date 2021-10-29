package com.example.intelligentcontrolsystem.service.impl;

import com.example.intelligentcontrolsystem.dao.TestDao;
import com.example.intelligentcontrolsystem.service.TestSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestSerImpl implements TestSer {

    private TestDao testDao;

    @Autowired
    public void setTest_dao(TestDao testDao) {
        this.testDao = testDao;
    }

    @Override
    public String test() throws Exception {
        return testDao.test();
    }
}
