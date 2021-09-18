package com.example.intelligentcontrolsystem.service.impl;

import com.example.intelligentcontrolsystem.dao.Link_Dao;
import com.example.intelligentcontrolsystem.entity.Link;
import com.example.intelligentcontrolsystem.service.Link_Ser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Link_Ser_impl implements Link_Ser {

    private Link_Dao link_dao;

    @Autowired
    public void setLink_dao(Link_Dao link_dao) {
        this.link_dao = link_dao;
    }

    @Override
    public List<Link> getAll() {
        return link_dao.getAll();
    }

    @Override
    public String getBWInfoOfAllType() {
        return link_dao.getBWInfoOfAllType();
    }

    @Override
    public String getBWConOfAllLinks() {
        return link_dao.getBWConOfAllLinks();
    }
}
