package com.spring.app.allokal.service;

import com.spring.app.allokal.dto.*;
import com.spring.app.allokal.mapper.UsersMapper;
import com.spring.app.allokal.security.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersMapper usersMapper;

    public UserServiceImpl(UsersMapper usersMapper) {
        this.usersMapper = usersMapper;
    }

    //id중복확인
    @Override
    public LoginVO checkOverlap(LoginVO loginVO) {
        System.out.println("login 입력 id :" + loginVO.getId());
        try {
            loginVO.setId(usersMapper.checkOverlap(loginVO).getId());
        } catch (NullPointerException e) {
            loginVO.setId("");
        }
        return loginVO;
    }

    //회원가입 insert
    @Override
    public int signUp(SignupVO signupVO) {
        usersMapper.signUp(signupVO);
        return 0;
    }

    @Override
    public SignupVO signUp_iq(SignupVO signupVO) {
        System.out.println("id :" + signupVO.getId());
        System.out.println("pw :" + signupVO.getPwd());
        if (usersMapper.signUp_iq(signupVO) != null) {
            System.out.println("회원가입 출력 user_num :" + usersMapper.signUp_iq(signupVO).getUser_num());
            signupVO.setUser_num(usersMapper.signUp_iq(signupVO).getUser_num());
        }
        return signupVO;
    }

    //Service
    @Override
    public List<FranchiseeVO> getFranchisee() {
        List<FranchiseeVO> resultList = usersMapper.getFranchisee();
        return resultList;
    }

    @Override
    public FranchiseeVO sel_franchisee(FranchiseeVO franchiseeVO) {
        System.out.println("franchisee : " + usersMapper.sel_franchisee(franchiseeVO).getName());
        franchiseeVO = usersMapper.sel_franchisee(franchiseeVO);
        return franchiseeVO;
    }

    @Override
    public int reservation(ReservationVO reservationVO) {
        usersMapper.reservation(reservationVO);
        int GetNum = reservationVO.getNum();
        return GetNum;
    }

    //예약 완료 페이지
    @Override
    public ReservationVO sel_reservation(ReservationVO reservationVO) {
        reservationVO = usersMapper.sel_reservation(reservationVO);
        return reservationVO;
    }

    @Override
    public ArrayList<ReservationVO> getReservationList(ReservationVO reservationVO) {
        System.out.println("user_num  : " + reservationVO.getUser_num());
        ArrayList<ReservationVO> resultList = usersMapper.getReservationList(reservationVO);
        return resultList;
    }

    @Override
    public int uploader(UploaderVO uploaderVO) {

        usersMapper.uploader(uploaderVO);

        return 0;
    }

    @Override
    public UserData getUserData(UserData userData) {
        UserData result = usersMapper.getUserData(userData);
        return result;
    }

    @Override
    public ArrayList<ReservationVO> allReservation(ReservationVO reservationVO) {
        ArrayList<ReservationVO> resultList = usersMapper.allReservation(reservationVO);
        if (resultList != null) {
            System.out.println("allReservation : " + resultList.get(0).getAddress());
        }
        return resultList;
    }

    @Override
    public ArrayList<ReservationVO> todayReservation(ReservationVO reservationVO) {
        ArrayList<ReservationVO> resultList = usersMapper.todayReservation(reservationVO);
        if (resultList.isEmpty()) {
            System.out.println("todayReservation : null");
        }else{
            System.out.println("todayReservation : " + resultList.get(0).getAddress());
        }
        return resultList;
    }

    @Override
    public ArrayList<ReservationVO> completionReservation(ReservationVO reservationVO) {
        ArrayList<ReservationVO> resultList = usersMapper.completionReservation(reservationVO);
        if (resultList != null) {
            System.out.println("allReservation : " + resultList.get(0).getAddress());
        }
        return resultList;
    }

    @Override
    public int update_Reservation(UploaderVO uploaderVO) {
        usersMapper.update_Reservation(uploaderVO);
        return 0;
    }

    @Override
    public LoginVO getAuth(LoginVO loginVO) {
        if (usersMapper.getAuth(loginVO) != null) {
            System.out.println("login 출력 user_num :" + usersMapper.checkId(loginVO).getAuth());
            loginVO.setAuth(usersMapper.getAuth(loginVO).getAuth());
        }
        return loginVO;
    }

    //로그인
    @Override
    public LoginVO checkId(LoginVO loginVO) {

        if (usersMapper.checkId(loginVO) != null) {
            System.out.println("login 출력 user_num :" + usersMapper.checkId(loginVO).getUser_num());
            loginVO.setUser_num(usersMapper.checkId(loginVO).getUser_num());
            SecurityService securityService = new SecurityService();
            loginVO.setToken(securityService.createToken(loginVO.getId(),2*60*1000));
        }

        return loginVO;
    }


}
