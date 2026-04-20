package practice_server.domain.member.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import practice_server.domain.member.dto.CreateMemberRequest;
import practice_server.domain.member.entity.Member;
import practice_server.domain.member.repository.MemberRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void join() throws Exception {
        CreateMemberRequest dto = new CreateMemberRequest("회원1", "1234");

        Long memberId = memberService.join(dto);
        Member getMember = memberRepository.findMemberByMemberId(memberId);

        Assertions.assertThat(getMember.getNickname()).isEqualTo("lee");
    }

    @Test
    public void join2() throws Exception {
        CreateMemberRequest dto1 = new CreateMemberRequest("회원1", "1234");
        CreateMemberRequest dto2 = new CreateMemberRequest("회원1", "1234");

        memberService.join(dto1);
        assertThrows(IllegalStateException.class,
                () -> memberService.join(dto2));
    }
}