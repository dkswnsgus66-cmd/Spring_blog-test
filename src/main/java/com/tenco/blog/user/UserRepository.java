package com.tenco.blog.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {


    // 사용자 닉네임 중복체크용
    @Query("""
                select u from User u where u.username = :username
            """)
    Optional<User> findByUsername(@Param("username") String username);


    // 사용자 로그인용
    @Query("""
            select u from User u where u.username = :username and u.password = :password
            """)
    Optional<User> findByUsernameAndpassword(@Param("username") String username, @Param("password") String password);

}
