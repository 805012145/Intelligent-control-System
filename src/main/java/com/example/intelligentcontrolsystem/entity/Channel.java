package com.example.intelligentcontrolsystem.entity;

import java.util.*;

public class Channel {

    private Map<String, String> channel1 = new HashMap<>();

    private Map<String, String> channel2 = new HashMap<>();
    private Map<String, String> channel3 = new HashMap<>();
    private Map<String, String> channel4 = new HashMap<>();
    private Map<String, String> channel5 = new HashMap<>();

    public Map<String, String> getChannel5() {
        return channel5;
    }

    public void setChannel5(Map<String, String> channel5) {
        this.channel5 = channel5;
    }

    public Map<String, String> getChannel1() {
        return channel1;
    }

    public void setChannel1(Map<String, String> channel1) {
        this.channel1 = channel1;
    }

    public Map<String, String> getChannel2() {
        return channel2;
    }

    public void setChannel2(Map<String, String> channel2) {
        this.channel2 = channel2;
    }

    public Map<String, String> getChannel3() {
        return channel3;
    }

    public void setChannel3(Map<String, String> channel3) {
        this.channel3 = channel3;
    }

    public Map<String, String> getChannel4() {
        return channel4;
    }

    public void setChannel4(Map<String, String> channel4) {
        this.channel4 = channel4;
    }
}
