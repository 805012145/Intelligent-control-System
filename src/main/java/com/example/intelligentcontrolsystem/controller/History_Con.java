package com.example.intelligentcontrolsystem.controller;

import com.example.intelligentcontrolsystem.entity.History_para;
import com.example.intelligentcontrolsystem.service.History_para_Ser;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class History_Con {

    private History_para_Ser history_para_ser;

    @Autowired
    public void setHistory_para_ser(History_para_Ser history_para_ser) {
        this.history_para_ser = history_para_ser;
    }

    /**
     * 历史信息展示
     * @return
     */
    @RequestMapping(value = "/history/historydata", method = RequestMethod.GET)
    @ResponseBody
    public String historyData() {
        return history_para_ser.getAllHistoryParam();
    }
}
