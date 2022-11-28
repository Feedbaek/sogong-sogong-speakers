package org.mybatis.jpetstore.domain;

import java.io.Serializable;

/*
 * The Class Chatting
 *
 * get or set Chatting log line by line
*/

public class Chatting implements Serializable {

    private String costumerId;

    private String managerId;

    private String chattingLog;


    //-------------------------getter & setter------------------------------------//

    public String getCostumerId() {
        return costumerId;
    }
    public void setCostumerId(String costumerId) {
        this.costumerId = costumerId;
    }

    public String getManagerId() {return managerId;}
    public void setManagerId(String managerId) {this.managerId = managerId;}

    public String getChattingLog() {return chattingLog;}
    public void setChattingLog(String chatLine) {this.chattingLog = chatLine;}
}
