package com.example.intelligentcontrolsystem.entity;

public class Link {

    private String src;

    private String src_port;

    private String dst;

    private String dst_port;

    private String type;

    private String remain_bandwidth;

    private String max_bandwidth;

    private String score;

    private String delay;

    private String drop;

    private String id;

    public Link(String src, String src_port, String dst, String dst_port, String type,
                String remain_bandwidth, String max_bandwidth, String score, String delay, String drop) {
        this.src = src;
        this.src_port = src_port;
        this.dst = dst;
        this.dst_port = dst_port;
        this.type = type;
        this.remain_bandwidth = remain_bandwidth;
        this.max_bandwidth = max_bandwidth;
        this.score = score;
        this.delay = delay;
        this.drop = drop;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getSrc_port() {
        return src_port;
    }

    public void setSrc_port(String src_port) {
        this.src_port = src_port;
    }

    public String getDst() {
        return dst;
    }

    public void setDst(String dst) {
        this.dst = dst;
    }

    public String getDst_port() {
        return dst_port;
    }

    public void setDst_port(String dst_port) {
        this.dst_port = dst_port;
    }

    public String gettype() {
        return type;
    }

    public void settype(String type) {
        this.type = type;
    }

    public String getRemain_bandwidth() {
        return remain_bandwidth;
    }

    public void setRemain_bandwidth(String remain_bandwidth) {
        this.remain_bandwidth = remain_bandwidth;
    }

    public String getMax_bandwidth() {
        return max_bandwidth;
    }

    public void setMax_bandwidth(String max_bandwidth) {
        this.max_bandwidth = max_bandwidth;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getDelay() {
        return delay;
    }

    public void setDelay(String delay) {
        this.delay = delay;
    }

    public String getDrop() {
        return drop;
    }

    public void setDrop(String drop) {
        this.drop = drop;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
