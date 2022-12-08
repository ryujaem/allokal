package com.spring.app.allokal.controller;

import com.spring.app.allokal.dto.AdminUploaderVO;
import com.spring.app.allokal.dto.LoginVO;
import com.spring.app.allokal.dto.ReservationVO;
import com.spring.app.allokal.dto.UserData;
import com.spring.app.allokal.logging.Logging;
import com.spring.app.allokal.mapper.UsersMapper;
import com.spring.app.allokal.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

@Slf4j
@RestController
public class AdminController {

    @Autowired
    private UserService userService;

    public AdminController(UsersMapper usersMapper) {
        this.userService = userService;
    }

    @GetMapping(value = "/log")
    public String LogPage(){
        Logging logging = new Logging();
        logging.info(AdminController.class.getSimpleName());

        return "완료";
    }
    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
    @ResponseBody
    public LoginVO Login_admin(@RequestBody LoginVO loginVO) { return userService.checkId(loginVO); }

    @RequestMapping(value = "/admin/reservation/lookup", method = RequestMethod.POST)
    @ResponseBody
    public ArrayList<ReservationVO> allReservationList(@RequestBody ReservationVO reservationVO) {
        System.out.println("IN allReservationList Controller");
        ArrayList<ReservationVO> result = userService.allReservation(reservationVO);
        return result;
    }

    @RequestMapping(value = "/admin/reservation/lookup/today", method = RequestMethod.POST)
    @ResponseBody
    public ArrayList<ReservationVO> todayReservationList(@RequestBody ReservationVO reservationVO) {
        System.out.println("IN todayReservationList Controller");
        ArrayList<ReservationVO> result = userService.todayReservation(reservationVO);
        return result;
    }

    @RequestMapping(value = "/admin/reservation/lookup/completion", method = RequestMethod.POST)
    @ResponseBody
    public ArrayList<ReservationVO> completionReservationList(@RequestBody ReservationVO reservationVO) {
        System.out.println("IN completionReservationList Controller");
        ArrayList<ReservationVO> result = userService.completionReservation(reservationVO);
        return result;
    }

    @RequestMapping(value = "/admin/reservation/files", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<byte[]> userDetailPage(@RequestBody AdminUploaderVO adminUploaderVO) throws IOException {

        String filepath = "user/"+adminUploaderVO.getUser_num()+"/passport/passport.png";
        System.out.println("FILE PATH : "+ filepath);
        InputStream in = new FileInputStream(filepath);
        byte[] imageByteArray = IOUtils.toByteArray(in);
        in.close();

        return new ResponseEntity<byte[]>(imageByteArray, HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/reservation/lookup/detail",method = RequestMethod.POST)
    @ResponseBody
    public UserData getUserData(@RequestBody UserData userData){

        return userService.getUserData(userData);
    }

}
