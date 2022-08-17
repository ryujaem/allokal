package com.spring.app.allokal.controller;

import com.spring.app.allokal.dto.FranchiseeVO;
import com.spring.app.allokal.dto.LoginVO;
import com.spring.app.allokal.dto.ReservationVO;
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


    @RequestMapping(value = "/exchange", method = RequestMethod.POST)
    @ResponseBody
    public List<FranchiseeVO> Franchisee(@RequestBody FranchiseeVO franchiseeVO) {

        List<FranchiseeVO> result = userService.getFranchisee();
        return result;
    }

    @RequestMapping(value = "/sel_franchisee", method = RequestMethod.POST)
    @ResponseBody
    public FranchiseeVO sel_franchisee(@RequestBody FranchiseeVO franchiseeVO) {

        return userService.sel_franchisee(franchiseeVO);
    }
    @RequestMapping(value = "/reservation", method = RequestMethod.POST)
    @ResponseBody
    public ReservationVO insert(@RequestBody ReservationVO reservationVO) {
        userService.reservation(reservationVO);
        return reservationVO;
    }

}
