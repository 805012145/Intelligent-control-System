package com.example.intelligentcontrolsystem.dao.impl;

import com.example.intelligentcontrolsystem.dao.TestDao;
import com.example.intelligentcontrolsystem.util.Util;
import org.springframework.stereotype.Repository;

@Repository
public class TestDaoImpl implements TestDao {

    @Override
    public String test() throws Exception {
        Util util = new Util();
        String key = "src:10.0.0.1 srcport:1 dst:10.0.0.2 dstport:1";
        util.hset(key, "receiveTime", "2021-11-04 10:30:53:0755");
        util.hset(key, "isArrive", "true");
        util.hset(key, "sendTime", "2021-11-04 10:30:52:0755");
        util.hset(key, "Qar_route", "[1,2]");
        util.hset(key, "Qar_linkType", "[1]");
        util.hset(key, "Qar_bandwidth", "[2]");
        util.hset(key, "sendPacket", "100");
        util.hset(key, "receivePacket", "100");
        util.hset(key, "businessType", "1");

        String key1 = "src:10.0.0.1 srcport:2 dst:10.0.0.3 dstport:2";
        util.hset(key1, "receiveTime", "2021-11-04 10:40:58:0755");
        util.hset(key1, "isArrive", "true");
        util.hset(key1, "sendTime", "2021-11-04 10:40:55:0755");
        util.hset(key1, "Qar_route", "[1,2,3]");
        util.hset(key1, "Qar_linkType", "[1,2]");
        util.hset(key1, "Qar_bandwidth", "[2,2]");
        util.hset(key1, "sendPacket", "100");
        util.hset(key1, "receivePacket", "100");
        util.hset(key1, "businessType", "2");

        String key2 = "src:10.0.0.1 srcport:3 dst:10.0.0.4 dstport:3";
        util.hset(key2, "receiveTime", "2021-11-04 10:50:59:0755");
        util.hset(key2, "isArrive", "true");
        util.hset(key2, "sendTime", "2021-11-04 10:50:56:0755");
        util.hset(key2, "Qar_route", "[1,2,3,4]");
        util.hset(key2, "Qar_linkType", "[1,2,3]");
        util.hset(key2, "Qar_bandwidth", "[2,2,2]");
        util.hset(key2, "sendPacket", "100");
        util.hset(key2, "receivePacket", "100");
        util.hset(key2, "businessType", "3");

        String key3 = "src:10.0.0.1 srcport:4 dst:10.0.0.5 dstport:4";
        util.hset(key3, "receiveTime", "2021-11-04 10:51:00:0755");
        util.hset(key3, "isArrive", "true");
        util.hset(key3, "sendTime", "2021-11-04 10:50:59:0755");
        util.hset(key3, "Qar_route", "[1,2,3,4,5]");
        util.hset(key3, "Qar_linkType", "[1,2,2,1]");
        util.hset(key3, "Qar_bandwidth", "[2,2,2,2]");
        util.hset(key3, "sendPacket", "100");
        util.hset(key3, "receivePacket", "100");
        util.hset(key3, "businessType", "4");

        String key4 = "src:10.0.0.1 srcport:4 dst:10.0.0.6 dstport:4";
//        util.hset(key3, "receiveTime", "2021-11-05 10:51:00:0755");
        util.hset(key4, "isArrive", "false");
        util.hset(key4, "sendTime", "2021-11-04 10:50:59:0755");
        util.hset(key4, "Qar_route", "[1,2,3,4,5,6]");
        util.hset(key4, "Qar_linkType", "[1,2,2,1,1]");
        util.hset(key4, "Qar_bandwidth", "[1,2,2,2,2]");
        util.hset(key4, "sendPacket", "100");
//        util.hset(key3, "receivePacket", "100");
        util.hset(key4, "businessType", "1");

        String key5 = "src:10.0.0.1 srcport:4 dst:10.0.0.7 dstport:4";
//        util.hset(key3, "receiveTime", "2021-11-05 10:51:00:0755");
        util.hset(key5, "isArrive", "false");
        util.hset(key5, "sendTime", "2021-11-04 10:50:59:0755");
        util.hset(key5, "Qar_route", "[1,2,3,4,5,7]");
        util.hset(key5, "Qar_linkType", "[1,2,2,1,1]");
        util.hset(key5, "Qar_bandwidth", "[2,2,2,2,2]");
        util.hset(key5, "sendPacket", "100");
//        util.hset(key3, "receivePacket", "100");
        util.hset(key5, "businessType", "2");

        String key6 = "src:10.0.0.1 srcport:4 dst:10.0.0.8 dstport:4";
//        util.hset(key3, "receiveTime", "2021-11-05 10:51:00:0755");
        util.hset(key6, "isArrive", "false");
        util.hset(key6, "sendTime", "2021-11-04 10:50:59:0755");
        util.hset(key6, "Qar_route", "[1,2,3,4,8]");
        util.hset(key6, "Qar_linkType", "[1,2,2,1]");
        util.hset(key6, "Qar_bandwidth", "[3,2,2,2]");
        util.hset(key6, "sendPacket", "100");
//        util.hset(key3, "receivePacket", "100");
        util.hset(key6, "businessType", "3");
        util.UtilClose();
        return null;
    }
}
