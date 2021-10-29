package com.example.intelligentcontrolsystem.controller;

import com.example.intelligentcontrolsystem.service.LinkSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BandWidthCon {

    private LinkSer linkSer;

    //推荐使用构造器注入方式，属性注入方式可能会出现NullPointerException
    @Autowired
    public void setLink_ser(LinkSer linkSer) {
        this.linkSer = linkSer;
    }

    /**
     * 各信道总带宽占用
     * @return
     */
    @RequestMapping(value = "/bandwidth/usage", method = RequestMethod.GET)
    @ResponseBody
    public String getBWUsage() {
        return linkSer.getBWInfoOfAllType();
    }

    //todo JSON字符串可能仍需重新设计
    /**
     * 带宽拥塞
     * @return
     */
    @RequestMapping(value = "/bandwidth/congestion", method = RequestMethod.GET)
    @ResponseBody
    public String getBWCongestion() {
        return linkSer.getBWConOfAllLinks();
    }
}
