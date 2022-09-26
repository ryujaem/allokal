package com.spring.app.allokal.dto;

import lombok.Data;

@Data
public class LoginVO {
    String token;
    String id ;
    String pwd;
    String user_num;
    int auth;
}