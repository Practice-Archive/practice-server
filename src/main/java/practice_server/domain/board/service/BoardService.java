package practice_server.domain.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice_server.domain.board.dto.BoardDetailResponse;
import practice_server.domain.board.dto.CreateBoardRequest;
import practice_server.domain.board.dto.BoardListResponse;
import practice_server.domain.board.entity.Board;
import practice_server.domain.board.repository.BoardRepository;
import practice_server.domain.member.entity.Member;
import practice_server.domain.member.repository.MemberRepository;
import practice_server.domain.reply.dto.CommentResponse;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    // 게시글 상세 조회
    public BoardDetailResponse findOne(Long id) {
        Board board = boardRepository.findBoardByBoardId(id);

        List<CommentResponse> replies = board.getReplies().stream()
                .map(CommentResponse::from)
                .toList();

        return BoardDetailResponse.from(board, replies);
    }

    // 게시글 리스트 조회
    public List<BoardListResponse> findAll() {
        List<Board> boards = boardRepository.findAll();
        return boards.stream()
                .map(BoardListResponse::from)
                .toList();
    }

    // 게시글 등록
    public Long save(CreateBoardRequest dto) {
        Member member = memberRepository.findMemberByMemberId(dto.getMemberId());
        Board board = Board.createBoard(member,dto.getTitle(),dto.getContent());
        boardRepository.save(board);
        return board.getBoardId();
    }

    // 게시글 삭제
    public void delete(Long id) {
        boardRepository.deleteById(id);
    }

    // 게시글 수정
    public void update(Long id, CreateBoardRequest dto) {
        Board board = boardRepository.findBoardByBoardId(id);
        board.setTitle(dto.getTitle());
        board.setContent(dto.getContent());
    }
}
