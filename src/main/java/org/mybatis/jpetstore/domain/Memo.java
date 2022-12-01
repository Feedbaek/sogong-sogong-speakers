package org.mybatis.jpetstore.domain;

import java.io.Serializable;

/*
 * The Class Memo
 *
 *
*/

public class Memo implements Serializable {

    private String customerId;

    private String managerId;
    private String evalLog;

    private String petName;
    private String petCategory;
    private String petAge;
    private String startDate;
    private String curStatus;


    //-------------------------getter & setter------------------------------------//

    public String getEvalLog() {
        return evalLog;
    }

    public void setEvalLog(String evalLog) {
        this.evalLog = evalLog;
    }

    public String getCurStatus() {
        return curStatus;
    }
    public void setCurStatus(String curStatus) {
        this.curStatus = curStatus;
    }

    public String getCustomerId() {
        return customerId;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getManagerId() {return managerId;}
    public void setManagerId(String managerId) {this.managerId = managerId;}

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetCategory() {
        return petCategory;
    }

    public void setPetCategory(String petCategory) {
        this.petCategory = petCategory;
    }

    public String getPetAge() {
        return petAge;
    }

    public void setPetAge(String petAge) {
        this.petAge = petAge;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
