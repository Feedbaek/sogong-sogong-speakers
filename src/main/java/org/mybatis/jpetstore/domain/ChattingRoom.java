package org.mybatis.jpetstore.domain;

import java.io.Serializable;

public class ChattingRoom implements Serializable {

    private String userId;

    private String managerId;



    //------------------------------------getter&setter-----------------------------------------//

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getManagerId() {return managerId;}
    public void setManagerId(String managerId) {this.managerId = managerId;}


}
