package com.example.intelligentcontrolsystem.entity;

import java.util.Objects;

public class Channel {

    private String used_BW1;
    private String remain1;

    private String used_BW2;
    private String remain2;

    private String used_BW3;
    private String remain3;

    private String used_BW4;
    private String remain4;

    private String used_BW5;
    private String remain5;

    public String getUsed_BW1() {
        return used_BW1;
    }

    public void setUsed_BW1(String used_BW1) {
        this.used_BW1 = used_BW1;
    }

    public String getRemain1() {
        return remain1;
    }

    public void setRemain1(String remain1) {
        this.remain1 = remain1;
    }

    public String getUsed_BW2() {
        return used_BW2;
    }

    public void setUsed_BW2(String used_BW2) {
        this.used_BW2 = used_BW2;
    }

    public String getRemain2() {
        return remain2;
    }

    public void setRemain2(String remain2) {
        this.remain2 = remain2;
    }

    public String getUsed_BW3() {
        return used_BW3;
    }

    public void setUsed_BW3(String used_BW3) {
        this.used_BW3 = used_BW3;
    }

    public String getRemain3() {
        return remain3;
    }

    public void setRemain3(String remain3) {
        this.remain3 = remain3;
    }

    public String getUsed_BW4() {
        return used_BW4;
    }

    public void setUsed_BW4(String used_BW4) {
        this.used_BW4 = used_BW4;
    }

    public String getRemain4() {
        return remain4;
    }

    public void setRemain4(String remain4) {
        this.remain4 = remain4;
    }

    public String getUsed_BW5() {
        return used_BW5;
    }

    public void setUsed_BW5(String used_BW5) {
        this.used_BW5 = used_BW5;
    }

    public String getRemain5() {
        return remain5;
    }

    public void setRemain5(String remain5) {
        this.remain5 = remain5;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Channel channel = (Channel) o;
        return Objects.equals(used_BW1, channel.used_BW1) && Objects.equals(remain1, channel.remain1) && Objects.equals(used_BW2, channel.used_BW2) && Objects.equals(remain2, channel.remain2) && Objects.equals(used_BW3, channel.used_BW3) && Objects.equals(remain3, channel.remain3) && Objects.equals(used_BW4, channel.used_BW4) && Objects.equals(remain4, channel.remain4) && Objects.equals(used_BW5, channel.used_BW5) && Objects.equals(remain5, channel.remain5);
    }

    @Override
    public int hashCode() {
        return Objects.hash(used_BW1, remain1, used_BW2, remain2, used_BW3, remain3, used_BW4, remain4, used_BW5, remain5);
    }

    @Override
    public String toString() {
        return "Channel{" +
                "used_BW1='" + used_BW1 + '\'' +
                ", remain1='" + remain1 + '\'' +
                ", used_BW2='" + used_BW2 + '\'' +
                ", remain2='" + remain2 + '\'' +
                ", used_BW3='" + used_BW3 + '\'' +
                ", remain3='" + remain3 + '\'' +
                ", used_BW4='" + used_BW4 + '\'' +
                ", remain4='" + remain4 + '\'' +
                ", used_BW5='" + used_BW5 + '\'' +
                ", remain5='" + remain5 + '\'' +
                '}';
    }
}
