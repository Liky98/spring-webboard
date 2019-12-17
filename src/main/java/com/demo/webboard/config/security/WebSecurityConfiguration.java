package com.demo.webboard.config.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    public static final String[] SECURITY_EXCLUDE_PATTERN_ARR = {
        "/css/**"
        , "/js/**"
        , "/error/**"
        , "/webfonts/**"
        , "/h2-console/**"
        , "/favicon.ico"
        , "/resources/**"
        , "/WEB-INF/decorators/**"
        , "/WEB-INF/view/**"
    };

    @Autowired
    private WebAuthenticationProvider authenticationProvider;

    @Autowired
    private WebAuthenticationSuccessHandler authenticationSuccessHandler;

//    @Autowired
//    private WebAuthenticationFailureHandler authenticationFailureHandler;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(SECURITY_EXCLUDE_PATTERN_ARR);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .authorizeRequests()    // 권한요청 처리 설정 메서드
                .antMatchers("/board/**/post/**").hasAnyRole("USER", "ADMIN")    // post CUD 권한은 USER에게만 존재
                .antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/admin/**").hasRole("ADMIN")   // admin페이지는 ADMIN에게만 존재
                .anyRequest().permitAll()   // 다른 요청은 누구든지 접근 가능
                .and()
            .formLogin()
                .loginPage("/login").permitAll()    // 로그인 기본 url
                .loginProcessingUrl("/login")
                .successHandler(authenticationSuccessHandler)
//                .failureHandler(authenticationFailureHandler)
//                .usernameParameter("userId")
//                .passwordParameter("password")
                .and()
            .logout()
                .logoutSuccessUrl("/").permitAll()
                .and()
            .csrf()
                .ignoringAntMatchers("/h2-console/**")
                .disable(); // GET메소드는 문제가 없는데 POST메소드만 안되서 CSRF 비활성화 시킴 (http://javakorean.com/%EC%8A%A4%ED%94%84%EB%A7%81-%EB%B6%80%ED%8A%B8-spring-boot%EC%97%90%EC%84%9C-%EC%8A%A4%ED%94%84%EB%A7%81-%EC%8B%9C%ED%81%90%EB%A6%AC%ED%8B%B0-security-%ED%99%9C%EC%84%B1%ED%99%94-%ED%9B%84-post/)
    }

    @Override
    protected void configure (AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider);
    }
}
