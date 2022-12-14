package com.spring.app.allokal.controller;

import com.spring.app.allokal.dto.LoginVO;
import com.spring.app.allokal.mapper.UsersMapper;
import com.spring.app.allokal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LoginController {

    @Autowired
    private UserService userService;

    @Value("${clientId}")
    String clientId;
    public LoginController(UsersMapper usersMapper) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public LoginVO Login(@RequestBody LoginVO loginVO) {
        return userService.checkId(loginVO);}

    @RequestMapping(value = "/user/getAuth", method = RequestMethod.POST)
    @ResponseBody
    public LoginVO getAuth(@RequestBody LoginVO loginVO) {
        System.out.println("LoginController getAuth user_num : "+loginVO.getUser_num());
        return userService.getAuth(loginVO);}


}
