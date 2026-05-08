package com.tenco.blog.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Integer> {

    // 단건 조회 작성자 정보도 같이

    @Query("""
                select b from Board b join fetch b.user where b.id = :id 
            """)
    Optional<Board> findByIdJoinUser(@Param("id") Integer id);

    // 전체 게시글 조회 작성자 포함
    @Query("""
                select b from Board b join fetch b.user
            """)
    List<Board> findJoinAll();

}
