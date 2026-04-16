package practice_server.domain.member.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
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
        Member member1 = new Member();
        member1.setNickname("lee");
        member1.setPassword("12345");

        Long memberId = memberService.join(member1);
        Member getMember = memberRepository.findMemberByMemberId(memberId);

        Assertions.assertThat(getMember.getNickname()).isEqualTo("lee");
    }

    @Test
    public void join2() throws Exception {
        Member member1 = new Member();
        member1.setNickname("lee");

        Member member2 = new Member();
        member2.setNickname("lee");

        memberService.join(member1);
        assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));
    }
}