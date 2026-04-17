package practice_server;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import practice_server.domain.board.entity.Board;
import practice_server.domain.board.service.BoardService;
import practice_server.domain.member.entity.Member;
import practice_server.domain.member.service.MemberService;

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
        private final BoardService boardService;

        public void dbInit1() {
            Member member = createMember("1번회원","1234");
            Board board = createBoard("첫번째게시글","첫번째 게시글 입니다~");

            memberService.join(member);
            boardService.save(member.getMemberId(), board);
        }

        public void dbInit2() {
            Member member = createMember("2번회원","3456");
            Board board = createBoard("두번째게시글","두번째 게시글 입니다~");

            memberService.join(member);
            boardService.save(member.getMemberId(), board);
        }

        private Member createMember(String nickname, String password) {
            Member member = new Member();
            member.setNickname(nickname);
            member.setPassword(password);
            return member;
        }

        private Board createBoard(String title, String content) {
            Board board = new Board();
            board.setTitle(title);
            board.setContent(content);
            return board;
        }
    }
}
