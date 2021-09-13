package com.example.intelligentcontrolsystem.entity;

public class History_para {

    private String controller_sum;

    private String link_sum;

    private String switch_sum;

    private String host_sum;

    private String business_sum;

    private String throughout;

    private String id;

    public History_para(String controller_sum, String link_sum, String switch_sum,
                        String host_sum, String throughout) {
        this.controller_sum = controller_sum;
        this.link_sum = link_sum;
        this.switch_sum = switch_sum;
        this.host_sum = host_sum;
        this.throughout = throughout;
    }

    public String getController_sum() {
        return controller_sum;
    }

    public void setController_sum(String controller_sum) {
        this.controller_sum = controller_sum;
    }

    public String getLink_sum() {
        return link_sum;
    }

    public void setLink_sum(String link_sum) {
        this.link_sum = link_sum;
    }

    public String getSwitch_sum() {
        return switch_sum;
    }

    public void setSwitch_sum(String switch_sum) {
        this.switch_sum = switch_sum;
    }

    public String getHost_sum() {
        return host_sum;
    }

    public void setHost_sum(String host_sum) {
        this.host_sum = host_sum;
    }

    public String getBusiness_sum() {
        return business_sum;
    }

    public void setBusiness_sum(String business_sum) {
        this.business_sum = business_sum;
    }

    public String getThroughout() {
        return throughout;
    }

    public void setThroughout(String throughout) {
        this.throughout = throughout;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
