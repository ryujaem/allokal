package com.spring.app.allokal.dto;

import lombok.Data;
import org.apache.ibatis.javassist.bytecode.ByteArray;

import java.io.File;
import java.net.URI;

@Data
public class AdminUploaderVO {
    int user_num;
    String filename;
    String filepath;
    String file;
}