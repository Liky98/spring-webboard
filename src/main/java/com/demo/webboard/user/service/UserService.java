package com.demo.webboard.user.service;

import com.demo.webboard.user.vo.UserVO;

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


}
