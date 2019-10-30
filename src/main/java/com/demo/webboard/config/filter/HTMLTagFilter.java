package com.demo.webboard.config.filter;

import org.springframework.context.annotation.DependsOn;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * filter 사용 대신 jsp 출력시 <c:out> 태그를 사용하는 것을 기본정책으로 한다.
 */
@Deprecated
public class HTMLTagFilter implements Filter {

    private FilterConfig config;

    @Override
    public void init(FilterConfig config) throws ServletException {
        this.config = config;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(new HTMLTagFilterRequestWrapper((HttpServletRequest)request), response);
    }

    @Override
    public void destroy() {

    }
}
