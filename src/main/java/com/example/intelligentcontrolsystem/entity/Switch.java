package com.example.intelligentcontrolsystem.entity;

public class Switch extends Node{
    public Switch(String name, String symbolsize, String x, String y,
                  String value, String category, String field) {
        super(name, symbolsize, x, y, value, category, field);
    }

    @Override
    public String toString() {
        return "Switch{" +
                "id='" + id + '\'' +
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
