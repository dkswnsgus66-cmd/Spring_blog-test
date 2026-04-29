package com.tenco.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // IoC
@RequiredArgsConstructor // DI 처리 됨
public class BoardPersistRepository {

    // JPA 핵심 인터페이스
    // 영속성 컨텍스트를 관리하고 엔티티의 생명주기를 제어
    // @Autowired // DI
    private final EntityManager em; // final 사용하면 성능 개선이 조금 됨

    // 의존 주입 (외부에서 생성되어 있는 객체의 주소값을 주입 받다)
//    public BoardPersistRepository(EntityManager em) {
//        this.em = em;
//    }

    // 게시글 저장
    @Transactional
    public Board save(Board board) {
        // 1. 매개 변수로 받은 board는 비영속상태
        //    -- 아직 영속성 컨텍스트에 관리 되고 있지 않는 상태
        //    -- 데이터베이스과 연관 없는 수순 JAVA 객체 일 뿐 아직은..

        //em.createNativeQuery(insert into board_tb .... ) x

        em.persist(board); // insert 처리 완료
        // 2. 이 board 객체를 영속성 컨텍스트에 넣어 둠 (SQL 저장소에 등록)
        //     -- 영속성 컨텍스트에 들어가더라도 아직 DB 실제 insert한 상태는 아님

        // 3. 트랜잭션 커밋시점에 실제 DB에 접근해서 insert 구문이 수행이 된다.

        // 4. board 객체의 id 변수값을 1차 캐쉬에 map 구조로 보관 되어 짐.
        //    1차 캐쉬에 들어간 이제 영속상태로 변경된 Object 리턴 한다.
        return board;
    }

    // JPQL을 사용한 게시글 목록 조회
    public List<Board> findAll() {

        // JPQL : 엔티티 객체를 대상으로 하는 객체지향 쿼리
        // Board는 엔티티 클래스 명, b 는 별칭
        // 주의! 테이블 명이 아닌 클래스명(엔티티명) 사용

        String jpql = """
                select b from Board b order by b.id desc
                """;
        List<Board> boardList = em.createQuery(jpql,Board.class).getResultList();

        return boardList;
    }


    // 게시글 상세보기 요청 (조회) (필수값 기본키로 조회)
    public Board findById(Integer id) {

        // 영속성 컨텍스트를 사용하기 위해
        // 1. 엔티티 매니저에서 제공하는 메서드를 활용 방법
         Board board = em.find(Board.class, id);

        // 2. JPQL 문법으로 Board 를 조회 하는 방법
//        String jpql = """
//                SELECT b FROM Board b WHERE b.id = :id
//                """;

//        return em.createQuery(jpql, Board.class)
//                .setParameter("id", id)
//                .getSingleResult();
        return board;
    }

    // 게시글 삭제
    @Transactional // 셀렉트 하고 업데이트 했기에 트랜젝션
    public void deleteById(Integer id){
        // 1. 삭제할때 먼저 삭제하고자 하는 엔티티를 조회
        Board board = em.find(Board.class,id); // 셀렉트 쿼리 날리는 메서드

        // 1.1 . 조회가 되었기 때문에 board는 영속화 된 상태이다
        if(board == null){
            throw new IllegalArgumentException("삭제할 게시글을 찾을수 없습니다." + id);
        }
        // 조회된 보드 넣음
        em.remove(board);


    }

    @Transactional
    public void updateById(Integer id, BoardRequest.UpdateDTO updateDTO) {
        // 수정시 항상 조회먼저 확인

        Board boardEntity = em.find(Board.class,id);
        // em.find 호출후 리턴받은 board 는 영속상태 이다.

        if(boardEntity == null){
            throw new IllegalArgumentException("수정할 게시글을 찾을수 없습니다." + id);
        }

        boardEntity.update(updateDTO);
        // 변경감지 (Dirty Checking) 동작 됨.
        // 영속성 컨텍스트에 관리 되어지는 객체(엔티티)안에 조회했을때 기준으로 1차 캐시에 저장 되어짐
        // 추후 1차 캐시에 들어가 있는 객체의(엔티티의) 변수의 값이 변경 되었다면 자동으로 감지한다.

        // em.persist(boardEntity);

        // 엔티티 -> 테이블과 메핑되는 Object는 UpdateDTO 가 없음
        // 우리가 관리하고자 하는 객체는 Board 이다.

        // 사용자가 수정한 게시글을 받아야 하니 엔티티를 넣어야 한다
        // boaboardEntity 는 그냥 조회해서 찿는용도

        // 찾은 보더에 값을 덮어쓰기 함
//        boardEntity.setUsername(board.getUsername());
//        boardEntity.setTitle(board.getTitle());
//        boardEntity.setContent(board.getContent());

        // boardEntity.id <---- 값이 있다 왜냐면 조회한 보드이기 때문에
//
//        boardEntity.setUsername(board.getUsername());
//        boardEntity.setTitle(board.getTitle());
//        boardEntity.setContent(board.getContent()); 이과정이 없을시 그냥 새로운 board 생성 왜냐면 id 값이 없어서 해당 보드가 어떤 보드인지 모른다




        // 앞으로 수정 기능을 만들어 줄때 더티 체킹 동작으로 사용하자 em.persist(boardEntity); 을 할필요 없다 알아서 테이블 수정 해줌

    }
}
