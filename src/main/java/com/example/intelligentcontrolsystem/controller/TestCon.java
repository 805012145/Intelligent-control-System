package com.example.intelligentcontrolsystem.controller;

import cn.hutool.core.date.DateUtil;
import com.example.intelligentcontrolsystem.entity.Business;
import com.example.intelligentcontrolsystem.entity.PieChart;
import com.example.intelligentcontrolsystem.service.BusinessSer;
import com.example.intelligentcontrolsystem.service.TestSer;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TestCon {

    private TestSer test_ser;
    private BusinessSer businessSer;
    @Autowired
    public void setTest_ser(TestSer test_ser) {
        this.test_ser = test_ser;
    }
    @Autowired
    public TestCon(BusinessSer businessSer) {
        this.businessSer = businessSer;
    }

//    @RequestMapping(value = "/test", method = RequestMethod.GET)
//    @ResponseBody
//    public String test() throws Exception {
//        test_ser.test();
//        return "yes";
//    }

    @RequestMapping(value = "/test/topo/business/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getBusById(@PathVariable String id) throws ParseException {
        System.out.println(id);
        List<Business> businesses = businessSer.getBusInfoBySrcId(id, "Routernet");
        Map<String, List<Business>> map = new HashMap<>();
        map.put("routingtable", businesses);
        return new Gson().toJson(map);
    }

    @RequestMapping(value = "/test/topo/business/{src}/{dst}/{type}", method = RequestMethod.GET)
    @ResponseBody
    public String getBusByLink(@PathVariable String src, @PathVariable String dst, @PathVariable String type) throws ParseException {
        try {
            System.out.println(DateUtil.now());
            Map<String, List<PieChart>> map = new HashMap<>();
            map.put("routingtable", businessSer.getBusInfoByParam(src, dst, type, "Routernet"));
            System.out.println(DateUtil.now());
            return new Gson().toJson(map);
        }catch (Exception e) {
            System.out.println("have no data");
        }
        return null;
    }
}
