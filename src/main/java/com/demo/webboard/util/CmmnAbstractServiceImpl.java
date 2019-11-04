package com.demo.webboard.util;

import com.demo.webboard.board.vo.Board;
import com.demo.webboard.user.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Service("myBatisSupport")
public class CmmnAbstractServiceImpl {

    @Autowired
    ApplicationContext applicationContext;

    private Logger LOGGER = Logger.getLogger(this.getClass().getName());

    /**
     * 트랜잭션 처리
     * @return
     */
    protected DefaultTransactionManager getTransactionManager() {
        return applicationContext.getBean(DefaultTransactionManager.class);
    }


    /**
     * session에 존재하는 login vo객제 값을 map으로 포맷하여 반환
     * @param paramsMap
     * @return
     * @throws Exception
     */
    @Deprecated
    protected Map getLoginSessionMap(Map<String, Object> paramsMap) throws Exception {
        User user = getUserData();
        if (null == user) {
            return null;
        }

        Field[] fields = user.getClass().getDeclaredFields();

        try {
            for (int i = 0; i <= fields.length - 1; i++) {
                fields[i].setAccessible(true);
                paramsMap.put(fields[i].getName(), fields[i].get(user));
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return paramsMap;
    }

    @Deprecated
    protected Map getLoginSessionMap() throws Exception {
        Map resultMap = new HashMap<String, Object>();
        return getLoginSessionMap(resultMap);
    }

    /**
     * session에 존재하는 login vo객제
     * @return
     */
    protected User getUserData() {
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        User user = (User) session.getAttribute("user");

        if (null != user && null != user.getUserId()) {
            return user;
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
    protected String getNickname() {
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        String nickname = (String) session.getAttribute("nickname");

        if (null != nickname) {
            return nickname;
        } else {
            return null;
        }
    }

    @Deprecated
    protected String getSessionData(String attribute) {
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        String returnString = (String) session.getAttribute(attribute);

        if (null != returnString) {
            return returnString;
        } else {
            return null;
        }
    }

    /**
     * session 정보 등록
     * @param user
     */
    protected void setUserData(User user) {
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        session.setAttribute("user", user);
        session.setAttribute("userId", user.getUserId());
        session.setAttribute("userName", user.getUserName());
        session.setAttribute("nickname", user.getNickname());
    }
}
