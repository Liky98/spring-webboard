package com.demo.webboard.main.service;

import com.demo.webboard.main.vo.UserVO;

import java.util.List;
import java.util.Map;

public interface UserService {

    // role 목록 조회
    List<String> selectUserRoleList(String userNo) throws Exception;

    // user 조회
    UserVO selectUserMap(UserVO userVO) throws Exception;

    // 개인정보 수정
    int updateUserInfo(UserVO userVO) throws Exception;

    // 비밀번호 찾기
    Map<String, Object> findPassword(UserVO userVO) throws Exception;

    // 임시비밀번호 생성
    String getRamdomPassword();

}
