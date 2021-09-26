package com.example.intelligentcontrolsystem.entity;

public class Switch extends Node{
    public Switch(String name, String symbolSize, Double x, Double y,
                  Integer value, Integer category, String field) {
        super(name, symbolSize, x, y, value, category, field);
    }

    @Override
    public String toString() {
        return "Switch{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", symbolSize='" + symbolSize + '\'' +
                ", x='" + x + '\'' +
                ", y='" + y + '\'' +
                ", value='" + value + '\'' +
                ", category='" + category + '\'' +
                ", field='" + field + '\'' +
                '}';
    }
}
