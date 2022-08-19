package com.spring.app.allokal.service;

import com.spring.app.allokal.dto.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface UserService {
    LoginVO checkId(LoginVO loginVO);
    LoginVO checkOverlap(LoginVO loginVO);
    int signUp(SignupVO signupVO);
    SignupVO signUp_iq(SignupVO signupVO);
    List<FranchiseeVO> getFranchisee();
    FranchiseeVO sel_franchisee(FranchiseeVO franchiseeVO);
    int reservation(ReservationVO reservationVO);
    ReservationVO sel_reservation(ReservationVO reservationVO);
    ArrayList<ReservationVO> getReservationList(ReservationVO reservationVO);
}
