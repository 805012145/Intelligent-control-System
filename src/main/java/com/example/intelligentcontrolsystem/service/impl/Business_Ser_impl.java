package com.example.intelligentcontrolsystem.service.impl;

import com.example.intelligentcontrolsystem.dao.Business_Dao;
import com.example.intelligentcontrolsystem.dao.Test_Dao;
import com.example.intelligentcontrolsystem.entity.Business;
import com.example.intelligentcontrolsystem.service.Business_Ser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Business_Ser_impl implements Business_Ser {

    @Autowired
    Business_Dao business_dao;

    @Override
    public List<Business> getBusInfoBySrcId(String id) {
        return business_dao.getBusInfoBySrcId(id);
    }

    @Override
    public List<Business> getBusInfoByParam(String src, String src_port, String dst, String dst_port, String link_type) {
        return business_dao.getBusInfoByParam(src, src_port, dst, dst_port, link_type);
    }

    @Override
    public List<Business> getBusInfo() {
        return business_dao.getBusInfo();
    }

    @Override
    public String getBusNumByEachType() {
        return business_dao.getBusNumByEachType();
    }
}
