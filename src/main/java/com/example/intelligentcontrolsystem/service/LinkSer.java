package com.example.intelligentcontrolsystem.service;

import com.example.intelligentcontrolsystem.entity.Link;

import java.util.List;

public interface LinkSer {
    List<Link> getAll();

    String getBWInfoOfAllType();

    String getBWConOfAllLinks();

    List<Link> getSingleScore();

    String getSingleLinkState(String source, String target);
}
