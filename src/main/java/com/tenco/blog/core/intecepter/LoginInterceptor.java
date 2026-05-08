package com.tenco.blog.core.intecepter;

import com.tenco.blog.core.error.Exception401;
import com.tenco.blog.user.UserResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();

        UserResponse.SessionDTO sessionUser = (UserResponse.SessionDTO)session.getAttribute("sessionUser");

        if(sessionUser == null){
            throw new Exception401("로그인 먼저 해주세요");
        }

        // 리턴이 true 이면 Controller 까지 간다
        // false 면 Controller 가기전에 오류 판별
        return true;
    }


}
