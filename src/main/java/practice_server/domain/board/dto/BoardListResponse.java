package practice_server.domain.board.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import practice_server.domain.board.entity.Board;

@Getter
@RequiredArgsConstructor
public class BoardListResponse {
    private final Long boardId;
    private final String nickname;
    private final String title;
    private final String content;
    private final Long commentCount;
}
