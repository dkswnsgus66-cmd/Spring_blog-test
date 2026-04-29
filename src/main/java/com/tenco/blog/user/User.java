package com.tenco.blog.user;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Data
@Table(name = "user_tb")
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true) // 사용자명 중복방지를 위한 유니크 제약 조건 설정
    private String username;

    private String password;
    private String email;
    @CreationTimestamp // 엔티티가 영속화 될때 자동으로 현재 시간을 주입해라 pc -> db now 생략
    private Timestamp createdAt;

    @Builder // 여기다 빌더 만들면 자동으로 옵션으로 쓸수있기에 필요한것만 넣어서 쓸수 있다.
    public User(Integer id, String username, String password, String email, Timestamp createdAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.createdAt = createdAt;
    }
}
