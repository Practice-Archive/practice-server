package practice_server.domain.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import practice_server.domain.board.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Board findBoardByBoardId(Long boardId);
}
