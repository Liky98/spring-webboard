package com.demo.webboard.config;

import com.demo.webboard.config.sitemesh.SiteMeshFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
public class ServletInitializer extends SpringBootServletInitializer {

    /**
     * 필터 등록
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistration() {
        FilterRegistrationBean filter = new FilterRegistrationBean();
        filter.setOrder(Ordered.LOWEST_PRECEDENCE);
        filter.setFilter(new SiteMeshFilter()); //sitemesh 필터

        return filter;
    }

}
