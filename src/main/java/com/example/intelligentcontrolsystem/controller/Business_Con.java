package com.example.intelligentcontrolsystem.controller;

import com.example.intelligentcontrolsystem.entity.Business;
import com.example.intelligentcontrolsystem.service.Business_Ser;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class Business_Con {

    @Autowired
    private Business_Ser business_ser;

    /**
     * 业务流量信息表格化展示
     * @return
     */
    @RequestMapping(value = "/businessdata", method = RequestMethod.GET)
    @ResponseBody
    public String getBusInfo() {
        List<Business> businesses = business_ser.getBusInfo();
        return new Gson().toJson(businesses);
    }

    @RequestMapping(value = "/businessnum", method = RequestMethod.GET)
    @ResponseBody
    public String getBusNumByEachType() {
        return business_ser.getBusNumByEachType();
    }
}
