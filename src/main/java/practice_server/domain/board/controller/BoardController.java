package practice_server.domain.board.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import practice_server.domain.board.dto.CreateBoardRequest;
import practice_server.domain.board.dto.CreateBoardResponse;
import practice_server.domain.board.service.BoardService;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/api/boards")
    public CreateBoardResponse save(@RequestBody @Valid CreateBoardRequest request) {
        return new CreateBoardResponse(boardService.save(request));
    }
    @DeleteMapping("/api/boards/{id}")
    public void delete(@PathVariable("id") Long id) {
        boardService.delete(id);
    }
    @PatchMapping("/api/boards/{id}")
    public void update(@PathVariable("id") Long id, @RequestBody @Valid CreateBoardRequest request) {
        boardService.update(id, request);
    }
}
