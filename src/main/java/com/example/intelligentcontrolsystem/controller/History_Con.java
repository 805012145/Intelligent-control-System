package com.example.intelligentcontrolsystem.controller;

import com.example.intelligentcontrolsystem.entity.History_para;
import com.example.intelligentcontrolsystem.service.History_para_Ser;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class History_Con {

    @Autowired
    private History_para_Ser history_para_ser;

    /**
     * 历史信息展示
     * @return
     */
    @RequestMapping(value = "/history/historydata", method = RequestMethod.GET)
    @ResponseBody
    public String historyData() {
        List<History_para> history_paras = history_para_ser.getAllHistoryParam();
        return new Gson().toJson(history_paras);
    }
}
