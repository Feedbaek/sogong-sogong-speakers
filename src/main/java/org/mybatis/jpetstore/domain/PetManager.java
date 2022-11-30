package org.mybatis.jpetstore.domain;

import java.io.Serializable;

public class PetManager implements Serializable {
    private String managerId;

    private String petType;

    private boolean catdog;

    private boolean repfish;

    private boolean bird;

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

    public boolean isCatdog() {
        return catdog;
    }

    public void setCatdog(boolean catdog) {
        this.catdog = catdog;
    }

    public boolean isRepfish() {
        return repfish;
    }

    public void setRepfish(boolean repfish) {
        this.repfish = repfish;
    }

    public boolean isBird() {
        return bird;
    }

    public void setBird(boolean bird) {
        this.bird = bird;
    }
}
