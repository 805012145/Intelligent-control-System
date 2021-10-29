package com.example.intelligentcontrolsystem.service.impl;

import com.example.intelligentcontrolsystem.dao.BusinessDao;
import com.example.intelligentcontrolsystem.entity.Business;
import com.example.intelligentcontrolsystem.service.BusinessSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessSerImpl implements BusinessSer {

    private BusinessDao businessDao;

    @Autowired
    public void setBusiness_dao(BusinessDao businessDao) {
        this.businessDao = businessDao;
    }

    @Override
    public List<Business> getBusInfoBySrcId(String id) {
        return businessDao.getBusInfoBySrcId(id);
    }

    @Override
    public List<Business> getBusInfoByParam(String src, String src_port, String dst, String dst_port, String link_type) {
        return businessDao.getBusInfoByParam(src, src_port, dst, dst_port, link_type);
    }

    @Override
    public List<Business> getBusInfo() {
        return businessDao.getBusInfo();
    }

    @Override
    public List<Business> getBusInfo(String algorithm) {
        return businessDao.getBusInfo(algorithm);
    }

    @Override
    public String getBusNumByEachType() {
        return businessDao.getBusNumByEachType();
    }

    @Override
    public String getBusNumByEachType(String algorithm) {
        return businessDao.getBusNumByEachType(algorithm);
    }

    @Override
    public String getBusNum() {
        return businessDao.getBusNum();
    }
}
