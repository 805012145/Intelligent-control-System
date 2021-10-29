package com.example.intelligentcontrolsystem.service;

import com.example.intelligentcontrolsystem.entity.Business;

import java.util.List;

public interface BusinessSer {

    List<Business> getBusInfoBySrcId(String id);

    List<Business> getBusInfoByParam(String src, String src_port, String dst, String dst_port, String link_type);

    List<Business> getBusInfo();

    List<Business> getBusInfo(String algorithm);

    String getBusNumByEachType();

    String getBusNumByEachType(String algorithm);

    String getBusNum();
}
