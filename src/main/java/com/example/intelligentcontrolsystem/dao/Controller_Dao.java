package com.example.intelligentcontrolsystem.dao;

import com.example.intelligentcontrolsystem.entity.Controller;
import com.example.intelligentcontrolsystem.entity.Location;

public interface Controller_Dao {

    public Controller get_controller_info(String id) throws Exception;

    public Location get_location(String id) throws Exception;

}
