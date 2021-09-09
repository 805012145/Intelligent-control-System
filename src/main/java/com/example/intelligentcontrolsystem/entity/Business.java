package com.example.intelligentcontrolsystem.entity;


public class Business {

    private String src;

    private String dst;

    private String route;

    private String link_type;

    private String bandwidth;

    private String delay;

    private String time;

    private String type;

    private String id;

    public Business(String src, String dst, String route, String link_type,
                    String bandwidth, String delay, String time, String type) {
        this.src = src;
        this.dst = dst;
        this.route = route;
        this.link_type = link_type;
        this.bandwidth = bandwidth;
        this.delay = delay;
        this.time = time;
        this.type = type;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public void setDst(String dst) {
        this.dst = dst;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public void setLink_type(String link_type) {
        this.link_type = link_type;
    }

    public void setBandwidth(String bandwidth) {
        this.bandwidth = bandwidth;
    }

    public void setDelay(String delay) {
        this.delay = delay;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSrc() {
        return src;
    }

    public String getDst() {
        return dst;
    }

    public String getRoute() {
        return route;
    }

    public String getLink_type() {
        return link_type;
    }

    public String getBandwidth() {
        return bandwidth;
    }

    public String getDelay() {
        return delay;
    }

    public String getTime() {
        return time;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }
}
