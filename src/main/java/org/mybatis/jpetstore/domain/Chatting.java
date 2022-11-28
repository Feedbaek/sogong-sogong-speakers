package org.mybatis.jpetstore.domain;

import java.io.Serializable;

/*
 * The Class Chatting
 *
 * get or set Chatting log line by line
*/

public class Chatting implements Serializable {

    private String userId;

    private String managerId;

    private String chattingLog;


    //-------------------------getter & setter------------------------------------//

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getManagerId() {return managerId;}
    public void setManagerId(String managerId) {this.managerId = managerId;}

    public String getChattingLog() {return chattingLog;}
    public void setChattingLog(String chatLine) {this.chattingLog = chatLine;}
}
