package com.spring.app.allokal.dto;

public class SignupVO {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getUser_num() {
        return user_num;
    }

    public void setUser_num(String user_num) {
        this.user_num = user_num;
    }

    public String getTel_phone() {
        return tel_phone;
    }

    public void setTel_phone(String tel_phone) {
        this.tel_phone = tel_phone;
    }

    String id;

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    String pwd;
    String name;
    String country;
    String email;
    String user_num;
    String tel_phone;

}
