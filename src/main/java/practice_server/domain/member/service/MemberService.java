package practice_server.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice_server.domain.member.dto.CreateMemberRequest;
import practice_server.domain.member.entity.Member;
import practice_server.domain.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    // 회원 가입
    public Long join(CreateMemberRequest dto) {
        validateDuplicateMember(dto);
        Member member = Member.builder()
                .nickname(dto.getNickname())
                .password(dto.getPassword())
                .build();
        memberRepository.save(member);
        return member.getMemberId();
    }

    // 닉네임 중복 검사
    public void validateDuplicateMember(CreateMemberRequest dto) {
        if(memberRepository.existsMemberByNickname(dto.getNickname())) {
            throw new IllegalStateException("이미 존재하는 닉네임 입니다.");
        }
    }

    // 회원 탈퇴
    public void delete(Long id) {
        memberRepository.deleteById(id);
    }
}
