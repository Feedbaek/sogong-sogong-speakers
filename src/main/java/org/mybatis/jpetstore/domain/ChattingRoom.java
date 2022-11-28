package org.mybatis.jpetstore.domain;

import java.io.Serializable;

public class ChattingRoom implements Serializable {

    private String costumerId;

    private String managerId;



    //------------------------------------getter&setter-----------------------------------------//

    public String getCostumerId() {
        return costumerId;
    }
    public void setCostumerId(String costumerId) {
        this.costumerId = costumerId;
    }


    public String getManagerId() {return managerId;}
    public void setManagerId(String managerId) {this.managerId = managerId;}


}
