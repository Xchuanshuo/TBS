package com.legend.tbs.common.model;

/**
 * @author Legend
 * @data by on 2018/4/5.
 * @description
 */

public class TbsBean {

    /**
     * fromNick : 一个认识2个月的男生
     * fromEncodeUin : *S1*ow4lNKSk7ecq7c
     * fromFaceUrl : man.png
     * fromGender : 0
     * toUin : 2414605975
     * toNick :
     * topicId : 50
     * topicName : 宅舞爱好者
     * timestamp : 1522912148
     */

    private String fromNick;
    private String fromEncodeUin;
    private String fromFaceUrl;
    private int fromGender;
    private long toUin;
    private String toNick;
    private int topicId;
    private String topicName;
    private int timestamp;

    public String getFromNick() {
        return fromNick;
    }

    public void setFromNick(String fromNick) {
        this.fromNick = fromNick;
    }

    public String getFromEncodeUin() {
        return fromEncodeUin;
    }

    public void setFromEncodeUin(String fromEncodeUin) {
        this.fromEncodeUin = fromEncodeUin;
    }

    public String getFromFaceUrl() {
        return fromFaceUrl;
    }

    public void setFromFaceUrl(String fromFaceUrl) {
        this.fromFaceUrl = fromFaceUrl;
    }

    public int getFromGender() {
        return fromGender;
    }

    public void setFromGender(int fromGender) {
        this.fromGender = fromGender;
    }

    public long getToUin() {
        return toUin;
    }

    public void setToUin(long toUin) {
        this.toUin = toUin;
    }

    public String getToNick() {
        return toNick;
    }

    public void setToNick(String toNick) {
        this.toNick = toNick;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }
}
