package com.spring.app.allokal.dto;

import java.util.List;

public class FranchiseeVO {
    int num;
    String name;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getUseable_time() {
        return useable_time;
    }

    public void setUseable_time(String useable_time) {
        this.useable_time = useable_time;
    }

    String address;
    String tel;
    String useable_time;

}
