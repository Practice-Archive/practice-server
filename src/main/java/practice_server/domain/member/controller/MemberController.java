package practice_server.domain.member.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import practice_server.domain.member.dto.CreateMemberRequest;
import practice_server.domain.member.dto.CreateMemberResponse;
import practice_server.domain.member.service.MemberService;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    // 회원 가입
    @PostMapping("/api/members")
    public CreateMemberResponse save(@RequestBody @Valid CreateMemberRequest request) {
        return new CreateMemberResponse(memberService.join(request));
    }

    // 회원 탈퇴
    @DeleteMapping("/api/members/{id}")
    public void delete(@PathVariable("id") Long id) {
        memberService.delete(id);
    }
}
