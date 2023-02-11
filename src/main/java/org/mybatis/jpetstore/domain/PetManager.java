package org.mybatis.jpetstore.domain;

import java.io.Serializable;

public class PetManager implements Serializable {
    private String managerId;
    private String petType;
    private String name;
    private String age;
    private String since;

    private String manage;
    private boolean catdog;
    private boolean repfish;
    private boolean bird;

    public String getName() {
        return name;
    }

    public String getManage() {
        return manage;
    }

    public void setManage(String manage) {
        this.manage = manage;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSince() {
        return since;
    }

    public void setSince(String since) {
        this.since = since;
    }
    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public boolean getCatdog() {
        return catdog;
    }

    public void setCatdog(boolean catdog) {
        this.catdog = catdog;
    }

    public boolean getRepfish() {
        return repfish;
    }

    public void setRepfish(boolean repfish) {
        this.repfish = repfish;
    }

    public boolean getBird() {
        return bird;
    }

    public void setBird(boolean bird) {
        this.bird = bird;
    }
}
