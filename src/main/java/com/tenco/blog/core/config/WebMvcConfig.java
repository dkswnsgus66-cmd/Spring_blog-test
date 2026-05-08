package com.tenco.blog.core.config;


import com.tenco.blog.core.intecepter.LoginInterceptor;
import com.tenco.blog.core.intecepter.SessionIntercepter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired // DI 처리 내가 사용할 인터셉터 등록
    // loginInterceptor  인터셉터에 로그인 session 기능이 있다
    // 즉 인터셉터에 session 기능을 추가하면 내가 원하는 주소에 session 기능을 수행시킬수 있다
    private LoginInterceptor loginInterceptor;
    @Autowired
    private SessionIntercepter sessionIntercepter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 로그인 확인 인터셉터
        registry.addInterceptor(loginInterceptor) // 인터셉터 추가
                .addPathPatterns("/board/**","/user/**") // 어떤 파일을 호출할때 인터셉터 기능을 수행할지
                .excludePathPatterns(   // 어떤 주소를 인터셉터 검사 제외할지 등록
                        "/login-form", // 로그인 화면 주소 요청시
                        "/join-form" ,   // 화원가입 화면 요청
                        "/" ,// 게시글 목록 화면 요청
                        "/board/{id:\\d+}", // 상세조회
                        "/h2-console/**" // 콘솔화면
                );

    }
}
