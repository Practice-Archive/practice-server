package practice_server.domain.reply.dto;

import lombok.Builder;
import lombok.Getter;
import practice_server.domain.reply.entity.Reply;

@Getter
public class CommentResponse {
    private final String nickname;
    private final String content;

    @Builder
    private CommentResponse(String nickname, String content) {
        this.nickname = nickname;
        this.content = content;
    }

    public static CommentResponse from(Reply reply) {
        return CommentResponse.builder()
                .nickname(reply.getMember().getNickname())
                .content(reply.getContent())
                .build();
    }
}
