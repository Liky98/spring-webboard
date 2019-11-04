package com.demo.webboard.user.web;

import com.demo.webboard.user.service.UserService;
import com.demo.webboard.user.vo.User;
import com.demo.webboard.util.CmmnAbstractServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class UserController extends CmmnAbstractServiceImpl {

    @Resource
    private UserService userService;

    /**
     * 로그인페이지 - spring security 사용
     * @param mav
     * @param request
     * @return
     * @throws Exception
     */
    @GetMapping("/login")
    public ModelAndView loginView(ModelAndView mav, HttpServletRequest request) throws Exception {
        mav.setViewName("main/loginView");
        return mav;
    }

    /**
     * 패스워드 찾기 화면
     * @param mav
     * @return
     * @throws Exception
     */
    @GetMapping("/pw")
    public ModelAndView pwView(ModelAndView mav) throws Exception {
        mav.setViewName("main/pwFindModalView");
        return mav;
    }

    /**
     * 패스워드 찾기
     * @param user
     * @return Map key값 :
     *      result : 1 - 성공, else - 실패
     *      password : 비밀번호
     * @throws Exception
     */
    @PostMapping("/pw")
    @ResponseBody
    public Map<String, Object> findPassword(@RequestBody User user) throws Exception {

        return userService.findPassword(user);
    }

    /**
     * 개인정보 수정페이지
     * @param mav
     * @return
     * @throws Exception
     */
    @GetMapping("/user")
    public ModelAndView userView(ModelAndView mav) throws Exception {
        User user = new User();
        user.setUserId(getUserId());
        user = userService.selectUserMap(user);

        mav.addObject("user", user);
        mav.setViewName("main/userView");
        return mav;
    }

    /**
     * 개인정보 수정
     * @param user
     * @return
     * @throws Exception
     */
    @PostMapping("/user")
    @ResponseBody
    public int updateUser(@RequestBody User user) throws Exception {
        user.setUserId(getUserId());

        // 패스워드 체크 후 정보수정
        return userService.updateUserInfo(user);
    }



/*
    // Security 에서 적용됨
    @PostMapping("/login")
    @ResponseBody
    public int loginCheck(@RequestBody Map<String, Object> paramsMap, HttpServletRequest request) throws Exception {
        if (null == paramsMap.get("userId") || null == paramsMap.get("password") || "".equals(paramsMap.get("userId")) || "".equals(paramsMap.get("password"))) {
            return -1;
        }

        int result = 0;
        Map<String, Object> userMap = null;
        try {
            userMap = loginService.selectUserMap(paramsMap);
        } catch(Exception e) {
            return -1;
        }

        if (Optional.ofNullable(userMap).map(s -> s.get("userId")).isPresent()) {
            HttpSession session = request.getSession();
            session.setAttribute("loginUserInfo", userMap);
            result = 1;
        }

        return result;
    }

    // Security 에서 적용됨
    @GetMapping("/logout")
    @ResponseBody
    public String loginView(HttpServletRequest request, HttpSession session) throws Exception {

        request.getSession().removeAttribute("loginUserInfo");
//        session.invalidate();

        return "redirect:/";
    }*/

}
