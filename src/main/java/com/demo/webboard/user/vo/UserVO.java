package com.demo.webboard.user.vo;

import lombok.Data;

/*
    lombok을 설정한다. https://blog.naver.com/myh814/221504225671
 */
@Data
public class UserVO {
    private String userNo;
    private String userId;
    private String password;
    private String password2;
    private String userName;
    private String nickname;
    private String roleNo;
    private String role;
    private String[] roles;
}
