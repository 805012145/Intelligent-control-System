package com.example.intelligentcontrolsystem.service.impl;

import com.example.intelligentcontrolsystem.dao.Node_Dao;
import com.example.intelligentcontrolsystem.entity.Node;
import com.example.intelligentcontrolsystem.service.Node_Ser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Node_Ser_impl implements Node_Ser {
    private Node_Dao node_dao;

    @Autowired
    public void setNode_dao(Node_Dao node_dao) {
        this.node_dao = node_dao;
    }

    @Override
    public List<Node> getAll() {
        return node_dao.getAll();
    }
}
