package com.spring.app.allokal.controller;

import com.spring.app.allokal.api.Download;
import com.spring.app.allokal.dto.LoginVO;
import com.spring.app.allokal.dto.UploaderVO;
import com.spring.app.allokal.mapper.UsersMapper;
import com.spring.app.allokal.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import java.io.File;
import java.io.IOException;

@RestController
public class UploadController {
    @Autowired
    private UserService userService;


    public UploadController(UsersMapper usersMapper) {
        this.userService = userService;
    }


    @RequestMapping(value = "/upload/passport", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public UploaderVO insert(UploaderVO uploaderVO) throws IOException {
        Download download = new Download();
        System.out.println("fileName : "+uploaderVO.getFile().getOriginalFilename());
        String filePath = "/Users/jaem/user/"+uploaderVO.getUser_num()+"/passport";
        download.saveFile(uploaderVO.getFile(),filePath);
        uploaderVO.setFilePath(filePath);
        uploaderVO.setFileName(uploaderVO.getFile().getOriginalFilename());
        userService.uploader(uploaderVO);
        return new UploaderVO();
    }
}
