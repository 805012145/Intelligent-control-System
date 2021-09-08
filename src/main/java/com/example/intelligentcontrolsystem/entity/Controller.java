package com.example.intelligentcontrolsystem.entity;

public class Controller extends Node{
    public Controller(String id, String name, String symbolsize, String x, String y,
                      String value, String category, String field) {
        super(id, name, symbolsize, x, y, value, category, field);
    }

    @Override
    public String toString() {
        return "Controller{" +
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
