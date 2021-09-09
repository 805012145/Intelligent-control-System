package com.example.intelligentcontrolsystem.controller;


import com.example.intelligentcontrolsystem.service.Business_Ser;
import com.example.intelligentcontrolsystem.service.Link_Ser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class BandWidth_Con {

    @Autowired
    private Business_Ser business_ser;

    @Autowired
    private Link_Ser link_ser;

    /**
     * 各信道总带宽占用
     * @return
     */
    @RequestMapping(value = "/bandwidth/usage", method = RequestMethod.GET)
    @ResponseBody
    public String getBWUsage() {
        return link_ser.getBWInfoOfAllType();
    }

    /**
     * 带宽拥塞
     * @return
     */
    @RequestMapping(value = "/bandwidth/congestion", method = RequestMethod.GET)
    @ResponseBody
    public String getBWCongestion() {
        return link_ser.getBWConOfAllLinks();
    }
}
