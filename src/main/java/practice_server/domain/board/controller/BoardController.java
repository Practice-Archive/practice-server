package practice_server.domain.board.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import practice_server.domain.board.dto.*;
import practice_server.domain.board.service.BoardService;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    // 게시글 목록 조회
    @GetMapping("/api/boards")
    public BoardPageResponse findAll
    (@PageableDefault(size = 10, sort = "boardId", direction = Sort.Direction.DESC)
     Pageable pageable) {
        return BoardPageResponse.from(boardService.findAll(pageable));
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
