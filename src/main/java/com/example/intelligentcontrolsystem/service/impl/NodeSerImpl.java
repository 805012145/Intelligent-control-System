package com.example.intelligentcontrolsystem.service.impl;

import com.example.intelligentcontrolsystem.dao.NodeDao;
import com.example.intelligentcontrolsystem.entity.Node;
import com.example.intelligentcontrolsystem.service.NodeSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NodeSerImpl implements NodeSer {
    private NodeDao nodeDao;

    @Autowired
    public void setNode_dao(NodeDao nodeDao) {
        this.nodeDao = nodeDao;
    }

    @Override
    public List<Node> getAll() {
        return nodeDao.getAll();
    }
}
