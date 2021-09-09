package com.example.intelligentcontrolsystem.service;

import com.example.intelligentcontrolsystem.entity.Link;

import java.util.List;

public interface Link_Ser {
    List<Link> getAll();

    String getBWInfoOfAllType();

    String getBWConOfAllLinks();
}
