package com.example.intelligentcontrolsystem.entity;

import java.util.Objects;

public class Host extends Node{

    private String switch_id;

    private String ip;

    public Host(String id, String name, String symbolsize, String x, String y,
                String value, String category, String field, String switch_id, String ip) {
        super(id, name, symbolsize, x, y, value, category, field);
        this.switch_id = switch_id;
        this.ip = ip;
    }

    public String getSwitch_id() {
        return switch_id;
    }

    public void setSwitch_id(String switch_id) {
        this.switch_id = switch_id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Host host = (Host) o;
        return Objects.equals(switch_id, host.switch_id) && Objects.equals(ip, host.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), switch_id, ip);
    }

    @Override
    public String toString() {
        return "Host{" +
                "switch_id='" + switch_id + '\'' +
                ", ip='" + ip + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", symbolsize='" + symbolsize + '\'' +
                ", x='" + x + '\'' +
                ", y='" + y + '\'' +
                ", value='" + value + '\'' +
                ", category='" + category + '\'' +
                ", field='" + field + '\'' +
                '}';
    }
}
