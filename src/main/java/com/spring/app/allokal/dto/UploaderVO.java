package com.spring.app.allokal.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UploaderVO {
    int user_num;
    String FileName;
    String FilePath;
    MultipartFile file;
}
