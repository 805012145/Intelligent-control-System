package com.example.intelligentcontrolsystem.dao.impl;

import com.example.intelligentcontrolsystem.dao.NodeDao;
import com.example.intelligentcontrolsystem.entity.*;
import com.example.intelligentcontrolsystem.util.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class NodeDaoImpl implements NodeDao {

    @Override
    public List<Node> getAll() {
        Util util = new Util();
        if(util.keys("node:*").size() == 0) {
            return null;
        }
        List<Node> nodes = new ArrayList<>();
        List<String> c_keys = new ArrayList<>(util.hmget("node:Controller").keySet()); //controller表里的item集合作为键集合
        for (String key : c_keys) {
            Controller controller = new Gson().fromJson(util.hget("node:Controller", key), new TypeToken<Controller>() {}.getType());
            controller.setId(key);
            nodes.add(controller);
        }

        List<String> s_keys = new ArrayList<>(util.hmget("node:Switch").keySet()); //Switch表里的item集合作为键集合
        for (String key : s_keys) {
            Switch aswitch = new Gson().fromJson(util.hget("node:Switch", key), new TypeToken<Switch>() {}.getType());
            aswitch.setId(key);
            nodes.add(aswitch);
        }
        List<String> h_keys = new ArrayList<>(util.hmget("node:Host").keySet()); //Host表里的item集合作为键集合
        for (String key : h_keys) {
            Host host = new Gson().fromJson(util.hget("node:Host", key), new TypeToken<Host>() {}.getType());
            host.setId(key);
            String id = host.getSwitch_id();
            nodes.add(host);
        }
        util.UtilClose();
        return nodes;
    }
}
