package com.spring.app.allokal.controller;

import com.spring.app.allokal.dto.CheckVO;
import com.spring.app.allokal.dto.LoginVO;
import com.spring.app.allokal.dto.SignupVO;
import com.spring.app.allokal.mapper.UsersMapper;
import com.spring.app.allokal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class SignupController {

    @Autowired
    private UserService userService;


    public SignupController(UsersMapper usersMapper) {
        this.userService = userService;
    }


    @RequestMapping(value = "/checkid", method = RequestMethod.POST)
    @ResponseBody
    public LoginVO CheckedId(@RequestBody LoginVO loginVO) {
        return userService.checkOverlap(loginVO);
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    @ResponseBody
    public SignupVO insert(@RequestBody SignupVO signupVO) {
        userService.signUp(signupVO);
        userService.signUp_iq(signupVO);
        return signupVO;
    }


}
