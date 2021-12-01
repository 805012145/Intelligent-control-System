package com.example.intelligentcontrolsystem.entity;

public class Business {

    private String src;

    private String dst;

    private String srcPort;

    private String dstPort;

    private String route;

    private String linkType;

    private String bandwidth;

    private long delay;

    private String sendTime;

    private String sendPacket;

    private String receivePacket;

    private String receiveTime;

    private int type;

    private String id;

    private String isArrive;

    private String activate_node;

    public String getActivate_node() {
        return activate_node;
    }

    public void setActivate_node(String activate_node) {
        this.activate_node = activate_node;
    }

    public Business() {

    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getDst() {
        return dst;
    }

    public void setDst(String dst) {
        this.dst = dst;
    }

    public String getSrcPort() {
        return srcPort;
    }

    public void setSrcPort(String srcPort) {
        this.srcPort = srcPort;
    }

    public String getDstPort() {
        return dstPort;
    }

    public void setDstPort(String dstPort) {
        this.dstPort = dstPort;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType;
    }

    public String getBandwidth() {
        return bandwidth;
    }

    public void setBandwidth(String bandwidth) {
        this.bandwidth = bandwidth;
    }

    public long getDelay() {
        return delay;
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getSendPacket() {
        return sendPacket;
    }

    public void setSendPacket(String sendPacket) {
        this.sendPacket = sendPacket;
    }

    public String getReceivePacket() {
        return receivePacket;
    }

    public void setReceivePacket(String receivePacket) {
        this.receivePacket = receivePacket;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsArrive() {
        return isArrive;
    }

    public void setIsArrive(String isArrive) {
        this.isArrive = isArrive;
    }
}
