package com.example.intelligentcontrolsystem.dao.impl;

import com.example.intelligentcontrolsystem.dao.Node_Dao;
import com.example.intelligentcontrolsystem.entity.*;
import com.example.intelligentcontrolsystem.util.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class Node_Dao_impl implements Node_Dao {
    Util util = Util.getInstance();
    @Override
    public List<Node> getAll() {
        List<Node> nodes = new ArrayList<>();
        List<String> c_keys = new ArrayList<>(util.hmget("node:Controller").keySet()); //controller表里的item集合作为键集合
        for (String key : c_keys) {
            Controller controller = new Gson().fromJson(util.hget("node:Controller", key), new TypeToken<Controller>() {}.getType());
            controller.setId(key);
            nodes.add(controller);
        }
        List<String> s_keys = new ArrayList<>(util.hmget("node:Switch").keySet()); //controller表里的item集合作为键集合
        for (String key : s_keys) {
            Controller controller = new Gson().fromJson(util.hget("node:Switch", key), new TypeToken<Switch>() {}.getType());
            controller.setId(key);
            nodes.add(controller);
        }

        List<String> h_keys = new ArrayList<>(util.hmget("node:Host").keySet()); //controller表里的item集合作为键集合
        for (String key : h_keys) {
            Controller controller = new Gson().fromJson(util.hget("node:Host", key), new TypeToken<Host>() {}.getType());
            controller.setId(key);
            nodes.add(controller);
        }
        return nodes;
    }
}
