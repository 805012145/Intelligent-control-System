package com.example.intelligentcontrolsystem.controller;

import com.example.intelligentcontrolsystem.entity.*;
import com.example.intelligentcontrolsystem.service.*;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class Topo_Con {

    @Autowired
    private Node_Ser node_ser;

    @Autowired
    private Link_Ser link_ser;

    @Autowired
    private Business_Ser business_ser;

    /**
     * 拓扑数据展示
     * @return
     */
    @RequestMapping(value = "/topodata", method = RequestMethod.GET)
    @ResponseBody
    public String topodata() {
        TopoEntity topoEntity = new TopoEntity();
        List<Link> links = link_ser.getAll();
        List<Node> nodes = node_ser.getAll();
        topoEntity.setNodes(nodes);
        topoEntity.setLinks(links);
        return new Gson().toJson(topoEntity);
    }

    /**
     * 前端返回id，查询business表中所有由id发出的业务信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/topo/business/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String get_bus_by_id(@PathVariable("id") String id) {
        System.out.println(id);
        List<Business> businesses = business_ser.getBusInfoBySrcId(id);
        return new Gson().toJson(businesses);
    }

    /**
     * 前端返回src,dst,src_port,dst_port,link_type，查询business表中满足所有条件的最近时刻的名称，类型与占用带宽情况
     * @param src
     * @param src_port
     * @param dst
     * @param dst_port
     * @param link_type
     * @return
     */
    @RequestMapping(value = "/topo/business/{src}/{src_port}/{dst}/{dst_port}/{link_type}", method = RequestMethod.GET)
    @ResponseBody
    public String get_bus_by_link(@PathVariable("src") String src, @PathVariable("src_port") String src_port,
                                  @PathVariable("dst") String dst, @PathVariable("dst_port") String dst_port,
                                  @PathVariable("link_type") String link_type) {
        System.out.println(src +" "+ src_port +" "+ dst +" "+ dst_port +" "+ link_type);
        List<Business> businesses = business_ser.getBusInfoByParam(src, src_port, dst, dst_port, link_type);
        return new Gson().toJson(businesses);
    }
}
