package practice_server;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import practice_server.domain.board.dto.CreateBoardRequest;
import practice_server.domain.board.entity.Board;
import practice_server.domain.board.repository.BoardRepository;
import practice_server.domain.board.service.BoardService;
import practice_server.domain.member.entity.Member;
import practice_server.domain.member.repository.MemberRepository;
import practice_server.domain.member.service.MemberService;
import practice_server.domain.reply.dto.CreateReplyRequest;
import practice_server.domain.reply.entity.Reply;
import practice_server.domain.reply.service.ReplyService;

@Component
@RequiredArgsConstructor
public class InitDb {
    private final InitService initService;


    @PostConstruct
    public void init() {
        initService.dbInit1();
        initService.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final MemberService memberService;
        private final MemberRepository memberRepository;
        private final BoardService boardService;
        private final BoardRepository boardRepository;
        private final ReplyService replyService;

        public void dbInit1() {
            Member member = createMember("1번회원","1234");
            CreateBoardRequest boardDTO = new CreateBoardRequest(1L,"첫번째게시글","첫번째 게시글 입니다~");

            memberRepository.save(member);
            Long boardId = boardService.save(boardDTO);

            CreateReplyRequest replyDTO1 = new CreateReplyRequest(member.getMemberId(), boardId, "첫번째 댓글");
            CreateReplyRequest replyDTO2 = new CreateReplyRequest(member.getMemberId(), boardId, "두번째 댓글");

            replyService.save(replyDTO1);
            replyService.save(replyDTO2);
        }

        public void dbInit2() {
            Member member = createMember("2번회원","3456");
            CreateBoardRequest dto = new CreateBoardRequest(2L,"두번째게시글","두번째 게시글 입니다~");

            memberRepository.save(member);
            Long boardId = boardService.save(dto);

            CreateReplyRequest replyDTO1 = new CreateReplyRequest(member.getMemberId(), boardId, "첫번째 댓글");
            CreateReplyRequest replyDTO2 = new CreateReplyRequest(member.getMemberId(), boardId, "두번째 댓글");

            replyService.save(replyDTO1);
            replyService.save(replyDTO2);
        }

        private Member createMember(String nickname, String password) {
            Member member = new Member();
            member.setNickname(nickname);
            member.setPassword(password);
            return member;
        }

        private Reply createReply(String content) {
            Reply reply = new Reply();
            reply.setContent(content);
            return reply;
        }
    }
}
