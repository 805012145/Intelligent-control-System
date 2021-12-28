package com.example.intelligentcontrolsystem.controller;

import com.example.intelligentcontrolsystem.entity.*;
import com.example.intelligentcontrolsystem.service.*;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TopoCon {

    private NodeSer nodeSer;
    private LinkSer linkSer;
    private BusinessSer businessSer;
    public String algorithm = "DNN";

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
//    @RequestMapping(value = "/topo/business/{id}", method = RequestMethod.GET)
    @PostMapping("topo/postNodeNum")
    @ResponseBody
    public String getBusById(String id) throws ParseException {
        System.out.println(id);
        List<Business> businesses = businessSer.getBusInfoBySrcId(id, algorithm);
        Map<String, List<Business>> map = new HashMap<>();
        map.put("routingtable", businesses);
        System.out.println(map);
        return new Gson().toJson(map);
    }

    /**
     * 前端返回src,dst,src_port,dst_port,link_type，查询business表中满足所有条件的最近时刻的名称，类型与占用带宽情况
     *
     * @return
     */
//    @RequestMapping(value = "/topo/business/{src}/{src_port}/{dst}/{dst_port}/{link_type}", method = RequestMethod.GET)
    @PostMapping("topo/postLinkState")
    @ResponseBody
    public String getBusByLink(String source, String target, String type) throws ParseException {
        System.out.println(source);
        List<PieChart> pieCharts = businessSer.getBusInfoByParam(source, target, type, algorithm);
        Map<String, List<PieChart>> map = new HashMap<>();
        map.put("routingtable", pieCharts);
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
    @PostMapping("topo/singleLinkState")
    @ResponseBody
    public String getSingleLinkState(String source, String target) throws ParseException {
        System.out.println(source +" "+target);
        System.out.println(linkSer.getSingleLinkState(source, target));
        return linkSer.getSingleLinkState(source, target);
    }
}