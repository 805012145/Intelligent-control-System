package com.example.intelligentcontrolsystem.dao;

import com.example.intelligentcontrolsystem.entity.Location;
import com.example.intelligentcontrolsystem.entity.Switch;

public interface Switch_Dao {

    public Switch get_switch_info(String id) throws Exception;

    public Location get_location(String id) throws Exception;
}
