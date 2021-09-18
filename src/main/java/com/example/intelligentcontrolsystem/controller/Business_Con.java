package com.example.intelligentcontrolsystem.controller;

import com.example.intelligentcontrolsystem.entity.Business;
import com.example.intelligentcontrolsystem.entity.Node;
import com.example.intelligentcontrolsystem.service.Business_Ser;
import com.example.intelligentcontrolsystem.service.Node_Ser;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class Business_Con {

    private Business_Ser business_ser;
    private Node_Ser node_ser;

    @Autowired
    public void setBusiness_ser(Business_Ser business_ser) {
        this.business_ser = business_ser;
    }
    @Autowired
    public void setNode_ser(Node_Ser node_ser) {
        this.node_ser = node_ser;
    }

    /**
     * 业务流量信息表格化展示
     * @return
     */
    @RequestMapping(value = "/business/businessdata", method = RequestMethod.GET)
    @ResponseBody
    public String getBusInfo() {
        List<Business> businesses = business_ser.getBusInfo();
        return new Gson().toJson(businesses);
    }

    /**
     * 节点信息展示
     * @return
     */
    @RequestMapping(value = "/business/nodedata", method = RequestMethod.GET)
    @ResponseBody
    public String getNodeData() {
        List<Node> nodes = node_ser.getAll();
        Map<String, List<Node>> map = new HashMap<>();
        map.put("nodes", nodes);
        return new Gson().toJson(map);
    }

    /**
     * 各类型业务数目展示
     * @return
     */
    @RequestMapping(value = "/business/businessnum", method = RequestMethod.GET)
    @ResponseBody
    public String getBusNumByEachType() {
        return business_ser.getBusNumByEachType();
    }
}
