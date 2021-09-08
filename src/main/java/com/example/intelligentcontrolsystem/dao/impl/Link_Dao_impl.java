package com.example.intelligentcontrolsystem.dao.impl;

import com.example.intelligentcontrolsystem.dao.Link_Dao;
import com.example.intelligentcontrolsystem.entity.Controller;
import com.example.intelligentcontrolsystem.entity.Link;
import com.example.intelligentcontrolsystem.util.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class Link_Dao_impl implements Link_Dao {

    Util util = Util.getInstance();
    @Override
    public List<Link> getAll() {
        List<String> keys = new ArrayList<>(util.hmget("link").keySet());
        List<Link> links = new ArrayList<>();
        for (String key : keys) {
            Link link = new Gson().fromJson(util.hget("link", key), new TypeToken<Link>() {}.getType());
            link.setId(key);
            links.add(link);
        }
        return links;
    }
}
