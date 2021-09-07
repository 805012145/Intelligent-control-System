package com.example.intelligentcontrolsystem.entity;

import java.util.Objects;

public class Node {
    private String id;

    private String name;

    private String symbolsize;

    private String x;

    private String y;

    private String value;

    private String category;

    private String field;

    private String ip;

    private String switch_id;

    public Node(String name, String symbolsize, String x, String y, String value, String category, String field, String ip, String switch_id) {
        this.name = name;
        this.symbolsize = symbolsize;
        this.x = x;
        this.y = y;
        this.value = value;
        this.category = category;
        this.field = field;
        this.ip = ip;
        this.switch_id = switch_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbolsize() {
        return symbolsize;
    }

    public void setSymbolsize(String symbolsize) {
        this.symbolsize = symbolsize;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getSwitch_id() {
        return switch_id;
    }

    public void setSwitch_id(String switch_id) {
        this.switch_id = switch_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(id, node.id) && Objects.equals(name, node.name) && Objects.equals(symbolsize, node.symbolsize) && Objects.equals(x, node.x) &&
                Objects.equals(y, node.y) && Objects.equals(value, node.value) && Objects.equals(category, node.category) && Objects.equals(field, node.field) &&
                Objects.equals(ip, node.ip) && Objects.equals(switch_id, node.switch_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, symbolsize, x, y, value, category, field, ip, switch_id);
    }

    @Override
    public String toString() {
        return "Node{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", symbolsize='" + symbolsize + '\'' +
                ", x='" + x + '\'' +
                ", y='" + y + '\'' +
                ", value='" + value + '\'' +
                ", category='" + category + '\'' +
                ", field='" + field + '\'' +
                ", ip='" + ip + '\'' +
                ", switch_id='" + switch_id + '\'' +
                '}';
    }
}
