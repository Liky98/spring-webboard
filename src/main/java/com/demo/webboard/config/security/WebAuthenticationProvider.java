package com.demo.webboard.config.security;

import com.demo.webboard.user.service.UserService;
import com.demo.webboard.user.vo.UserVO;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

@Component
public class WebAuthenticationProvider implements AuthenticationProvider {

    @Resource
    private UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
        /*
            비밀번호 암호화
            passwordEncoder.encode(password);

            비밀번호 일치 확인 (true:일치, false:불일치)
            passwordEncoder.matches(password, encodePassword);
         */
    }

    private static final String ROLE_PREFIX = "ROLE_";

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userId = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        if (null == userId || null == password || "".equals(userId) || "".equals(password)) {
            throw new BadCredentialsException("로그인 정보를 찾을 수 없습니다.");
        }

        UserVO userVO = new UserVO();
        userVO.setUserId(userId);
        String userNo = null;
        try {
            userVO = userService.selectUserMap(userVO); // userId로 user정보 조회
            userNo = userVO.getUserNo();
        } catch(Exception e) {
            throw new BadCredentialsException("로그인 정보를 찾을 수 없습니다.");
        }

        if (null != userNo || "".equals(userNo)) {
            if (passwordEncoder().matches(password, userVO.getPassword())) { // 비밀번호 일치 확인
                List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
                List<String> roleList = null;
                try {
                    roleList = userService.selectUserRoleList(userNo);  // user의 ROLE 정보 조회
                    roleList.forEach(role -> roles.add(new SimpleGrantedAuthority(ROLE_PREFIX + role)));
                } catch(Exception e) {
                    // roles.add(new SimpleGrantedAuthority(ROLE_PREFIX + "USER"));
                }

                UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(userId, password, roles);
                result.setDetails(new WebUserDetails(userNo));
                return result;
            } else {
                throw new BadCredentialsException("로그인 정보를 다시 확인하십시오.");
            }
        } else {
            throw new BadCredentialsException("로그인 정보를 찾을 수 없습니다.");
        }
    }
}
