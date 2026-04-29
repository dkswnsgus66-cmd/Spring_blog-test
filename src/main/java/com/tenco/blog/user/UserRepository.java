package com.tenco.blog.user;


// SRP - 단일 책임의 원칙


import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

// IoC 메모리에 객체를 띄우는 역할

@Repository
@RequiredArgsConstructor
public class UserRepository {

//    @Autowired // DI - 스프링 프레임 워크가 주소값 자동 주입

    // @RequiredArgsConstructor 쓰면 AutoWired 쓸 필요없다
    private final EntityManager em;

    // 회원 가입 요청시 --> insert
    @Transactional
    public User save(User user) {
        // 매개 변수로 들어온 user Object는 비영속 상태

        em.persist(user); // 영속상태
        // user 영속 상태
        // 리턴시 user 오브젝트는 영속화 된 상태이다.
        return user;
    }

    // 사용자 이름 중복확인
    public User findByUsername(String username) {

        // 외부에서 유저 들어옴
        String jsql = """
                select u from User u where u.username = :username
                """;


        // 이게 원리가 select u from User u where u.username = :username 에서 들어온걸 Object 를 User로 형변환 해준것근데 만약 null 이들어오면 형변환 못하기에
        // try catch로 null 일때 null을 반환하는 코드를 만든것이다
        try {
            User user = em.createQuery(jsql, User.class).setParameter("username", username).getSingleResult();
            return user;
        } catch (Exception e) {
            return null;
        }

        // User에 유니크 걸어놨기 때문에 알아서 중복 되면 걸러준다
        // 리턴으로 중복안된 유저 정보 리턴
        // 컨트롤러에서 만약 유저가 null 이면 중복된 이름이 있단 소리임 그러면 if( user != null) 일때 컨트롤러에서 save 를 호출해서 insert 처리


    }


    // 로그인 요청시 --> select

    public User findByUsernameAndPassword(String username, String password) {

        String jpqlStr = """
                select u from User u where u.username = :username and u.password = :password
                """;

        try {
            User userEntity = em.createQuery(jpqlStr, User.class)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();

            return userEntity;

        } catch (Exception e) {
            return null;
        }

    }


}
