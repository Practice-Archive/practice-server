package practice_server.domain.reply.service;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import practice_server.domain.board.entity.Board;
import practice_server.domain.board.service.BoardService;
import practice_server.domain.member.entity.Member;
import practice_server.domain.member.service.MemberService;
import practice_server.domain.reply.entity.Reply;
import practice_server.domain.reply.repository.ReplyRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ReplyServiceTest {
    @Autowired private ReplyService replyService;
    @Autowired private ReplyRepository replyRepository;

    @Test
    void 댓글_생성() {
        // given
        Member member = new Member();
        Board board = new Board();
        Reply reply = new Reply();

        reply.setContent("첫번째 댓글입니다~~");

        // when
        Long id = replyService.save(member.getMemberId(), board.getBoardId(), reply);
        Reply getReply = replyRepository.findReplyByReplyId(id);

        // then
        Assertions.assertThat(getReply.getContent()).isEqualTo("첫번째 댓글입니다~~");
    }

    @Test
    void 댓글_수정() {
        // given
        Member member = new Member();
        Board board = new Board();
        Reply reply = new Reply();

        reply.setContent("첫번째 댓글입니다~~");

        Long id = replyService.save(member.getMemberId(), board.getBoardId(), reply);

        // when
        replyService.update(id, "첫번쨰 댓글 수정 입니다~~");

        // then
        Reply getReply = replyRepository.findReplyByReplyId(id);
        Assertions.assertThat(getReply.getContent()).isEqualTo("첫번쨰 댓글 수정 입니다~~");
    }

    @Test
    void 댓글_삭제() {
        // given
        Member member = new Member();
        Board board = new Board();
        Reply reply = new Reply();

        reply.setContent("첫번째 댓글입니다~~");

        Long id = replyService.save(member.getMemberId(), board.getBoardId(), reply);

        // when
        replyService.delete(id);

        // then
        Reply getReply = replyRepository.findReplyByReplyId(id);
        Assertions.assertThat(getReply).isEqualTo(null);
    }
}