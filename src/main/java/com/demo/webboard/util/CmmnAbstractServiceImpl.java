package com.demo.webboard.util;

import com.demo.webboard.main.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Service("myBatisSupport")
public class CmmnAbstractServiceImpl {

    @Autowired
    ApplicationContext applicationContext;

    /**
     * 트랜잭션 처리
     * @return
     */
    public DefaultTransactionManager getTransactionManager() {
        return applicationContext.getBean(DefaultTransactionManager.class);
    }


    /**
     * session에 존재하는 login vo객제 값을 map으로 포맷하여 반환
     * @param paramsMap
     * @return
     * @throws Exception
     */
    protected Map getLoginSessionMap(Map<String, Object> paramsMap) throws Exception {
        UserVO userVO = getUserData();
        if (null == userVO) {
            return null;
        }

        Field[] fields = userVO.getClass().getDeclaredFields();

        try {
            for (int i = 0; i <= fields.length - 1; i++) {
                fields[i].setAccessible(true);
                paramsMap.put(fields[i].getName(), fields[i].get(userVO));
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return paramsMap;
    }
    protected Map getLoginSessionMap() throws Exception {
        Map resultMap = new HashMap<String, Object>();
        return getLoginSessionMap(resultMap);
    }

    /**
     * session에 존재하는 login vo객제
     * @return
     */
    protected UserVO getUserData() {
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        UserVO userVO = (UserVO) session.getAttribute("loginVO");

        if (null != userVO && null != userVO.getUserId()) {
            return userVO;
        } else {
            return null;
        }
    }
    /**
     * session에 존재하는 userId
     * @return
     */
    protected String getUserId() {
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        String userId = (String) session.getAttribute("userId");

        if (null != userId) {
            return userId;
        } else {
            return null;
        }
    }

    /**
     * session 정보 등록
     * @param userVO
     */
    protected void setUserData(UserVO userVO) {
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        session.setAttribute("loginVO", userVO);
        session.setAttribute("userId", userVO.getUserId());
        session.setAttribute("userName", userVO.getUserName());
        session.setAttribute("nickname", userVO.getNickname());
    }
}
