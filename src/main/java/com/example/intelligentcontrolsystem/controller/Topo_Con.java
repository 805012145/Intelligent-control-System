package com.example.intelligentcontrolsystem.controller;

import com.example.intelligentcontrolsystem.entity.*;
import com.example.intelligentcontrolsystem.service.*;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @RequestMapping(value = "/topodata", method = RequestMethod.GET)
    @ResponseBody
    public String topodata1() {
        TopoEntity topoEntity = new TopoEntity();
        List<Link> links = link_ser.getAll();
        List<Node> nodes = node_ser.getAll();
        topoEntity.setNodes(nodes);
        topoEntity.setLinks(links);
        return new Gson().toJson(topoEntity);
    }

}
