package practice_server.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import practice_server.domain.member.entity.Member;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findMemberByMemberId(Long memberId);
    boolean existsMemberByNickname(String nickname);
}
