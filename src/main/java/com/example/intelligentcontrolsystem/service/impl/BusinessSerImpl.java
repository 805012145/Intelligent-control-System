package com.example.intelligentcontrolsystem.service.impl;

import com.example.intelligentcontrolsystem.dao.BusinessDao;
import com.example.intelligentcontrolsystem.entity.Business;
import com.example.intelligentcontrolsystem.entity.PieChart;
import com.example.intelligentcontrolsystem.service.BusinessSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public class BusinessSerImpl implements BusinessSer {

    private BusinessDao businessDao;

    @Autowired
    public void setBusiness_dao(BusinessDao businessDao) {
        this.businessDao = businessDao;
    }

    @Override
    public List<Business> getBusInfoBySrcId(String id, String algorithm) throws ParseException {
        return businessDao.getBusInfoBySrcId(id, algorithm);
    }

    @Override
    public List<PieChart> getBusInfoByParam(String source, String target,
                                            String type, String algorithm) throws ParseException {
        return businessDao.getBusInfoByParam(source, target, type, algorithm);
    }

    @Override
    public List<Business> getBusInfo(String algorithm) throws ParseException {
        return businessDao.getBusInfo(algorithm);
    }


    @Override
    public String getBusInfoByType(String algorithm) throws ParseException {
        return businessDao.getBusInfoByType(algorithm);
    }

    @Override
    public double[] getBusAvgDelay(String algorithm) throws ParseException {
        return businessDao.getBusAvgDelay(algorithm);
    }

    @Override
    public double[] getBusAvgArrivate(String algorithm) throws ParseException {
        return businessDao.getBusAvgArrivate(algorithm);
    }

    @Override
    public int[] getBusNum(String algorithm) throws ParseException {
        return businessDao.getBusNum(algorithm);
    }


}