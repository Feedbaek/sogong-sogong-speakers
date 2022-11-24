package org.mybatis.jpetstore.domain;

import java.io.Serializable;

public class Manager implements Serializable {

    private static final long serialVersionUID = 4848493088014792188L;

    private int id;

    private String image;

    private String major;

    private int grade;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

}
