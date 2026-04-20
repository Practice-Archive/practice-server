package practice_server.domain.board.dto;

import lombok.Builder;
import lombok.Getter;
import practice_server.domain.board.entity.Board;
import practice_server.domain.reply.dto.CommentResponse;

import java.util.List;

@Getter
public class BoardDetailResponse {
    private final String nickname;
    private final String title;
    private final String content;
    private final List<CommentResponse> replies;

    @Builder
    private BoardDetailResponse(String nickname, String title, String content, List<CommentResponse> replies) {
        this.nickname = nickname;
        this.title = title;
        this.content = content;
        this.replies = replies;
    }

    public static BoardDetailResponse from (Board board, List<CommentResponse> replies) {
        return BoardDetailResponse.builder()
                .nickname(board.getMember().getNickname())
                .title(board.getTitle())
                .content(board.getContent())
                .replies(replies)
                .build();
    }
}
