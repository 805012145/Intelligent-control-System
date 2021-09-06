package com.example.intelligentcontrolsystem.dao.impl;

import com.example.intelligentcontrolsystem.dao.Test_Dao;
import com.example.intelligentcontrolsystem.util.Util;
import org.springframework.stereotype.Repository;

@Repository
public class Test_Dao_impl implements Test_Dao {
    Util util = Util.getInstance();
    @Override
    public String test() throws Exception {
        String age = util.hget("user:001", "age");
        return age;
    }
}
