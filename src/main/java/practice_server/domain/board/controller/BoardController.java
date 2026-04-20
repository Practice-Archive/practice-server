package practice_server.domain.board.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import practice_server.domain.board.dto.BoardDetailResponse;
import practice_server.domain.board.dto.CreateBoardRequest;
import practice_server.domain.board.dto.CreateBoardResponse;
import practice_server.domain.board.dto.BoardListResponse;
import practice_server.domain.board.service.BoardService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    // 게시글 목록 조회
    @GetMapping("/api/boards")
    public List<BoardListResponse> findAll() {
        return boardService.findAll();
    }

    // 게시글 조회
    @GetMapping("/api/boards/{id}")
    public BoardDetailResponse findById(@PathVariable("id") Long id) {
        return boardService.findOne(id);
    }

    // 게시글 저장
    @PostMapping("/api/boards")
    public CreateBoardResponse save(@RequestBody @Valid CreateBoardRequest request) {
        return new CreateBoardResponse(boardService.save(request));
    }
    // 게시글 삭제
    @DeleteMapping("/api/boards/{id}")
    public void delete(@PathVariable("id") Long id) {
        boardService.delete(id);
    }
    // 게시글 수정
    @PatchMapping("/api/boards/{id}")
    public void update(@PathVariable("id") Long id, @RequestBody @Valid CreateBoardRequest request) {
        boardService.update(id, request);
    }
}
