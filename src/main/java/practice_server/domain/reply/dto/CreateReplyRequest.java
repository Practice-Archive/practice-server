package practice_server.domain.reply.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateReplyRequest {
    private final Long memberId;
    private final Long boardId;
    private final String content;
}
