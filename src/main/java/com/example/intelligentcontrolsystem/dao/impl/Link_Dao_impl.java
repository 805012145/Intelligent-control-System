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

    @Override
    public List<Link> getAll() {

        Util util = new Util();
        if (util.keys("link").size() == 0) {
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
        util.UtilClose();
        return links;
    }

    @Override
    public String getBWInfoOfAllType() {
        Util util = new Util();
        if (util.keys("link").size() == 0) {
            return null;
        }
        Channel.Data channelList = new Channel.Data();
        Map<String, String> channel1 = new HashMap<>();
        Map<String, String> channel2 = new HashMap<>();
        Map<String, String> channel3 = new HashMap<>();
        Map<String, String> channel4 = new HashMap<>();
        Map<String, String> channel5 = new HashMap<>();
        float used_BW1 = 0;
        float remain1 = 0;

        float used_BW2 = 0;
        float remain2 = 0;

        float used_BW3 = 0;
        float remain3 = 0;

        float used_BW4 = 0;
        float remain4 = 0;

        float used_BW5 = 0;
        float remain5 = 0;
        Channel channel = new Channel();
        List<String> keys = new ArrayList<>(util.hmget("link").keySet());
        for (String key : keys) {
            Link link = new Gson().fromJson(util.hget("link", key), new TypeToken<Link>() {}.getType());
            link.setId(key);
            switch (link.gettype()) {
                case "1":
                    remain1 += Float.parseFloat(link.getRemain_bandwidth());
                    used_BW1 += (Float.parseFloat(link.getMax_bandwidth()) - Float.parseFloat(link.getRemain_bandwidth()));
                    break;
                case "2":
                    remain2 += Float.parseFloat(link.getRemain_bandwidth());
                    used_BW2 += (Float.parseFloat(link.getMax_bandwidth()) - Float.parseFloat(link.getRemain_bandwidth()));
                    break;
                case "3":
                    remain3 += Float.parseFloat(link.getRemain_bandwidth());
                    used_BW3 += (Float.parseFloat(link.getMax_bandwidth()) - Float.parseFloat(link.getRemain_bandwidth()));
                    break;
                case "4":
                    remain4 += Float.parseFloat(link.getRemain_bandwidth());
                    used_BW4 += (Float.parseFloat(link.getMax_bandwidth()) - Float.parseFloat(link.getRemain_bandwidth()));
                    break;
                case "5":
                    if(link.getRemain_bandwidth()!=null && link.getMax_bandwidth()!=null) {
                        remain5 += Float.parseFloat(link.getRemain_bandwidth());
                        used_BW5 += (Float.parseFloat(link.getMax_bandwidth()) - Float.parseFloat(link.getRemain_bandwidth()));
                    }
                    break;
                default:
                    break;
            }
        }
        channel1.put("used_Bw", String.valueOf(used_BW1));
        channel1.put("remain", String.valueOf(remain1));
        channel2.put("used_Bw", String.valueOf(used_BW2));
        channel2.put("remain", String.valueOf(remain2));
        channel3.put("used_Bw", String.valueOf(used_BW3));
        channel3.put("remain", String.valueOf(remain3));
        channel4.put("used_Bw", String.valueOf(used_BW4));
        channel4.put("remain", String.valueOf(remain4));
        channel5.put("used_Bw", String.valueOf(used_BW5));
        channel5.put("remain", String.valueOf(remain5));
        channelList.setChannel1(channel1);
        channelList.setChannel2(channel2);
        channelList.setChannel3(channel3);
        channelList.setChannel4(channel4);
        channelList.setChannel5(channel5);
        channel.setChannelList(channelList);
        util.UtilClose();
        return new Gson().toJson(channel);
    }

    @Override
    public String getBWConOfAllLinks() {
        Util util = new Util();
        if (util.keys("link").size() == 0) {
            return null;
        }
        BandEntity bandEntity = new BandEntity();
        List<String[]> bwInfo = new ArrayList<>();
        BandEntity.Data data = new BandEntity.Data();
        String[] header = {"score1", "amount1", "type1", "score2", "amount2", "type2", "score3", "amount3", "type3",
                "score4", "amount4", "type4", "score5", "amount5", "type5", "product"};
        bwInfo.add(header);

        Map<String, List<LinkEntity>> pairlinksMap = new HashMap<>();
        List<String> keys = new ArrayList<>(util.hmget("link").keySet());
        for (String key : keys) {
            Link link = new Gson().fromJson(util.hget("link", key), new TypeToken<Link>() {}.getType());
            link.setId(key);
            if (link.gettype().equals("0")) {
                continue;
            }
            String product = link.getSrc()+":"+link.getDst();
            pairlinksMap.computeIfAbsent(product, k -> new ArrayList<>());
            LinkEntity linkEntity = new LinkEntity(link.getScore(),link.getRemain_bandwidth(), link.gettype());
            linkEntity.setProduct(product);
            pairlinksMap.get(product).add(linkEntity);
        }
        for (String key : pairlinksMap.keySet()) {
            String[] linkInfo = new String[16];
            List<LinkEntity> linkEntities = pairlinksMap.get(key);
            for (LinkEntity linkEntity : linkEntities) {
                switch (linkEntity.type) {
                    case "1":
                        linkInfo[0] = linkEntity.score;
                        linkInfo[1] = linkEntity.amount;
                        linkInfo[2] = linkEntity.type;
                        break;
                    case "2":
                        linkInfo[3] = linkEntity.score;
                        linkInfo[4] = linkEntity.amount;
                        linkInfo[5] = linkEntity.type;
                        break;
                    case "3":
                        linkInfo[6] = linkEntity.score;
                        linkInfo[7] = linkEntity.amount;
                        linkInfo[8] = linkEntity.type;
                        break;
                    case "4":
                        linkInfo[9] = linkEntity.score;
                        linkInfo[10] = linkEntity.amount;
                        linkInfo[11] = linkEntity.type;
                        break;
                    case "5":
                        linkInfo[12] = linkEntity.score;
                        linkInfo[13] = linkEntity.amount;
                        linkInfo[14] = linkEntity.type;
                        break;
                    default:
                        break;
                }
            }
            linkInfo[15] = key;
            bwInfo.add(linkInfo);
        }
        data.setSource(bwInfo);
        bandEntity.setData(data);
        util.UtilClose();
        return new Gson().toJson(bandEntity);
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
            return Objects.equals(score, that.score) && Objects.equals(amount, that.amount)
                    && Objects.equals(type, that.type) && Objects.equals(product, that.product);
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
