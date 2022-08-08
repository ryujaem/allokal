package com.spring.app.allokal.controller;

import com.spring.app.allokal.dto.FranchiseeVO;
import com.spring.app.allokal.dto.LoginVO;
import com.spring.app.allokal.dto.SignupVO;
import com.spring.app.allokal.mapper.UsersMapper;
import com.spring.app.allokal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ExchangeController {
    @Autowired
    private UserService userService;


    public ExchangeController(UsersMapper usersMapper) {
        this.userService = userService;
    }


    @RequestMapping(value = "/exchage", method = RequestMethod.POST)
    @ResponseBody
    public List<FranchiseeVO> Franchisee(@RequestBody FranchiseeVO franchiseeVO) {
        List<FranchiseeVO> result = userService.getFranchisee();
        return result;
    }

}
