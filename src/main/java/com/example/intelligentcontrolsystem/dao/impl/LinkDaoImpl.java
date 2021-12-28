package com.example.intelligentcontrolsystem.dao.impl;

import com.example.intelligentcontrolsystem.dao.LinkDao;
import com.example.intelligentcontrolsystem.entity.*;
import com.example.intelligentcontrolsystem.util.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class LinkDaoImpl implements LinkDao {

    @Override
    public List<Link> getAll() {
        Util util = new Util();
        if (util.keys("link*").size() == 0) {
            return null;
        }
        List<Link> links = new ArrayList<>();
        List<String> tables = new ArrayList<>(util.keys("link*"));
        for(String table : tables) {
            List<String> keys = new ArrayList<>(util.hgetAll(table).keySet());
            for (String key : keys) {
                Link link = new Gson().fromJson(util.hget(table, key), new TypeToken<Link>() {}.getType());
                if (key != null && link != null) {
                    try {
                        link.setId(key);
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                links.add(link);
            }
        }
        util.UtilClose();
        return links;
    }

    @Override
    public String getBWInfoOfAllType() {
        Util util = new Util();
        if (util.keys("link*").size() == 0) {
            return null;
        }
        Map<String, List<Channel>> channelMap = new HashMap<>();
        Channel.Data data1 = new Channel.Data();
        Channel.Data data2 = new Channel.Data();
        Channel.Data data3 = new Channel.Data();
        Channel.Data data4 = new Channel.Data();
        Channel.Data data5 = new Channel.Data();
        List<Channel.Data> channelDataList = new ArrayList<>();
        List<Channel> channelList = new ArrayList<>();
        List<String> tables = new ArrayList<>(util.keys("link*"));
        for (String table : tables) {
            List<String> keys = new ArrayList<>(util.hgetAll(table).keySet());
            for (String key : keys) {
                Link link = new Gson().fromJson(util.hget(table, key), new TypeToken<Link>() {}.getType());
                link.setId(key);
                switch (link.gettype()) {
                    case "1":
                        data1.remain += Float.parseFloat(link.getRemain_bandwidth());
                        data1.used_Bw += (Float.parseFloat(link.getMax_bandwidth()) - Float.parseFloat(link.getRemain_bandwidth()));
                        break;
                    case "2":
                        data2.remain += Float.parseFloat(link.getRemain_bandwidth());
                        data2.used_Bw += (Float.parseFloat(link.getMax_bandwidth()) - Float.parseFloat(link.getRemain_bandwidth()));
                        break;
                    case "3":
                        data3.remain += Float.parseFloat(link.getRemain_bandwidth());
                        data3.used_Bw += (Float.parseFloat(link.getMax_bandwidth()) - Float.parseFloat(link.getRemain_bandwidth()));
                        break;
                    case "4":
                        data4.remain += Float.parseFloat(link.getRemain_bandwidth());
                        data4.used_Bw += (Float.parseFloat(link.getMax_bandwidth()) - Float.parseFloat(link.getRemain_bandwidth()));
                        break;
                    case "5":
                        if(link.getRemain_bandwidth()!=null && link.getMax_bandwidth()!=null) {
                            data5.remain += Float.parseFloat(link.getRemain_bandwidth());
                            data5.used_Bw += (Float.parseFloat(link.getMax_bandwidth()) - Float.parseFloat(link.getRemain_bandwidth()));
                        }
                        break;
                    default:
                        break;
                }
            }
        }
        channelDataList.add(data1);
        channelDataList.add(data2);
        channelDataList.add(data3);
        channelDataList.add(data4);
        channelDataList.add(data5);
        for (Channel.Data data : channelDataList) {
            Channel channel = new Channel(data);
            channelList.add(channel);
        }
        util.UtilClose();
        channelMap.put("channel", channelList);
        return new Gson().toJson(channelMap);
    }

    @Override
    public String getBWConOfAllLinks() {
        Util util = new Util();
        if (util.keys("link*").size() == 0) {
            return null;
        }
        TableEntity tableEntity = new TableEntity();
        List<Object[]> bwInfo = new ArrayList<>();
        TableEntity.Data data = new TableEntity.Data();
        Object[] header = {"score1", "amount1", "type1", "score2", "amount2", "type2", "score3", "amount3", "type3",
                "score4", "amount4", "type4", "product"};
        bwInfo.add(header);

        Map<String, List<LinkEntity>> pairlinksMap = new HashMap<>();
        List<String> tables = new ArrayList<>(util.keys("link*"));

        for (String table : tables) {
            List<String> keys = new ArrayList<>(util.hgetAll(table).keySet());
            for (String key : keys) {
                Link link = new Gson().fromJson(util.hget(table, key), new TypeToken<Link>() {
                }.getType());
                if (key != null) {
                    link.setId(key);
                }
                if (link.gettype().equals("5") || link.gettype().equals("0")) {
                    continue;
                }
                String product = link.getSrc() + ":" + link.getDst();
                pairlinksMap.computeIfAbsent(product, k -> new ArrayList<>());
                LinkEntity linkEntity = new LinkEntity(link.getScore(), link.getRemain_bandwidth(), link.gettype());
                linkEntity.setProduct(product);
                pairlinksMap.get(product).add(linkEntity);
                String product2 = link.getDst() + ":" + link.getSrc();
                pairlinksMap.computeIfAbsent(product2, k -> new ArrayList<>());
                LinkEntity linkEntity2 = new LinkEntity(link.getScore(), link.getRemain_bandwidth(), link.gettype());
                linkEntity.setProduct(product);
                pairlinksMap.get(product2).add(linkEntity2);
            }
        }
        for (String key : pairlinksMap.keySet()) {
            Object[] linkInfo = new Object[13];
            linkInfo[2] = 1;
            linkInfo[5] = 2;
            linkInfo[8] = 3;
            linkInfo[11] = 4;
            List<LinkEntity> linkEntities = pairlinksMap.get(key);
            for (LinkEntity linkEntity : linkEntities) {
                switch (linkEntity.type) {
                    case "1":
                        linkInfo[0] = Float.parseFloat(linkEntity.score);
                        linkInfo[1] = Float.parseFloat(linkEntity.amount);
                        break;
                    case "2":
                        linkInfo[3] = Float.parseFloat(linkEntity.score);
                        linkInfo[4] = Float.parseFloat(linkEntity.amount);
                        break;
                    case "3":
                        linkInfo[6] = Float.parseFloat(linkEntity.score);
                        linkInfo[7] = Float.parseFloat(linkEntity.amount);
                        break;
                    case "4":
                        linkInfo[9] = Float.parseFloat(linkEntity.score);
                        linkInfo[10] = Float.parseFloat(linkEntity.amount);
                        break;
                    default:
                        break;
                }
            }
            linkInfo[12] = key;
            for (int i = 0; i < 11; i++) {
                if (linkInfo[i] == null) {
                    linkInfo[i] = -1;
                }
            }
            bwInfo.add(linkInfo);
        }
        data.setSource(bwInfo);
        tableEntity.setData(data);
        util.UtilClose();
        return new Gson().toJson(tableEntity);
    }

    @Override
    public List<Link> getSingleScore() {
        Util util = new Util();
        List<Link> links = new ArrayList<>();
        if (util.keys("link*").size() == 0) {
            return null;
        }
        Map<SrcDstPair, Float> singleLinkMap = new HashMap<>();
        List<String> tables = new ArrayList<>(util.keys("link*"));

        for (String table : tables) {
            List<String> keys = new ArrayList<>(util.hgetAll(table).keySet());
            for (String key : keys) {
                Link link = new Gson().fromJson(util.hget(table, key), new TypeToken<Link>() {
                }.getType());
                if (key != null) {
                    link.setId(key);
                }
                if (link.getScore() == null) {
                    links.add(link);
                    continue;
                }
                SrcDstPair srcDstPair = new SrcDstPair(link.getSrc(), link.getDst());
                if (!singleLinkMap.containsKey(srcDstPair)) {
                    singleLinkMap.put(srcDstPair, Float.parseFloat(link.getScore()));
                } else {
                    singleLinkMap.put(srcDstPair, (singleLinkMap.get(srcDstPair) + Float.parseFloat(link.getScore())) / 2);
                }
            }
        }
        util.UtilClose();

        for (SrcDstPair pair : singleLinkMap.keySet()) {
            Link link = new Link(pair.getSrc(), pair.getDst(), String.valueOf(singleLinkMap.get(pair)));
            links.add(link);
        }
        return links;
    }

    @Override
    public String getSingleLinkState(String source, String target) {
        Util util = new Util();
        Object[] linkList = new Object[5];
        Object[] header = {"source", "amount", "type"};
        linkList[0] = header;
        TableEntity tableEntity = new TableEntity();
        TableEntity.Data data = new TableEntity.Data();
        int domain = 2;
        if (Integer.parseInt(source) <= 12) {
            domain = 1;
        }
        Map<String, String> linkMap = util.hgetAll("link:"+domain);
        for (String key : linkMap.keySet()) {
            Object[] linkInfo = new Object[3];
            Link link = new Gson().fromJson(linkMap.get(key), new TypeToken<Link>() {
            }.getType());
            if (link.getSrc().equals(source) && link.getDst().equals(target) || link.getSrc().equals(target) && link.getDst().equals(source)) {
                linkInfo[0] = link.getScore() == null ? "0":link.getScore() ;
                linkInfo[1] = link.getRemain_bandwidth() == null ? "0" : link.getRemain_bandwidth() ;
                linkInfo[2] = link.gettype();
                linkList[Integer.parseInt(link.gettype()) == 0 ? 1 : Integer.parseInt(link.gettype())] = linkInfo;
            }
        }
        for (int i = 0; i < 5; i++) {
            if (linkList[i] == null) {
                Object[] linkInfo = {"0", "0", String.valueOf(i)};
                linkList[i] = linkInfo;
            }
        }
        data.setSource(linkList);
        tableEntity.setData(data);
        return new Gson().toJson(tableEntity);
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
