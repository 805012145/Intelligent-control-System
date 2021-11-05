package com.example.intelligentcontrolsystem.controller;

import com.example.intelligentcontrolsystem.entity.Business;
import com.example.intelligentcontrolsystem.entity.Node;
import com.example.intelligentcontrolsystem.service.BusinessSer;
import com.example.intelligentcontrolsystem.service.NodeSer;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BusinessCon {

    private BusinessSer businessSer;
    private NodeSer nodeSer;
    public String algorithm = "DNN";

    @Autowired
    public void setBusinessSer(BusinessSer businessSer) {
        this.businessSer = businessSer;
    }
    @Autowired
    public void setNodeSer(NodeSer nodeSer) {
        this.nodeSer = nodeSer;
    }

    /**
     * 业务流量信息表格化展示
     * @return
     */
    @RequestMapping(value = "/business/businessdata", method = RequestMethod.GET)
    @ResponseBody
    public String getBusInfo() throws ParseException {
        List<Business> businesses = businessSer.getBusInfo(algorithm);
        return new Gson().toJson(businesses);
    }

    /**
     * 节点信息展示
     * @return
     */
    @RequestMapping(value = "/business/nodedata", method = RequestMethod.GET)
    @ResponseBody
    public String getNodeData() {
        List<Node> nodes = nodeSer.getAll();
        Map<String, List<Node>> map = new HashMap<>();
        map.put("nodes", nodes);
        return new Gson().toJson(map);
    }

    /**
     *
     */
    @RequestMapping(value = "/business/info", method = RequestMethod.GET)
    @ResponseBody
    public String getBusDelay() throws ParseException {
        return businessSer.getBusInfoByType(algorithm);
    }

}
