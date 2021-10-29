package com.example.intelligentcontrolsystem.dao.impl;

import com.example.intelligentcontrolsystem.dao.TestDao;
import com.example.intelligentcontrolsystem.util.Util;
import org.springframework.stereotype.Repository;

@Repository
public class TestDaoImpl implements TestDao {

    @Override
    public String test() throws Exception {
        Util util = new Util();
        if(util.getIp().equals("127.0.0.1")) {
        }
        util.UtilClose();
        return null;
    }
}
