package practice_server.domain.reply.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice_server.domain.board.entity.Board;
import practice_server.domain.board.repository.BoardRepository;
import practice_server.domain.member.entity.Member;
import practice_server.domain.member.repository.MemberRepository;
import practice_server.domain.reply.entity.Reply;
import practice_server.domain.reply.repository.ReplyRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    // 댓글 저장
    public Long save(Long memberId, Long boardId, Reply reply) {
        Member member = memberRepository.findMemberByMemberId(memberId);
        Board board = boardRepository.findBoardByBoardId(boardId);

        reply.setMember(member);
        reply.setBoard(board);
        replyRepository.save(reply);

        return reply.getReplyId();
    }

    // 댓글 수정
    public void update(Long id, String content) {
        Reply reply = replyRepository.findReplyByReplyId(id);
        reply.setContent(content);
    }

    // 댓글 삭제
    public void delete(Long id) {
        replyRepository.deleteById(id);
    }
}
