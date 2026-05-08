package com.tenco.blog.core.error;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception400.class)
    public String ex400(Exception400 e , HttpServletRequest request){
        log.warn("=== 400 Bad Request 에러 발생 ===");
        // URL 에러
        log.warn("요청 URL : {}" ,request.getRequestURL());
        // 메세지로 에러
        log.warn("에러 메세지 : {}" , e.getMessage());
        request.setAttribute("msg",e.getMessage());
        // 에어 400 페이지 반환
        return "err/400";
    }

    @ExceptionHandler(Exception401.class)
    @ResponseBody
    public String ex401(Exception401 e, HttpServletRequest request){
        log.warn("=== 401 Bad Request 에러 발생 ===");
        // URL 에러
        log.warn("요청 URL : {}" ,request.getRequestURL());
        // 메세지로 에러
        log.warn("에러 메세지 : {}" , e.getMessage());
        request.setAttribute("msg",e.getMessage());
        // 에어 400 페이지 반환
        return "err/401";


    }

    @ExceptionHandler(Exception403.class)
    @ResponseBody
    public String ex403(Exception403 e, HttpServletRequest request){
        log.warn("=== 403 Bad Request 에러 발생 ===");
        // URL 에러
        log.warn("요청 URL : {}" ,request.getRequestURL());
        // 메세지로 에러
        log.warn("에러 메세지 : {}" , e.getMessage());
        request.setAttribute("msg",e.getMessage());
        // 에어 400 페이지 반환
        return "err/403";


    }

    @ExceptionHandler(Exception404.class)
    @ResponseBody
    public String ex404(Exception404 e, HttpServletRequest request){
        log.warn("=== 400 Bad Request 에러 발생 ===");
        // URL 에러
        log.warn("요청 URL : {}" ,request.getRequestURL());
        // 메세지로 에러
        log.warn("에러 메세지 : {}" , e.getMessage());
        request.setAttribute("msg",e.getMessage());
        // 에어 400 페이지 반환
        return "err/404";
    }

    @ExceptionHandler(Exception500.class)
    @ResponseBody
    public String ex500(Exception500 e, HttpServletRequest request){
        log.warn("=== 500 Bad Request 에러 발생 ===");
        // URL 에러
        log.warn("요청 URL : {}" ,request.getRequestURL());
        // 메세지로 에러
        log.warn("에러 메세지 : {}" , e.getMessage());
        request.setAttribute("msg",e.getMessage());
        // 에어 400 페이지 반환
        return "err/500";
    }

    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(RuntimeException e, HttpServletRequest request){
        log.warn("=== 예상치 못한 런타임 발생 ===");
        log.warn("요청 URL : {}" ,request.getRequestURL());
        log.warn("에러메세지:{}" , e.getMessage());

        request.setAttribute("msg","시스템 오류가 발생했습니다. 관리자에세 문의해주세요");
        return "err/500";
    }



}
