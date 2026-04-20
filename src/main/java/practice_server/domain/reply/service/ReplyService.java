package practice_server.domain.reply.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice_server.domain.board.entity.Board;
import practice_server.domain.board.repository.BoardRepository;
import practice_server.domain.member.entity.Member;
import practice_server.domain.member.repository.MemberRepository;
import practice_server.domain.reply.dto.CreateReplyRequest;
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
    public Long save(CreateReplyRequest dto) {
        Member member = memberRepository.findMemberByMemberId(dto.getMemberId());
        Board board = boardRepository.findBoardByBoardId(dto.getBoardId());
        Reply reply = Reply.createReply(board, member, dto.getContent());

        replyRepository.save(reply);
        return reply.getReplyId();
    }

    // 댓글 수정
    public void update(Long id, CreateReplyRequest dto) {
        Reply reply = replyRepository.findReplyByReplyId(id);
        reply.setContent(dto.getContent());
    }

    // 댓글 삭제
    public void delete(Long id) {
        replyRepository.deleteById(id);
    }
}
