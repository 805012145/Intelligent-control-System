package com.example.intelligentcontrolsystem.dao.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.intelligentcontrolsystem.dao.Test_Dao;
import com.example.intelligentcontrolsystem.entity.Controller;
import com.example.intelligentcontrolsystem.entity.Host;
import com.example.intelligentcontrolsystem.entity.Link;
import com.example.intelligentcontrolsystem.entity.Switch;
import com.example.intelligentcontrolsystem.util.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class Test_Dao_impl implements Test_Dao {
    Util util = Util.getInstance();
    @Override
    public String test() throws Exception {

        Switch aswitch = new Switch("switch", "10", "10", "10", "10", "10", "1");
        Host host = new Host("Host",  "10", "10", "10", "10", "10", "1", "1", "10.0.0.1");
        Controller controller = new Controller("controller", "10", "10","20", "10", "1", "1");
        Link link = new Link("1", "1", "2", "1", "1", "10M", "10M", "0", "1ms", "0");
        util.hset("node:Controller", "001", new Gson().toJson(controller));
        util.hset("node:Controller", "002", new Gson().toJson(controller));
        util.hset("link", "001", new Gson().toJson(link));
        util.hset("link", "002", new Gson().toJson(link));
        util.hset("node:Switch", "001", new Gson().toJson(aswitch));
        util.hset("node:Switch", "002", new Gson().toJson(aswitch));
        util.hset("node:Host", "001", new Gson().toJson(host));
        util.hset("node:Host", "002", new Gson().toJson(host));
        List<String> keys = new ArrayList<>(util.hmget("node:Controller").keySet()); //controller表里的item集合作为键集合
        List<Controller> controllers = new ArrayList<>();
        for (String key : keys) {
            Controller controller1 = new Gson().fromJson(util.hget("node:Controller", key), new TypeToken<Controller>() {}.getType());
            controller1.setId(key);
            controllers.add(controller1);
        }
        System.out.println(controllers);


        return new Gson().toJson(controllers);
    }
}
