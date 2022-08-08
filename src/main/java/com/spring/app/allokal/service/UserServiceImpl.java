package com.spring.app.allokal.service;

import com.spring.app.allokal.dto.FranchiseeVO;
import com.spring.app.allokal.dto.LoginVO;
import com.spring.app.allokal.dto.SignupVO;
import com.spring.app.allokal.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public int signUp(SignupVO signupVO){
        System.out.println("id :" + signupVO.getId());
        System.out.println("pw :" + signupVO.getPwd());
        System.out.println("email :" + signupVO.getEmail());
        System.out.println("국적 :" + signupVO.getCountry());
        System.out.println("이름 :" + signupVO.getName());
        System.out.println("연락처 :" + signupVO.getTel_phone());
        usersMapper.signUp(signupVO);
        return 0;
    }

    @Override
    public SignupVO signUp_iq(SignupVO signupVO) {
        String user_num = "";
        System.out.println("id :" + signupVO.getId());
        System.out.println("pw :" + signupVO.getPwd());
        if (usersMapper.signUp_iq(signupVO) != null) {
            System.out.println("회원가입 출력 user_num :" + usersMapper.signUp_iq(signupVO).getUser_num());
            signupVO.setUser_num(usersMapper.signUp_iq(signupVO).getUser_num());
        }
        return signupVO;
    }

    @Override
    public List<FranchiseeVO> getFranchisee() {
        List<FranchiseeVO> resultList = usersMapper.getFranchisee();
        return resultList;
    }



    //로그인
    @Override
    public LoginVO checkId(LoginVO loginVO) {
        System.out.println("login 입력 id :" + loginVO.getId());
        System.out.println("login 입력 pw :" + loginVO.getPwd());

        if (usersMapper.checkId(loginVO) != null) {
            System.out.println("login 출력 user_num :" + usersMapper.checkId(loginVO).getUser_num());
            loginVO.setUser_num(usersMapper.checkId(loginVO).getUser_num());
        }

        return loginVO;
    }



}
