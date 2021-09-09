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
        List<String> keys = new ArrayList<>(util.hmget("link").keySet());
        List<Link> links = new ArrayList<>();
        for (String key : keys) {
            Link link = new Gson().fromJson(util.hget("link", key), new TypeToken<Link>() {}.getType());
            link.setId(key);
            links.add(link);
        }
        return links;
    }

    @Override
    public String getBWInfoOfAllType() {
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
            if (link.getLink_type().equals("1")) {
                remain1 += Long.parseLong(link.getRemain_bandwidth());
                used_BW1 += (Long.parseLong(link.getMax_bandwidth()) - remain1);
            }else if (link.getLink_type().equals("2")) {
                remain2 += Long.parseLong(link.getRemain_bandwidth());
                used_BW2 += (Long.parseLong(link.getMax_bandwidth()) - remain1);
            }else if (link.getLink_type().equals("3")) {
                remain3 += Long.parseLong(link.getRemain_bandwidth());
                used_BW3 += (Long.parseLong(link.getMax_bandwidth()) - remain1);
            }else if (link.getLink_type().equals("4")) {
                remain4 += Long.parseLong(link.getRemain_bandwidth());
                used_BW4 += (Long.parseLong(link.getMax_bandwidth()) - remain1);
            }else if (link.getLink_type().equals("5")) {
                remain5 += Long.parseLong(link.getRemain_bandwidth());
                used_BW5 += (Long.parseLong(link.getMax_bandwidth()) - remain1);
            }
        }
        channel.setRemain1(String.valueOf(remain1));
        channel.setRemain2(String.valueOf(remain2));
        channel.setRemain3(String.valueOf(remain3));
        channel.setRemain4(String.valueOf(remain4));
        channel.setRemain5(String.valueOf(remain5));
        channel.setUsed_BW1(String.valueOf(used_BW1));
        channel.setUsed_BW2(String.valueOf(used_BW2));
        channel.setUsed_BW3(String.valueOf(used_BW3));
        channel.setUsed_BW4(String.valueOf(used_BW4));
        channel.setUsed_BW5(String.valueOf(used_BW5));

        return new Gson().toJson(channel);
    }

    @Override
    public String getBWConOfAllLinks() {

        Map<String, List<LinkEntity>> pairlinksMap = new HashMap<>();
        List<String> keys = new ArrayList<>(util.hmget("link").keySet());
        for (String key : keys) {
            Link link = new Gson().fromJson(util.hget("link", key), new TypeToken<Link>() {}.getType());
            link.setId(key);
            SrcDstPair srcDstPair = new SrcDstPair(link.getSrc(), link.getDst());
            pairlinksMap.computeIfAbsent(srcDstPair.getPair(), k -> new ArrayList<>());
            System.out.println(srcDstPair);
            LinkEntity linkEntity = new LinkEntity(link.getScore(),link.getRemain_bandwidth(), link.getLink_type());
            String product = srcDstPair.getSrc()+":"+srcDstPair.getDst();
            linkEntity.setProduct(product);
            pairlinksMap.get(srcDstPair.getPair()).add(linkEntity);
        }
        System.out.println(new Gson().toJson(pairlinksMap));
        return new Gson().toJson(pairlinksMap);
    }



    private class LinkEntity {
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
