package com.example.intelligentcontrolsystem.entity;

import java.util.*;

public class Channel {

    private Data data = new Data();

    public Channel(Data data) {
        this.data = data;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {
        public Float used_Bw = 0F;
        public Float remain = 0F;

        public Float getUsed_Bw() {
            return used_Bw;
        }

        public void setUsed_Bw(Float used_Bw) {
            this.used_Bw = used_Bw;
        }

        public Float getRemain() {
            return remain;
        }

        public void setRemain(Float remain) {
            this.remain = remain;
        }
    }
}
