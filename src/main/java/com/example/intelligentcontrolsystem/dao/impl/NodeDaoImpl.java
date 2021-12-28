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
//    public static void main(String[] args) {
//        Util util = new Util();
//        List<String> c_tables = new ArrayList<>(util.keys("node:Controller:*"));
//        System.out.println(c_tables);
//    }

    @Override
    public List<Node> getAll() {
        Util util = new Util();
        if(util.keys("node:*").size() == 0) {
            util.UtilClose();
            return null;
        }
        List<Node> nodes = new ArrayList<>();
        List<String> c_tables = new ArrayList<>(util.keys("node:Controller:*"));
        List<String> s_tables = new ArrayList<>(util.keys("node:Switch:*"));
        List<String> h_tables = new ArrayList<>(util.keys("node:Host:*"));
        for (String table : c_tables) {
            List<String> c_keys = new ArrayList<>(util.hgetAll(table).keySet()); //controller表里的item集合作为键集合
            for (String key : c_keys) {
                Controller controller = new Gson().fromJson(util.hget(table, key), new TypeToken<Controller>() {
                }.getType());
                nodes.add(controller);
            }
        }
        for (String table : s_tables) {
            List<String> s_keys = new ArrayList<>(util.hgetAll(table).keySet()); //Switch表里的item集合作为键集合
            for (String key : s_keys) {
                Switch aswitch = new Gson().fromJson(util.hget(table, key), new TypeToken<Switch>() {
                }.getType());
                nodes.add(aswitch);
            }
        }
        for (String table : h_tables) {
            List<String> h_keys = new ArrayList<>(util.hgetAll(table).keySet()); //Host表里的item集合作为键集合
            for (String key : h_keys) {
                Host host = new Gson().fromJson(util.hget(table, key), new TypeToken<Host>() {
                }.getType());
                nodes.add(host);
            }
        }
        util.UtilClose();
        return nodes;
    }
}
