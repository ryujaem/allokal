package com.spring.app.allokal.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ReservationVO {
    int num;
    String franchisee_name;
    int money;
    int ex_money;
    Date request_date;
    String require_date;
    Date result_date;
    int user_num;
    String address;
    String file_name;
    String file_path;
    String name;
}
