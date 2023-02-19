package hello.login.web;

import hello.login.web.argumentResolver.LoginMemberArgumentResolver;
import hello.login.web.filter.LoginCheckFilter;
import hello.login.web.filter.LoginFilter;
import hello.login.web.interceptor.LogInterceptor;
import hello.login.web.interceptor.LoginCheckInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginMemberArgumentResolver());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .order(1)
                // 전체경로 가능
                .addPathPatterns("/**")
                // 전체 경로가 가능하지만, 해당 주소는 인터셉터 X
                .excludePathPatterns("/css/**", "/*.ico", "/error");


        registry.addInterceptor(new LoginCheckInterceptor())
                .order(2)
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/members/add", "/login", "/logout",
                        "/css/**", "/*.ico", "/error");

    }

/*
    @Bean
    // 스프링 부트로 사용할때 필터
    public FilterRegistrationBean logFilter(){
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LoginFilter());
        filterRegistrationBean.setOrder(1);

        // 모든 url에 다 적용이된다.
        filterRegistrationBean.addUrlPatterns("/*");

        return filterRegistrationBean;

    }


    @Bean
    // 스프링 부트로 사용할때 필터
    public FilterRegistrationBean loginCheckFilter(){
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LoginCheckFilter());
        filterRegistrationBean.setOrder(2);

        // 모든 url에 다 적용이된다.
        filterRegistrationBean.addUrlPatterns("/*");

        return filterRegistrationBean;

    }*/
}
