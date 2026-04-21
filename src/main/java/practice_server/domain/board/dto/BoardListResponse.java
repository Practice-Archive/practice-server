package practice_server.domain.board.dto;

import lombok.Builder;
import lombok.Getter;
import practice_server.domain.board.entity.Board;

@Getter
public class BoardListResponse {
    private final Long boardId;
    private final String nickname;
    private final String title;
    private final String content;
    private final Long commentCount;

    @Builder
    private BoardListResponse(Long boardId, String nickname, String title, String content, Long commentCount) {
        this.boardId = boardId;
        this.nickname = nickname;
        this.title = title;
        this.content = content;
        this.commentCount = commentCount;
    }

    public static BoardListResponse from (Board board) {
        return BoardListResponse.builder()
                .boardId(board.getBoardId())
                .nickname(board.getMember().getNickname())
                .title(board.getTitle())
                .content(board.getContent())
                .commentCount((long) board.getReplies().size())
                .build();
    }
}
