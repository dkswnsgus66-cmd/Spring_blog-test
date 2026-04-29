package com.tenco.blog.user;


import lombok.Data;

@Data
public class UserRequest {



    // 로그인 DTO
    @Data
    public static class LoginDTO{

        private String username;
        private String password;

        // 유효성 검사
        public void validate(){
            if (username == null || username.trim().isEmpty()){
                throw new IllegalArgumentException("사용자 명을 입력하세요");
            }
            if (password == null || password.trim().isEmpty()){
                throw new IllegalArgumentException("비밀번호를 입력하세요");
            }
        }
        // insert 동작을 하는게 아니기 때문에 toEntity 는 만들지 않는다.
    }

    // 회원가입 DTO
    @Data
    public static class JoinDTO{

        private String username;
        private String password;
        private String email;



        // 메서드 편의기능 - 내가 가지고 있는 멤버변수 값으로 User Entity 생성
        public User toEntity(){

            return User
                    .builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .build();

        }

        // 유효성 검사 메서드 만들기

        public void validate(){

            if(username == null || username.trim().isEmpty()){
                throw new IllegalArgumentException("사용자명은 필수 입니다.");
            }

            if(password == null || password.trim().isEmpty()){
                throw new IllegalArgumentException("비밀번호는 필수 입니다.");
            }

            if(email == null || email.trim().isEmpty()){
                throw new IllegalArgumentException("이메일은 필수 입니다.");
            }
            // 입력값: abc@naver.com --> contains() --> true ! --> false
            if(!email.contains("@")){
                throw new IllegalArgumentException("올바른 이메일 형식이 아닙니다.");
            }


        }



    }

}
