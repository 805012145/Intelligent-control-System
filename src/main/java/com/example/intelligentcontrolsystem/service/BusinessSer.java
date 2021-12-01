package com.example.intelligentcontrolsystem.service;

import com.example.intelligentcontrolsystem.entity.Business;
import com.example.intelligentcontrolsystem.entity.PieChart;

import java.text.ParseException;
import java.util.List;

public interface BusinessSer {

    List<Business> getBusInfoBySrcId(String id, String algorithm) throws ParseException;

    List<PieChart> getBusInfoByParam(String source, String target, String type, String algorithm) throws ParseException;

    List<Business> getBusInfo(String algorithm) throws ParseException;

    String getBusInfoByType(String algorithm) throws ParseException;

    double[] getBusAvgDelay(String algorithm) throws ParseException;

    double[] getBusAvgArrivate(String algorithm) throws ParseException;

    int[] getBusNum(String algorithm) throws ParseException;
}
