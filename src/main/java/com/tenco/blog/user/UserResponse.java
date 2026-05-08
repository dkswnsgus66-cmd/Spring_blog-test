package com.tenco.blog.user;


import lombok.Data;

public class UserResponse {


    // 회원가입 DTO
    @Data
    public static class JoinDTO{

        private Integer id;
        private String username;
        private String email;

        public JoinDTO(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.email = user.getEmail();
        }

    }

    // 확인 DTO
    @Data
    public static class SessionDTO extends User{

        public SessionDTO(User user){
            super.setId(user.getId());
            super.setUsername(user.getUsername());
            super.setEmail(user.getEmail());
        }

    }

}
