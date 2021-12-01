package com.example.intelligentcontrolsystem.dao.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
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
    private Map<String, String > flagMap = new HashMap<>();

    @Override
    public List<Business> getBusInfoBySrcId(String id, String algorithm) throws ParseException {
        Util util = new Util();
        if (util.keys("*\"src\"*").size() == 0) {
            util.UtilClose();
            return null;
        }
        List<Business> businesses = new ArrayList<>();
        for (int i = 1; i <=6 ; i++) {
            List<String> tables = new ArrayList<>(util.keys("*src*business*:"+i+"*"));
            for (String table : tables) {
                Map<String, String > map;
                map = util.hgetAll(table);
                String[] quaternion = table.split(",");
                if (map.get(algorithm+"_route") == null) {
                    continue;
                }
                Business business;
                if (!map.get("recv_time").equals("0")) {
                    business = getBusiness(algorithm, quaternion, true, map);
                } else {
                    business = getBusiness(algorithm, quaternion, false, map);
                }
                if (business.getRoute() != null && business.getRoute().contains(id)) {
                    businesses.add(business);
                }
            }
        }
        util.UtilClose();
        return businesses;
    }

    @Override
    public List<PieChart> getBusInfoByParam(String source, String target,
                                            String type, String algorithm) throws ParseException {
        Util util = new Util();
        if (util.keys("*\"src\"*").size() == 0) {
            util.UtilClose();
            return null;
        }
        int[] total = new int[6];
        List<PieChart> pieCharts = new ArrayList<>();
        for (int i = 1; i <=6 ; i++) {
            List<String> tables = new ArrayList<>(util.keys("*src*business*:"+i+"*"));
            if (tables.size() == 0) continue;
            for (String table : tables) {
                Map<String, String > map;
                map = util.hgetAll(table);
                if (map.get(algorithm+"_route") == null || !map.get("recv_time").equals("0")) {
                    continue;
                }
                if (StringUtil.hasStr(source, target, map.get(algorithm+"_route"))) {
                    String[] routes = StringUtil.StringToArray(map.get(algorithm+"_route"));
                    String[] link_types = StringUtil.StringToArray(map.get(algorithm+"_linkType"));
                    String[] bandwidth = StringUtil.StringToArray(map.get(algorithm+"_bandwidth"));
                    for (int j = 0; j < routes.length - 1; j++) {
                        String[] quaternion = table.split(",");
                        if (routes[j].equals(source) && routes[j+1].equals(target) && link_types[j].equals(type)) {
                            Integer busType =  Integer.parseInt(quaternion[3].split(":")[1].replace('}',' ').trim());
                            String band = bandwidth[j];
                            total[busType-1] +=Integer.parseInt(band);
                            break;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < 6; i++) {
            PieChart pieChart = new PieChart(total[i], i+1);
            pieCharts.add(pieChart);
        }
        util.UtilClose();
        return pieCharts;
    }

    @Override
    public List<Business> getBusInfo(String algorithm) throws ParseException {
        Util util = new Util();
        if (util.keys("*\"src\"*").size() == 0) {
            util.UtilClose();
            return null;
        }
        List<Business> businesses = new ArrayList<>();
        for (int i = 1; i <=6 ; i++) {
            List<String> tables = new ArrayList<>(util.keys("*src*business*:"+i+"*"));
            for (String table : tables) {
                Map<String, String > map;
                map = util.hgetAll(table);
                String[] quaternion = table.split(",");
                Business business;
                if (map.get(algorithm+"_route") != null) {
                    if (!map.get("recv_time").equals("0")) {
                        business = getBusiness(algorithm, quaternion, true, map);
                    } else {
                        business = getBusiness(algorithm, quaternion, false, map);
                    }
                    businesses.add(business);
                }
            }
        }
        util.UtilClose();
        return businesses;
    }

    @Override
    public String getBusInfoByType(String algorithm) throws ParseException {
        List<Object[]> busInfo = new ArrayList<>();
        Object[] header = {"flowtype", "sum", "avgDelay", "avgArrivate"};
        busInfo.add(header);
        TableEntity tableEntity = new TableEntity();
        TableEntity.Data data = new TableEntity.Data();
        System.out.println(DateUtil.now());
        List<Business>[] typeList = getTypeList(algorithm);
        System.out.println(DateUtil.now());
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
                        lossFlow = getLossFlow(lossFlow, business, sendPacket, receivePacket);
                    }
                }
                bus[0] = String.valueOf(i);
                bus[1] = type.size();
                bus[2] = delayNum == 0 ? 0 : timeDelay / (1.0 * delayNum);
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

    @Override
    public double[] getBusAvgDelay(String algorithm) throws ParseException {
        Util util = new Util();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        double[] delayList = new double[6];
        if (util.keys("*\"src\"*").size() == 0) {
            util.UtilClose();
            return delayList;
        }
        List<Business>[] typeList = getArriveListByType(algorithm, util);
        int i = 0;
        for (List<Business> type : typeList) {
            i++;
            String delayFlag = "delayFlag"+i;
            if (type.size() <= 0) continue;
            int size = 0;
            if (flagMap.get(delayFlag) != null) {
                String flag = flagMap.get(delayFlag);
                for (Business business : type) {
                    Long time1 = formatter.parse(business.getReceiveTime()).getTime();
                    Long time2 = formatter.parse(flag).getTime();
                    if (time1.compareTo(time2) > 0) {
                        size++;
                        delayList[i-1] += business.getDelay();
                    }
                }
            }else {
                size = type.size();
                for (Business business : type) {
                    delayList[i-1] += business.getDelay();
                }
            }
            delayList[i - 1] = size == 0 ? 0 : delayList[i - 1] / size;
            flagMap.put(delayFlag, type.get(0).getReceiveTime());
        }
        util.UtilClose();
        return delayList;
    }

    @Override
    public double[] getBusAvgArrivate(String algorithm) throws ParseException {
        Util util = new Util();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        double[] arrivateList = new double[6];
        if (util.keys("*\"src\"*").size() == 0) {
            util.UtilClose();
            return arrivateList;
        }
        List<Business>[] typeList = getTypeList(algorithm);
        int i = 0;
        for (List<Business> type : typeList) {
            i++;
            int size = 0;
            int lossFlow = 0;
            String arrivateFlag = "arrivateFlag"+i;
            if (type.size() <= 0) continue;
            if (flagMap.get(arrivateFlag) != null) {
                String flag = flagMap.get(arrivateFlag);
                for (Business business : type) {
                    Long time1 = formatter.parse(business.getSendTime()).getTime();
                    Long time2 = formatter.parse(flag).getTime();
                    if (time1.compareTo(time2) > 0) {
                        size++;
                        if (business.getIsArrive().equals("false")) {
                            lossFlow++;
                        } else {
                            int sendPacket = Integer.parseInt(business.getSendPacket());
                            int receivePacket = Integer.parseInt(business.getReceivePacket());
                            lossFlow = getLossFlow(lossFlow, business, sendPacket, receivePacket);
                        }
                    }
                }
            }else {
                size = type.size();
                for (Business business : type) {
                    if (business.getIsArrive().equals("false")) {
                        lossFlow++;
                    } else {
                        int sendPacket = Integer.parseInt(business.getSendPacket());
                        int receivePacket = Integer.parseInt(business.getReceivePacket());
                        lossFlow = getLossFlow(lossFlow, business, sendPacket, receivePacket);
                    }
                }
            }
            arrivateList[i-1] = size == 0?  0:(size-lossFlow)/(1.0 * size);
            flagMap.put(arrivateFlag, type.get(0).getSendTime());
        }
        util.UtilClose();
        return arrivateList;
    }

    @Override
    public int[] getBusNum(String algorithm) throws ParseException {
        Util util = new Util();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        int[] numList = new int[6];
        if (util.keys("*\"src\"*").size() == 0) {
            util.UtilClose();
            return numList;
        }
        List<Business>[] typeList = getTypeList(algorithm);
        int i = 0;
        for (List<Business> type : typeList) {
            i++;
            int size = 0;
            String numFlag = "numFlag" + i;
            if (type.size() <= 0) continue;
            String flag = flagMap.get(numFlag);
            for (Business business : type) {
                if (flag!=null) {
                    Long time1 = formatter.parse(business.getSendTime()).getTime();
                    Long time2 = formatter.parse(flag).getTime();
                    if (time1.compareTo(time2) > 0) {
                        size++;
                    }
                } else {
                    size = type.size();
                }
            }
            numList[i-1] = size;
            flagMap.put(numFlag, type.get(0).getSendTime());
        }
        util.UtilClose();
        return numList;
    }

    private int getLossFlow(int lossFlow, Business business, int sendPacket, int receivePacket) {
        switch (business.getType()) {
            case 1:
                if (receivePacket / (1.0 * sendPacket) < 0.9) {
                    lossFlow++;
                }
                break;
            case 2:
                if (receivePacket / (1.0 * sendPacket) < 0.8) {
                    lossFlow++;
                }
                break;
            case 3:
                if (receivePacket / (1.0 * sendPacket) < 0.7) {
                    lossFlow++;
                }
                break;
            case 4:
                if ( receivePacket / (1.0 * sendPacket) < 0.6) {
                    lossFlow++;
                }
                break;
            case 5:
                if (receivePacket / (1.0 * sendPacket) < 0.5) {
                    lossFlow++;
                }
                break;
            case 6:
                if ( receivePacket / (1.0 * sendPacket) < 0.4) {
                    lossFlow++;
                }
                break;
            default:
                break;
        }
        return lossFlow;
    }

    private void sort(SimpleDateFormat formatter, List<Business> businesses) {
        businesses.sort((t1, t2) -> {
            Long time1 = 0L;
            Long time2 = 0L;
            try {
                time1 = formatter.parse(t1.getSendTime()).getTime();
                time2 = formatter.parse(t2.getSendTime()).getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return time2.compareTo(time1);
        });
    }

    private List<Business>[] getArriveListByType(String algorithm, Util util) throws ParseException {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        List<Business>[] typeList = new List[6];
        for (int i = 1; i <=6 ; i++) {
            List<Business> type = new ArrayList<>();
            List<String> tables = new ArrayList<>(util.keys("*src*business*:"+i+"*"));
            for (String table : tables) {
                Map<String, String > map;
                map = util.hgetAll(table);
                String[] quaternion = table.split(",");
                Business business;
                if (map.get("recv_time").equals("0")) {
                    continue;
                }
                business = getBusiness(algorithm, quaternion, true, map);
                type.add(business);
            }
            typeList[i-1] = type;
        }
        for (List<Business> businesses : typeList) {
            sort(formatter, businesses);
        }
        return typeList;
    }
    
    private List<Business>[] getTypeList(String algorithm) throws ParseException {
        Util util = new Util();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        List<Business>[] typeList = new List[6];
        for (int i = 1; i <=6 ; i++) {
            List<Business> type = new ArrayList<>();
            List<String> tables = new ArrayList<>(util.keys("*src*business*:"+i+"*"));
            for (String table : tables) {
                Map<String, String > map;
                map = util.hgetAll(table);
                String[] quaternion = table.split(",");
                Business business;
                if (map.get("recv_time").equals("0")) {
                    business = getBusiness(algorithm, quaternion, false, map);
                } else {
                    business = getBusiness(algorithm, quaternion, true, map);
                }
                type.add(business);
            }
            typeList[i-1] = type;
        }
        for (List<Business> businesses : typeList) {
            sort(formatter, businesses);
        }
        util.UtilClose();
        return typeList;
    }

    private Business getBusiness(String algorithm, String[] quaternion, boolean isArrive, Map<String, String> map) throws ParseException {
        Business business = new Business();
        business.setSrc(quaternion[0].split(":")[1].replace('\"', ' ').replace('\"', ' ').trim());
        business.setSrcPort(quaternion[2].split(":")[1]);
        business.setDst(quaternion[1].split(":")[1].replace('\"', ' ').replace('\"', ' ').trim());
        business.setDstPort(quaternion[2].split(":")[1]);
        business.setType(Integer.parseInt(quaternion[3].split(":")[1].replace('}',' ').trim()));
        business.setSendTime(map.get("send_time"));
        business.setSendPacket(map.get("send_number"));
        if (isArrive) {
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            business.setReceiveTime(map.get("recv_time"));
            business.setReceivePacket(map.get("recv_number"));
            business.setDelay(DateUtil.betweenMs(DateTime.of(formatter.parse(map.get("recv_time").substring(0, 23))), DateTime.of(formatter.parse(map.get("send_time").substring(0, 23)))));
            business.setRoute(map.get(algorithm+"_route"));
            business.setLinkType(map.get(algorithm+"_linkType"));
            business.setBandwidth(map.get(algorithm+"_bandwidth"));
            business.setActivate_node(map.get("active_node"));
            business.setIsArrive("true");
        }else if (map.get(algorithm+"_route") != null) {
            business.setRoute(map.get(algorithm+"_route"));
            business.setLinkType(map.get(algorithm+"_linkType"));
            business.setBandwidth(map.get(algorithm+"_bandwidth"));
            business.setActivate_node(map.get("active_node"));
            business.setIsArrive("false");
            business.setDelay(-1);
        }else {
            business.setDelay(-1);
            business.setIsArrive("false");
        }
        return business;
    }
}