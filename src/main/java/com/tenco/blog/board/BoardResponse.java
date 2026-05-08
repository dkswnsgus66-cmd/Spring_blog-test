package com.tenco.blog.board;


import com.tenco.blog.util.MyDateUtil;
import lombok.Data;

public class BoardResponse {

    // 전체 게시글 조회 데이터
    // OISV 를 false로 바꿧기 때문에 컨트롤러에 반환할때 필요한 데이터를 여기서 받아서 컨트롤러에 반환 해야한다
    @Data
    public static class ListDTO{
        // 패스워드는 비밀이라 않받고 나머지 부분만 받아서 반환할거임
        private Integer id;
        private String title;
        private String content;
        // 유저 정보도 외부에서 보드의 유저를 받은걸 껍질벗겨서 전부 스트링으로 받을거임(평탄화)
        private String username;
        private String createdAt;

        public ListDTO(Board board){

            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();

            //  null 포인트 방지
            // board.getUser() 가 null 이면 User와 관련된 모든 데이터가 터지기에 null포인트가 발생한다
            // 그냥 데이터 1개는 null 값이 들어가도 null 반환이고 User 클래스가 null 이면 말이안됨
            if(board.getUser() != null){
                this.username = board.getUser().getUsername();
            }

            if (board.getCreatedAt() != null){
                this.createdAt = MyDateUtil.timestapmFormat(board.getCreatedAt());
            }

        }
    }

    // 단건조회용
    @Data
    public static class BoardDTO {

        private Integer id;
        private String username;
        private String content;
        private String title;
        private String createAt;

        // 나중에 Board 를 DTO로 변환시킬때 쓰는 메서드
        public BoardDTO(Board board){

            this.id = board.getId();
            this.content = board.getContent();
            this.title = board.getTitle();

            if(board.getUser() != null){
                this.username = board.getUser().getUsername();
            }
            if(board.getCreatedAt() != null){
                this.createAt = MyDateUtil.timestapmFormat(board.getCreatedAt());
            }
        }


    }

}
