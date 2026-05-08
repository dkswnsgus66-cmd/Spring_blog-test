package com.tenco.blog.board;


import com.tenco.blog.user.User;
import com.tenco.blog.util.MyDateUtil;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "board_tb")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;

    // 다대일 설정
    @ManyToOne(fetch = FetchType.LAZY)
    // 유저 아이디와 조인
    @JoinColumn(name = "user_id")
    private User user;

    // 타임스태프 기능 지원
    @CreationTimestamp
    private Timestamp createdAt;

    public String getTime(){

        return MyDateUtil.timestapmFormat(createdAt);

    }




}
