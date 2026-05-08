package com.tenco.blog.board;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/")
    // 메스테치에 데이터 뿌리는 기능인 Model 선언
    public String list(Model model){

        List<BoardResponse.ListDTO> listDTO = boardService.findAllJoinUser();
        model.addAttribute("boardList",listDTO);
        return "board/list";

    }

    // 단건조회
    @GetMapping("/board/{id}")
    public String findById(@PathVariable("id") Integer id, Model model){
        BoardResponse.BoardDTO dto = boardService.findByIdJoinUser(id);
        model.addAttribute("board",dto);
        return "board/detail";
    }




}
