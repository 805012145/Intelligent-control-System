package com.example.intelligentcontrolsystem.service.impl;

import com.example.intelligentcontrolsystem.dao.LinkDao;
import com.example.intelligentcontrolsystem.entity.Link;
import com.example.intelligentcontrolsystem.service.LinkSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkSerImpl implements LinkSer {

    private LinkDao linkDao;

    @Autowired
    public void setLink_dao(LinkDao linkDao) {
        this.linkDao = linkDao;
    }

    @Override
    public List<Link> getAll() {
        return linkDao.getAll();
    }

    @Override
    public String getBWInfoOfAllType() {
        return linkDao.getBWInfoOfAllType();
    }

    @Override
    public String getBWConOfAllLinks() {
        return linkDao.getBWConOfAllLinks();
    }

    @Override
    public List<Link> getSingleScore() {
        return linkDao.getSingleScore();
    }

    @Override
    public String getSingleLinkState(String source, String target) {
        return linkDao.getSingleLinkState(source, target);
    }
}
