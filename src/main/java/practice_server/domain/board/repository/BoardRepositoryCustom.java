package practice_server.domain.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import practice_server.domain.board.dto.BoardListResponse;

public interface BoardRepositoryCustom {
    Page<BoardListResponse> findBoardPage(Pageable pageable);
}
