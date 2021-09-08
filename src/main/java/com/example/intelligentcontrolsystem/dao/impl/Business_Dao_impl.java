package com.example.intelligentcontrolsystem.dao.impl;

import com.example.intelligentcontrolsystem.dao.Business_Dao;
import com.example.intelligentcontrolsystem.entity.Business;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class Business_Dao_impl implements Business_Dao {
    @Override
    public List<Business> getBusInfoBySrcId(String id) {
        return null;
    }

    @Override
    public List<Business> getBusInfoByParam(String src, String src_port, String dst, String dst_port, String link_type) {
        return null;
    }

    @Override
    public List<Business> getBusInfo() {
        return null;
    }

    @Override
    public String getBusNumByEachType() {
        return null;
    }
}
