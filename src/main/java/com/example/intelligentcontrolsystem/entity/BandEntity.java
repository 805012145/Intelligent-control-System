package com.example.intelligentcontrolsystem.entity;

import java.util.ArrayList;
import java.util.List;

public class BandEntity {

    /**
     * data
     */
    private Data data = new Data();

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {
        /**
         * source
         */
        private List<List<String>> source = new ArrayList<>();

        public List<List<String>> getSource() {
            return source;
        }

        public void setSource(List<List<String>> source) {
            this.source = source;
        }
    }
}
