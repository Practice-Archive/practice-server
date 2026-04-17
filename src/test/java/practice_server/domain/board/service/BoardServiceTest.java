package practice_server.domain.board.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import practice_server.domain.board.entity.Board;
import practice_server.domain.board.repository.BoardRepository;
import practice_server.domain.member.entity.Member;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BoardServiceTest {
    @Autowired private BoardRepository boardRepository;
    @Autowired private BoardService boardService;

    @Test
    void 게시글_생성() {
        // given
        Board board = new Board();
        board.setTitle("안녕하세요~");
        board.setContent("반갑습니다");

        Member member = new Member();
        member.setNickname("test1");

        // when
        Long id = boardService.save(member.getMemberId(), board);
        Board getBoard = boardService.findOne(id);

        // then
        Assertions.assertThat(board.getContent()).isEqualTo(getBoard.getContent());
    }

    @Test
    void 게시글_수정() {
        // given
        Board board = new Board();
        board.setTitle("안녕하세요~");
        board.setContent("반갑습니다");

        Member member = new Member();
        member.setNickname("test2");

        Long id = boardService.save(member.getMemberId(), board);

        // when
        boardService.updateBoard(id, "수정타이틀", "수정콘텐츠");

        // then
        Board getBoard = boardRepository.findBoardByBoardId(id);
        Assertions.assertThat(getBoard.getContent()).isEqualTo("수정콘텐츠");
    }

    @Test
    void 게시글_삭제() {
        // given
        Board board = new Board();
        board.setTitle("안녕하세요~");
        board.setContent("반갑습니다");

        Member member = new Member();
        member.setNickname("test3");

        Long id = boardService.save(member.getMemberId(), board);

        // when
        boardService.delete(id);

        // then
        Board getBoard = boardRepository.findBoardByBoardId(id);
        Assertions.assertThat(getBoard).isEqualTo(null);
    }
}