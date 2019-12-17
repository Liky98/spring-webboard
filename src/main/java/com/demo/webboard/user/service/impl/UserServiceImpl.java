package com.demo.webboard.user.service.impl;

import com.demo.webboard.user.mapper.UserDAO;
import com.demo.webboard.user.service.UserService;
import com.demo.webboard.user.vo.UserVO;
import com.demo.webboard.util.CmmnAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl extends CmmnAbstractServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<String> selectUserRoleList(String userNo) throws Exception {
        return userDAO.selectUserRoleList(userNo);
    }

    @Override
    public UserVO selectUserMap(UserVO userVO) throws Exception {
        return userDAO.selectUserMap(userVO);
    }

    @Override
    public int updateUserInfo(UserVO userVO) throws Exception {
        String password = userVO.getPassword();
        if (null == password) {
            return -1;
        }

        // 해당 user가 있는지 check
        UserVO paramVO = new UserVO();
        paramVO.setUserId(userVO.getUserId());
        paramVO = userDAO.selectUserMap(paramVO);
        if (!passwordEncoder.matches(password, paramVO.getPassword())) {
            return 2;   // 비밀번호 틀림
        }

        // 현재 비밀번호랑 변경 비밀번호랑 같은지 check
        String password2 = userVO.getPassword2();
        if (password.equals(password2)) {
            return 3;
        }

        // 같은 닉네임이 존재하는지 check
        UserVO sessionVO = getUserData();    // session에 존재하는 data를 불러온다
        if (!sessionVO.getNickname().equals(userVO.getNickname())) {
            // 기존 닉네임과 변경 닉네임이 같지않으면 닉네임 중복 체크
            paramVO = new UserVO();
            paramVO.setNickname(userVO.getNickname());
            if (0 < userDAO.selectUserCount(paramVO)) {
                return 4;   // 이미 존재하는 닉네임
            }
        }

        // 정보 수정
        int result;
        try {
            userVO.setPassword(passwordEncoder.encode(password2));
            result = userDAO.updateUserInfo(userVO);   // 정보 수정 성공 (return 1)

            // session 다시
            sessionVO.setNickname(userVO.getNickname());    // 변경된 nickname을 설정한다
            setUserData(sessionVO);              // 변경된 user정보를 session에 설정한다
        } catch (Exception e) {
            result = -1;    // 정보수정 실패
        }

        return result;
    }

    @Override
    public Map<String, Object> findPassword(UserVO userVO) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        int result;

        try {
            result = userDAO.selectUserCount(userVO);    // userId와 userName으로 user 존재 유무 확인

            if (result == 1) {  // user 존재
                String password = getRamdomPassword();  // 임시 비밀번호 생성
                userVO.setPassword(passwordEncoder.encode(password));  // 비밀번호 암호화
                userDAO.updateUserInfo(userVO);  // 임시 비밀번호로 수정
                resultMap.put("password", password);
            }
        } catch (Exception e) {
            result = -1;
        }

        resultMap.put("result", result);
        return resultMap;
    }

    /**
     * 임시 비밀번호 생성
     * @return
     */
    private String getRamdomPassword() {
        char[] charSet = new char[] {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                '!', '@', '#', '$', '%', '^', '&' };

        StringBuffer sb = new StringBuffer();
        SecureRandom sr = new SecureRandom();
        sr.setSeed(new Date().getTime());

        int idx = 0;
        int len = charSet.length;
        for (int i=0; i<10; i++) {
//            idx = (int) (len * Math.random());
            idx = sr.nextInt(len);    // 강력한 난수를 발생시키기 위해 SecureRandom을 사용한다.
            sb.append(charSet[idx]);
        }

        return sb.toString();
    }

}
