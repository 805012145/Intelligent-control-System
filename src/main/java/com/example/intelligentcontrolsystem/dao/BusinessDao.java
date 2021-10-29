package com.example.intelligentcontrolsystem.dao;

import com.example.intelligentcontrolsystem.entity.Business;

import java.util.List;

public interface BusinessDao {

    /**
     * 根据节点找业务信息
     * @param id
     * @return
     */
    List<Business> getBusInfoBySrcId(String id);

    /**
     * 根据链路找业务信息
     * @param src
     * @param src_port
     * @param dst
     * @param dst_port
     * @param link_type
     * @return
     */
    List<Business> getBusInfoByParam(String src, String src_port, String dst, String dst_port, String link_type);

    /**
     * 获取业务信息
     * @return
     */
    List<Business> getBusInfo();


    List<Business> getBusInfo(String algorithm);

    /**
     * 获取不同业务种类的业务数目
     * @return
     */
    String getBusNumByEachType();

    String getBusNumByEachType(String algorithm);

    /**
     * 获取业务信息表中的个数
     * @return
     */
    String getBusNum();
}
