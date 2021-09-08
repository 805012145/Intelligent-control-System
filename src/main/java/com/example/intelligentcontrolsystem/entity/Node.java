package com.example.intelligentcontrolsystem.entity;

import java.util.Objects;

public class Node {
    public String id;

    public String name;

    public String symbolsize;

    public String x;

    public String y;

    public String value;

    public String category;

    public String field;

    public Node() {
    }

    public Node(String name, String symbolsize, String x, String y,
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(id, node.id) && Objects.equals(name, node.name) && Objects.equals(symbolsize, node.symbolsize) && Objects.equals(x, node.x) && Objects.equals(y, node.y) && Objects.equals(value, node.value) && Objects.equals(category, node.category) && Objects.equals(field, node.field);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, symbolsize, x, y, value, category, field);
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
                '}';
    }
}
