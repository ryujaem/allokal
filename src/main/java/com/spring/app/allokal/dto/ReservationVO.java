package com.spring.app.allokal.dto;

import java.util.Date;

public class ReservationVO {
    int num;

    public String getFranchisee_name() {
        return franchisee_name;
    }

    public void setFranchisee_name(String franchisee_name) {
        this.franchisee_name = franchisee_name;
    }

    String franchisee_name;
    int money;
    int ex_money;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }


    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getEx_money() {
        return ex_money;
    }

    public void setEx_money(int ex_money) {
        this.ex_money = ex_money;
    }



    public int getUser_num() {
        return user_num;
    }

    public void setUser_num(int user_num) {
        this.user_num = user_num;
    }

    public Date getRequire_date() {
        return require_date;
    }

    public void setRequire_date(Date require_date) {
        this.require_date = require_date;
    }

    public Date getResult_date() {
        return result_date;
    }

    public void setResult_date(Date result_date) {
        this.result_date = result_date;
    }

    Date require_date;
    Date result_date;
    int user_num;
}
