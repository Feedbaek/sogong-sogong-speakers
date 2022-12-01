package org.mybatis.jpetstore.domain;

import java.io.Serializable;


public class Alarm implements Serializable {

    private String senderId;

    private String receiverId;

    private String alarm;

    //-------------------------getter & setter------------------------------------//

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getAlarm() {
        return alarm;
    }

    public void setAlarm(String alarm) {
        this.alarm = alarm;
    }
}
