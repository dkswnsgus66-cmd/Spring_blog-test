package com.tenco.blog.board;

import lombok.Builder;
import lombok.Data;

// 요청 데이터를 담는 DTO 클래스
// 컨트롤러.. 비즈니스 .. 데이터 계층 사이에서 데이터 전송 역할 객체
public class BoardRequest {

    @Data
    @Builder
    public static class SaveDTO {
        private String username;
        private String title;
        private String content;

        // 편의 기능 설계 가능
        // DTO 에서 Entity로 변환해주는 편의 메서드
        public Board toEntity() {

            return Board.builder()
//                    .username(username)
                    .title(title)
                    .content(content)
                    .build();
        }
    }

    // 내부 정적 클래스 게시글 수정 DTO 설계
    @Data
    public static class UpdateDTO{
        private String username;
        private String title;
        private String content;

        public Board toEntity() {


            return Board.builder()
//                    .username(username)
                    .title(title)
                    .content(content)
                    .build();
        }


        // 게시글 수정시 유효성 검사 편의 메서드
        public void validate(){
            if (username.trim().isEmpty() || username == null) {
                throw new IllegalArgumentException("유저 이름 넣어라");
            }

            if (title == null || title.trim().isEmpty()) {
                throw new IllegalArgumentException("타이틀 넣어라");
            }

            if (content == null || content.length() < 3) {
                throw new IllegalArgumentException("내용물 넣어라 3 글자 이상");
            }

        }

    }


}
