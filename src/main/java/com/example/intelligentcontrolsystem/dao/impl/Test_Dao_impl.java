package com.example.intelligentcontrolsystem.dao.impl;

import com.example.intelligentcontrolsystem.dao.Test_Dao;
import com.example.intelligentcontrolsystem.entity.Controller;
import com.example.intelligentcontrolsystem.entity.Node;
import com.example.intelligentcontrolsystem.util.JSONUtil;
import com.example.intelligentcontrolsystem.util.Util;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Repository
public class Test_Dao_impl implements Test_Dao {
    Util util = Util.getInstance();
    @Override
    public List<Node> test() throws Exception {

        util.hset("node:Controller1", "id" , "5757");
        util.hset("node:Controller1", "name" , "Controller");
        util.hset("node:Controller1", "symbolsize" , "30");
        util.hset("node:Controller1", "x" , "30");
        util.hset("node:Controller1", "y" , "30");
        util.hset("node:Controller1", "value" , "10");
        util.hset("node:Controller1", "category" , "0");
        util.hset("node:Controller1", "field" , "1");

        util.hset("node:Controller2", "id" , "6767");
        util.hset("node:Controller2", "name" , "Controller");
        util.hset("node:Controller2", "symbolsize" , "30");
        util.hset("node:Controller2", "x" , "60");
        util.hset("node:Controller2", "y" , "60");
        util.hset("node:Controller2", "value" , "10");
        util.hset("node:Controller2", "category" , "0");
        util.hset("node:Controller2", "field" , "1");



        Node node = new Node();
        Set<String> keys = util.keys("node:Con*");
        List<String> key_List = new ArrayList<>(keys);
        List<Node> nodes = new ArrayList<>();

        for (String key : key_List) {
            node.setId(util.hget(key, "id"));
            node.setName(util.hget(key, "name"));
            node.setSymbolsize(util.hget(key, "symbolsize"));
            node.setX(util.hget(key, "x"));
            node.setY(util.hget(key, "y"));
            node.setValue(util.hget(key, "value"));
            node.setCategory(util.hget(key, "category"));
            node.setField(util.hget(key, "field"));
            nodes.add(node);
        }
        return nodes;
    }
}
