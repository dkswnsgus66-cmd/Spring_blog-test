package com.tenco.blog.user;


import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    // 회원가입
    @Transactional
    public UserResponse.JoinDTO Join(UserRequest.JoinDTO joinDTO){

        log.info("회원가입 시작");
        // 닉네이 중복 확인 isPresent 값이 있는지 없는지 여부 체크

        // 중복 체크라 데이터가 있을때도 예외를 던져야한다
        userRepository.findByUsername(joinDTO.getUsername()).ifPresent(user -> {
            log.info("닉네임이 중복됬습니다: {}" , user.getUsername());
            throw new IllegalArgumentException("이미존재하는 사용자 이름 ");
        });

        // 데이터 없을때 예외던짐
//        userRepository.findByUsername(joinDTO.getUsername()).orElseThrow(() -> {
//            throw new IllegalArgumentException("닉네임이 중복되면 안됩니다.");
//        });

        User savedUserEntity = userRepository.save(joinDTO.toEntity());
        log.info("회원가입 완료 ID: {}",savedUserEntity.getId());

        return new UserResponse.JoinDTO(savedUserEntity);

    }
    // 로그인
    // 당연히 로그인 요청할땐 UserRequest 로그인 성공후 UserResponse 반환
    public UserResponse.SessionDTO login(UserRequest.LoginDTO loginDTO){

        log.info("로그인 서비스 시작");
        // 로그인 할때 데이터베이스에 아이디와 패스워드 동일한게 있는지 확인 하고 userEntity 넣기
        User userEntity = userRepository.findByUsernameAndpassword(loginDTO.getUsername(), loginDTO.getPassword()).orElseThrow(() -> {
            log.warn("아이디 또는 패스워드가 올바르지 않습니다.");
            throw new IllegalArgumentException("아이디 또는 패스워드가 올바르지 않습니다.");
        });
        log.info("로그인 성공");
        return new UserResponse.SessionDTO(userEntity);
    }


    // 회원정보 조회
    // 회원정보 조회면 id 로 찾고 로그인 여부 확인
    public UserResponse.SessionDTO findById(Integer id){

        User userEntity = userRepository.findById(id).orElseThrow(() -> {
            log.warn("해당 화원을 조회할수 없습니다.");
            throw new IllegalArgumentException("해당 회원을 조회할수 없습니다");
        });
        UserResponse.SessionDTO sessionDTO = new UserResponse.SessionDTO(userEntity);
        return sessionDTO;

    }

    // 회원정보 수정
    @Transactional
    public UserResponse.SessionDTO updateUser(Integer id , UserRequest.UpdateDTO updateDTO, HttpSession session){

        User findUser = userRepository.findById(id).orElseThrow(() -> {
            log.warn("해당 회원을 조회할수 없습니다.");
            throw new IllegalArgumentException("해당 회원을 조회할수 없습니다");
        });

        // 더티 체킹 활용
        findUser.update(updateDTO);
        UserResponse.SessionDTO sessionDTO = new UserResponse.SessionDTO(findUser);
        // 세션 동기화
        session.setAttribute("sessionUser",sessionDTO);
        return sessionDTO;

    }

}
