package com.demo.webboard.config.security;

import com.demo.webboard.user.service.UserService;
import com.demo.webboard.user.vo.UserVO;
import com.demo.webboard.util.CmmnAbstractServiceImpl;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class WebAuthenticationSuccessHandler extends CmmnAbstractServiceImpl implements AuthenticationSuccessHandler {

    @Resource
    private UserService userService;

    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        try {
            WebUserDetails loginUserDetails = (WebUserDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();

            String userNo = loginUserDetails.getUserNo();

            // userNo으로 user 정보 가져오기
            UserVO userVO = new UserVO();
            userVO.setUserNo(userNo);
            userVO = userService.selectUserMap(userVO);

            // session
            setUserData(userVO);
            /*HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("userId", userVO.getUserId());
            session.setAttribute("userName", userVO.getUserName());
            session.setAttribute("nickname", userVO.getNickname());*/

            // 이전페이지 혹은 홈으로 이동
            resultRedirectStrategy(request, response, authentication);
        } catch(Exception e) {
//            redirectStrategy.sendRedirect(request, response, "/login");
            throw new BadCredentialsException("로그인에 실패했습니다.");
        }
    }

    protected void resultRedirectStrategy(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        if (null != savedRequest) {
            String targetUrl = savedRequest.getRedirectUrl();
            redirectStrategy.sendRedirect(request, response, targetUrl);
        } else {
            // 직접 로그인 화면으로 이동했을 경우 : default url - 메인
            redirectStrategy.sendRedirect(request, response, "/");
        }
    }
}
