package com.example.intelligentcontrolsystem.dao.impl;

import com.example.intelligentcontrolsystem.dao.Test_Dao;
import com.example.intelligentcontrolsystem.entity.*;
import com.example.intelligentcontrolsystem.util.Util;
import com.google.gson.Gson;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class Test_Dao_impl implements Test_Dao {

    @Override
    public String test() throws Exception {
        Util util = new Util();
        if(util.getIp().equals("127.0.0.1")) {
//            Date date = new Date();
//            History_para history_para = new History_para("1", "10", "10", "10", "5");
//            Business business = new Business("1", "2", "[1,2,3]", "[1,2]", "[10,10]", "5", "10", "1");
//            Link link = new Link("1", "1", "2", "1", "1", "1000", "1000", "0", "0", "0");
//            Link link1 = new Link("1", "1", "2", "1", "2", "1000", "1000", "0", "0", "0");
//            Link link2 = new Link("1", "2", "2", "2", "3", "1000", "1000", "0", "0", "0");
//            Link link3 = new Link("1", "3", "2", "3", "4", "1000", "1000", "0", "0", "0");
//            Link link4 = new Link("2", "1", "1", "1", "1", "1000", "1000", "0", "0", "0");
//            Controller controller1 = new Controller("Controller1", "1", "0", "0", "10", "10", "1");
//            Controller controller2 = new Controller("Controller2", "1", "10", "10", "20", "10", "2");
//            Switch aswitch1 = new Switch("S1", "20", "10", "10", "30", "10", "1");
//            Switch aswitch2 = new Switch("S2", "20", "10", "10", "30", "10", "2");
//            Host host1 = new Host("h1", "10", "20", "20", "10", "10", "1", "1", "10.0.0.1");
//            Host host2 = new Host("h2", "10", "20", "20", "10", "10", "2", "2", "10.0.0.2");
//            util.hset("link", "001", new Gson().toJson(link));
//            util.hset("link", "002", new Gson().toJson(link1));
//            util.hset("link", "003", new Gson().toJson(link2));
//            util.hset("link", "004", new Gson().toJson(link3));
//            util.hset("link", "005", new Gson().toJson(link4));
//            util.hset("node:Controller", "001", new Gson().toJson(controller1));
//            util.hset("node:Controller", "002", new Gson().toJson(controller2));
//            util.hset("node:Switch", "001", new Gson().toJson(aswitch1));
//            util.hset("node:Switch", "002", new Gson().toJson(aswitch2));
//            util.hset("node:Host", "001", new Gson().toJson(host1));
//            util.hset("node:Host", "002", new Gson().toJson(host2));
//            util.hset("history_para", date.toString(), new Gson().toJson(history_para));
//            util.hset("business", "001", new Gson().toJson(business));
        }
        util.UtilClose();
        return null;
    }
}
