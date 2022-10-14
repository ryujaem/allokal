package com.spring.app.allokal.controller;

import com.spring.app.allokal.dto.LoginVO;
import com.spring.app.allokal.mapper.UsersMapper;
import com.spring.app.allokal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LoginController {

    @Autowired
    private UserService userService;


    public LoginController(UsersMapper usersMapper) {
        this.userService = userService;
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    @ResponseBody
    public String Test(){
        System.out.println("TEST IN");
        String text = "TEST SERVER IN";
        return text;
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public LoginVO Login(@RequestBody LoginVO loginVO) { return userService.checkId(loginVO);}

}
