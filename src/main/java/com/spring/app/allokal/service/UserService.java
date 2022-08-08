package com.spring.app.allokal.service;

import com.spring.app.allokal.dto.CheckVO;
import com.spring.app.allokal.dto.LoginVO;
import com.spring.app.allokal.dto.SignupVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserService {
    LoginVO checkId(LoginVO loginVO);
    LoginVO checkOverlap(LoginVO loginVO);
    int signUp(SignupVO signupVO);
    SignupVO signUp_iq(SignupVO signupVO);
}
