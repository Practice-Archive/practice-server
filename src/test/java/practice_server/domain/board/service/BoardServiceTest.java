package practice_server.domain.board.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import practice_server.domain.board.dto.CreateBoardRequest;
import practice_server.domain.board.entity.Board;
import practice_server.domain.board.repository.BoardRepository;
import practice_server.domain.member.entity.Member;

@SpringBootTest
@Transactional
class BoardServiceTest {
    @Autowired private BoardRepository boardRepository;
    @Autowired private BoardService boardService;

    @Test
    void 게시글_생성() {
        // given
        Member member = new Member();
        member.setNickname("test1");
        CreateBoardRequest dto = new CreateBoardRequest(1L, "test", "testtest");

        // when
        Long id = boardService.save(dto);
        Board getBoard = boardRepository.findBoardByBoardId(id);

        // then
        Assertions.assertThat(dto.getContent()).isEqualTo(getBoard.getContent());
    }

    @Test
    void 게시글_수정() {
        // given
        Member member = new Member();
        member.setNickname("test2");

        CreateBoardRequest dto = new CreateBoardRequest(1L, "test", "testtest");

        Long id = boardService.save(dto);

        // when
        boardService.update(id, dto);

        // then
        Board getBoard = boardRepository.findBoardByBoardId(id);
        Assertions.assertThat(getBoard.getContent()).isEqualTo("수정콘텐츠");
    }

    @Test
    void 게시글_삭제() {
        // given
        Member member = new Member();
        member.setNickname("test3");

        CreateBoardRequest dto = new CreateBoardRequest(1L, "test", "testtest");

        Long id = boardService.save(dto);

        // when
        boardService.delete(id);

        // then
        Board getBoard = boardRepository.findBoardByBoardId(id);
        Assertions.assertThat(getBoard).isEqualTo(null);
    }
}