package practice_server.domain.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice_server.domain.board.entity.Board;
import practice_server.domain.board.repository.BoardRepository;
import practice_server.domain.member.entity.Member;
import practice_server.domain.member.repository.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    // 게시글 상세 조회
    public Board findOne(Long id) {
        return boardRepository.findBoardByBoardId(id);
    }

    // 게시글 리스트 조회
    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    // 게시글 등록
    public Long save(Long memberId, Board board) {
        Member member = memberRepository.findMemberByMemberId(memberId);
        board.setMember(member);
        boardRepository.save(board);
        return board.getBoardId();
    }

    // 게시글 삭제
    public void delete(Long id) {
        boardRepository.deleteById(id);
    }

    // 게시글 수정
    public void updateBoard(Long id, String title, String content) {
        Board board = boardRepository.findBoardByBoardId(id);
        board.setTitle(title);
        board.setContent(content);
    }
}
