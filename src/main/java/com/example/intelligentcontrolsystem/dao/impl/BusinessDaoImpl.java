package com.example.intelligentcontrolsystem.dao.impl;

import com.example.intelligentcontrolsystem.dao.BusinessDao;
import com.example.intelligentcontrolsystem.entity.Business;
import com.example.intelligentcontrolsystem.entity.PieChart;
import com.example.intelligentcontrolsystem.entity.TableEntity;
import com.example.intelligentcontrolsystem.util.StringUtil;
import com.example.intelligentcontrolsystem.util.Util;
import com.google.gson.Gson;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class BusinessDaoImpl implements BusinessDao {

    @Override
    public List<Business> getBusInfoBySrcId(String id, String algorithm) throws ParseException {
        Util util = new Util();
        if (util.keys("src:*").size() == 0) {
            util.UtilClose();
            return null;
        }
        List<Business> businesses = new ArrayList<>();
        List<String> tables = new ArrayList<>(util.keys("src:*"));
        for (String table : tables) {
            String[] quaternion = table.split(" ");
            Business business;
            if (util.hget(table, "isArrive").equals("true")) {
                business = getBusiness(algorithm, quaternion, true, table, util);
            } else {
                business = getBusiness(algorithm, quaternion, false, table, util);
            }
            if (business.getRoute().contains(id)) {
                businesses.add(business);
            }
        }
        util.UtilClose();
        return businesses;
    }

    @Override
    public List<PieChart> getBusInfoByParam(String source, String target,
                                            String type, String algorithm) throws ParseException {
        Util util = new Util();
        if (util.keys("src:*").size() == 0) {
            util.UtilClose();
            return null;
        }
        List<PieChart> pieCharts = new ArrayList<>();
        List<String> tables = new ArrayList<>(util.keys("src:*"));
        for (String table : tables) {
            if (StringUtil.hasStr(source, target, util.hget(table, algorithm+"_route"))
//                    && System.currentTimeMillis() - Long.parseLong(util.hget(table, "sendTime")) < 5000
                    && util.hget(table, "isArrive").equals("false")) {
                String[] routes = StringUtil.StringToArray(util.hget(table, algorithm+"_route"));
                String[] link_types = StringUtil.StringToArray(util.hget(table, algorithm+"_linkType"));
                String[] bandwidth = StringUtil.StringToArray(util.hget(table, algorithm+"_bandwidth"));
                for (int i = 0; i < routes.length - 1; i++) {
                    if (routes[i].equals(source) && routes[i+1].equals(target) && link_types[i].equals(type)) {
                        String band = bandwidth[i];
                        PieChart pieChart = new PieChart(Integer.parseInt(band), Integer.parseInt(util.hget(table, "businessType")));
                        pieCharts.add(pieChart);
                        break;
                    }
                }
            }
        }
        util.UtilClose();
        return pieCharts;
    }

    @Override
    public List<Business> getBusInfo(String algorithm) throws ParseException {
        Util util = new Util();
        if (util.keys("src:*").size() == 0) {
            util.UtilClose();
            return null;
        }
        List<String> tables = new ArrayList<>(util.keys("src*"));
        List<Business> businesses = new ArrayList<>();
        for (String table : tables) {
            String[] quaternion = table.split(" ");
            Business business;
            if (util.hget(table, algorithm+"_route") != null) {
                if (util.hget(table, "isArrive").equals("true")) {
                    business = getBusiness(algorithm, quaternion, true, table, util);
                } else {
                    business = getBusiness(algorithm, quaternion, false, table, util);
                }
                businesses.add(business);
            }
        }
        util.UtilClose();
        return businesses;
    }

    @Override
    public String getBusInfoByType(String algorithm) throws ParseException {
        Util util = new Util();
        List<Object[]> busInfo = new ArrayList<>();
        Object[] header = {"flowtype", "sum", "avgDelay", "avgArrivate"};
        busInfo.add(header);
        TableEntity tableEntity = new TableEntity();
        TableEntity.Data data = new TableEntity.Data();
        List<List<Business>> typeList = getTypeList(algorithm, util);
        int i = 0;
        for (List<Business> type : typeList) {
            i++;
            int timeDelay = 0;
            int lossFlow = 0;
            int delayNum = 0;
            Object[] bus = new Object[4];
            if (type.size() != 0) {
                for (Business business : type) {
                    if (business.getIsArrive().equals("false")) {
                        lossFlow++;
                    } else {
                        delayNum++;
                        int sendPacket = Integer.parseInt(business.getSendPacket());
                        int receivePacket = Integer.parseInt(business.getReceivePacket());
                        timeDelay += business.getDelay();
                        switch (business.getType()) {
                            case 1:
                                if ((sendPacket - receivePacket) / (1.0 * sendPacket) < 0.9) {
                                    lossFlow++;
                                }
                                break;
                            case 2:
                                if ((sendPacket - receivePacket) / (1.0 * sendPacket) < 0.8) {
                                    lossFlow++;
                                }
                                break;
                            case 3:
                                if ((sendPacket - receivePacket) / (1.0 * sendPacket) < 0.7) {
                                    lossFlow++;
                                }
                                break;
                            case 4:
                                if ((sendPacket - receivePacket) / (1.0 * sendPacket) < 0.6) {
                                    lossFlow++;
                                }
                                break;
                            case 5:
                                if ((sendPacket - receivePacket) / (1.0 * sendPacket) < 0.5) {
                                    lossFlow++;
                                }
                                break;
                            case 6:
                                if ((sendPacket - receivePacket) / (1.0 * sendPacket) < 0.4) {
                                    lossFlow++;
                                }
                                break;
                            default:
                                break;
                        }
                    }
                }
                bus[0] = String.valueOf(i);
                bus[1] = type.size();
                bus[2] = timeDelay / (1.0 * delayNum);
                bus[3] = (type.size() - lossFlow) / (1.0 * type.size());
            }else {
                bus[0] = String.valueOf(i);
                bus[1] = 0;
                bus[2] = 0;
                bus[3] = 0;
            }
            busInfo.add(bus);
        }
        data.setSource(busInfo);
        tableEntity.setData(data);
        return new Gson().toJson(tableEntity);
    }


    private List<List<Business>> getTypeList(String algorithm, Util util) throws ParseException {
        List<Business> type1 = new ArrayList<>();
        List<Business> type2 = new ArrayList<>();
        List<Business> type3 = new ArrayList<>();
        List<Business> type4 = new ArrayList<>();
        List<Business> type5 = new ArrayList<>();
        List<Business> type6 = new ArrayList<>();
        List<List<Business>> typeList = new ArrayList<>();
        List<String> tables = new ArrayList<>(util.keys("src*"));
        for (String table : tables) {
            String[] quaternion = table.split(" ");
            Business business;
            if (util.hget(table, "isArrive").equals("true")) {
                business = getBusiness(algorithm, quaternion, true, table, util);
            } else {
                business = getBusiness(algorithm, quaternion, false, table, util);
            }
            switch (business.getType()) {
                case 1:
                    type1.add(business);
                    break;
                case 2:
                    type2.add(business);
                    break;
                case 3:
                    type3.add(business);
                    break;
                case 4:
                    type4.add(business);
                    break;
                case 5:
                    type5.add(business);
                    break;
                case 6:
                    type6.add(business);
                    break;
                default:
                    break;
            }
        }
        typeList.add(type1);
        typeList.add(type2);
        typeList.add(type3);
        typeList.add(type4);
        typeList.add(type5);
        typeList.add(type6);
        return typeList;
    }

    private Business getBusiness(String algorithm, String[] quaternion, boolean isArrive, String table, Util util) throws ParseException {
        Business business = new Business();
        business.setSrc(quaternion[0].split(":")[1]);
        business.setSrcPort(quaternion[1].split(":")[1]);
        business.setDst(quaternion[2].split(":")[1]);
        business.setDstPort(quaternion[3].split(":")[1]);
        business.setType(Integer.parseInt(util.hget(table, "businessType")));
        business.setSendTime(util.hget(table, "sendTime"));
        business.setSendPacket(util.hget(table, "sendPacket"));
        if (isArrive) {
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSSS");
            business.setReceivePacket(util.hget(table, "receivePacket"));
            business.setDelay(formatter.parse(util.hget(table, "receiveTime")).getTime() -
                    formatter.parse(util.hget(table, "sendTime")).getTime());
            business.setRoute(util.hget(table, algorithm+"_route"));
            business.setLinkType(util.hget(table, algorithm+"_linkType"));
            business.setBandwidth(util.hget(table, algorithm+"_bandwidth"));
        }else if (util.hget(table, algorithm+"_route") != null) {
            business.setRoute(util.hget(table, algorithm+"_route"));
            business.setLinkType(util.hget(table, algorithm+"_linkType"));
            business.setBandwidth(util.hget(table, algorithm+"_bandwidth"));
            business.setDelay(-1);
        }else {
            business.setDelay(-1);
        }
        business.setIsArrive(util.hget(table, "isArrive"));
        return business;
    }
}