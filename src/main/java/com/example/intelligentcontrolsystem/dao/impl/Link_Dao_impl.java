package com.example.intelligentcontrolsystem.dao.impl;

import com.example.intelligentcontrolsystem.dao.Link_Dao;
import com.example.intelligentcontrolsystem.entity.*;
import com.example.intelligentcontrolsystem.util.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class Link_Dao_impl implements Link_Dao {

    Util util = Util.getInstance();
    @Override
    public List<Link> getAll() {
        if (!util.hasKey("link")) {
            return null;
        }
        List<String> keys = new ArrayList<>(util.hmget("link").keySet());
        List<Link> links = new ArrayList<>();
        for (String key : keys) {
            Link link = new Gson().fromJson(util.hget("link", key), new TypeToken<Link>() {}.getType());
            if (key != null) {
                link.setId(key);
            }
            links.add(link);
        }
        return links;
    }

    @Override
    public String getBWInfoOfAllType() {
        if (!util.hasKey("link")) {
            return null;
        }
        Map<String, String> channel1 = new HashMap<>();
        Map<String, String> channel2 = new HashMap<>();
        Map<String, String> channel3 = new HashMap<>();
        Map<String, String> channel4 = new HashMap<>();
        Map<String, String> channel5 = new HashMap<>();
        long used_BW1 = 0;
        long remain1 = 0;

        long used_BW2 = 0;
        long remain2 = 0;

        long used_BW3 = 0;
        long remain3 = 0;

        long used_BW4 = 0;
        long remain4 = 0;

        long used_BW5 = 0;
        long remain5 = 0;
        Channel channel = new Channel();
        List<String> keys = new ArrayList<>(util.hmget("link").keySet());
        for (String key : keys) {
            Link link = new Gson().fromJson(util.hget("link", key), new TypeToken<Link>() {}.getType());
            link.setId(key);
            switch (link.getLink_type()) {
                case "1":
                    remain1 += Long.parseLong(link.getRemain_bandwidth());
                    used_BW1 += (Long.parseLong(link.getMax_bandwidth()) - Long.parseLong(link.getRemain_bandwidth()));
                    break;
                case "2":
                    remain2 += Long.parseLong(link.getRemain_bandwidth());
                    used_BW2 += (Long.parseLong(link.getMax_bandwidth()) - Long.parseLong(link.getRemain_bandwidth()));
                    break;
                case "3":
                    remain3 += Long.parseLong(link.getRemain_bandwidth());
                    used_BW3 += (Long.parseLong(link.getMax_bandwidth()) - Long.parseLong(link.getRemain_bandwidth()));
                    break;
                case "4":
                    remain4 += Long.parseLong(link.getRemain_bandwidth());
                    used_BW4 += (Long.parseLong(link.getMax_bandwidth()) - Long.parseLong(link.getRemain_bandwidth()));
                    break;
                case "5":
                    remain5 += Long.parseLong(link.getRemain_bandwidth());
                    used_BW5 += (Long.parseLong(link.getMax_bandwidth()) - Long.parseLong(link.getRemain_bandwidth()));
                    break;
                default:
                    break;
            }
        }
        channel1.put("used_Bw1", String.valueOf(used_BW1));
        channel1.put("remain1", String.valueOf(remain1));
        channel2.put("used_Bw2", String.valueOf(used_BW2));
        channel2.put("remain2", String.valueOf(remain2));
        channel3.put("used_Bw3", String.valueOf(used_BW3));
        channel3.put("remain3", String.valueOf(remain3));
        channel4.put("used_Bw4", String.valueOf(used_BW4));
        channel4.put("remain4", String.valueOf(remain4));
        channel5.put("used_Bw5", String.valueOf(used_BW5));
        channel5.put("remain5", String.valueOf(remain5));
        channel.setChannel1(channel1);
        channel.setChannel2(channel2);
        channel.setChannel3(channel3);
        channel.setChannel4(channel4);
        channel.setChannel5(channel5);
        return new Gson().toJson(channel);
    }

    @Override
    public String getBWConOfAllLinks() {
        if (!util.hasKey("link")) {
            return null;
        }
        Map<String, List<LinkEntity>> pairlinksMap = new HashMap<>();
        List<String> keys = new ArrayList<>(util.hmget("link").keySet());
        for (String key : keys) {
            Link link = new Gson().fromJson(util.hget("link", key), new TypeToken<Link>() {}.getType());
            link.setId(key);
            SrcDstPair srcDstPair = new SrcDstPair(link.getSrc(), link.getDst());
            pairlinksMap.computeIfAbsent(srcDstPair.getPair(), k -> new ArrayList<>());
            LinkEntity linkEntity = new LinkEntity(link.getScore(),link.getRemain_bandwidth(), link.getLink_type());
            String product = srcDstPair.getSrc()+":"+srcDstPair.getDst();
            linkEntity.setProduct(product);
            pairlinksMap.get(srcDstPair.getPair()).add(linkEntity);
        }
        return new Gson().toJson(pairlinksMap);
    }

    public static class LinkEntity {
        private String score;
        private String amount;
        private String type;
        private String product;

        public LinkEntity(String score, String amount, String type, String product) {
            this.score = score;
            this.amount = amount;
            this.type = type;
            this.product = product;
        }

        public LinkEntity(String score, String amount, String link_type) {
            this.score = score;
            this.amount = amount;
            this.type = link_type;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getProduct() {
            return product;
        }

        public void setProduct(String product) {
            this.product = product;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            LinkEntity that = (LinkEntity) o;
            return Objects.equals(score, that.score) && Objects.equals(amount, that.amount) && Objects.equals(type, that.type) && Objects.equals(product, that.product);
        }

        @Override
        public int hashCode() {
            return Objects.hash(score, amount, type, product);
        }

        @Override
        public String toString() {
            return "LinkEntity{" +
                    "score='" + score + '\'' +
                    ", amount='" + amount + '\'' +
                    ", type='" + type + '\'' +
                    ", product='" + product + '\'' +
                    '}';
        }
    }
}
