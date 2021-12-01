package com.example.intelligentcontrolsystem.dao;

import com.example.intelligentcontrolsystem.entity.Business;
import com.example.intelligentcontrolsystem.entity.PieChart;

import java.text.ParseException;
import java.util.List;

public interface BusinessDao {

    /**
     * 根据节点找业务信息
     * @param id
     * @return
     */
    List<Business> getBusInfoBySrcId(String id, String algorithm) throws ParseException;

    /**
     * 根据链路找业务信息
     * @return
     */
    List<PieChart> getBusInfoByParam(String source, String target,
                                     String type, String algorithm) throws ParseException;

    /**
     * 获取业务信息
     * @return
     */


    List<Business> getBusInfo(String algorithm) throws ParseException;

    /**
     * 获取不同业务种类的业务数目
     * @return
     */

    String getBusInfoByType(String algorithm) throws ParseException;

    double[] getBusAvgDelay(String algorithm) throws ParseException;

    double[] getBusAvgArrivate(String algorithm) throws ParseException;

    int[] getBusNum(String algorithm) throws ParseException;
}
