package practice_server.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice_server.domain.member.entity.Member;
import practice_server.domain.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getMemberId();
    }

    public void validateDuplicateMember(Member member) {
        if(memberRepository.existsMemberByNickname(member.getNickname())) {
            throw new IllegalStateException("이미 존재하는 닉네임 입니다.");
        }
    }
}
