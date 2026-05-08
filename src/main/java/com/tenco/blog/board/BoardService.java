package com.tenco.blog.board;


import com.tenco.blog.core.error.Exception404;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j // log
@Service // Ioc
@RequiredArgsConstructor // DI
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;


    // 전체 조회
    public List<BoardResponse.ListDTO> findAllJoinUser(){


        log.info("게시글 목록조회 서비스");
        List<BoardResponse.ListDTO> listDTO = new ArrayList<>();

        List<Board> boardList = boardRepository.findJoinAll(); // 조인된 데이터 전부 리스트 넣음

        for(int i =0; i<boardList.size(); i++){

            BoardResponse.ListDTO dto = new BoardResponse.ListDTO(boardList.get(i));
            listDTO.add(dto);

        }
        return listDTO;
    }




    // 단건 조회
    public BoardResponse.BoardDTO findByIdJoinUser(Integer id){

        log.info("게시글 단건조회 서비스");
        // id 로 찾은 Board
       Board boardEntity = boardRepository.findByIdJoinUser(id).orElseThrow(() -> {
           log.warn("게시글 조회 실패 - ID {}" , id);
           throw new Exception404("게시글을 찾을수 없습니다.");
       });

       log.info("게시글 찾기 완료 ID: {}" ,id );
       return new BoardResponse.BoardDTO(boardEntity);
    }

    // 게시글 작성
//    public BoardRequest.SaveDTO save(){
//        log.info("게시글 작성 서비스");
//        boardRepository.save();
//    }


}
