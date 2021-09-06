package com.example.intelligentcontrolsystem.dao.impl;

import com.example.intelligentcontrolsystem.dao.Controller_Dao;
import com.example.intelligentcontrolsystem.entity.Controller;
import com.example.intelligentcontrolsystem.entity.Location;
import org.springframework.stereotype.Repository;

@Repository
public class Controller_Dao_impl implements Controller_Dao {
    @Override
    public Controller get_controller_info(String id) throws Exception {
        return null;
    }

    @Override
    public Location get_location(String id) throws Exception {
        return null;
    }
}
