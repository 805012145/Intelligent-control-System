package com.example.intelligentcontrolsystem.entity;

import java.util.Objects;

public class History_para {

    public Integer controller_sum;

    public Integer link_sum;

    public Integer switch_sum;

    public Integer host_sum;

    public Integer business_sum;

    public Long throughout;

    public String date;

    public History_para(Integer controller_sum, Integer link_sum, Integer switch_sum, Integer host_sum, Integer business_sum, Long throughout) {
        this.controller_sum = controller_sum;
        this.link_sum = link_sum;
        this.switch_sum = switch_sum;
        this.host_sum = host_sum;
        this.business_sum = business_sum;
        this.throughout = throughout;
    }

    public Integer getController_sum() {
        return controller_sum;
    }

    public void setController_sum(Integer controller_sum) {
        this.controller_sum = controller_sum;
    }

    public Integer getLink_sum() {
        return link_sum;
    }

    public void setLink_sum(Integer link_sum) {
        this.link_sum = link_sum;
    }

    public Integer getSwitch_sum() {
        return switch_sum;
    }

    public void setSwitch_sum(Integer switch_sum) {
        this.switch_sum = switch_sum;
    }

    public Integer getHost_sum() {
        return host_sum;
    }

    public void setHost_sum(Integer host_sum) {
        this.host_sum = host_sum;
    }

    public Integer getBusiness_sum() {
        return business_sum;
    }

    public void setBusiness_sum(Integer business_sum) {
        this.business_sum = business_sum;
    }

    public Long getThroughout() {
        return throughout;
    }

    public void setThroughout(Long throughout) {
        this.throughout = throughout;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        History_para that = (History_para) o;
        return Objects.equals(controller_sum, that.controller_sum) && Objects.equals(link_sum, that.link_sum) && Objects.equals(switch_sum, that.switch_sum) && Objects.equals(host_sum, that.host_sum) && Objects.equals(business_sum, that.business_sum) && Objects.equals(throughout, that.throughout) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(controller_sum, link_sum, switch_sum, host_sum, business_sum, throughout, date);
    }

    @Override
    public String toString() {
        return "History_para{" +
                "controller_sum=" + controller_sum +
                ", link_sum=" + link_sum +
                ", switch_sum=" + switch_sum +
                ", host_sum=" + host_sum +
                ", business_sum=" + business_sum +
                ", throughout=" + throughout +
                ", id='" + date + '\'' +
                '}';
    }
}
