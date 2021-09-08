package com.example.intelligentcontrolsystem.controller;

import com.example.intelligentcontrolsystem.service.Test_Ser;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Test_Con {
    @Autowired
    private Test_Ser test_ser;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String test() throws Exception {
        return new Gson().toJson(test_ser.test());
    }
}
