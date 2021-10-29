package com.example.intelligentcontrolsystem.controller;

import com.example.intelligentcontrolsystem.entity.*;
import com.example.intelligentcontrolsystem.service.*;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TopoCon {

    private NodeSer nodeSer;
    private LinkSer linkSer;
    private BusinessSer businessSer;

    @Autowired
    public void setNodeSer(NodeSer nodeSer) {
        this.nodeSer = nodeSer;
    }
    @Autowired
    public void setLinkSer(LinkSer linkSer) {
        this.linkSer = linkSer;
    }
    @Autowired
    public void setBusinessSer(BusinessSer businessSer) {
        this.businessSer = businessSer;
    }

    /**
     * 拓扑数据展示
     * @return
     */

    @RequestMapping(value = "/topodata", method = RequestMethod.GET)
    @ResponseBody
    public String topoData() {
        TopoEntity topoEntity = new TopoEntity();
        try {
            List<Link> links = linkSer.getAll();
            List<Node> nodes = nodeSer.getAll();
            topoEntity.setNodes(nodes);
            topoEntity.setLinks(links);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new Gson().toJson(topoEntity);
    }

    /**
     * 前端返回id，查询business表中所有由id发出的业务信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/topo/business/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getBusById(@PathVariable("id") String id) {
        List<Business> businesses = businessSer.getBusInfoBySrcId(id);
        Map<String, List<Business>> map = new HashMap<>();
        map.put("business", businesses);
        return new Gson().toJson(map);
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
    public String getBusByLink(@PathVariable("src") String src, @PathVariable("src_port") String src_port,
                                  @PathVariable("dst") String dst, @PathVariable("dst_port") String dst_port,
                                  @PathVariable("link_type") String link_type) {
        List<Business> businesses = businessSer.getBusInfoByParam(src, src_port, dst, dst_port, link_type);
        Map<String, List<Business>> map = new HashMap<>();
        map.put("business", businesses);
        return new Gson().toJson(map);
    }

    @RequestMapping(value = "/topo/single", method = RequestMethod.GET)
    @ResponseBody
    public String getSingleTopo() {
        TopoEntity topoEntity = new TopoEntity();
        try {
            List<Link> links = linkSer.getSingleScore();
            List<Node> nodes = nodeSer.getAll();
            topoEntity.setNodes(nodes);
            topoEntity.setLinks(links);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new Gson().toJson(topoEntity);
    }
}
