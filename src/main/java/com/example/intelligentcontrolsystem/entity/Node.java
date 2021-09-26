package com.example.intelligentcontrolsystem.entity;

import java.util.Objects;

public class Node {
    public String id;

    public String name;

    public String symbolSize;

    public Double x;

    public Double y;

    public Integer value;

    public Integer category;

    public String field;

    public String symbol;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Node() {
    }

    public Node(String name, String symbolSize, Double x, Double y,
                Integer value, Integer category, String field) {
        this.name = name;
        this.symbolSize = symbolSize;
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

    public String getSymbolSize() {
        return symbolSize;
    }

    public void setSymbolSize(String symbolSize) {
        this.symbolSize = symbolSize;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
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
        return Objects.equals(id, node.id) && Objects.equals(name, node.name) && Objects.equals(symbolSize, node.symbolSize) && Objects.equals(x, node.x) && Objects.equals(y, node.y) && Objects.equals(value, node.value) && Objects.equals(category, node.category) && Objects.equals(field, node.field) && Objects.equals(symbol, node.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, symbolSize, x, y, value, category, field, symbol);
    }
}
