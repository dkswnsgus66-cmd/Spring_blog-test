package com.tenco.blog.user;

import lombok.Data;

public class UserRequest {

    // 로그인 DTO
    @Data
    public static class LoginDTO{

        private String username;
        private String password;

        // 유효성 검사
        public void validate(){

            if(username == null || username.trim().isEmpty()){
                throw new IllegalArgumentException("사용자명을 입력하세요");
            }
            if(password == null || password.trim().isEmpty()){
                throw new IllegalArgumentException("비밀번호를 입력하세요");
            }
        }

    }

    // 회원가입 DTO
    @Data
    public static class JoinDTO{

        private String username;
        private String password;
        private String email;


        // 편의기능 DTO 값만 가진 User 만들기 메서드
        // 회원가입이니 외부에서 받아온 값으로 만드는게 아니다. 새로운 User 생성
        public User toEntity(){
            return User.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .build();
        }

        // 유효성 검사 그냥 데이터 받는다 싶으면 유효성 검사 하자
        public void validate(){
            if(username == null || username.trim().isEmpty()){
                throw new IllegalArgumentException("사용자명을 입력해 주세요");
            }
            if(password == null || password.trim().isEmpty()){
                throw new IllegalArgumentException("비밀번호를 입력해 주세요.");
            }
            if(email == null || email.trim().isEmpty()){
                throw new IllegalArgumentException("이메일을 입력해 주세요");
            }
        }

    }

    // 회원정보 수정 DTO
    @Data
    public static class UpdateDTO{

        private String username;
        private String password;

        // 유효성 검사
        public void validate(){

            if(username == null || username.trim().isEmpty()){
                throw new IllegalArgumentException("유저 네임을 입력해 주새요");
            }
            if(password == null || password.trim().isEmpty()){
                throw new IllegalArgumentException("비밀번호를 입력해 주세요");
            }

        }

    }

}
