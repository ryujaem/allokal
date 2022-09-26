package com.spring.app.allokal.dto;

import lombok.Data;

import java.io.File;

@Data
public class AdminUploaderVO {
    int user_num;
    String filename;
    String filepath;
    File file;
}