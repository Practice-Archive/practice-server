package practice_server.domain.reply.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import practice_server.domain.reply.dto.CreateReplyRequest;
import practice_server.domain.reply.dto.CreateReplyResponse;
import practice_server.domain.reply.service.ReplyService;

@RestController
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyService replyService;

    // 댓글 저장
    @PostMapping("/api/replies")
    public CreateReplyResponse save(@RequestBody @Valid CreateReplyRequest request) {
        return new CreateReplyResponse(replyService.save(request));
    }

    // 댓글 수정
    @PatchMapping("/api/replies/{id}")
    public void update(@PathVariable("id") Long id, @RequestBody @Valid CreateReplyRequest request) {
        replyService.update(id, request);
    }

    // 댓글 삭제
    @DeleteMapping("/api/replies/{id}")
    public void delete(@PathVariable("id") Long id) {
        replyService.delete(id);
    }
}
