package com.example.intelligentcontrolsystem.controller;

import com.example.intelligentcontrolsystem.service.HistoryParaSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HistoryCon {

    private HistoryParaSer historyParaSer;

    @Autowired
    public void setHistory_para_ser(HistoryParaSer historyParaSer) {
        this.historyParaSer = historyParaSer;
    }

    /**
     * 历史信息展示
     * @return
     */
    @RequestMapping(value = "/history/historydata", method = RequestMethod.GET)
    @ResponseBody
    public String historyData() {
        return historyParaSer.getAllHistoryParam();
    }
}
