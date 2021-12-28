package com.example.intelligentcontrolsystem.dao;

import com.example.intelligentcontrolsystem.entity.Link;

import java.util.List;


public interface LinkDao {
    List<Link> getAll();

    String getBWInfoOfAllType();

    String getBWConOfAllLinks();

    List<Link> getSingleScore();

    String getSingleLinkState(String source, String target);
}
