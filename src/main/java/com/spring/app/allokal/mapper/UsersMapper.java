package com.spring.app.allokal.mapper;

import com.spring.app.allokal.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Repository
@Mapper
public interface UsersMapper {
    LoginVO checkId(LoginVO loginVO);
    CheckVO checkOverlap(LoginVO loginVO);
    int signUp(SignupVO signupVO);
    SignupVO signUp_iq(SignupVO signupVO);

    List<FranchiseeVO> getFranchisee();
    FranchiseeVO sel_franchisee(FranchiseeVO franchiseeVO);
    int reservation(ReservationVO reservationVO);
    ArrayList<ReservationVO> getReservationList(ReservationVO reservationVO);
    ReservationVO sel_reservation(ReservationVO reservationVO);
}
