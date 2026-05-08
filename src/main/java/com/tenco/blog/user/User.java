package com.tenco.blog.user;


import com.tenco.blog.util.MyDateUtil;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Table(name = "user_tb")
@Entity
@Builder
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    private String email;


    @CreationTimestamp
    private Timestamp createdAt;

    public String getTime(){
       return MyDateUtil.timestapmFormat(createdAt);
    }

    public void update(UserRequest.UpdateDTO updateDTO){

        this.username = updateDTO.getUsername();
        this.password = updateDTO.getPassword();
    }

}
