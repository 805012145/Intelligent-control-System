package com.example.intelligentcontrolsystem.entity;

public class Switch {

    private String id;

    private String name;

    private String symbolsize;

    private String x;

    private String y;

    private String value;

    private String category;

    private String field;

    public Switch(String name, String symbolsize, String x, String y,
                  String value, String category, String field) {
        this.name = name;
        this.symbolsize = symbolsize;
        this.x = x;
        this.y = y;
        this.value = value;
        this.category = category;
        this.field = field;
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
}
