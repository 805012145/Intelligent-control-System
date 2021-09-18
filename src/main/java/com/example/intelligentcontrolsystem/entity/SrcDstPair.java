package com.example.intelligentcontrolsystem.entity;

import java.util.Objects;

public class SrcDstPair {
    String src;
    String dst;

    public SrcDstPair(String src, String dst) {
        this.src = src;
        this.dst = dst;
    }

    public String getPair() {

        return "src:"+src+",dst:"+dst;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getDst() {
        return dst;
    }

    public void setDst(String dst) {
        this.dst = dst;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SrcDstPair that = (SrcDstPair) o;
        return Objects.equals(src, that.src) && Objects.equals(dst, that.dst);
    }

    @Override
    public int hashCode() {
        return Objects.hash(src, dst);
    }

    @Override
    public String toString() {
        return "src=" + this.src + ", dst=" + this.dst;
    }
}
