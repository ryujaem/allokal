package com.spring.app.allokal.controller;

import com.spring.app.allokal.dto.UploaderVO;
import com.spring.app.allokal.mapper.UsersMapper;
import com.spring.app.allokal.module.Download;
import com.spring.app.allokal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class UploadController {
    @Autowired
    private UserService userService;

    public UploadController(UsersMapper usersMapper) {
        this.userService = userService;
    }

    @RequestMapping(value = "/upload/passport", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public UploaderVO upload_passport(UploaderVO uploaderVO) throws IOException {
        Download download = new Download();
        System.out.println("fileName : "+uploaderVO.getFile().getOriginalFilename());
        String filePath = "user/"+uploaderVO.getUser_num()+"/passport";
        download.saveFile(uploaderVO.getFile(),filePath);
        uploaderVO.setFilePath(filePath);
        uploaderVO.setFileName("passport.png");
        userService.uploader(uploaderVO);
        return new UploaderVO();
    }

    @RequestMapping(value = "/admin/upload/signature", method = RequestMethod.PUT, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Boolean upload_signature(UploaderVO uploaderVO) throws IOException {
        try {
            Download download = new Download();
            System.out.println("fileName : " + uploaderVO.getFile().getOriginalFilename());
            System.out.println("num : " + uploaderVO.getNum());

            String filePath = "user/" + uploaderVO.getUser_num() + "/signature";
            download.saveFile(uploaderVO.getFile(), filePath);
            return true;

        }catch (Throwable e){
            e.printStackTrace();
            return false;
        }
    }
}
