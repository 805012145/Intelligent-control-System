package com.example.intelligentcontrolsystem.controller;

import com.example.intelligentcontrolsystem.service.Test_Ser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Test_Con {

    private Test_Ser test_ser;
    @Autowired
    public void setTest_ser(Test_Ser test_ser) {
        this.test_ser = test_ser;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String test() throws Exception {
        return test_ser.test();
    }
}
