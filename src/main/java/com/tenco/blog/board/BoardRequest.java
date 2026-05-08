package com.tenco.blog.board;


import com.tenco.blog.user.User;
import com.tenco.blog.user.UserRequest;
import com.tenco.blog.util.MyDateUtil;
import lombok.Builder;
import lombok.Data;

public class BoardRequest {

    //게시글 작성 DTO 작성은 요청이라 여기서 만듬
    @Data
    @Builder
    public static class SaveDTO {

        private String content;
        private String title;
        private User user;

        // Board를 DTO로 바꾸는 생성자
        // 빌더 로 DTO 만듬
        public Board toEntity(User user){
            //user는 나중에 username 까지 출력할려고 만든거임
            return Board.builder()
                    .content(content)
                    .title(title)
                    .user(user)
                    .build();
        }

        // 생성자로 DTO 만들어봄


        // 유효성검사
        public void validate(){

            if(content.trim().isEmpty() || content.length() < 3 || content == null){
                throw new IllegalArgumentException("내용입력은 필수이고 3글자 이상 입력해야합니다");
            }
            if(title.trim().isEmpty() || title.length() < 3 || title == null){
                throw new IllegalArgumentException("제목입력은 필수이고 3글자 이상 입력해야합니다");
            }

        }

    }

    //  업데이트 요청 DTO
    @Data
    public static class UpdateDTO{

        private String content;
        private String title;
        // 게시글 업데이트 유효성 검사 메서드
        public void validate(){

            if(content == null || content.trim().isEmpty()){
                throw new IllegalArgumentException("내용입력은 필수 입니다.");

            }
            if(title == null || title.trim().isEmpty()){
                throw new IllegalArgumentException("제목입력은 필수 입니다.");
            }

        }


    }

}
